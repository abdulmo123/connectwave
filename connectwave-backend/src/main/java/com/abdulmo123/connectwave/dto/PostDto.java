package com.abdulmo123.connectwave.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Data
public class PostDto implements Serializable {
    private Long id;
    private String content;
    private Date createdDate;
    private UserDto user;
    private List<CommentDto> postComments;

    public PostDto(Long id, String content, Date createdDate, UserDto user, List<CommentDto> postComments) {
        this.id = id;
        this.content = content;
        this.createdDate = createdDate;
        this.user = user;
        this.postComments = postComments;
    }
}