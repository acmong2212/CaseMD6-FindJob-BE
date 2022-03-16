package com.codegym.findJob.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    private double minSalary;

    private double maxSalary;

    // vị trí tuyển dụng
    private String position;

    // địa điểm làm việc
    private String jobLocation;

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
