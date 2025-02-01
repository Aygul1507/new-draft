package de.aygul.minicms.service;

import de.aygul.minicms.model.*;
import de.aygul.minicms.repository.BlogPostRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RequiredArgsConstructor
class BlogPostServiceTest {

    BlogPostRepository mockedBlogPostRepository = mock(BlogPostRepository.class);

    BlogPostService blogPostService = new BlogPostService(mockedBlogPostRepository);

    @Test
    @DisplayName("createBlog save BlogPost with Draft status")
    void testCreateBlogPostWithDraftStatus() {

        BlogPostDTO blogPostDTO = new BlogPostDTO("Valid Title",
                "Valid Body",
                "Valid Author",
                List.of(new CategoryDTO("Tech")));

        BlogPost blogPost = new BlogPost(1L,
                "Valid Title",
                "Valid Body",
                "Valid Author",
                null,
                BlogPostStatus.DRAFT,
                new ArrayList<>());

        when(mockedBlogPostRepository.save(any(BlogPost.class))).thenAnswer(invocation -> {
            BlogPost savedPost = invocation.getArgument(0);
            savedPost.setId(1L);
            return savedPost;
        });
        Long result = blogPostService.createBlog(blogPostDTO);

        assertEquals(1L, result);
        verify(mockedBlogPostRepository, times(1)).save(any(BlogPost.class));
    }
}