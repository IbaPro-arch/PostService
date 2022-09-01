package com.example.postservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "db_sequence")
@AllArgsConstructor
@NoArgsConstructor
public class DbSequence {

    @Id
    private Integer id;
    private int seq;
}
