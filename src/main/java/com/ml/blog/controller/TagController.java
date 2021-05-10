package com.ml.blog.controller;

import com.ml.blog.entity.Tag;
import com.ml.blog.service.TagService;
import com.ml.blog.vo.ResultVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Mr.ml
 * @date 2021/3/28
 */
@RestController
@RequestMapping("/tag")
public class TagController {

    @Resource
    private TagService tagService;

    @PostMapping("/insert}")
    public ResultVO insertTag(Tag tag) {
        tagService.saveTag(tag);
        return ResultVO.success();
    }

    @DeleteMapping("/delete/{tagId}")
    public ResultVO deleteTag(@PathVariable("tagId") Integer tagId) {
        tagService.removeTag(tagId);
        return ResultVO.success();
    }

    @PutMapping("/update")
    public ResultVO updateTag(Tag tag) {
        tagService.updateTag(tag);
        return ResultVO.success();
    }

    @GetMapping("/get/{tagId}")
    public ResultVO getTag(@PathVariable("tagId") Integer tagId) {
        return ResultVO.success(tagService.getTag(tagId));
    }

}
