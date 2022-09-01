package com.example.postservice.MongoController;

import com.example.postservice.MongoDBRepository.PostRepository;
import com.example.postservice.entity.Post;
import com.example.postservice.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static com.example.postservice.entity.Post.SEQUENCE_NAME;


@RestController
@RequestMapping(value = "/", produces = "application/json")
public class MongoController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;


    @PostMapping("/savebook")
    public Post addPost(@RequestBody Post post) {
        post.setBookId(sequenceGeneratorService.getSequenceNumber(SEQUENCE_NAME));
        return sequenceGeneratorService.addPost(post);
    }

    @GetMapping("/post")
    public List<Post> getAllBooks() {
        return sequenceGeneratorService.getAllBooks();
    }

    @GetMapping(path = "/post/{bookId}")
    public Post getPost(@PathVariable Integer bookId) {
        return sequenceGeneratorService.getBook(bookId);
    }

    @GetMapping("/post/isVisible")
    public ResponseEntity<Map<String, Object>> findByIsVisible(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {

        try {
            List<Post> posts = new ArrayList<>();
            Pageable paging = PageRequest.of(page, size);

            Page<Post> pageTuts = postRepository.findByIsVisible(true, paging);
            posts = pageTuts.getContent();

            if (posts.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("isVisibleTrue", posts);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("post/{bookId}")
    public ResponseEntity<Post> update(@PathVariable Integer bookId,
                                       @RequestBody boolean isVisible) {
        Post postUpdate = postRepository.findById(String.valueOf(bookId)).get();
        postUpdate.setIsVisible(isVisible);
        return new ResponseEntity<Post>(postRepository.save(postUpdate), HttpStatus.OK);
    }

    @PatchMapping("posts/{id}")
    public ResponseEntity<Post> update(@PathVariable Integer id) {
        Post postUpdate = postRepository.findById(String.valueOf(id)).get();
        postUpdate.setCount(String.valueOf(postUpdate.getTitle().length()));
        return new ResponseEntity<Post>(postRepository.save(postUpdate), HttpStatus.OK);
    }
}
