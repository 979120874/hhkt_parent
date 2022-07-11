package com.ik.hhkt.vod.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ik.hhkt.model.vod.Chapter;
import com.ik.hhkt.vo.vod.ChapterVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author wsh
 * @since 2022-07-10
 */
@Mapper
public interface ChapterMapper extends BaseMapper<Chapter> {

    List<ChapterVo> getNestedTreeList(Long courseId);
}
