package com.myblog1.blogapp1.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
}
