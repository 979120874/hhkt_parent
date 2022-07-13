package com.ik.hhkt.vod.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ik.hhkt.model.vod.VideoVisitor;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 视频来访者记录表 服务类
 * </p>
 *
 * @author wsh
 * @since 2022-07-11
 */
public interface VideoVisitorService extends IService<VideoVisitor> {

    //获取某段时间内登录所访问该视频的总人数
    Map findCount(Long courseId, String startDate, String endDate);
}
