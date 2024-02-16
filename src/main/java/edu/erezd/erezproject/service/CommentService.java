package edu.erezd.erezproject.service;

import edu.erezd.erezproject.entity.Comment;

public interface CommentService {
    Comment createComment (long ticketId, Comment comment);

    Comment updateComment (long id, Comment comment);

    void deleteComment(long id);
}
