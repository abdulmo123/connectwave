package com.abdulmo123.connectwave.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class CommentDto implements Serializable {
    private Long id;
    private String content;
    private Date createdDate;
    private String publisherName;

    public CommentDto(Long id, String content, Date createdDate, String publisherName) {
        this.id = id;
        this.content = content;
        this.createdDate = createdDate;
        this.publisherName = publisherName;
    }
}
