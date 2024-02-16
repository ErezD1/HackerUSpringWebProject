package edu.erezd.erezproject.dto;

import edu.erezd.erezproject.entity.Status;
import edu.erezd.erezproject.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketResponseDTO {

    private Long id;

    private String subject;

    private String description;

    private Status status;

    private User user;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private List<CommentResponseDTO> comments;

    private String closingComment;

}
