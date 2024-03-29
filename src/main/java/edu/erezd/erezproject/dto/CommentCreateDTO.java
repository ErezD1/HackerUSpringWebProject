package edu.erezd.erezproject.dto;

import edu.erezd.erezproject.entity.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentCreateDTO {

    @NotNull
    @Size(min = 2)
    private String content;


}