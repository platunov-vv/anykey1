package com.anykey.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;



@Data
@Entity
@Table(name ="tb_requests")
public class Requests {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")

    private String id;
    @Column(name = "text")
    private String text;
    @Column(name = "fio")
    private String fio;

}
