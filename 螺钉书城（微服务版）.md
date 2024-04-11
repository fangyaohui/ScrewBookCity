# 螺钉书城（微服务版）

## 大纲

第一步：项目规划和设计

1. **需求分析**：明确项目需求，包括用户注册登录、商品展示、购物车、订单管理等功能。
2. **技术选型**：确认使用的技术栈，如Spring Boot作为后端框架、Vue.js作为前端框架、MySQL作为数据库、Redis用于缓存等。
3. **架构设计**：设计项目架构，考虑如何将前后端分离，如何使用微服务架构等。

第二步：后端开发

1. **搭建后端环境**：使用Spring Boot搭建后端项目。
2. **数据库设计**：设计MySQL数据库表结构，包括用户表、商品表、订单表等。
3. **业务逻辑开发**：实现用户认证、商品管理、购物车功能、订单管理等业务逻辑。
4. **集成Redis**：使用Redis进行缓存、会话管理等。
5. **微服务化**：考虑使用Spring Cloud实现微服务化，拆分功能模块为独立的微服务。

第三步：前端开发

1. **搭建前端环境**：使用Vue.js搭建前端项目。
2. **页面设计**：设计用户界面，包括商品展示页面、购物车页面、订单页面等。
3. **前后端交互**：实现前后端数据交互，通过RESTful API进行通信。
4. **界面交互**：实现用户交互功能，如商品搜索、下单操作等。

第四步：系统集成和测试

1. **系统集成**：将前后端集成到一起，确保系统各部分能够正常协作。
2. **单元测试**：编写单元测试，确保各个功能模块的正确性。
3. **集成测试**：进行系统集成测试，验证系统整体功能和性能。

第五步：部署和优化

1. **部署上线**：将项目部署到服务器上，确保项目能够正常运行。
2. **性能优化**：优化系统性能，如数据库查询优化、缓存策略优化等。
3. **安全加固**：加强系统安全性，如防止SQL注入、XSS攻击等。

## 一、项目规划和设计

### 1. 项目概述

**目标**：开发一个书城项目，让客户可以浏览、搜索、购买书籍，并管理自己的购物车和订单。

**用户角色**：普通用户（顾客）、管理员。

### 2. 技术栈

- **前端**：Vue.js
- **后端**：Spring Cloud、Spring Boot
- **数据库**：MySQL
- **消息队列**：RabbitMQ（用于处理订单支付、通知等异步消息）
- **缓存**：Redis（用于缓存热点数据，提高系统响应速度）
- **搜索引擎**：Elasticsearch（用于提高书籍搜索的效率和准确性）
- **文件存储**：阿里云OSS或其他云服务（用于存储书籍封面图片等静态资源）

### 3. 系统架构设计

- **微服务拆分**：
  1. 用户服务：处理用户注册、登录、信息管理等功能。
  2. 书籍服务：管理书籍信息，提供书籍查询、分类、详情等接口。
  3. 订单服务：处理订单创建、查询、支付等业务。
  4. 购物车服务：管理用户的购物车，包括添加、删除书籍等功能。
  5. 推荐服务：基于用户行为提供书籍推荐。
  6. 管理服务：供管理员使用，进行书籍、订单、用户的管理。
- **服务间通信**：采用Spring Cloud OpenFeign进行服务间同步调用，RabbitMQ处理异步消息。

### 4. 数据库设计

- 用户表：用户ID、用户名、密码（加密存储）、邮箱、注册时间等。
- 书籍表：书籍ID、书名、作者、价格、库存、分类ID、封面图片URL、描述等。
- 订单表：订单ID、用户ID、订单状态、支付方式、订单总价、创建时间等。
- 订单详情表：详情ID、订单ID、书籍ID、购买数量、书籍价格等。
- 购物车表：用户ID、书籍ID、添加数量等。
- 分类表：分类ID、分类名称。

### 5. 前端设计

- **页面组件**：
  1. 首页：展示书籍推荐、热门书籍。
  2. 书籍列表页：按分类展示书籍，支持搜索。
  3. 书籍详情页：展示书籍详细信息，购买和加入购物车按钮。
  4. 购物车页面：显示用户加入的书籍，可以修改数量或删除。
  5. 订单确认页：从购物车中生成订单，选择支付方式。
  6. 用户中心：展示个人信息、订单历史等。
