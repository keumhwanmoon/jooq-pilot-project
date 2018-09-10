package project.pilot.api.article.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.pilot.api.article.dto.ArticleDTO;
import project.pilot.api.article.service.ArticleService;

import java.util.List;

/**
 * @author jason, Moon
 * @since 2018. 9. 8.
 */
@RestController
public class ArticleRestController {

    private final ArticleService articleService;

    @Autowired
    public ArticleRestController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/article")
    public void postArticle(@RequestBody ArticleDTO request) {
        articleService.insert(request);
    }

    @GetMapping("/article")
    public List<ArticleDTO> getArticles() {
        return articleService.getArticles();
    }
}
