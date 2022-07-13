package com.ik.hhkt.vod.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ik.hhkt.model.vod.VideoVisitor;
import com.ik.hhkt.vo.vod.VideoVisitorCountVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 视频来访者记录表 Mapper 接口
 * </p>
 *
 * @author wsh
 * @since 2022-07-11
 */
@Mapper
public interface VideoVisitorMapper extends BaseMapper<VideoVisitor> {

    //获取某段时间内登录所访问该视频的总人数
    List<VideoVisitorCountVo> findCount(Map map);
}
