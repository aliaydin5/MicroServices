package com.depart.depart6.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersFile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ıd;

    private  long userId;

    private long  fileId;

}
