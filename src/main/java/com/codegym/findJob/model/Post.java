package com.codegym.findJob.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;

@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String title;

    private String salary;

    // vị trí tuyển dụng
    private String jobLocation;

    private String position;

    // kinh nghiệm
    private Long experience;

    // Loại công việc
    private boolean jobType;

    // Ngày hết hạn
    private Date applicationDeadline;

    private String description;

    // Số lượng tuyển dụng
    private Long vacancy;

    private String gender;

    // mã bài tuyển dụng
    private String postCode;

    private boolean status;

    @ManyToOne
    private Company company;

    @ManyToOne
    private Field field;
}
