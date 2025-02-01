package de.aygul.minicms.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String body;
    private String author;

    @Column(columnDefinition = "DATE")
    private LocalDateTime publicationDate;

    @Enumerated(EnumType.STRING)
    private BlogPostStatus blogPostStatus;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Category> categories = new ArrayList<>();
}
