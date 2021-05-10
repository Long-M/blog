package com.ml.blog.mapper;

import com.ml.blog.entity.Message;

import java.util.List;

public interface MessageMapper {
    int insertMessage(Message message);

    int deleteMessage(Integer messageId);

    int updateMessage(Message message);

    Message getMessage(Integer messageId);

    List<Message> listMessages();
}