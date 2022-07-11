package com.ik.hhkt.vod.controller;


import com.ik.hhkt.model.vod.Course;
import com.ik.hhkt.result.Result;
import com.ik.hhkt.vo.vod.CourseFormVo;
import com.ik.hhkt.vo.vod.CoursePublishVo;
import com.ik.hhkt.vo.vod.CourseQueryVo;
import com.ik.hhkt.vo.vod.CourseVo;
import com.ik.hhkt.vod.service.CourseService;
import com.sun.xml.bind.v2.model.core.ID;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author wsh
 * @since 2022-07-10
 */
@Api(tags = "课程管理接口")
@RestController
@RequestMapping(value = "/admin/vod/course")
@CrossOrigin
public class CourseController {
    @Resource
    private CourseService courseService;

    @ApiOperation("点播课程列表")
    @PostMapping("{page}/{limit}")
    public Result courseList(@ApiParam(name = "page", value = "页码", defaultValue = "0", required = true) @PathVariable("page") Long page,
                             @ApiParam(name = "limit", value = "每页数据", required = true) @PathVariable("limit") Long limit,
                             @ApiParam(name = "courseQueryVo", value = "查询条件", required = true) @RequestBody CourseQueryVo courseQueryVo) {
        Map<String, Object> map = courseService.findPage(page, limit, courseQueryVo);
        return Result.ok(map);
    }

    //添加课程基本信息
    @ApiOperation(value = "新增课程信息")
    @PostMapping("save")
    public Result save(@RequestBody CourseFormVo courseFormVo) {
        Long courseId = courseService.saveCourseInfo(courseFormVo);
        return Result.ok(courseId);
    }

    //根据id获取课程信息
    @ApiOperation("根据ID获取课程信息")
    @GetMapping("/get/{id}")
    public Result getCourseInfo(@ApiParam(name = "id",value = "课程ID",required = true)
                                    @PathVariable("id") Long id){
        CourseFormVo courseInfo = courseService.getCourseInfo(id);
        return Result.ok(courseInfo);
    }
    //修改课程信息
    @ApiOperation("修改课程信息")
    @PostMapping("/update")
    public Result updateCourseInfo(@ApiParam(name = "courseInfo",value = "课程信息",required = true)
                                               @RequestBody CourseFormVo courseInfo){
        Long i=courseService.updateCourseInfo(courseInfo);
        if (i<=0){
            Result.fail();
        }
        return Result.ok(courseInfo.getId());
    }

    //课程最终发布接口
    @ApiOperation("根据id获取课程发布信息")
    @GetMapping("getCoursePublishVo/{id}")
    public Result getCoursePublishVo(@ApiParam(name = "id",value = "课程id",required = true) @PathVariable Long id){
        CoursePublishVo coursePublishVo = courseService.getCoursePublishVo(id);
        return Result.ok(coursePublishVo);
    }
    @ApiOperation("根据id发布课程,发布课程按钮")
    @PutMapping("publishCourseById/{id}")
    public Result publishCourseById(@ApiParam(name = "id",value = "课程id",required = true) @PathVariable Long id){
        Boolean aBoolean = courseService.publishCourseById(id);
        return Result.ok(aBoolean);
    }
    @ApiOperation(value = "删除课程")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        courseService.removeCourseById(id);
        return Result.ok();
    }
}

