package com.anykey.model.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name ="tb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;

}
