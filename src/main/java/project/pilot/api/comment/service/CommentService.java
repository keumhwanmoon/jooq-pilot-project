package project.pilot.api.comment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.pilot.api.comment.dao.CommentDAO;
import project.pilot.api.comment.dto.CommentDTO;

import java.util.List;

/**
 * @author jason, Moon (jason.moon.kr@gmail.com)
 * @since 2018-09-10
 */
@Service
public class CommentService {
    private CommentDAO commentDAO;

    @Autowired
    public CommentService(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    public void postComment(CommentDTO request) {
        commentDAO.postComment(request);
    }

    public List<CommentDTO> getComments(Integer articleSeq) {
        return commentDAO.getComments(articleSeq);
    }
}
