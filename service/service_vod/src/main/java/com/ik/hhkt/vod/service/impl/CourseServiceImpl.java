package com.ik.hhkt.vod.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ik.hhkt.model.vod.*;
import com.ik.hhkt.vo.vod.CourseFormVo;
import com.ik.hhkt.vo.vod.CoursePublishVo;
import com.ik.hhkt.vo.vod.CourseQueryVo;
import com.ik.hhkt.vod.mapper.CourseDescriptionMapper;
import com.ik.hhkt.vod.mapper.CourseMapper;
import com.ik.hhkt.vod.mapper.SubjectMapper;
import com.ik.hhkt.vod.mapper.TeacherMapper;
import com.ik.hhkt.vod.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author wsh
 * @since 2022-07-10
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Resource
    private CourseMapper courseMapper;
    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private SubjectMapper subjectMapper;
    @Resource
    private CourseDescriptionMapper courseDescriptionMapper;
    @Resource
    private VideoService videoService;
    @Resource
    private ChapterService chapterService;
    @Resource
    private CourseDescriptionService courseDescriptionService;

    @Override
    public Map<String, Object> findPage(Long page, Long limit, CourseQueryVo courseQueryVo) {
        //获取条件值
        String title = courseQueryVo.getTitle();//名称
        Long subjectId = courseQueryVo.getSubjectId();//二级分类
        Long subjectParentId = courseQueryVo.getSubjectParentId();//一级分类
        Long teacherId = courseQueryVo.getTeacherId();//讲师
        Page<Course> coursePage = new Page<>(page,limit);
        QueryWrapper queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(title)){
            queryWrapper.like("title",title);
        }
        if (!StringUtils.isEmpty(subjectId)){
            queryWrapper.eq("subject_id",subjectId);
        }
        if (!StringUtils.isEmpty(subjectParentId)){
            queryWrapper.eq("subject_parent_id",subjectParentId);
        }
        if (!StringUtils.isEmpty(teacherId)){
            queryWrapper.eq("teacher_id",teacherId);
        }
        Page selectPage = courseMapper.selectPage(coursePage, queryWrapper);
        long totalCount = selectPage.getTotal();//总记录数
        long totalPage = selectPage.getPages();//总页数
        long currentPage = selectPage.getCurrent();//当前页
        long size = selectPage.getSize();//每页记录数
        List<Course> records = selectPage.getRecords();
        records.forEach(item ->this.getTeacherOrSubjectName(item));

        Map<String, Object> map = new HashMap<>();
        map.put("totalCount",totalCount);
        map.put("totalPage",totalPage);
        map.put("records",records);
        return map;
    }

    //实现方法
    //添加课程基本信息
    @Override
    @Transactional
    public Long saveCourseInfo(CourseFormVo courseFormVo) {
        //添加到课程信息
        Course course = new Course();
        BeanUtils.copyProperties(courseFormVo,course);
        int i1 = courseMapper.insert(course);
        //添加到课程描述信息
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setCourseId(course.getId());
        courseDescription.setDescription(courseFormVo.getDescription());
        int i2 = courseDescriptionMapper.insert(courseDescription);

        if (i1<0 || i2<0){
            return -1L;
        }
        return courseFormVo.getId();
    }

    //通过id查询课程信息
    @Override
    public CourseFormVo getCourseInfo(Long id) {
        CourseFormVo courseInfo = courseMapper.getCourseInfo(id);
        return courseInfo;
    }

    //修改课程信息
    @Override
    public Long updateCourseInfo(CourseFormVo courseFormVo) {
        Course course = new Course();
        //修改课程信息表内容
        BeanUtils.copyProperties(courseFormVo,course);
        int i1 = courseMapper.updateById(course);
        //修改课程描述表内容
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setId(course.getId());
        courseDescription.setDescription(courseFormVo.getDescription());
        int i2 = courseDescriptionMapper.updateById(courseDescription);
        if (i1<0 || i2<0){
            return -1L;
        }
        return course.getId();
    }

    //根据id获取课程发布信息
    @Override
    public CoursePublishVo getCoursePublishVo(Long id) {
        CoursePublishVo coursePublishVo= courseMapper.getCoursePublishVo(id);
        return coursePublishVo;
    }

    //根据id发布课程
    @Override
    public Boolean publishCourseById(Long id) {
        Course course = new Course();
        course.setId(id);
        course.setStatus(1);
        course.setPublishTime(Date.from(LocalDateTime.now().withNano(0).atZone(ZoneId.systemDefault()).toInstant()));
        return this.updateById(course);
    }

    @Override
    public void removeCourseById(Long id) {
        //根据课程id删除小节
        videoService.removeVideoByCourseId(id);
        //根据课程id删除章节
        chapterService.removeChapterByCourseId(id);
        //根据课程id删除描述
        courseDescriptionService.removeById(id);
        //根据课程id删除课程
        baseMapper.deleteById(id);
    }

    //获取讲师和分类名称
    private Course getTeacherOrSubjectName(Course course){
        //查询讲师名称
        Teacher teacher = teacherMapper.selectById(course.getTeacherId());
        if(teacher != null) {
            course.getParam().put("teacherName",teacher.getName());
        }
        //查询分类名称
        Subject subjectOne = subjectMapper.selectById(course.getSubjectParentId());
        if(subjectOne != null) {
            course.getParam().put("subjectParentTitle",subjectOne.getTitle());
        }
        Subject subjectTwo = subjectMapper.selectById(course.getSubjectId());
        if(subjectTwo != null) {
            course.getParam().put("subjectTitle",subjectTwo.getTitle());
        }
        return course;
    }

}
