package com.yswdqz.library.mapper;

import com.yswdqz.library.model.Administrator;
import com.yswdqz.library.model.Book;
import org.apache.ibatis.annotations.*;

@Mapper
public interface BookMapper {
    @Insert("insert into book (isbn,author,title,publisher,booktype)" +
            " values(#{book.isbn},#{book.author},#{book.title}" +
            ",#{book.publisher},#{book.booktype})")
    Integer insertBook(@Param("book") Book book);

    @Delete("delete from book where isbn = #{isbn}")
    Integer deleteBook(String isbn);

    @Update("update book set author = #{book.author}" +
            ",title = #{book.title},publisher = #{book.publisher}, " +
            "booktype = #{book.booktype} where isbn = #{book.isbn}")
    Integer updateBook(@Param("book") Book book);
}
