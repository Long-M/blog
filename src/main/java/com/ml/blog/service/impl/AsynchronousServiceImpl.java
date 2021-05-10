package com.ml.blog.service.impl;

import com.ml.blog.enums.RedisKeyEnum;
import com.ml.blog.mapper.ArticleMapper;
import com.ml.blog.service.AsynchronousService;
import com.ml.blog.util.RedisUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.Future;

@Service
public class AsynchronousServiceImpl implements AsynchronousService {

    @Resource
    private RedisUtils redisUtils;
    @Resource
    private ArticleMapper articleMapper;

    @Async
    @Override
    public Future updateViews(Integer articleId) {
        String key = RedisKeyEnum.ARTICLE_VIEW_COUNT.name() + articleId;
        if (!redisUtils.hasKey(key)) {
            Integer views = articleMapper.getArticle(articleId).getViews();
            redisUtils.set(key, views);
        }
        long res = redisUtils.incr(key, 1);
        return new AsyncResult(res);
    }

}
