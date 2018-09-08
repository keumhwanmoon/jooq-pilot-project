package project.pilot.api.article.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author jason, Moon
 * @since 2018. 9. 8.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO {

    private Long seq;
    private String postTitle;
    private String postContent;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
