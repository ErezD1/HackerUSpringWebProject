package edu.erezd.erezproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponseDTO {

    private Long id;
    private String content;
    private UserResponseDTO user;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
