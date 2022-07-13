package com.ik.hhkt.vod.controller;

import com.ik.hhkt.result.Result;
import com.ik.hhkt.vod.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * cos保存头像
 *
 * @className: FileUploadController
 * @author: weishihuan
 * @date: 2022-07-08 17:39
 **/
@RestController
@RequestMapping("/admin/vod/file")
@Api(tags = "文件管理接口")
//@CrossOrigin
public class FileUploadController {

    @Resource
    private FileService fileService;

    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public Result upload(@ApiParam(name = "file",value = "选择要上传的文件" ,required = true)
                             @RequestParam("file") MultipartFile file){
        String uploadUrl=fileService.upload(file);
        return Result.ok(uploadUrl).message("文件上传成功");
    }
}
