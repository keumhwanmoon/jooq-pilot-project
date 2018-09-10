package project.pilot.api.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author jason, Moon (jason.moon.kr@gmail.com)
 * @since 2018-09-10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private Integer seq;
    @NotNull
    private Integer articleSeq;
    private String commentContent;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
