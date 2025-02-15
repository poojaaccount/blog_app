package com.myblog1.blogapp1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="title", nullable = false)
   // @Size(min = 2, max=20, message = "title should have at least 2 letters")
    private String title;

    @Column(name="description", nullable = false)
    //@Size(min = 20, message = "title should have at least 2 letters")
    private String description;

    @Column(name="content", nullable = false)
   // @NotNull
    //@NotEmpty
    private String content;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Comment> comments = new HashSet<>();
}
