package edu.erezd.erezproject.dto;

import edu.erezd.erezproject.entity.Role;
import edu.erezd.erezproject.entity.Status;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketCreateDTO {

    @NotNull
    @Size(min = 2, max =128)
    private String subject;

    @NotNull
    @Size(min = 2)
    private String description;

    private Status status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
