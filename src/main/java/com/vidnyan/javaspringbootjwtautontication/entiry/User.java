package com.vidnyan.javaspringbootjwtautontication.entiry;

import javax.persistence.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    private long userId;
    private String userName;
    private String password;
    private String email;
}
