package com.ik.hhkt.vod.service;

import com.ik.hhkt.model.vod.Video;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author wsh
 * @since 2022-07-10
 */
public interface VideoService extends IService<Video> {
    //根据课程id删除小节
    void removeVideoByCourseId(Long courseId);
    //根据小节id删除小节删除视频
    void removeVideoById(Long id);

}
