package com.ik.hhkt.vod.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ik.hhkt.model.vod.Video;
import com.ik.hhkt.vod.mapper.VideoMapper;
import com.ik.hhkt.vod.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ik.hhkt.vod.service.VodService;
import org.springframework.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author wsh
 * @since 2022-07-10
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Resource
    VodService vodService;
    @Resource
    VideoMapper videoMapper;

    //根据课程id删除小节
    @Override
    public void removeVideoByCourseId(Long courseId) {
        //删除小节中的视频
        //根据课程id获取课程里面所有小节
        //通过课程id查看当前课程下面所有的小节
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id",courseId);
        List<Video> videoList = baseMapper.selectList(videoQueryWrapper);
        //拿到前课程下面所有视频的  云点播id
        //List<String> videoSourceIdList = list.stream().map(Video::getVideoSourceId).collect(Collectors.toList());
        for (Video video : videoList) {
            if (!StringUtils.isEmpty(video.getVideoSourceId())){
                //删除小节下的视频
                vodService.deleteVideo(video.getVideoSourceId());
                //清空video表中videoSourceId字段的内容
                videoMapper.updateVideoSourceIdAndName(video.getId());
            }
        }
        //2.根据课程删除小节
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);

    }

    //根据小节id删除小节删除视频
    @Override
    public void removeVideoById(Long id) {
        //通过id查询小节信息
        Video video = videoMapper.selectById(id);
        System.out.println("getVideoSourceId="+video.getVideoSourceId());
        if (!StringUtils.isEmpty(video.getVideoSourceId())){
            //清空video表中videoSourceId字段的内容
            videoMapper.updateVideoSourceIdAndName(id);
            //如果视频id不为空，调用方法删除
            vodService.deleteVideo(video.getVideoSourceId());
        }
        videoMapper.deleteById(id);
    }



}
