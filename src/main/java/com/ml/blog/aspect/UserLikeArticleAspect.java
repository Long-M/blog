package com.ml.blog.aspect;

import com.ml.blog.enums.RedisKeyEnum;
import com.ml.blog.enums.ResultCodeEnum;
import com.ml.blog.util.FastJSONUtils;
import com.ml.blog.util.RedisUtils;
import com.ml.blog.vo.ResultVO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;

@Aspect
@Component
public class UserLikeArticleAspect {

    @Resource
    private RedisUtils redisUtils;

    @Pointcut("execution(* com.ml.blog.controller.ArticleController.likeArticle(..))")
    public void like() {

    }

    @Pointcut("execution(* com.ml.blog.controller.ArticleController.unlikeArticle(..))")
    public void unlike() {

    }

    @Around("like()")
    public ResultVO doAroundLike(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        String userId = String.valueOf(args[0]);
        String articleId = String.valueOf(args[1]);

        String articleIds = (String) redisUtils.hget(RedisKeyEnum.USER_LIKE_ARTICLE_KEY.name(), userId);
        String userIds = (String) redisUtils.hget(RedisKeyEnum.ARTICLE_LIKED_USER_KEY.name(), articleId);
        Set<Integer> articleIdSet = FastJSONUtils.deserializeToSet(articleIds, Integer.class);
        Set<Integer> userIdSet = FastJSONUtils.deserializeToSet(userIds, Integer.class);

        if (articleIdSet != null && articleIdSet.contains(Integer.valueOf(articleId))
                || userIdSet != null && userIdSet.contains(Integer.valueOf(userId))) {
            return ResultVO.error(ResultCodeEnum.FAIL);
        }

        return (ResultVO) proceedingJoinPoint.proceed();
    }

    @Around("unlike()")
    public ResultVO doAroundUnlike(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        String userId = String.valueOf(args[0]);
        String articleId = String.valueOf(args[1]);

        String articleIds = (String) redisUtils.hget(RedisKeyEnum.USER_LIKE_ARTICLE_KEY.name(), userId);
        String userIds = (String) redisUtils.hget(RedisKeyEnum.ARTICLE_LIKED_USER_KEY.name(), articleId);
        Set<Integer> articleIdSet = FastJSONUtils.deserializeToSet(articleIds, Integer.class);
        Set<Integer> userIdSet = FastJSONUtils.deserializeToSet(userIds, Integer.class);

        if (articleIdSet == null || !articleIdSet.contains(Integer.valueOf(articleId))
                || userIdSet == null || !userIdSet.contains(Integer.valueOf(userId))) {
            return ResultVO.error(ResultCodeEnum.FAIL);
        }

        return (ResultVO) proceedingJoinPoint.proceed();
    }

}
