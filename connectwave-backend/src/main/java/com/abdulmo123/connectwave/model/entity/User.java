package com.abdulmo123.connectwave.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;


@Entity
@Table(name = "user")
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(nullable = false, updatable = false)
  private Long id;

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Column(name = "password")
  @Transient
  private String password;

  @NotEmpty(message = "Please enter first name")
  @Column(name = "first_name")
  private String firstName;

  @NotEmpty(message = "Please enter last name")
  @Column(name = "last_name")
  private String lastName;

  @Column(name = "gender")
  private String gender;

  @Column(name = "user_bio")
  private String bio;

  @CreatedDate
  @Column(name = "created_date", nullable = false, updatable = false)
  private Date createdDate;

  @LastModifiedDate
  @Column(name = "last_login_date")
  private Date lastLoginDate;

  @JsonIgnore
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
  private List<Post> userPosts = new ArrayList<>();

  @JsonIgnore
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "sender", cascade = CascadeType.ALL)
  private List<Friendship> friends;

  @Column(name = "reset_pwd_token")
  private String resetPwdToken;

  @Column(name = "reset_pwd_token_exp_dt")
  private LocalDateTime resetPwdTokenExpDt;

  public User() {}

  public User(String email, String password, String firstName, String lastName, String gender, String bio, Date createdDate, Date lastLoginDate, List<Post> userPosts, List<Friendship> friends, String resetPwdToken, LocalDateTime resetPwdTokenExpDt) {
    this.email = email;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.gender = gender;
    this.bio = bio;
    this.createdDate = createdDate;
    this.lastLoginDate = lastLoginDate;
    this.userPosts = userPosts;
    this.friends = friends;
    this.resetPwdToken = resetPwdToken;
    this.resetPwdTokenExpDt = resetPwdTokenExpDt;
  }

  public Long getId() { return id; }

  public void setId(Long id) { this.id = id; }

  public String getEmail() { return email; }

  public void setEmail(String email) { this.email = email; }

  public String getPassword() { return password; }

  public void setPassword(String password) { this.password = password; }

  public String getFirstName() { return firstName; }

  public void setFirstName(String firstName) { this.firstName = firstName; }

  public String getLastName() { return lastName; }

  public void setLastName(String lastName) { this.lastName = lastName; }

  public String getGender() { return gender; }

  public void setGender(String gender) { this.gender = gender; }

  public String getBio() { return bio; }

  public void setBio(String bio) { this.bio = bio; }

  public Date getCreatedDate() { return createdDate; }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public Date getLastLoginDate() { return lastLoginDate; }

  public void setLastLoginDate(Date lastLoginDate) {
    this.lastLoginDate = lastLoginDate;
  }

  public List<Post> getUserPosts() { return userPosts; }

  public void setUserPosts(List<Post> userPosts) { this.userPosts = userPosts; }

  public List<Friendship> getFriends() {
    return friends;
  }

  public void setFriends(List<Friendship> friends) {
    this.friends = friends;
  }

  public String getResetPwdToken() {
    return resetPwdToken;
  }

  public void setResetPwdToken(String resetPwdToken) {
    this.resetPwdToken = resetPwdToken;
  }

  public LocalDateTime getResetPwdTokenExpDt() {
    return resetPwdTokenExpDt;
  }

  public void setResetPwdTokenExpDt(LocalDateTime resetPwdTokenExpDt) {
    this.resetPwdTokenExpDt = resetPwdTokenExpDt;
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", gender='" + gender + '\'' +
            ", bio='" + bio + '\'' +
            ", createdDate=" + createdDate +
            ", lastLoginDate=" + lastLoginDate +
            ", userPosts=" + userPosts +
            ", friends=" + friends +
            ", resetPwdToken='" + resetPwdToken + '\'' +
            ", resetPwdTokenExpDt=" + resetPwdTokenExpDt +
            '}';
  }
}
