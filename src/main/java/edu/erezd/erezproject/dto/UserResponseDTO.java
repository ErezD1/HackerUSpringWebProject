package edu.erezd.erezproject.dto;

import edu.erezd.erezproject.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO {

    private long id;
    private String username;
    private String email;
    private Role role;

}
