package com.example.board.repository.querydsl;

import com.example.board.domain.Article;
import com.example.board.domain.QArticle;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ArticleRepositoryCustomImpl extends QuerydslRepositorySupport implements ArticleRepositoryCustom {

    public ArticleRepositoryCustomImpl() {
        super(Article.class);
    }

    @Override
    public List<String> findAllDistinctHashtags() {
        QArticle article = QArticle.article; //길어지는것을 방지하기 위해 꺼내온것임

        //from은 QuerydslRepositorySupport에서 제공해주는 기능
        //JPQL쿼리랑 QuerydslRepositorySupport를 이용할때는 from부터 시작
        return from(article)
                .distinct()
                .select(article.hashtag)
                .where(article.hashtag.isNotNull())
                .fetch();
    }
}
