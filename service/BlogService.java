package service;

import entity.Author;
import entity.BlogPost;
import entity.Comment;
import org.springframework.stereotype.Service;
import repository.AuthorRepository;
import repository.BlogPostRepository;
import repository.CommentRepository;

import java.util.List;

@Service
public class BlogService {

    private final AuthorRepository authorRepository;
    private final BlogPostRepository blogPostRepository;
    private final CommentRepository commentRepository;

    public BlogService(
            AuthorRepository authorRepository,
            BlogPostRepository blogPostRepository,
            CommentRepository commentRepository) {

        this.authorRepository = authorRepository;
        this.blogPostRepository = blogPostRepository;
        this.commentRepository = commentRepository;
    }

    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    public BlogPost createPost(Long authorId, BlogPost post) {
        Author author = authorRepository.findById(authorId).orElse(null);

        if (author == null) {
            throw new RuntimeException("Author not found");
        }

        post.setAuthor(author);
        return blogPostRepository.save(post);
    }

    public Comment addComment(Long postId, Comment comment) {
        BlogPost post = blogPostRepository.findById(postId).orElse(null);

        if (post == null) {
            throw new RuntimeException("Post not found");
        }

        comment.setBlogPost(post);
        return commentRepository.save(comment);
    }

    public void deletePost(Long postId) {
        blogPostRepository.deleteById(postId);
    }

    public List<BlogPost> getAllPosts() {
        return blogPostRepository.findAll();
    }
}
