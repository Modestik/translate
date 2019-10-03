package com.project.translate.model.db;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class Users {

    @Id
    private String acc;
    @Column
    private String password;
}
