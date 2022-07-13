package com.ik.hhkt.vod.controller;


import com.ik.hhkt.result.Result;
import com.ik.hhkt.vod.service.VideoVisitorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 视频来访者记录表 前端控制器
 * </p>
 *
 * @author wsh
 * @since 2022-07-11
 */
@Api(value = "VideoVisitor管理", tags = "VideoVisitor管理")
@RestController
@RequestMapping(value="/admin/vod/videoVisitor")
@CrossOrigin
public class VideoVisitorController {

    @Resource
    private VideoVisitorService videoVisitorService;

    //获取某段时间内登录所访问该视频的总人数
    @ApiOperation("显示统计数据")
    @GetMapping("findCount/{courseId}/{startDate}/{endDate}")
    public Result showChart(@ApiParam("课程ID") @PathVariable Long courseId,
                            @ApiParam("开始时间") @PathVariable String startDate,
                            @ApiParam("结束时间") @PathVariable String endDate){
        Map map = videoVisitorService.findCount(courseId, startDate, endDate);
        return Result.ok(map);
    }
}

