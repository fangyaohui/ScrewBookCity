package com.example.bookservice.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.bookservice.entity.Book;
import com.example.common.enetity.Result;

public interface BookService extends IService<Book> {

    Result getBookById(int id);

    Result getCommendedBooks();

    Result getHotBook();

    Result getBookType();

    Result getCateGorizeBook(int booktype,int page);
}
