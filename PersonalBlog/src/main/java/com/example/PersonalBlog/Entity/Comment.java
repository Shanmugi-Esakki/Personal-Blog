package com.example.PersonalBlog.Entity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;
    private String text;
    private String postedBy;
    private LocalDateTime postedOn;

    @ManyToOne
    @JoinColumn(name = "blog_id") // Ensure this column name matches the column in your database
    @JsonIgnore 
    private Blog bl;


    public Long getCId() {
        return cid;
    }

    public void setCId(Long id) {
        this.cid = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public LocalDateTime getPostedOn() {
        return postedOn;
    }

    public void setPostedOn(LocalDateTime postedOn) {
        this.postedOn = postedOn;
    }

    public Blog getBl() {
        return bl;
    }

    public void setBl(Blog bl) {
        this.bl = bl;
    }
}

 
