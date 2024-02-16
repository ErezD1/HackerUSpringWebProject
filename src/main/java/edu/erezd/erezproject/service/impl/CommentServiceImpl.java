package edu.erezd.erezproject.service.impl;

import edu.erezd.erezproject.entity.Comment;
import edu.erezd.erezproject.entity.Ticket;
import edu.erezd.erezproject.exception.ResourceNotFoundException;
import edu.erezd.erezproject.repository.CommentRepository;
import edu.erezd.erezproject.repository.TicketRepository;
import edu.erezd.erezproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final TicketRepository ticketRepository;

    @Override
    public Comment createComment(long ticketId, Comment comment) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket", "id", ticketId));
        comment.setTicket(ticket); // Set the ticket for the comment
        return commentRepository.save(comment); // Save the comment
    }

    @Override
    public Comment updateComment(long id, Comment comment) {
        Comment existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", id));
        existingComment.setContent(comment.getContent()); // Update the content of the existing comment
        return commentRepository.save(existingComment); // Save the updated comment
    }

    @Override
    public void deleteComment(long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", id));
        commentRepository.delete(comment); // Delete the comment
    }
}