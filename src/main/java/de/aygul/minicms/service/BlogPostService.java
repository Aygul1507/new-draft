package de.aygul.minicms.service;

import de.aygul.minicms.model.BlogPost;
import de.aygul.minicms.model.BlogPostDTO;
import de.aygul.minicms.model.BlogPostStatus;
import de.aygul.minicms.model.Category;
import de.aygul.minicms.repository.BlogPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlogPostService {

    private final BlogPostRepository blogPostRepository;


    public Long createBlog(BlogPostDTO blogPostDTO) {
        BlogPost blogPost=convertToEntity(blogPostDTO);
        blogPostRepository.save(blogPost);
        return blogPost.getId();
    }
    public BlogPost convertToEntity(BlogPostDTO blogPostDTO) {

        List<Category> categories = blogPostDTO.getCategoriesDTO().stream()
                                               .map(categoryDTO -> new Category(null, categoryDTO.getCategoryName(),
                                                       new ArrayList<>()))
                                               .collect(Collectors.toList());

        return new BlogPost(
                null,
                blogPostDTO.getTitle(),
                blogPostDTO.getBody(),
                blogPostDTO.getAuthor(),
                blogPostDTO.getCreatedAt(),
                BlogPostStatus.DRAFT,
                categories
        );
    }
}