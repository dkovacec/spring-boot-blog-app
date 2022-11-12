package com.brightstraining.springbootblogapplication.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")   //will create table in mysql
public class Post {

    @Id  //this will be primary key
    @GeneratedValue(strategy = GenerationType.SEQUENCE)   //will be generated in database automatically
    private long id;
    @NotEmpty(message="Title cannot be empty")
    @NotNull
    private String title;    //title of the post
    @Column(columnDefinition = "TEXT")
    private String content;  // entire text of the post
    @Column(columnDefinition = "TEXT")
    private String summary;  //short text, intro to the post

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "userAccount_id", referencedColumnName="id")
    private UserAccount userAccount;

    public Post() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
}
