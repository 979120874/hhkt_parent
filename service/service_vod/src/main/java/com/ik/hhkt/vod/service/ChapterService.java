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

    //大纲列表（章节和小节列表）
    List<ChapterVo> getNestedTreeList(Long courseId);
    //根据课程id删除小节
    void removeChapterByCourseId(Long id);
}
