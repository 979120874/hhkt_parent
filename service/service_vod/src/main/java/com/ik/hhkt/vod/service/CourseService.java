package com.ik.hhkt.vod.service;

import com.ik.hhkt.model.vod.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ik.hhkt.vo.vod.CourseFormVo;
import com.ik.hhkt.vo.vod.CoursePublishVo;
import com.ik.hhkt.vo.vod.CourseQueryVo;
import com.ik.hhkt.vo.vod.CourseVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author wsh
 * @since 2022-07-10
 */
public interface CourseService extends IService<Course> {

    //查询条件并分页
    Map<String, Object> findPage(Long page, Long limit, CourseQueryVo courseQueryVo);

    //添加课程
    Long saveCourseInfo(CourseFormVo courseFormVo);

    //通过id查询课程信息
    CourseFormVo getCourseInfo(Long id);

    //修改课程信息
    Long updateCourseInfo(CourseFormVo courseFormVo);

    //根据id获取课程发布信息
    CoursePublishVo getCoursePublishVo(Long id);

    //根据id发布课程
    Boolean publishCourseById(Long id);

    void removeCourseById(Long id);
}
