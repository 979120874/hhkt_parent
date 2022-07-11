package com.ik.hhkt.vod.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ik.hhkt.model.vod.Chapter;
import com.ik.hhkt.vo.vod.ChapterVo;
import com.ik.hhkt.vod.mapper.ChapterMapper;
import com.ik.hhkt.vod.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author wsh
 * @since 2022-07-10
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Resource
    private ChapterMapper chapterMapper;

    @Override
    public List<ChapterVo> getNestedTreeList(Long courseId) {
        List<ChapterVo> nestedTreeList = chapterMapper.getNestedTreeList(courseId);
        return nestedTreeList;
    }
}
