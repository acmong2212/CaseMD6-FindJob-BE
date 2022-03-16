package com.codegym.findJob.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "company", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @NaturalId(mutable = true)
    private String email;

    @JsonIgnore
    private String password;

    @Lob
    private String avatar;

    // mã doanh nghiệp
    private String companyCode;

    private String phoneNumber;

    @Lob
    private String description;

    private String address;

    // Số lượng nhân viên
    private Long numberOfEmployees;

    // Chi nhánh
    private String branch;

    // Lĩnh vực hoạt động
    private String fieldOfActivity;

    private String website;

    private String facebook;

    // link gg map
    private String mapLink;

    private boolean status = false;

    public Company(@NotBlank @Size(min = 3, max = 50) String name,
                   @NotBlank @Size(max = 50) @Email String email, String description,  String avatar,
                   @NotBlank @Size(min = 6, max = 100) String encode) {
        this.name = name;
        this.email = email;
        this.avatar = avatar;
        this.password = encode;
        this.description = description;
    }

    public Company() {
    }

    public Company(Long id, String name, String email, String password, String avatar, String companyCode, String phoneNumber,
                   String description, String address, Long numberOfEmployees, String branch, String fieldOfActivity, String website,
                   String facebook, String mapLink, boolean status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.companyCode = companyCode;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.address = address;
        this.numberOfEmployees = numberOfEmployees;
        this.branch = branch;
        this.fieldOfActivity = fieldOfActivity;
        this.website = website;
        this.facebook = facebook;
        this.mapLink = mapLink;
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(Long numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getFieldOfActivity() {
        return fieldOfActivity;
    }

    public void setFieldOfActivity(String fieldOfActivity) {
        this.fieldOfActivity = fieldOfActivity;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getMapLink() {
        return mapLink;
    }

    public void setMapLink(String mapLink) {
        this.mapLink = mapLink;
    }
}
