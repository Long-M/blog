package com.ml.blog.controller;

import com.ml.blog.entity.Visitor;
import com.ml.blog.service.VisitorService;
import com.ml.blog.vo.ResultVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Mr.ml
 * @date 2021/3/28
 */
@RestController
@RequestMapping("/visitor")
public class VisitorController {

    @Resource
    private VisitorService visitorService;

    @PostMapping("/insert}")
    public ResultVO insertVisitor(Visitor visitor) {
        visitorService.saveVisitor(visitor);
        return ResultVO.success();
    }

    @DeleteMapping("/delete/{visitorId}")
    public ResultVO deleteVisitor(@PathVariable("visitorId") Integer visitorId) {
        visitorService.removeVisitor(visitorId);
        return ResultVO.success();
    }

    @PutMapping("/update")
    public ResultVO updateVisitor(Visitor visitor) {
        visitorService.updateVisitor(visitor);
        return ResultVO.success();
    }

    @GetMapping("/get/{visitorId}")
    public ResultVO getVisitor(@PathVariable("visitorId") Integer visitorId) {
        return ResultVO.success(visitorService.getVisitor(visitorId));
    }

}
