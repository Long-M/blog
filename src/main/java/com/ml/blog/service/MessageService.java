package com.ml.blog.service;

import com.github.pagehelper.PageInfo;
import com.ml.blog.entity.Message;

/**
 * @author Mr.ml
 * @date 2021/1/16
 */
public interface MessageService {

    int saveMessage(Message message);

    int removeMessage(Integer messageId);

    int updateMessage(Message message);

    Message getMessage(Integer messageId);

    PageInfo<Message> listMessages(Integer pageNum, Integer pageSize);

}
