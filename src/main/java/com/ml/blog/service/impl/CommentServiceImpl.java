package com.ml.blog.service.impl;

import com.ml.blog.enums.ResultCodeEnum;
import com.ml.blog.exception.InsertException;
import com.ml.blog.exception.SelectException;
import com.ml.blog.mapper.CommentMapper;
import com.ml.blog.entity.Comment;
import com.ml.blog.service.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Mr.ml
 * @date 2021/1/16
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;
    private List<Comment> comments = new ArrayList<>();

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int saveComment(Comment comment) {
        Integer parentCommentId = comment.getParentCommentId();
        if (parentCommentId == -1) {
            comment.setParentCommentId(0);
        } else {
            comment.setParentCommentId(parentCommentId);
        }
        comment.setCreateTime(new Date());
        int res = commentMapper.insertComment(comment);
        if (res == -1) {
            throw new InsertException(ResultCodeEnum.INSERT_FAIL, "评论新增失败");
        }
        return res;
    }

    @Override
    public List<Comment> listCommentsByArticleId(Integer articleId) {
        //PageHelper.startPage("create_time");
        comments = commentMapper.listCommentsByArticleId(articleId);
        comments = buildTree();
        if (comments == null) {
            throw new SelectException(ResultCodeEnum.SELECT_FAIL, "评论查询失败");
        }
        return comments;
    }

    /**
     * 建立树形结构
     * @return
     */
    public List<Comment> buildTree() {
        // 用来存储处理好的数据
        List<Comment> treeMenus = new ArrayList<>();
        // 先利用getRootNode方法获取根节点，
        // 然后根据根节点调用递归方法获取到各个根节点对应的字节点，将其放进上面的List中去
        for (Comment rootComment : getRootNode(comments)) {
            rootComment = buildChildTree(rootComment);
            treeMenus.add(rootComment);
        }
        return treeMenus;
    }

    /**
     * 递归，建立子树形结构
     * @param comment
     * @return
     */
    private Comment buildChildTree(Comment comment) {
        // 用来存放子节点集合
        List<Comment> replyComment = new ArrayList<>();
        // 根据根节点，遍历父子节点是根节点的节点出来。然后放进上面的集合中去
        // 这里递归调用add方法。
        for (Comment c : comments) {
            if (c.getParentCommentId().equals(comment.getCommentId())) {
                replyComment.add(buildChildTree(c));
            }
        }
        // 最后将递归得到的所有子节点放进根节点的children的属性中去
        comment.setReplyComments(replyComment);
        return comment;
    }

    /**
     * 获取根节点
     * @return
     */
    private List<Comment> getRootNode(List<Comment> comments) {
        List<Comment> rootComments = new ArrayList<>();
        for (Comment comment : comments) {
            if (comment.getParentCommentId() == 0) {
                rootComments.add(comment);
            }
        }
        return rootComments;
    }

}
