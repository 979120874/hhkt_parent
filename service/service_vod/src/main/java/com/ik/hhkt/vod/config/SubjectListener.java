package com.ik.hhkt.vod.config;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ik.hhkt.model.vod.Subject;
import com.ik.hhkt.vo.vod.SubjectEeVo;
import com.ik.hhkt.vod.mapper.SubjectMapper;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @className: SubjectListener
 * @author: weishihuan
 * @date: 2022-07-09 16:10
 **/
public class SubjectListener extends AnalysisEventListener<SubjectEeVo> {

    private SubjectMapper subjectMapper;
    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     */
    public SubjectListener(SubjectMapper subjectMapper) {
        this.subjectMapper = subjectMapper;
    }

    @Override
    public void invoke(SubjectEeVo subjectEeVo, AnalysisContext analysisContext) {
        Subject subject = new Subject();
        BeanUtils.copyProperties(subjectEeVo,subject);
        subjectMapper.insert(subject);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
