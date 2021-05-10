package com.ml.blog.controller;

import com.ml.blog.entity.Type;
import com.ml.blog.service.TypeService;
import com.ml.blog.vo.ResultVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Mr.ml
 * @date 2021/3/28
 */
@RestController
@RequestMapping("/type")
public class TypeController {

    @Resource
    private TypeService typeService;

    @PostMapping("/insert}")
    public ResultVO insertType(Type type) {
        typeService.saveType(type);
        return ResultVO.success();
    }

    @DeleteMapping("/delete/{typeId}")
    public ResultVO deleteType(@PathVariable("typeId") Integer typeId) {
        typeService.removeType(typeId);
        return ResultVO.success();
    }

    @PutMapping("/update")
    public ResultVO updateType(Type type) {
        typeService.updateType(type);
        return ResultVO.success();
    }

    @GetMapping("/get/{typeId}")
    public ResultVO getType(@PathVariable("typeId") Integer typeId) {
        typeService.getType(typeId);
        return ResultVO.success();
    }

}
