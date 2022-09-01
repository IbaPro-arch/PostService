package com.example.postservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "postservice")
@Entity
@Builder
public class Post {

    @Transient
    public static final String SEQUENCE_NAME = "user_sequence";

    @Id
    @Field("id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "bookId")
    @Field("bookId")
    private Integer bookId;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    private String type;

    private double price;

    private String language;

    private String isbn13;

    @Column(name = "isVisible")
    private Boolean isVisible = true;

    @Column(name = "dateOfStartSales")
    private String dateOfStartSales = "null";

    @Column(name = "count")
    private String count;

}
