package com.ik.hhkt.vod.service.impl;

import com.ik.hhkt.model.vod.Video;
import com.ik.hhkt.vod.mapper.VideoMapper;
import com.ik.hhkt.vod.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
