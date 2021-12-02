package com.yswdqz.library.controller;

import com.yswdqz.library.mapper.BookMapper;
import com.yswdqz.library.model.Book;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookMapper bookMapper;
    @PostMapping("/insert")
    public String insert(@RequestBody Book book){
        Integer integer = bookMapper.insertBook(book);
        return integer>0?"success":"fail";
    }
    @GetMapping("/delete")
    public String delete(String isbn){
        Integer integer = bookMapper.deleteBook(isbn);
        return integer>0?"success":"fail";
    }
    @PostMapping("/update")
    public String update(@RequestBody Book book){
        Integer integer = bookMapper.updateBook(book);
        return integer>0?"success":"fail";
    }
}
