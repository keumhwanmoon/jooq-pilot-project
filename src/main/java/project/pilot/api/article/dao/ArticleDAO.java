package project.pilot.api.article.dao;

import com.google.common.collect.Lists;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.pilot.api.article.dto.ArticleDTO;
import project.pilot.api.comment.dto.CommentDTO;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static project.pilot.api.jooq.tables.Article.ARTICLE;
import static project.pilot.api.jooq.tables.Comment.COMMENT;

/**
 * @author jason, Moon
 * @since 2018. 9. 8.
 */
@Repository
public class ArticleDAO {

    private final DSLContext dslContext;

    @Autowired
    public ArticleDAO(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public List<ArticleDTO> getArticles() {
        Map<Record, Result<Record>> recordResultMap = dslContext
                .select()
                .from(ARTICLE)
                .leftOuterJoin(COMMENT)
                .on(ARTICLE.SEQ.eq(COMMENT.ARTICLE_SEQ))
                .fetch()
                .intoGroups(ARTICLE.fields());

        List<ArticleDTO> articles = Lists.newArrayList();

        recordResultMap.forEach((record, result) -> {
            ArticleDTO article = record.into(ArticleDTO.class);

            List<CommentDTO> comments = Lists.newArrayList();

            result.forEach(r -> {
                if (Objects.nonNull(r.getValue(5))) { // index 5: comment's seq
                    comments.add(r.into(CommentDTO.class));
                }
            });

            article.setComments(comments);

            articles.add(article);
        });

        return articles;
    }

    public void insert(ArticleDTO request) {
        dslContext
                .insertInto(ARTICLE)
                .columns(ARTICLE.POST_TITLE, ARTICLE.POST_CONTENT)
                .values(request.getPostTitle(), request.getPostContent())
                .execute();
    }
}