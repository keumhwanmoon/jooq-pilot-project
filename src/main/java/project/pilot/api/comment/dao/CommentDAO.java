package project.pilot.api.comment.dao;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.pilot.api.comment.dto.CommentDTO;

import static project.pilot.api.jooq.tables.Comment.COMMENT;

/**
 * @author jason, Moon (jason.moon.kr@gmail.com)
 * @since 2018-09-10
 */
@Repository
public class CommentDAO {
    private final DSLContext dslContext;

    @Autowired
    public CommentDAO(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public void postComment(CommentDTO request) {
        dslContext
                .insertInto(COMMENT)
                .columns(COMMENT.ARTICLE_SEQ, COMMENT.COMMENT_CONTENT)
                .values(request.getArticleSeq(), request.getCommentContent())
                .execute();
    }
}
