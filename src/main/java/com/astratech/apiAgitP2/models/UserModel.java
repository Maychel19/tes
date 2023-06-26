package com.astratech.apiAgitP2.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "User",schema = "public")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_userIdSeq")
    @SequenceGenerator(name ="generator_userIdSeq", sequenceName = "userIdSeq", schema = "public")
    private int Id;
    private String Name;
    @Column(name = "Gender")
    private String Gender;
}
