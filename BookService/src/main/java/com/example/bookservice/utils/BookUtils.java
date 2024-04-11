package com.example.bookservice.utils;

import com.example.bookservice.entity.Book;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static com.example.common.utils.Constans.BOOK_NUM;

/**
 * @author fangyaohui
 * @version 0.0.3
 * @description BookUtils
 * @since 2024/3/19 21:01
 */
public class BookUtils {

    public static void BookImagePath(List<Book> books, String pre){
        for(Book book : books){
            String path = book.getImgpath();
            book.setImgpath(pre + path);
        }

    }

    public static Set<Integer> generateRandomNumbers(int count) {
        Set<Integer> randomNumbers = new HashSet<>();
        Random random = new Random();

        while (randomNumbers.size() < count) {
            int randomNumber = random.nextInt(BOOK_NUM); // 生成0到99之间的随机数
            randomNumbers.add(randomNumber);
        }
        return randomNumbers;
    }
}