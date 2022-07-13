package com.ik.hhkt.vod.service.impl;

import com.ik.hhkt.model.vod.VideoVisitor;
import com.ik.hhkt.vo.vod.VideoVisitorCountVo;
import com.ik.hhkt.vod.mapper.VideoVisitorMapper;
import com.ik.hhkt.vod.service.VideoVisitorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 视频来访者记录表 服务实现类
 * </p>
 *
 * @author wsh
 * @since 2022-07-11
 */
@Service
public class VideoVisitorServiceImpl extends ServiceImpl<VideoVisitorMapper, VideoVisitor> implements VideoVisitorService {

    @Resource
    private VideoVisitorMapper videoVisitorMapper;

    //获取某段时间内登录所访问该视频的总人数
    @Override
    public Map findCount(Long courseId, String startDate, String endDate) {
        Map<String, Object> map = new HashMap<>();
        map.put("courseId",courseId);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        //获取结果 {【2021-11-22（日期）, 2（次数）】，【2021-11-20, 1】}形式结果
        List<VideoVisitorCountVo> videoVisitorCountVoList = videoVisitorMapper.findCount(map);
        //把各个日期的访问次数放到一个数组   yData
        List<Integer> UserCounts = videoVisitorCountVoList.stream().map(VideoVisitorCountVo::getUserCount).collect(Collectors.toList());
        //把各个日期放到一个数组   xData
        List<String> JoinTimeS = videoVisitorCountVoList.stream().map(VideoVisitorCountVo::getJoinTime).collect(Collectors.toList());
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("xData",JoinTimeS);
        resultMap.put("yData",UserCounts);
        return resultMap;
    }
}
