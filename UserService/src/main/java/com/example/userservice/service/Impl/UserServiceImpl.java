package com.example.userservice.service.Impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.example.common.utils.PasswordUtils;
import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.enetity.Result;
import com.example.userservice.entity.User;
import com.example.userservice.dto.UserDTO;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.example.common.utils.JWTUtils;


import java.util.HashMap;
import java.util.Map;

import java.util.UUID;
import java.util.concurrent.TimeUnit;


import static com.example.common.utils.Constans.*;

/**
 * @author fangyaohui
 * @version 0.0.3
 * @description UserServiceImpl
 * @since 2024/3/17 21:16
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue sendMailQueue;


    /**
     * 该函数实现登录功能：
     *  1、如果用户密码正确，则生成一个随机token，加入到redis中
     *  2、将这个随机的token作为JWT的payload生成JWT字符串返回给前端
     *  3、前端保存这个token，并每次请求都在Authorization字段中携带JWT字符串
     *  4、定义一个拦截器，每次收到前端请求时，都先从请求头中的Authorization
     *      字段中取出JWT字符串并进行验证，验证通过后解析出payload中的随机token，
     *      然后再用这个随机token得到key，从Redis中获取用户信息，如果能获取到就说明用户已经登录
     * @param user 前端用户携带的用户名和密码
     * @return 如果登录成功返回token给前端
     */
    @Override
    public Result login(User user) {

        String username = user.getUsername();
        String password = user.getPassword();

        User usertemp = query().eq("username",username).one();

        if(usertemp == null) return Result.fail("该用户未注册");

        if(usertemp.getState() == 0) return Result.fail("该用户账号已封禁，请联系管理员解禁");

        String pwd = user.getPassword();
        String hashpwd = PasswordUtils.getPassword(pwd);
        log.info("加密后密码："+hashpwd);

        if(hashpwd.equals(usertemp.getPassword())){
            log.info(user.getUsername() + "登录成功");

            String randomToken = UUID.randomUUID().toString();
            String tokenKey = LOGIN_USER_KEY + randomToken;

//          这段代码中，将user拷贝到userDTO中，userDTO隐去了密码。
//          然后将userDTO转变成userMap即为Map形式
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(usertemp, userDTO);
//            Map<String,Object> userMap = BeanUtil.beanToMap(userDTO,new HashMap<String,Object>(),
//                    CopyOptions.create()
//                            .setIgnoreNullValue(true)
//                            .setFieldValueEditor((fieldName,fieldValue) -> {
//                                if(fieldValue != null){
//                                    return fieldValue.toString();
//                                }else{
//                                    return "";
//                                }
//                            }));

//          将tokenKey作为key，usermap作为value 存入redis
//            redisTemplate.opsForHash().put(tokenKey,userDTO.toString(),userDTO);
            redisTemplate.opsForValue().set(tokenKey,userDTO);

//          为这个key设置过期时间
            redisTemplate.expire(tokenKey,LOGIN_USER_TTL, TimeUnit.MINUTES);

//          生成一个map
            Map<String,String> tokenMap = new HashMap<>();
            tokenMap.put("token",tokenKey);

//          以token作为payload，调佣JWTUtils 获取生成后的新token
           String token = JWTUtils.getToken(tokenMap);

//          将这个新生成的token返回给前端 前端应该保存到本地 并每次访问都应该携带这个token进行身份验证
            return Result.ok(token);
        }

        return Result.ok("密码错误，登录失败");

    }


    /**
     * 该函数实现注册功能
     * @param user 前端输入的用户名和密码
     * @return 是否注册成功
     */
    @Override
    public Result register(User user) {

        User usertemp = query().eq("username",user.getUsername()).one();
        if(usertemp != null) return Result.fail("该用户名已经被注册");

        String pwd = user.getPassword();
        log.info(pwd);
        String hashedPwd = PasswordUtils.getPassword(pwd);
        log.info(hashedPwd);
        user.setPassword(hashedPwd);

        String name = USER_NAME + user.getUsername();
        user.setName(name);
        user.setState(1);
        user.setBalance(10000);

        save(user);

        log.info(user.getUsername() + "注册成功！！！");

        sendmail(user.getUsername() + " register successful");

        return Result.ok();

    }


    /**
     * 该函数获取存储的用户
     * @param token Redis的key
     * @return userDTO
     */
    @Override
    public Result sign(String token) {

        UserDTO userDTO = (UserDTO) redisTemplate.opsForValue().get(token);

        assert userDTO != null;
        log.info(userDTO.toString());

        return Result.ok(userDTO);
    }

    /**
     * 使用RabbitMQ进行异步消息通知处理，用户注册后使用该函数进行异步发送邮件通知
     *
     */
    public void sendmail(String message){

        rabbitTemplate.convertAndSend(sendMailQueue.getName(),message);
    }
}