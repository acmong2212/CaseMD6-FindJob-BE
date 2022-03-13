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
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @NaturalId(mutable = true)
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @JsonIgnore
    @NotBlank
    @Size(max = 100)
    private String password;

    @Lob
    private String avatar;

    private String cv;

    private String phoneNumber;

    private String address;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<Role> roles = new HashSet<>();

    public Users(@NotBlank @Size(min = 3, max = 50) String name,
                 @NotBlank @Size(max = 50) @Email String email,
                 String phoneNumber,
                 String avatar,
                 @NotBlank @Size(min = 6, max = 100) String encode) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
        this.password = encode;
    }

    public Users() {
    }

    public Users(Long id, String name, String email, String password, String avatar, String cv, String phoneNumber, String address,
                 Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.cv = cv;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.roles = roles;
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

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
