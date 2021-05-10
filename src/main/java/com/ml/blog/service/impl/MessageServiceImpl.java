package com.ml.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ml.blog.entity.Message;
import com.ml.blog.exception.DeleteException;
import com.ml.blog.exception.InsertException;
import com.ml.blog.exception.SelectException;
import com.ml.blog.exception.UpdateException;
import com.ml.blog.mapper.MessageMapper;
import com.ml.blog.service.MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Mr.ml
 * @date 2021/1/16
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    private MessageMapper messageMapper;

    @Override
    public int saveMessage(Message message) {
        int res = messageMapper.insertMessage(message);
        if (res == -1) {
            throw new InsertException("");
        }
        return res;
    }

    @Override
    public int removeMessage(Integer id) {
        int res = messageMapper.deleteMessage(id);
        if (res == -1) {
            throw new DeleteException("");
        }
        return res;
    }

    @Override
    public int updateMessage(Message message) {
        int res = messageMapper.updateMessage(message);
        if (res == -1) {
            throw new UpdateException("");
        }
        return res;
    }

    @Override
    public Message getMessage(Integer messageId) {
        Message message = messageMapper.getMessage(messageId);
        if (message == null) {
            throw new SelectException("");
        }
        return message;
    }

    @Override
    public PageInfo<Message> listMessages(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize, "create_time desc");
        List<Message> messages = messageMapper.listMessages();
        if (messages == null) {
            throw new SelectException("");
        }
        return new PageInfo<>(messages);
    }

}
