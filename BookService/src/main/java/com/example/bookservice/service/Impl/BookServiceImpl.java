package com.example.bookservice.service.Impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bookservice.entity.Book;
import com.example.bookservice.entity.BookType;
import com.example.bookservice.mapper.BookMapper;
import com.example.bookservice.service.BookService;
import com.example.bookservice.service.BookTypeService;
import com.example.bookservice.utils.BookUtils;
import com.example.common.enetity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.example.common.utils.Constans.CATEGORIZE_MAX_PAGE_SIZE;

/**
 * @author fangyaohui
 * @version 0.0.3
 * @description BookServiceImpl
 * @since 2024/3/19 19:46
 */

@Service
@Slf4j
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

    @Autowired
    private BookTypeService bookTypeService;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public Result getBookById(int id) {

        Book  book = (Book) redisTemplate.opsForValue().get("book:"+id);
        if(book == null){
            book = query().eq("id",id).one();
            redisTemplate.opsForValue().set("book:"+id,book);
            redisTemplate.expire("book:"+id,30, TimeUnit.MINUTES);
            return Result.ok(book);
        }
        return Result.ok(book);
    }

    @Override
    public Result getCommendedBooks() {

        List<Book> books = new ArrayList<>();

        Set<Integer> randomNumbers = BookUtils.generateRandomNumbers(10);

        for(int index : randomNumbers){
            Book book = (Book) getBookById(index).getData();
            books.add(book);
        }
        BookUtils.BookImagePath(books, "");

        return Result.ok(books);
    }

    @Override
    public Result getHotBook() {
        List<Book> books = query().orderBy(true,true,"buynum").last("LIMIT 19").list();

        return Result.ok(books);
    }

    @Override
    public Result getBookType() {

        List<BookType> bookTypes = bookTypeService.query().list();

        return Result.ok(bookTypes);

//        return null;
    }

    @Override
    public Result getCateGorizeBook(int booktype, int page) {



        Page<Book> bookPage = query().eq("booktype",booktype).page(new Page<>(page, CATEGORIZE_MAX_PAGE_SIZE));
        List<Book> bookList = bookPage.getRecords();

        return Result.ok(bookList);
    }
}