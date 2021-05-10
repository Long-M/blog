package com.ml.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ml.blog.enums.RedisKeyEnum;
import com.ml.blog.exception.*;
import com.ml.blog.form.ArticleInsertForm;
import com.ml.blog.entity.Article;
import com.ml.blog.entity.ArticleContent;
import com.ml.blog.entity.ArticleTag;
import com.ml.blog.lucene.ArticleIndex;
import com.ml.blog.form.ArticleUpdateForm;
import com.ml.blog.mapper.ArticleContentMapper;
import com.ml.blog.mapper.ArticleMapper;
import com.ml.blog.mapper.ArticleTagMapper;
import com.ml.blog.service.ArticleService;
import com.ml.blog.service.AsynchronousService;
import com.ml.blog.util.FastJSONUtils;
import com.ml.blog.util.MarkdownUtils;
import com.ml.blog.util.RedisUtils;
import com.ml.blog.vo.ArticleSearchVO;
import com.ml.blog.vo.ArticleVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author Mr.ml
 * @date 2021/1/16
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private ArticleContentMapper articleContentMapper;
    @Resource
    private ArticleTagMapper articleTagMapper;
    @Resource
    private ArticleIndex articleIndex;
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private AsynchronousService asynchronousService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int saveArticle(ArticleInsertForm articleInsertForm, String tagIds) {
        Article article = new Article();

        BeanUtils.copyProperties(articleInsertForm, article);
        article.setCreateTime(new Date());
        article.setUpdateTime(new Date());
        article.setViews(0);
        article.setUpdateTime(new Date());

        int res = articleMapper.insertArticle(article);
        try {
            // 发布成功后，将博客的相关信息放到lucene索引库中
            //articleIndex.addIndex(article);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Integer articleId = article.getArticleId();
        String[] tags = tagIds.split(",");
        for (String tagId : tags) {
            ArticleTag articleTag = new ArticleTag(articleId, Integer.parseInt(tagId));
            res = articleTagMapper.insertArticleTag(articleTag);
            if (res == -1) {
                break;
            }
        }
        if (res == -1) {
            throw new InsertException("");
        }
        return res;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int removeArticle(Integer articleId) {
        String key = "article" + articleId;
        if (redisUtils.hasKey(key)) {
            redisUtils.del(key);
        }

        int res = articleTagMapper.deleteArticleTagByArticleId(articleId);
        res = res | articleMapper.deleteArticle(articleId);

        if (res == -1) {
            throw new DeleteException("");
        }
        return res;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateArticle(ArticleUpdateForm articleUpdateForm, String tagIds) {
        Integer articleId = articleUpdateForm.getArticleId();
        String key = "article" + articleId;
        if (redisUtils.hasKey(key)) {
            redisUtils.del(key);
        }
        Article article = articleMapper.getArticle(articleId);
        if (article == null) {
            throw new NotFoundException("该博客不存在");
        }

        // 修改后的标签id集合
        HashSet<String> newTagIdSet = new HashSet<>(Arrays.asList(tagIds.split(",")));
        // 修改前的标签id集合
        HashSet<Object> oldTagIdSet = new HashSet<>();
        List<ArticleTag> articleTags = articleTagMapper.listArticleTagByArticleId(articleId);
        articleTags.forEach(articleTag -> {
            // 旧集合中的元素未出现在新集合中
            if (!newTagIdSet.contains(articleTag.getTagId())) {
                articleTagMapper.deleteArticleTag(articleTag.getArticleTagId());
            }
            // 初始化旧集合
            oldTagIdSet.add(articleTag.getTagId());
        });
        // 新旧集合取差集
        newTagIdSet.removeAll(oldTagIdSet);

        newTagIdSet.forEach(newTagId -> {
            ArticleTag articleTag = new ArticleTag(articleId, Integer.parseInt(newTagId));
            articleTagMapper.insertArticleTag(articleTag);
        });

        article.setUpdateTime(new Date());
        int res = articleMapper.updateArticle(article);
        if (res == -1) {
            throw new UpdateException("");
        }
        return res;
    }

    @Override
    public Article getArticle(Integer articleId) {
        String key = "article" + articleId;
        if (redisUtils.hasKey(key)) {
            return (Article) redisUtils.get(key);
        }
        Article article = articleMapper.getArticle(articleId);
        redisUtils.set(key, article, 5 * 60);
        return article;
    }

    @Transactional
    @Override
    public ArticleVO getAndConvert(Integer articleId) {
        Article article = articleMapper.getArticle(articleId);
        if (article == null) {
            throw new NotFoundException("该博客不存在");
        }
        ArticleContent articleContent = articleContentMapper.getArticleContent(article.getArticleContentId());
        ArticleVO articleVO = new ArticleVO();
        BeanUtils.copyProperties(article, articleVO);
        String content = articleContent.getArticleContent();
        articleVO.setContent(MarkdownUtils.markdownToHtmlExtensions(content));

        //articleMapper.updateViews(articleId);
        asynchronousService.updateViews(articleId);

        return articleVO;
    }

    @Override
    public PageInfo<Article> listArticles(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize, "update_time desc");
        List<Article> articles = articleMapper.listArticles();
        PageInfo<Article> articlePageInfo = new PageInfo<Article>(articles);
        return articlePageInfo;
    }

    @Override
    public PageInfo<Article> listArticlesByTypeId(Integer typeId, Integer pageNum, Integer pageSize) {
        if (typeId == -1) {
            typeId = 1;
        }
        PageHelper.startPage(pageNum, pageSize, "update_time desc");
        List<Article> articlePageInfo = articleMapper.listArticlesByTypeId(typeId);
        return new PageInfo<>(articlePageInfo);
    }

    @Override
    public PageInfo<Article> listArticlesByTagId(Integer tagId, Integer pageNum, Integer pageSize) {
        if (tagId == -1) {
            tagId = 1;
        }
        List<ArticleTag> articleTags = articleTagMapper.listArticleTagByTagId(tagId);
        List<Article> articles = new ArrayList<>();
        articleTags.forEach(articleTag -> {
            Article article = articleMapper.getArticle(articleTag.getArticleId());
            articles.add(article);
        });
        if (articles == null) {
            throw new SelectException("");
        }
        return new PageInfo<>(articles);
    }

    @Override
    public PageInfo<Article> listRecommendArticlesTop(Integer size) {
        PageHelper.startPage(1, size, "update_time desc");
        List<Article> articles = articleMapper.listRecommendArticlesTop();
        if (articles == null) {
            throw new SelectException("");
        }
        return new PageInfo<>(articles);
    }

    @Override
    public Map<String, List<Article>> archiveArticles() {
        List<String> years = articleMapper.listGroupYear();
        Map<String, List<Article>> map = new HashMap<>();
        for (String year : years) {
            map.put(year, articleMapper.listArticlesByYear(year));
        }
        return map;
    }

    @Override
    public int countArticles() {
        return articleMapper.count();
    }

    @Override
    public List<ArticleSearchVO> queryArticle(String keyword) {
        List<ArticleSearchVO> articleSearchVOs = null;
        try {
            // 调用查询博客的方法
            articleSearchVOs = articleIndex.searchIndex(keyword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articleSearchVOs;
    }

    @Override
    public int likeArticle(Integer userId, Integer articleId) {
        synchronized (this) {
            // 获取用户点赞的博客列表
            String articleIds = (String) redisUtils.hget(RedisKeyEnum.USER_LIKE_ARTICLE_KEY.name(), String.valueOf(userId));
            Set<Integer> articleIdSet = new HashSet<>();
            if (articleIds != null) {
                articleIdSet = FastJSONUtils.deserializeToSet(articleIds, Integer.class);
            }
            articleIdSet.add(articleId);
            redisUtils.hset(RedisKeyEnum.USER_LIKE_ARTICLE_KEY.name(), String.valueOf(userId), FastJSONUtils.serialize(articleIdSet));

            // 获取点赞了博客的用户列表
            String userIds = (String) redisUtils.hget(RedisKeyEnum.ARTICLE_LIKED_USER_KEY.name(), String.valueOf(articleId));
            Set<Integer> userIdSet = new HashSet<>();
            if (userIds != null) {
                userIdSet = FastJSONUtils.deserializeToSet(userIds, Integer.class);
            }
            userIdSet.add(articleId);
            redisUtils.hset(RedisKeyEnum.ARTICLE_LIKED_USER_KEY.name(), String.valueOf(articleId), FastJSONUtils.serialize(userIdSet));
            return 1;
        }
    }

    @Override
    public int unlikeArticle(Integer userId, Integer articleId) {
        synchronized (this) {
            // 获取用户点赞的博客列表
            String articleIds = (String) redisUtils.hget(RedisKeyEnum.USER_LIKE_ARTICLE_KEY.name(), String.valueOf(userId));
            Set<Integer> articleIdSet = new HashSet<>();
            if (articleIds != null) {
                articleIdSet = FastJSONUtils.deserializeToSet(articleIds, Integer.class);
            }
            articleIdSet.remove(articleId);
            redisUtils.hset(RedisKeyEnum.USER_LIKE_ARTICLE_KEY.name(), String.valueOf(userId), FastJSONUtils.serialize(articleIdSet));

            // 获取点赞了博客的用户列表
            String userIds = (String) redisUtils.hget(RedisKeyEnum.ARTICLE_LIKED_USER_KEY.name(), String.valueOf(articleId));
            Set<Integer> userIdSet = new HashSet<>();
            if (userIds != null) {
                userIdSet = FastJSONUtils.deserializeToSet(userIds, Integer.class);
            }
            userIdSet.remove(articleId);
            redisUtils.hset(RedisKeyEnum.ARTICLE_LIKED_USER_KEY.name(), String.valueOf(articleId), FastJSONUtils.serialize(userIdSet));
            return 1;
        }
    }

    @Override
    public int countArticleLike(Integer articleId) {
        synchronized (this) {
            String userIds = (String) redisUtils.hget(RedisKeyEnum.ARTICLE_LIKED_USER_KEY.name(), String.valueOf(articleId));
            Set<Integer> userIdSet = new HashSet<>();
            if (userIds != null) {
                userIdSet = FastJSONUtils.deserializeToSet(userIds, Integer.class);
            }
            int count = 0;
            if (userIdSet != null) {
                count = userIdSet.size();
            }
            return count;
        }
    }

}
