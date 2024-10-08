package org.proleesh.repository;

import org.proleesh.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author sung-hyuklee
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByCommentedUserId(Long userId);
}
