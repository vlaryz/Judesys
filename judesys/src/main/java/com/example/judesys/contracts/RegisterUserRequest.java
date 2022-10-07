package com.example.judesys.contracts;

import com.example.judesys.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterUserRequest {
    private String userName;
    private String password;
    private String email;
    private String name;
    private String surname;


    public User getUser() {
        return new User(this.userName, this.name, this.surname, this.email, this.password);
    }
}
