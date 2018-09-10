package project.pilot.api.article.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.pilot.api.comment.dto.CommentDTO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author jason, Moon
 * @since 2018. 9. 8.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO {

    private Integer seq;
    private String postTitle;
    private String postContent;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private List<CommentDTO> comments;
}
