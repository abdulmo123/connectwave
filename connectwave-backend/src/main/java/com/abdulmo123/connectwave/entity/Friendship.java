package com.abdulmo123.connectwave.entity;

import com.abdulmo123.connectwave.enums.FriendshipStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Date;

@Entity
@Table(name = "friendship")
public class Friendship {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friend_id", referencedColumnName = "id")
    private User friend;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private FriendshipStatus status;

    @CreatedDate
    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    public Friendship() {}

    public Friendship(User user, User friend, FriendshipStatus status, Date createdDate) {
        this.user = user;
        this.friend = friend;
        this.status = status;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }

    public FriendshipStatus getStatus() {
        return status;
    }

    public void setStatus(FriendshipStatus status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Friendship{" +
                "id=" + id +
                ", user=" + user +
                ", friend=" + friend +
                ", status=" + status +
                ", createdDate=" + createdDate +
                '}';
    }
}
