package com.example.demo_springboot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users") // tên bảng trong database
public class User {

    @Id
    @Column(nullable = false, unique = true, length = 100)
    private String email;   // dùng làm khóa chính

    @Column(nullable = false, length = 100)
    private String password;

}