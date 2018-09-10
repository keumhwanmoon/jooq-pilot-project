package project.pilot.api.article.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.pilot.api.article.dao.ArticleDAO;
import project.pilot.api.article.dto.ArticleDTO;

import java.util.List;

/**
 * @author jason, Moon
 * @since 2018. 9. 8.
 */
@Service
public class ArticleService {

    private final ArticleDAO articleDAO;

    @Autowired
    public ArticleService(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    public void insert(ArticleDTO request) {
        articleDAO.insert(request);
    }

    public List<ArticleDTO> getArticles() {
        return articleDAO.getArticles();
    }
}
