package com.ik.hhkt.vod.controller;


import com.ik.hhkt.model.vod.Subject;
import com.ik.hhkt.result.Result;
import com.ik.hhkt.vod.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author wsh
 * @since 2022-07-08
 */
@RestController
@RequestMapping("admin/vod/subject")
@Api(tags = "课程分类管理")
//@CrossOrigin
public class SubjectController {

    @Resource
    private SubjectService subjectService;

    //查询下一层课程分类
    //根据parent_id
    @RequestMapping("/getChildSubject/{id}")
    @ApiOperation(value = "查询下一层的课程分类")
    public Result getChildSubject(@PathVariable("id") Long id){
        List<Subject> subjects=subjectService.findChildSubject(id);
        return Result.ok(subjects);
    }

    @ApiOperation(value="使用EasyExcel导出")
    @GetMapping(value = "/exportData")
    public void exportData(HttpServletResponse response) {
        subjectService.exportData(response);
    }

    @ApiOperation(value="使用EasyExcel导入")
    @PostMapping(value = "/importData")
    public Result importData(MultipartFile file) {
        subjectService.importData(file);
        return Result.ok();
    }
}

