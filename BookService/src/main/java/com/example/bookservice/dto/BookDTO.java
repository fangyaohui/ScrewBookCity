package com.example.bookservice.dto;

import com.example.bookservice.entity.Book;
import lombok.Data;

import java.util.List;

/**
 * @author fangyaohui
 * @version 0.0.3
 * @description BookDTO
 * @since 2024/3/19 20:55
 */

@Data
public class BookDTO {

    public List<Book>books;



}