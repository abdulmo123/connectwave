package com.abdulmo123.connectwave.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "comment")
//@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @CreatedDate
    @Column(name="created_date", updatable=false)
    private Date createdDate;

//    @JsonBackReference(value = "comment-user")
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

//    @JsonBackReference(value = "comment-post")
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    @Transient
    private String publisherName;

    public Comment () {}

    public Comment (String content, Date createdDate, User user, Post post, String publisherName) {
        this.content = content;
        this.createdDate = createdDate;
        this.user = user;
        this.post = post;
        this.publisherName = publisherName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getPublisherName() {
        return user != null ? user.getFirstName() + " " + user.getLastName() : null;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

}
