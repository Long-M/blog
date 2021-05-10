package com.ml.blog.service;

import com.github.pagehelper.PageInfo;
import com.ml.blog.entity.Article;
import com.ml.blog.form.ArticleInsertForm;
import com.ml.blog.form.ArticleUpdateForm;
import com.ml.blog.vo.ArticleSearchVO;
import com.ml.blog.vo.ArticleVO;

import java.util.List;
import java.util.Map;

/**
 * @author Mr.ml
 * @date 2021/1/16
 */
public interface ArticleService {

    /**
     * 保存文章信息
     * @param articleInsertForm
     * @return
     */
    int saveArticle(ArticleInsertForm articleInsertForm, String tagIds);

    /**
     * 根据文章id删除文章信息
     * @param articleId
     * @return
     */
    int removeArticle(Integer articleId);

    /**
     * 根据文章id更新文章信息
     * @param articleUpdateForm
     * @return
     */
    int updateArticle(ArticleUpdateForm articleUpdateForm, String tagIds);

    /**
     * 根据文章id查找文章信息
     * @param articleId
     * @return
     */
    Article getArticle(Integer articleId);

    /**
     * 根据文章id查找文章信息并将其转换成markdown格式
     * @param articleId
     * @return
     */
    ArticleVO getAndConvert(Integer articleId);

    /**
     * 查找所有的文章信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Article> listArticles(Integer pageNum, Integer pageSize);

    /**
     * 根据文章类型查找文章信息
     * @param typeId
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Article> listArticlesByTypeId(Integer typeId, Integer pageNum, Integer pageSize);

    /**
     * 查找具有指定标签的所有的文章信息
     * @param tagId
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Article> listArticlesByTagId(Integer tagId, Integer pageNum, Integer pageSize);

    /**
     * 得到指定数量的推荐文章
     * @param size
     * @return
     */
    PageInfo<Article> listRecommendArticlesTop(Integer size);

    /**
     * 归档博客
     * @return
     */
    Map<String, List<Article>> archiveArticles();

    /**
     * 计算文章数量
     * @return
     */
    int countArticles();

    List<ArticleSearchVO> queryArticle(String keyword);

    int likeArticle(Integer userId, Integer articleId);

    int unlikeArticle(Integer userId, Integer articleId);

    int countArticleLike(Integer articleId);

}
