package com.ik.hhkt.vod.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ik.hhkt.model.vod.Course;
import com.ik.hhkt.vo.vod.CourseFormVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author wsh
 * @since 2022-07-10
 */
@Mapper
public interface CourseMapper extends BaseMapper<Course> {

    CourseFormVo getCourseInfo(Long id);

}
