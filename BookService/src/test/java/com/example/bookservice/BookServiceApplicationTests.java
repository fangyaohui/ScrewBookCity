package com.example.bookservice;

import com.example.bookservice.dto.BookDTO;
import com.example.bookservice.service.BookService;
import com.example.common.enetity.Result;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookServiceApplicationTests {

    @Autowired
    private BookService bookService;

    @Test
    void contextLoads() {
    }


    @Test
    void mysqlConnectTest(){
        Result result = bookService.getBookById(1);

        System.out.println(result.getData());
    }

    @Test
    void bookdtoTest(){
        Result result = bookService.getCommendedBooks();
        System.out.println(result.getData());
    }

}
