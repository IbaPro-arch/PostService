package com.example.postservice.kafkaList;


import com.example.postservice.entity.Post;
import com.example.postservice.MongoController.MongoController;
import com.example.postservice.service.SequenceGeneratorService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;




@Service
public class KafkaMongo {

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;


    @KafkaListener(topics = "MenageService", groupId = "group_id 1")
    @KafkaHandler
    public void listener(@Payload String message) {
        String stringCanParse = cutStartAndEnd(message);
        Post post = new Gson().fromJson(stringCanParse, Post.class);
        System.out.println("Recieved message: " + message);
        sequenceGeneratorService.addPost(post);
    }
    public String cutStartAndEnd(String line){
        return line;
    }
}
