package project.pilot.api.comment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.pilot.api.comment.dto.CommentDTO;
import project.pilot.api.comment.service.CommentService;

import javax.validation.Valid;
import java.util.List;

/**
 * @author jason, Moon (jason.moon.kr@gmail.com)
 * @since 2018-09-10
 */
@RestController
public class CommentRestController {
    private CommentService commentService;

    @Autowired
    public CommentRestController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comment")
    public void postComment(@Valid @RequestBody CommentDTO request) {
        commentService.postComment(request);
    }

    @GetMapping("/comment")
    public List<CommentDTO> getComments(@RequestParam Integer articleSeq) {
        return commentService.getComments(articleSeq);
    }
}
