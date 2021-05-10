package com.ml.blog.controller;

import com.ml.blog.entity.Message;
import com.ml.blog.service.MessageService;
import com.ml.blog.vo.ResultVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Mr.ml
 * @date 2021/3/28
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Resource
    private MessageService messageService;

    @PostMapping("/insert}")
    public ResultVO insertMessage(Message message) {
        messageService.saveMessage(message);
        return ResultVO.success();
    }

    @DeleteMapping("/delete/{messageId}")
    public ResultVO deleteMessage(@PathVariable("messageId") Integer messageId) {
        messageService.removeMessage(messageId);
        return ResultVO.success();
    }

    @PutMapping("/update")
    public ResultVO updateMessage(Message message) {
        messageService.updateMessage(message);
        return ResultVO.success();
    }

    @GetMapping("/get/{messageId}")
    public ResultVO getMessage(@PathVariable("messageId") Integer messageId) {
        messageService.getMessage(messageId);
        return ResultVO.success();
    }

}
