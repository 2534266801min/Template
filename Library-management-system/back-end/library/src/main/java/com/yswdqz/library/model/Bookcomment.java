package com.yswdqz.library.model;


import lombok.Data;

import java.util.Date;

@Data
public class Bookcomment {
    private Integer commentId;
    private Integer rate;
    private String content;
    private Date postDate;

}
