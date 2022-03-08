package com.codegym.findJob.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Apply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Users users;

    @ManyToOne
    private Post post;
}
