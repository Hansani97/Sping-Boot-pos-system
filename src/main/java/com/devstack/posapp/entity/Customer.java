package com.devstack.posapp.entity;

import com.devstack.posapp.entity.process.FileResource;
import lombok.*;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long publicId;
    private String name;
    private String address;
    private double salary;
    @Column(columnDefinition = "TINYINT")
    private boolean activeState;
    @Embedded
    private FileResource fileResource;

}
