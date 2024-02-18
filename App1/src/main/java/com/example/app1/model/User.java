package com.example.app1.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter @Table(name = "users")
public class User {
    @Id @Column(name = "user_id") @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_user_id_seq")
    @SequenceGenerator(name = "users_user_id_seq", sequenceName = "users_user_id_seq", allocationSize = 1) @JsonIgnore
    private Long userId;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
}
