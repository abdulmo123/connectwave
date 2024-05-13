package com.abdulmo123.connectwave.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name="post")
//@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(name="content")
    private String content;

    @CreatedDate
    @Column(name="created_date", nullable=false, updatable=false)
    private Date createdDate;

//    @JsonBackReference(value = "post-user")
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> postComments = new ArrayList<>();

    @Transient
    private String publisherName;

    @Transient
    private int numLikes;

    @Transient
    private int numComments;

    public Post() {}

    public Post(String content, Date createdDate, User user, List<Comment> postComments, String publisherName, int numLikes, int numComments) {
        this.content = content;
        this.createdDate = createdDate;
        this.user = user;
        this.postComments = postComments;
        this.publisherName = publisherName;
        this.numLikes = numLikes;
        this.numComments = numComments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPublisherName() {
        return user != null ? user.getFirstName() + " " + user.getLastName() : null;
    }

    public List<Comment> getPostComments() {
        return postComments;
    }

    public void setPostComments(List<Comment> postComments) {
        this.postComments = postComments;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public int getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(int numLikes) {
        this.numLikes = numLikes;
    }

    public int getNumComments() {
        return numComments;
    }

    public void setNumComments(int numComments) {
        this.numComments = numComments;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", createdDate=" + createdDate +
                ", user=" + user +
                ", postComments=" + postComments +
                ", publisherName='" + publisherName + '\'' +
                ", numLikes=" + numLikes +
                ", numComments=" + numComments +
                '}';
    }
}
