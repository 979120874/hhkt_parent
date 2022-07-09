package com.ik.hhkt.vod.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ik.hhkt.model.vod.Subject;
import com.ik.hhkt.vo.vod.SubjectEeVo;
import com.ik.hhkt.vo.vod.SubjectVo;
import com.ik.hhkt.vod.config.SubjectListener;
import com.ik.hhkt.vod.mapper.SubjectMapper;
import com.ik.hhkt.vod.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author wsh
 * @since 2022-07-08
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Resource
    private SubjectMapper subjectMapper;

    @Override
    public List<Subject> findChildSubject(Long id) {
        QueryWrapper<Subject> subjectQueryWrapper = new QueryWrapper<>();
        subjectQueryWrapper.eq("parent_id",id);
        List<Subject> subjects = baseMapper.selectList(subjectQueryWrapper);
        for (Subject subject : subjects) {
            if (subject.getParentId()==0){
                subject.setHasChildren(true);
            }
        }
        return subjects;
    }

    @Override
    public void exportData(HttpServletResponse response) {
        try {
            // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("课程分类", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

            List<Subject> subjects = baseMapper.selectList(null);
            List<SubjectEeVo> subjectVos = new ArrayList<>();
            for (Subject subject : subjects) {
                SubjectEeVo subjectEeVo = new SubjectEeVo();
                BeanUtils.copyProperties(subject,subjectEeVo);
                subjectVos.add(subjectEeVo);
            }
            EasyExcel.write(response.getOutputStream(), SubjectEeVo.class).sheet("分类").doWrite(subjectVos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void importData(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(),SubjectEeVo.class,new SubjectListener(subjectMapper)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
