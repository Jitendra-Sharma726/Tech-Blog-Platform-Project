package controller;

import entity.Author;
import entity.BlogPost;
import entity.Comment;
import org.springframework.web.bind.annotation.*;
import service.BlogService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/authors")
    public Author createAuthor(@RequestBody Author author) {
        return blogService.saveAuthor(author);
    }

    @PostMapping("/authors/{authorId}/posts")
    public BlogPost createPost(
            @PathVariable Long authorId,
            @RequestBody BlogPost post) {

        return blogService.createPost(authorId, post);
    }

    @PostMapping("/posts/{postId}/comments")
    public Comment addComment(
            @PathVariable Long postId,
            @RequestBody Comment comment) {

        return blogService.addComment(postId, comment);
    }

    @GetMapping("/posts")
    public List<BlogPost> getFeed() {
        return blogService.getAllPosts();
    }

    @DeleteMapping("/posts/{postId}")
    public void deletePost(@PathVariable Long postId) {
        blogService.deletePost(postId);
    }
}
