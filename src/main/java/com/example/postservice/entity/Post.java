package com.example.postservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "postservice")
public class Post {

    @Transient
    public static final String SEQUENCE_NAME = "user_sequence";

    @Id
    private Integer id;
    @Column(name = "bookId")
    private Integer bookId;
    @Column(name = "title")
    private String title;
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
