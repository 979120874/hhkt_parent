package com.ik.hhkt.vod.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ik.hhkt.model.vod.Chapter;
import com.ik.hhkt.vo.vod.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author wsh
 * @since 2022-07-10
 */
public interface ChapterService extends IService<Chapter> {

    List<ChapterVo> getNestedTreeList(Long courseId);
}
