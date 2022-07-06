package com.ik.hhkt.vod.controller;


import com.ik.hhkt.model.vod.Teacher;
import com.ik.hhkt.vod.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author wsh
 * @since 2022-07-05
 */
@RestController
@RequestMapping("/admin/vod/teacher")
@Api(tags = "讲师管理接口")
public class TeacherController {

    @Resource
    private TeacherService teacherService;

    /**
     * 查询所有讲师列表接口
     * @return
     */
    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    @ApiOperation(value = "查询所有讲师列表接口")
    public List getTeacherList(){
        List<Teacher> list = teacherService.list();
        return list;
    }

    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    @ApiOperation(value = "通过id删除讲师")
    public String deleteTeacherById(@ApiParam(name = "id",value = "ID",required = true) @PathVariable String id){
        boolean b = teacherService.removeById(id);
        if (!b){
            return "删除失败";
        }
        return "删除成功";
    }
}

