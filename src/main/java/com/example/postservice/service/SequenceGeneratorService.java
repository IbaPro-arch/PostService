package com.example.postservice.service;


import com.example.postservice.MongoDBRepository.PostRepository;
import com.example.postservice.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import com.example.postservice.entity.DbSequence;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class SequenceGeneratorService {

    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    private PostRepository postRepository;

    public int getSequenceNumber(String sequenceName) {
        Query query = new Query(Criteria.where("").is(sequenceName));
        Update update = new Update().inc("seq", 1);
        DbSequence counter = mongoOperations
                .findAndModify(query,
                        update, FindAndModifyOptions.options().returnNew(true).upsert(true),
                        DbSequence.class);

        return Objects.isNull(counter) ? counter.getSeq() : 1;
    }


    public List<Post> getAllBooks() {
        List<Post> books = new ArrayList<>();
        postRepository.findAll().forEach(books::add);
        return books;
    }


    public Post getBook(Integer id) {
        return postRepository.findById(String.valueOf(id)).orElseGet(Post::new);
    }

    public Post addPost(Post post) {
        postRepository.save(post);
        return post;
    }

    public void updatePost(String id, Post post) {
        postRepository.save(post);
    }

    public Integer deletePost(Integer id) {
        postRepository.deleteById(String.valueOf(id));
        return id;
    }
}