- **交互设计**：使用Vue Router进行路由管理，Vuex进行状态管理，提供流畅的用户体验。

### 6. 安全性设计

- 用户密码加密存储。
- 使用JWT进行用户认证。
- 接口权限控制，防止未授权访问。
- 输入验证，防止SQL注入、XSS攻击等。

### 7. 性能优化

- 使用Redis缓存热点数据，减轻数据库压力。
- Elasticsearch优化搜索效率。
- 图片、静态资源使用CDN加速。
- 服务限流、熔断保证系统稳定性。

### 8. 开发与部署

- **开发计划**：根据需求优先级，分阶段进行迭代开发。
- **测试策略**：采用单元测试保证代码质量，集成测试确保服务间交互正确，性能测试检测系统负载能力。
- **代码管理**：使用Git进行版本控制，采用Git Flow工作流程管理开发流程。
- **CI/CD**：使用Jenkins或GitHub Actions实现持续集成和持续部署，自动化测试和部署流程。
- **容器化部署**：使用Docker容器化各个微服务，Kubernetes作为容器编排工具，提高系统的可扩展性和可维护性。
- **监控和日志**：集成Prometheus和Grafana进行系统监控，使用ELK（Elasticsearch, Logstash, Kibana）堆栈进行日志管理和分析。

### 9. 运营维护

- **用户反馈**：建立用户反馈机制，及时收集用户意见和需求，不断优化用户体验。
- **版本迭代**：根据市场反馈和技术发展，定期进行功能迭代和优化。
- **安全更新**：定期检查和更新系统安全漏洞，确保用户数据安全。

### 10. 预算规划

- **硬件成本**：考虑服务器租赁、带宽等费用。
- **软件成本**：包括开发工具、第三方服务费用等。
- **人力成本**：项目团队成员的薪酬和福利。
- **运营成本**：市场推广、客户服务等费用。

### 11. 项目风险评估

- **技术风险**：新技术的学习和应用可能带来的风险。
- **需求变更**：需求频繁变更可能导致项目延期。
- **安全风险**：数据泄露或系统被攻击的风险。
- **运维风险**：系统高可用和稳定性维护的风险。

## 二、后端开发

### 2.1 JWT

#### 2.1.1 什么是JWT

在介绍JWT之前，我们先来回顾一下利用token进行用户身份验证的流程：

1. 客户端使用用户名和密码请求登录

2. 服务端收到请求，验证用户名和密码
3. 验证成功后，服务端会签发一个token，再把这个token返回给客户端
4. 客户端收到token后可以把它存储起来，比如放到cookie中
5. 客户端每次向服务端请求资源时需要携带服务端签发的token，可以在cookie或者header中携带
6. 服务端收到请求，然后去验证客户端请求里面带着的token，如果验证成功，就向客户端返回请求数据

这种基于token的认证方式相比传统的session认证方式更节约服务器资源，并且对移动端和分布式更加友好。其优点如下：

- 支持跨域访问：cookie是无法跨域的，而token由于没有用到cookie(前提是将token放到请求头中)，所以跨域后不会存在信息丢失问题

- 无状态：token机制在服务端不需要存储session信息，因为token自身包含了所有登录用户的信息，所以可以减轻服务端压力
- 更适用CDN：可以通过内容分发网络请求服务端的所有资料
- 更适用于移动端：当客户端是非浏览器平台时，cookie是不被支持的，此时采用token认证方式会简单很多
- 无需考虑CSRF：由于不再依赖cookie，所以采用token认证方式不会发生CSRF，所以也就无需考虑CSRF的防御

而JWT就是上述流程当中token的一种具体实现方式，其全称是JSON Web Token，官网地址：https://jwt.io/

**通俗地说，JWT的本质就是一个字符串，它是将用户信息保存到一个Json字符串中，然后进行编码后得到一个JWT token，并且这个JWT token带有签名信息，接收后可以校验是否被篡改，所以可以用于在各方之间安全地将信息作为Json对象传输。**JWT的认证流程如下：

