package com.ik.hhkt.vod.service;

import com.ik.hhkt.model.vod.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author wsh
 * @since 2022-07-08
 */
public interface SubjectService extends IService<Subject> {
    List<Subject> findChildSubject(Long id);
    void exportData(HttpServletResponse response);
    void importData(MultipartFile file);
}
