package edu.erezd.erezproject.repository;


import edu.erezd.erezproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {


}
