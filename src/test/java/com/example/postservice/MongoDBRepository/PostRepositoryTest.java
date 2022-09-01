package com.example.postservice.MongoDBRepository;

import com.example.postservice.entity.Post;
import com.example.postservice.service.SequenceGeneratorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.util.List;

import static com.mongodb.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
@EnableWebSecurity
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    private SequenceGeneratorService sequenceGeneratorService;

    @Test
    void selectExistAuthorAndTitle() {
        Post post = postCreate("33", 2,
                "something", "something",
                "something", 42.3,
                "something", "something",
                true,"something", "something");
        List<Post> allPosts = postRepository.findAll();
        postRepository.save(post);
        assertUsers(allPosts.get(5));
        assertEquals("33", post.getId());
        assertNotNull(allPosts.get(5));
    }

    void assertUsers(Post post) {
        assertNotNull(post.getId());
        assertNotNull(post.getBookId());
        assertNotNull(post.getTitle());
        assertNotNull(post.getAuthor());
        assertNotNull(post.getType());
        assertNotNull(post.getPrice());
        assertNotNull(post.getLanguage());
        assertNotNull(post.getIsbn13());
        assertNotNull(post.getIsVisible());
        assertNotNull(post.getDateOfStartSales());
        assertNotNull(post.getCount());
    }

    Post postCreate(String id, Integer bookdId, String title, String author, String type, double price, String language, String isbn13, boolean isVisible, String dateOfStartSales, String count){
        Post post = Post.builder().id(id).bookId(bookdId).author(author).title(title).type(type).price(price).language(language).isbn13(isbn13).isVisible(isVisible).dateOfStartSales(dateOfStartSales).count(count).build();
        return postRepository.save(post);
    }
}