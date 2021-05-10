package com.ml.blog;

import com.ml.blog.lucene.ArticleIndex;
import com.ml.blog.vo.ArticleSearchVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class BlogApplicationTests {

    @Resource
    private ArticleIndex articleIndex;

    @Test
    void contextLoads() throws Exception {
        /*ArticleVO articleVO = new ArticleVO();
        articleVO.setArticleId(1);
        articleVO.setTitle("这是标题111");
        articleVO.setDescription("这是摘要111");
        articleVO.setContent("这是正文呀111");
        articleVO.setAuthor("毛龙111");
        articleVO.setUpdateTime(new Date());
        articleVO.setViews(666);
        articleVO.setFirstPicture("www.maolong.site");
        articleIndex.addIndex(articleVO);*/

        List<ArticleSearchVO> list = articleIndex.searchIndex("正文");
        list.forEach(articleVO1 -> {
            System.out.println(articleVO1.toString());
        });
    }

}
