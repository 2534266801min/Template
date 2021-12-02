package com.yswdqz.library.mapper;

import com.yswdqz.library.model.Administrator;
import com.yswdqz.library.model.Book;
import com.yswdqz.library.model.Reader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sun.reflect.generics.tree.VoidDescriptor;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BookMapper bookMapper;
    @Test
    void read() {
//        Book book = new Book();
//        book.setBooktype("1");
//        book.setAuthor("1");
//        book.setIsbn("1");
//        book.setPublisher("1");
//        book.setTitle("1");
//
//        bookMapper.insertBook(book);
        bookMapper.deleteBook("1");
    }
}
