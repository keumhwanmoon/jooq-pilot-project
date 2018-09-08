package project.pilot.api.article.dao;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.pilot.api.article.dto.ArticleDTO;

import java.util.List;

import static project.pilot.api.jooq.tables.Article.ARTICLE;

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

    public void insert(ArticleDTO request) {
        dslContext
                .insertInto(ARTICLE)
                .columns(ARTICLE.POST_TITLE, ARTICLE.POST_CONTENT)
                .values(request.getPostTitle(), request.getPostContent())
                .execute();
    }

    public List<ArticleDTO> getList() {
        return dslContext
                .selectFrom(ARTICLE)
                .fetchInto(ArticleDTO.class);
    }
}
