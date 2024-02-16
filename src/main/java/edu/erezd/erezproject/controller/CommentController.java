package edu.erezd.erezproject.controller;

<<<<<<< HEAD
public class CommentController {
=======
import edu.erezd.erezproject.dto.CommentCreateDTO;
import edu.erezd.erezproject.dto.CommentResponseDTO;
import edu.erezd.erezproject.dto.CommentUpdateDTO;
import edu.erezd.erezproject.entity.Comment;
import edu.erezd.erezproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{ticketId}")
    public ResponseEntity<CommentResponseDTO> createComment(@PathVariable long ticketId, @RequestBody CommentCreateDTO commentDTO) {
        Comment comment = Comment.builder()
                .content(commentDTO.getContent())
                .build();
        Comment savedComment = commentService.createComment(ticketId, comment);
        CommentResponseDTO responseDTO = mapToCommentResponseDTO(savedComment);
        return ResponseEntity.created(URI.create("/comments/" + responseDTO.getId())).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDTO> updateComment(@PathVariable long id, @RequestBody CommentUpdateDTO commentDTO) {
        Comment comment = Comment.builder()
                .id(id)
                .content(commentDTO.getContent())
                .build();
        Comment updatedComment = commentService.updateComment(id, comment);
        CommentResponseDTO responseDTO = mapToCommentResponseDTO(updatedComment);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }

    // Helper method to map Comment entity to CommentResponseDTO
    private CommentResponseDTO mapToCommentResponseDTO(Comment comment) {
        return CommentResponseDTO.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .build();
    }
>>>>>>> b9077c4 (V1 for Project)
}
