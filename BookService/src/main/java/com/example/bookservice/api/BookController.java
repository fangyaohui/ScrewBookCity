package com.example.bookservice.api;

import com.example.bookservice.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.common.enetity.Result;

import java.util.Map;


/**
 * @author fangyaohui
 * @version 0.0.3
 * @description bookController
 * @since 2024/3/19 19:32
 */

@RestController
@Slf4j
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;


    @RequestMapping("/getRecommendedBooks")
    public Result getRecommendedBooks(){
        log.info("getRecommendedBooks running...");
        return bookService.getCommendedBooks();
    }

    @RequestMapping("/getHotBook")
    public Result getHotBook(){
        log.info("getHotBook running ...");
        return bookService.getHotBook();
//        return Result.ok("asdfasf");
//        return null;
    }


    @RequestMapping("/getBook/{bookid}")
    public Result getBook(@PathVariable("bookid") int bookid){

        log.info("getBook running " + bookid);

        return bookService.getBookById(bookid);
//        return Result.ok("fasdkfaklsdfk");
    }

    @RequestMapping("/getBookType")
    public Result getBookType(){

        return bookService.getBookType();

//        return null;
    }

    @RequestMapping("/getCateGorizeBook")
    public Result getCateGorizeBook(@RequestBody Map<String,String> map){

        log.info(map.get("booktype"));
        int booktype = Integer.parseInt(map.get("booktype"));
        int page = Integer.parseInt(map.get("page"));
        return bookService.getCateGorizeBook(booktype,page);
    }


}