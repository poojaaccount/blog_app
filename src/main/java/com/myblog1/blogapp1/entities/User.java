package com.myblog1.blogapp1.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="users", uniqueConstraints = @UniqueConstraint(columnNames = {"username","email"}))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String username;

    private String password;

    private String email;

}
