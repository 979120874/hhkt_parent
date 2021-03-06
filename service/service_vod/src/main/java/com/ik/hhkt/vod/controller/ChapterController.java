package com.ik.hhkt.vod.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ik.hhkt.model.vod.Chapter;
import com.ik.hhkt.result.Result;
import com.ik.hhkt.vo.vod.ChapterVo;
import com.ik.hhkt.vod.service.ChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程章节接口
 * </p>
 *
 * @author wsh
 * @since 2022-07-10
 */
@RestController
@RequestMapping(value="/admin/vod/chapter")
//@CrossOrigin
@Api(tags="课程章节接口")
public class ChapterController {

    @Resource
    private ChapterService chapterService;

    //1 大纲列表（章节和小节列表）
    @ApiOperation("大纲列表")
    @GetMapping("getNestedTreeList/{courseId}")
    public Result getNestedTreeList(@ApiParam(value = "课程ID", required = true)
                                        @PathVariable("courseId") Long courseId){
        List<ChapterVo> nestedTreeList = chapterService.getNestedTreeList(courseId);
        return Result.ok(nestedTreeList);
    }

    //2 添加章节
    @ApiOperation("添加章节")
    @PostMapping("/add")
    public Result save(@RequestBody Chapter chapter){
        chapterService.save(chapter);
        return Result.ok();
    }

    //3 修改-根据id查询
    @ApiOperation("修改-根据id查询")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable Long id){ ;
        Chapter chapter = chapterService.getById(id);
        return Result.ok(chapter);
    }

    //4 修改-最终实现
    @ApiOperation("修改-最终实现")
    @PostMapping("/update")
    public Result update(@RequestBody Chapter chapter){
        chapterService.updateById(chapter);
        return Result.ok(null);
    }

    //5 删除章节
    @ApiOperation("删除章节")
    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable Long id){
        chapterService.removeById(id);
        return Result.ok(null);
    }
}

