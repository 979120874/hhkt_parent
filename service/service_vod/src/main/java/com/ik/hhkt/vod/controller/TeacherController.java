package com.ik.hhkt.vod.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ik.hhkt.model.vod.Teacher;
import com.ik.hhkt.result.Result;
import com.ik.hhkt.vo.vod.TeacherQueryVo;
import com.ik.hhkt.vod.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
@CrossOrigin
public class TeacherController {

    @Resource
    private TeacherService teacherService;

    /**
     * 查询所有讲师列表接口
     *
     * @return
     */
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有讲师列表接口")
    public Result getTeacherList() {
        List<Teacher> list = teacherService.list();
        return Result.ok(list);
    }

    /**
     * 通过id删除讲师
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "通过id删除讲师接口")
    public Result deleteTeacherById(@ApiParam(name = "id", value = "ID", required = true) @PathVariable String id) {
        boolean b = teacherService.removeById(id);
        if (!b) {
            return Result.fail();
        }
        return Result.ok().code(20000);
    }

    @ApiOperation(value = "条件查询分页")
    @PostMapping("findQueryPage/{page}/{limit}")
    public Result findPage(@ApiParam(name = "page",value = "当前页码",required = true) @PathVariable Long page,
                        @ApiParam(name = "limit",value = "每页记录数",required = true) @PathVariable Long limit,
                        @ApiParam(name = "teacherQueryVo",value = "查询对象",required = false) @RequestBody(required = false) TeacherQueryVo teacherQueryVo) {
        Page<Teacher> teacherPage = new Page<>(page, limit);
        String name = teacherQueryVo.getName();
        Integer level = teacherQueryVo.getLevel();
        String joinDateBegin = teacherQueryVo.getJoinDateBegin();
        String joinDateEnd = teacherQueryVo.getJoinDateEnd();
        QueryWrapper queryWrapper = new QueryWrapper();
        if (StringUtils.checkValNotNull(name)) {
            queryWrapper.like("name", name);
        }
        if (StringUtils.checkValNotNull(level)) {
            queryWrapper.eq("level", level);
        }
        if (StringUtils.checkValNotNull(joinDateBegin)) {
            queryWrapper.ge("join_date", joinDateBegin);
        }
        if (StringUtils.checkValNotNull(joinDateEnd)) {
            queryWrapper.le("join_date", joinDateEnd);
        }
        IPage<Teacher> pageModel = teacherService.page(teacherPage, queryWrapper);
        return Result.ok(pageModel).code(20000);
    }

    @ApiOperation(value = "添加讲师接口")
    @PostMapping("addTeacher")
    public Result addTeacher(@ApiParam(name = "teacher",value = "要添加的老师",required = true) @RequestBody Teacher teacher){
        boolean save = teacherService.save(teacher);
        return Result.ok(save);
    }

    @ApiOperation(value = "通过id修改讲师接口")
    @PostMapping("updata/{id}")
    public Result updateTeacher(@ApiParam(name = "id",value = "修改ID",required = true) @PathVariable Long id,
                                @ApiParam(name = "teacher",value = "修改内容",required = true) @RequestBody Teacher teacher){
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        boolean r= teacherService.update(teacher, wrapper);
        if (!r){
            return Result.fail();
        }
        return Result.ok(r);
    }

    @ApiOperation(value = "通过id获取教师信息")
    @GetMapping("get/{id}")
    public Result get(@ApiParam(name = "id",value = "修改ID",required = true) @PathVariable Long id) {
        Teacher teacher = teacherService.getById(id);
        return Result.ok(teacher);
    }

    @ApiOperation(value = "修改教师信息")
    @PostMapping("update")
    public Result updateById(@ApiParam(name = "teacher",value = "修改内容",required = true) @RequestBody Teacher teacher) {
        teacherService.updateById(teacher);
        return Result.ok(null);
    }

    @ApiOperation("批量删除讲师接口")
    @PostMapping("batchRemoveById")
    public Result batchRemoveById(@ApiParam(name = "idList",value = "所需删除的id",required = true) @RequestBody List<Long> idList){
        boolean b = teacherService.removeByIds(idList);
        if (!b){
            return Result.fail();
        }
        return Result.ok(b);
    }
}

