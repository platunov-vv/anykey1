package com.anykey.model.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.Optional;
import java.util.UUID;


@Data
@Entity
@Table(name ="tb_user")
public class User {

    @Id
   /* @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")*/
    private UUID id;

    @Column(name = "firstname")
    @NotNull
    private String firstName;

    @Column(name = "lastname")
    private String lastName;
    @PrePersist
    public void generateId() {
        if (id == null) {
            this.id = UUID.randomUUID(); // или другой способ генерации
        }
    }

}
