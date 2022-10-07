package com.example.judesys.contracts;

import com.example.judesys.models.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoleRequest {
    private String roleName;

    public Role getRole(){
        return new Role(this.roleName);
    }
}