1. 首先，前端通过Web表单将自己的用户名和密码发送到后端的接口，这个过程一般是一个POST请求。建议的方式是通过SSL加密的传输(HTTPS)，从而避免敏感信息被嗅探
2. 后端核对用户名和密码成功后，将包含用户信息的数据作为JWT的Payload，将其与JWT Header分别进行Base64编码拼接后签名，形成一个JWT Token，形成的JWT Token就是一个如同lll.zzz.xxx的字符串
3. 后端将JWT Token字符串作为登录成功的结果返回给前端。前端可以将返回的结果保存在浏览器中，退出登录时删除保存的JWT Token即可
4. 前端在每次请求时将JWT Token放入HTTP请求头中的Authorization属性中(解决XSS和XSRF问题)
5. 后端检查前端传过来的JWT Token，验证其有效性，比如检查签名是否正确、是否过期、token的接收方是否是自己等等
6. 验证通过后，后端解析出JWT Token中包含的用户信息，进行其他逻辑操作(一般是根据用户信息得到权限等)，返回结果

![img](https://img-blog.csdnimg.cn/img_convert/900b3e81f832b2f08c2e8aabb540536a.png)

#### 2.1.2 为什么要用JWT

##### 传统Session认证的弊端

1. 每个用户的登录信息都会保存到服务器的session中，随着用户的增多，服务器开销会明显增大
2. 由于session是存在与服务器的物理内存中，所以在分布式系统中，这种方式将会失效。虽然可以将session统一保存到Redis中，但是这样做无疑增加了系统的复杂性，对于不需要redis的应用也会白白多引入一个缓存中间件
3. 对于非浏览器的客户端、手机移动端等不适用，因为session依赖于cookie，而移动端经常没有cookie
4. 因为session认证本质基于cookie，所以如果cookie被截获，用户很容易收到跨站请求伪造攻击。并且如果浏览器禁用了cookie，这种方式也会失效
5. 前后端分离系统中更加不适用，后端部署复杂，前端发送的请求往往经过多个中间件到达后端，cookie中关于session的信息会转发多次
6. 由于基于Cookie，而cookie无法跨域，所以session的认证也无法跨域，对单点登录不适用

##### JWT认证的优势

- 简洁：JWT Token数据量小，传输速度也很快
- 因为JWT Token是以JSON加密形式保存在客户端的，所以JWT是跨语言的，原则上任何web形式都支持
- 不需要在服务端保存会话信息，也就是说不依赖于cookie和session，所以没有了传统session认证的弊端，特别适用于分布式微服务
- 单点登录友好：使用Session进行身份认证的话，由于cookie无法跨域，难以实现单点登录。但是，使用token进行认证的话， token可以被保存在客户端的任意位置的内存中，不一定是cookie，所以不依赖cookie，不会存在这些问题
- 适合移动端应用：使用Session进行身份认证的话，需要保存一份信息在服务器端，而且这种方式会依赖到Cookie（需要 Cookie 保存 SessionId），所以不适合移动端

#### 2.1.3 JWT结构

JWT由3部分组成：标头([Header](https://so.csdn.net/so/search?q=Header&spm=1001.2101.3001.7020))、有效载荷(Payload)和签名(Signature)。在传输的时候，会将JWT的3部分分别进行Base64编码后用`.`进行连接形成最终传输的字符串。

![img](https://img-blog.csdnimg.cn/img_convert/b9cec7cc70df068e7a882b6dcef06299.png)

#### 2.1.4 JWT的种类

官网推荐了6个Java使用JWT的开源库，其中比较推荐使用的是`java-jwt`和`jjwt-root`

![img](https://img-blog.csdnimg.cn/img_convert/edf8486457b203795e1a96092c45e729.png)

##### java-jwt

###### 对称签名

```java
<dependency>
    <groupId>com.auth0</groupId>
    <artifactId>java-jwt</artifactId>
    <version>3.10.3</version>
</dependency>


```

生成JWT的Token

```java
public class JWTTest {
    @Test
    public void testGenerateToken(){
        // 指定token过期时间为10秒
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 10);

        String token = JWT.create()
                .withHeader(new HashMap<>())  // Header
                .withClaim("userId", 21)  // Payload
                .withClaim("userName", "baobao")
                .withExpiresAt(calendar.getTime())  // 过期时间
                .sign(Algorithm.HMAC256("!34ADAS"));  // 签名用的secret

        System.out.println(token);
    }
}

```

解析JWT字符串

```java
@Test
public void testResolveToken(){
    // 创建解析对象，使用的算法和secret要与创建token时保持一致
    JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("!34ADAS")).build();
    // 解析指定的token
    DecodedJWT decodedJWT = jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyTmFtZSI6ImJhb2JhbyIsImV4cCI6MTU5OTkyMjUyOCwidXNlcklkIjoyMX0.YhA3kh9KZOAb7om1C7o3vBhYp0f61mhQWWOoCrrhqvo");
    // 获取解析后的token中的payload信息
    Claim userId = decodedJWT.getClaim("userId");
    Claim userName = decodedJWT.getClaim("userName");
    System.out.println(userId.asInt());
    System.out.println(userName.asString());
    // 输出超时时间
    System.out.println(decodedJWT.getExpiresAt());
}

```

封装成工具类

```java
public class JWTUtils {
    // 签名密钥
    private static final String SECRET = "!DAR$";

    /**
     * 生成token
     * @param payload token携带的信息
     * @return token字符串
     */
    public static String getToken(Map<String,String> payload){
        // 指定token过期时间为7天
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 7);

        JWTCreator.Builder builder = JWT.create();
        // 构建payload
        payload.forEach((k,v) -> builder.withClaim(k,v));
        // 指定过期时间和签名算法
        String token = builder.withExpiresAt(calendar.getTime()).sign(Algorithm.HMAC256(SECRET));
        return token;
    }


    /**
     * 解析token
     * @param token token字符串
     * @return 解析后的token
     */
    public static DecodedJWT decode(String token){
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        return decodedJWT;
    }
}

```

###### 非对称签名

生成jwt串的时候需要指定私钥，解析jwt串的时候需要指定公钥

```java
private static final String RSA_PRIVATE_KEY = "...";
private static final String RSA_PUBLIC_KEY = "...";

/**
     * 生成token
     * @param payload token携带的信息
     * @return token字符串
     */
public static String getTokenRsa(Map<String,String> payload){
    // 指定token过期时间为7天
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DATE, 7);

    JWTCreator.Builder builder = JWT.create();
    // 构建payload
    payload.forEach((k,v) -> builder.withClaim(k,v));

    // 利用hutool创建RSA
    RSA rsa = new RSA(RSA_PRIVATE_KEY, null);
    // 获取私钥
    RSAPrivateKey privateKey = (RSAPrivateKey) rsa.getPrivateKey();
    // 签名时传入私钥
    String token = builder.withExpiresAt(calendar.getTime()).sign(Algorithm.RSA256(null, privateKey));
    return token;
}

/**
     * 解析token
     * @param token token字符串
     * @return 解析后的token
     */
public static DecodedJWT decodeRsa(String token){
    // 利用hutool创建RSA
    RSA rsa = new RSA(null, RSA_PUBLIC_KEY);
    // 获取RSA公钥
    RSAPublicKey publicKey = (RSAPublicKey) rsa.getPublicKey();
    // 验签时传入公钥
    JWTVerifier jwtVerifier = JWT.require(Algorithm.RSA256(publicKey, null)).build();
    DecodedJWT decodedJWT = jwtVerifier.verify(token);
    return decodedJWT;
}

```

#### 2.1.5实际开发中的应用

在实际的SpringBoot项目中，一般我们可以用如下流程做登录：

1. 在登录验证通过后，给用户生成一个对应的随机token(注意这个token不是指jwt，可以用uuid等算法生成)，然后将这个token作为key的一部分，用户信息作为value存入Redis，并设置过期时间，这个过期时间就是登录失效的时间
2. 将第1步中生成的随机token作为JWT的payload生成JWT字符串返回给前端
3. 前端之后每次请求都在请求头中的Authorization字段中携带JWT字符串
4. 后端定义一个拦截器，每次收到前端请求时，都先从请求头中的Authorization字段中取出JWT字符串并进行验证，验证通过后解析出payload中的随机token，然后再用这个随机token得到key，从Redis中获取用户信息，如果能获取到就说明用户已经登录

```java
public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String JWT = request.getHeader("Authorization");
        try {
            // 1.校验JWT字符串
            DecodedJWT decodedJWT = JWTUtils.decode(JWT);
            // 2.取出JWT字符串载荷中的随机token，从Redis中获取用户信息
            ...
            return true;
        }catch (SignatureVerificationException e){
            System.out.println("无效签名");
            e.printStackTrace();
        }catch (TokenExpiredException e){
            System.out.println("token已经过期");
            e.printStackTrace();
        }catch (AlgorithmMismatchException e){
            System.out.println("算法不一致");
            e.printStackTrace();
        }catch (Exception e){
            System.out.println("token无效");
            e.printStackTrace();
        }
        return false;
    }
}

```

### 2.2 实际开发中存在的问题

#### 2.2.1 gateway跨域问题

在前端项目中，前端端口为8080，前端通过：

```vue
const response = axios.post('http://localhost:8081/login', {
          username: this.username,
          password: this.password
        }, {
          headers: {
            'Content-Type': 'application/json'
          }
        });
```

调用login接口，gateway为中间层，端口号为8081，负责将这个请求转给login接口为8082,。这时候会出现跨域问题。

对于gateway跨域问题会导致：

1. **跨域请求被浏览器阻止**：现代浏览器实施同源策略，不允许跨域请求，默认情况下会阻止跨域请求。如果 Gateway 未进行跨域设置，前端应用发起的跨域请求将被浏览器拦截，导致请求无法成功发送到后端服务。
2. **请求被拒绝**：如果 Gateway 未设置跨域配置，后端服务会拒绝跨域请求。这会导致前端应用无法获取所需的数据，从而影响应用的功能和用户体验。
3. **安全问题**：跨域请求存在安全风险，未正确处理跨域请求可能会导致安全漏洞，例如跨站脚本攻击（XSS）或跨站请求伪造（CSRF）等问题。
4. **功能无法正常使用**：如果前端应用需要从后端服务获取数据或执行操作，而由于跨域问题导致无法正常访问后端服务，将影响应用的功能和性能。
5. **用户体验下降**：跨域请求受限会导致用户体验下降，例如页面加载时间延长、功能无法正常使用等，从而影响用户对应用的满意度。

在gateway微服务中，进行如下配置就可以解决这个问题：

```yaml
spring:
  cloud:
    gateway:
      # 。。。
      globalcors: # 全局的跨域处理
        add-to-simple-url-handler-mapping: true # 解决options请求被拦截问题
        corsConfigurations:
          '[/**]':
            allowedOrigins: # 允许哪些网站的跨域请求 
              - "http://localhost:8090"
            allowedMethods: # 允许的跨域ajax的请求方式
              - "GET"
              - "POST"
              - "DELETE"
              - "PUT"
              - "OPTIONS"
            allowedHeaders: "*" # 允许在请求中携带的头信息
            allowCredentials: true # 是否允许携带cookie
            maxAge: 360000 # 这次跨域检测的有效期
```



### 2.3 Feign使用





### 2.4 Redis使用

#### 2.4.1 字符串（String）

- **增加/修改**
  使用 `opsForValue().set()` 方法增加或修改字符串值。

  ```
  redisTemplate.opsForValue().set("key", "value");
  ```

- **查询**
  使用 `opsForValue().get()` 方法查询字符串值。

  ```
  String value = redisTemplate.opsForValue().get("key");
  ```

- **删除**
  使用 `delete()` 方法删除键。

  ```
  redisTemplate.delete("key");
  ```

#### 2.4.2 哈希（Hash）

- **增加/修改**
  使用 `opsForHash().put()` 方法向哈希中添加条目。

  ```
  redisTemplate.opsForHash().put("hashKey", "field", "value");
  ```

- **查询**
  使用 `opsForHash().get()` 方法获取哈希中的值。

  ```
  Object value = redisTemplate.opsForHash().get("hashKey", "field");
  ```

- **删除**
  使用 `opsForHash().delete()` 方法从哈希中删除一个或多个字段。

  ```
  redisTemplate.opsForHash().delete("hashKey", "field");
  ```

#### 2.4.3 列表（List）

- **增加**
  使用 `opsForList().rightPush()` 方法向列表中添加元素。

  ```
  redisTemplate.opsForList().rightPush("listKey", "value");
  ```

- **查询**
  使用 `opsForList().range()` 方法查询列表中的元素。

  ```
  List<String> elements = redisTemplate.opsForList().range("listKey", 0, -1);
  ```

- **删除**
  使用 `opsForList().remove()` 方法从列表中删除元素。

  ```
  redisTemplate.opsForList().remove("listKey", 1, "value");
  ```

#### 2.4.4 集合（Set）

- **增加**
  使用 `opsForSet().add()` 方法向集合中添加元素。

  ```
  redisTemplate.opsForSet().add("setKey", "value1", "value2");
  ```

- **查询**
  使用 `opsForSet().members()` 方法获取集合中的所有元素。

  ```
  Set<String> members = redisTemplate.opsForSet().members("setKey");
  ```

- **删除**
  使用 `opsForSet().remove()` 方法从集合中删除一个或多个元素。

  ```
  redisTemplate.opsForSet().remove("setKey", "value1");
  ```

#### 2.4.5 有序集合

- **增加**
  使用 `opsForZSet().add()` 方法向有序集合中添加元素，并设置分数。

  ```
  redisTemplate.opsForZSet().add("zsetKey", "value", 1);
  ```

- **查询**
  使用 `opsForZSet().range()` 方法获取有序集合中的元素。

  ```
  Set<String> zset = redisTemplate.opsForZSet().range("zsetKey", 0, -1);
  ```

- **删除**
  使用 `opsForZSet().remove()` 方法从有序集合中删除一个或多个元素。

  ```
  redisTemplate.opsForZSet().remove("zsetKey", "value");
  ```

### 2.5 RabbitMQ使用

当谈到 RabbitMQ 时，可以将其想象成一个快递中转站，用于在不同的应用程序之间传递消息。以下是一种通俗易懂的介绍：

#### 2.5.1 什么是 RabbitMQ？

**RabbitMQ** 是一个开源的消息队列软件，用于在应用程序之间传递消息。它实现了高级消息队列协议（AMQP），允许不同的软件系统之间进行通信，无论这些系统是用什么语言编写的。

#### 2.5.2 RabbitMQ 的用途：

1. **解耦系统：** RabbitMQ 可以帮助不同的应用程序或服务之间实现解耦，使它们可以独立地工作和通信，而不需要直接依赖彼此。
2. **异步通信：** 应用程序可以将消息发送到 RabbitMQ，而无需等待接收方立即处理消息。这种异步通信方式可以提高系统的性能和响应速度。
3. **消息传递：** RabbitMQ 可以确保消息的可靠传递，即使在消息发送方和接收方之间出现故障的情况下也能保证消息不会丢失。
4. **负载均衡：** RabbitMQ 可以用于实现负载均衡，将消息分发给多个消费者，以确保系统能够有效地处理大量的消息。
5. **消息队列：** RabbitMQ 将消息存储在队列中，按照一定的规则进行分发，确保消息能够按照顺序或者特定条件被消费。

#### 2.5.3 应用场景

##### 异步处理

![image-20240326234532713](C:\Users\29441\AppData\Roaming\Typora\typora-user-images\image-20240326234532713.png)

##### 应用解耦

![image-20240326234556146](C:\Users\29441\AppData\Roaming\Typora\typora-user-images\image-20240326234556146.png)

##### 流量削峰

![image-20240326234646904](C:\Users\29441\AppData\Roaming\Typora\typora-user-images\image-20240326234646904.png)

#### 2.5.4 常见MQ产品对比

![img](https://img-blog.csdnimg.cn/085a0965fce941ce8a5a241082a22503.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBAX1_lpYvmlpfnmoTljaHljaE=,size_12,color_FFFFFF,t_70,g_se,x_16#pic_center)

#### 2.5.5 RabbitMQ工作模式

1. 简单队列模式（Simple Queue）

在简单队列模式中，有一个生产者将消息发送到一个队列，然后有一个消费者从队列中获取消息并进行处理。这是最基本的消息传递模式，适用于单个生产者和单个消费者的场景。

2. 工作队列模式（Work Queues）

工作队列模式也称为任务队列模式。在这种模式中，有一个或多个生产者将消息发送到一个队列，然后有多个消费者从队列中获取消息并进行处理。这种模式适用于需要处理大量任务的场景，可以实现任务的并发处理和负载均衡。

3. 发布/订阅模式（Publish/Subscribe）

发布/订阅模式中，有一个生产者将消息发送到一个交换机（Exchange），然后交换机将消息广播给多个队列，每个队列都有一个或多个消费者来处理消息。这种模式适用于需要将消息广播给多个消费者的场景，如实时通知、事件驱动架构等。

4. 路由模式（Routing）

路由模式是发布/订阅模式的一种扩展，其中交换机根据消息的路由键（Routing Key）将消息路由到特定的队列。消费者可以根据自己感兴趣的路由键来订阅消息，从而只接收到特定的消息。这种模式适用于需要根据消息内容进行筛选和路由的场景。

5. 主题模式（Topics）

主题模式是路由模式的一种更加灵活和强大的版本，其中交换机根据消息的主题（Topic）进行匹配，并将消息路由到符合条件的队列。消费者可以使用通配符来订阅匹配特定主题的消息，从而实现更灵活的消息订阅和路由。

##### 交换机

在 RabbitMQ 中，交换机（Exchange）是消息的路由器，它接收来自生产者的消息，并根据规则将消息路由到一个或多个队列。交换机的作用类似于邮局的分拣员，根据信件的目的地将信件分发到不同的信箱中。

RabbitMQ 提供了几种不同类型的交换机，每种类型都有不同的消息路由规则。以下是 RabbitMQ 中常见的交换机类型：

1. **Direct Exchange（直连交换机）**：直连交换机根据消息的 routingKey 将消息路由到与之绑定的队列中。当一个队列绑定到一个直连交换机上时，需要指定一个 routingKey，只有消息的 routingKey 与该队列绑定时指定的 routingKey 匹配时，消息才会被路由到该队列。
2. **Fanout Exchange（扇形交换机）**：扇形交换机会将收到的消息广播到所有与之绑定的队列中。无需指定 routingKey，只需要将队列绑定到扇形交换机上即可，消息会被路由到所有绑定的队列中。
3. **Topic Exchange（主题交换机）**：主题交换机根据消息的 routingKey 和交换机与队列的绑定模式将消息路由到一个或多个队列中。主题交换机支持通配符匹配，可以根据 routingKey 的模式匹配来选择路由消息。
4. **Headers Exchange（头部交换机）**：头部交换机根据消息的头部信息来进行匹配，而不是像直连交换机那样根据 routingKey。需要定义一组键值对来描述消息的头部信息，并且需要在绑定时指定一组匹配规则。



##### 简单队列模式

![img](https://img-blog.csdnimg.cn/20210521171206752.png)

添加配置依赖：

```xml
<!-- Maven 依赖 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-amqp</artifactId>
</dependency>
```

定义配置类：

```java
package com.example.userservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fangyaohui
 * @version 0.0.3
 * @description RabbitMQConfig
 * @since 2024/3/26 22:53
 */
@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue sendMailQueue(){
        // 声明该队列名称 这里生成了一个名为User.sendmail的消息队列
        return new Queue("user.sendmail");
    }
}
```

创建生产者：

```java
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue sendMailQueue;

    public void send(String message) {
        rabbitTemplate.convertAndSend(sendMailQueue.getName(), message);
        System.out.println(" [x] Sent '" + message + "'");
    }
}
```

创建消费者：

```java
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @RabbitListener(queues = "user.sendmail")
    public void receive(String message) {
        System.out.println(" [x] Received '" + message + "'");
    }
}
```



##### 工作队列模式

![img](https://img-blog.csdnimg.cn/20210521171353848.png)

一个生产者对应多个消费者，但是一条消息只能有一个消费者获得消息！！！
轮询分发就是将[消息队列](https://so.csdn.net/so/search?q=消息队列&spm=1001.2101.3001.7020)中的消息，依次发送给所有消费者。一个消息只能被一个消费者获取。

对于工作队列模式来说，其实现和简单队列模式如出一辙。与简单队列不同的是，其工作队列对于了多个消息消费者，但是其消息只能被一个消费者接收，不能重复消费。

##### 发布订阅模式

![img](https://img-blog.csdnimg.cn/2021052117143529.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MTg4MjIwMA==,size_16,color_FFFFFF,t_70)

**发布订阅模式（Publish-Subscribe Pattern）**是一种消息队列模式，用于将消息广播给多个消费者。在这种模式中，生产者将消息发布到交换机（Exchange），而交换机将消息路由到与其绑定的所有队列，每个队列都有一个或多个消费者来接收消息。

这里使用的交换机是FanoutExchange.



[参考资料（内用具体代码demo）](https://blog.csdn.net/weixin_44741023/article/details/127824789?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522171154342816800192279944%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=171154342816800192279944&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduend~default-1-127824789-null-null.142^v100^pc_search_result_base2&utm_term=rabbitmq%20%E5%8F%91%E5%B8%83%E8%80%85%E8%AE%A2%E9%98%85%E8%80%85&spm=1018.2226.3001.4187)

```java
package com.demo.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutConfig {
    /**
     *  声明FanoutExchange（广播交换机）
     */
    @Bean
    public FanoutExchange fanoutExchange(){
        //交换机的名称
        return new FanoutExchange("exchange.fanout");
    }

    /**
     *  声明第一个队列
     */
    @Bean
    public Queue fanoutQueue1(){
        return new Queue("fanout.queue1");
    }

    /**
     *  声明第二个队列
     */
    @Bean
    public Queue fanoutQueue2(){
        return new Queue("fanout.queue2");
    }

    /**
     *  绑定 队列1 到 交换机
     */
    @Bean
    public Binding bindingQueue1(Queue fanoutQueue1, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fanoutQueue1).to(fanoutExchange);
    }

    /**
     *  绑定 队列2 到 交换机
     */
    @Bean
    public Binding bindingQueue2(Queue fanoutQueue2, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fanoutQueue2).to(fanoutExchange);
    }
}

```

详细请[参考资料](https://blog.csdn.net/weixin_44741023/article/details/127824789?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522171154342816800192279944%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=171154342816800192279944&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduend~default-1-127824789-null-null.142^v100^pc_search_result_base2&utm_term=rabbitmq%20%E5%8F%91%E5%B8%83%E8%80%85%E8%AE%A2%E9%98%85%E8%80%85&spm=1018.2226.3001.4187)

##### 路由模式

![img](https://img-blog.csdnimg.cn/20210521171531659.png)

生产者将消息发送到direct交换器，在绑定队列和交换器的时候有一个路由key，生产者发送的消息会指定一个路由key，那么消息只会发送到相应key相同的队列，接着监听该队列的消费者消费消息。

也就是让消费者有选择性的接收消息。
路由模式，是以路由规则为导向，引导消息存入符合规则的队列中。再由队列的消费者进行消费的。

[参考资料](https://blog.csdn.net/qq_38374397/article/details/120533844?ops_request_misc=&request_id=&biz_id=102&utm_term=rabbitmq%20%20%E8%B7%AF%E7%94%B1%20%E6%B3%A8%E8%A7%A3&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduweb~default-0-120533844.142^v100^pc_search_result_base2&spm=1018.2226.3001.4187)



##### 主题模式

![img](https://img-blog.csdnimg.cn/20210521171737590.png)

上面的路由模式是根据路由key进行完整的匹配（完全相等才发送消息），这里的通配符模式通俗的来讲就是模糊匹配。

符号“#”表示匹配一个或多个词，符号“*”表示匹配一个词。
　　与路由模式相似，但是，主题模式是一种模糊的匹配方式。

**主题模式（Topic Pattern）**是 RabbitMQ 中一种灵活的消息路由模式，它允许生产者根据消息的主题进行匹配，并将消息发送到符合条件的队列中。在主题模式中，生产者发送带有主题（Topic）的消息到交换机，交换机根据消息的主题和队列绑定的模式将消息路由到一个或多个队列中。

在主题模式中，主题由一个或多个单词组成，用点号（.）分隔。在匹配主题时，可以使用通配符符号（*）匹配一个单词，或者使用井号（#）匹配零个或多个单词。

```java
// 生产者
@Component
public class Producer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMessage(String routingKey, String message) {
        amqpTemplate.convertAndSend("topic-exchange", routingKey, message);
        System.out.println("Message sent with routing key: " + routingKey);
    }
}

// 消费者
@Component
public class Consumer {

    @RabbitListener(queues = "queue1")
    public void receiveMessage(String message) {
        System.out.println("Message received in queue1: " + message);
    }

    @RabbitListener(queues = "queue2")
    public void receiveMessage(String message) {
        System.out.println("Message received in queue2: " + message);
    }
}
```

```java
@SpringBootApplication
public class TopicPatternApplication {

    public static final String TOPIC_EXCHANGE = "topic-exchange";
    public static final String QUEUE_NAME_1 = "queue1";
    public static final String QUEUE_NAME_2 = "queue2";

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public Queue queue1() {
        return new Queue(QUEUE_NAME_1);
    }

    @Bean
    public Queue queue2() {
        return new Queue(QUEUE_NAME_2);
    }

    @Bean
    public Binding binding1() {
        return BindingBuilder.bind(queue1()).to(topicExchange()).with("topic.*");
    }

    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(queue2()).to(topicExchange()).with("topic.#");
    }

    public static void main(String[] args) {
        SpringApplication.run(TopicPatternApplication.class, args);
    }
}
```

