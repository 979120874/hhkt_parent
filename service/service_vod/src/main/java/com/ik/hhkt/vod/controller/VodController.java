package com.ik.hhkt.vod.controller;

import com.ik.hhkt.result.Result;
import com.ik.hhkt.vod.config.ConstantPropertiesUtil;
import com.ik.hhkt.vod.config.Signature;
import com.ik.hhkt.vod.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Random;

/**
 * TODO
 *
 * @className: VodController
 * @author: weishihuan
 * @date: 2022-07-12 17:23
 **/
@Api(tags = "腾讯云点播")
@RestController
@RequestMapping("/admin/vod")
//@CrossOrigin
public class VodController {

    @Resource
    private VodService vodService;

    //1.服务端上传视频
    @PostMapping("upload")
    @ApiOperation("上传视频")
    public Result uploadVod(){
        String FileId=vodService.uploadVideo();
        return Result.ok(FileId);
    }

    //删除视频
    @PostMapping("delete/{fileId}")
    @ApiOperation("删除视频")
    public Result deleteVod(@PathVariable String fileId){
        vodService.deleteVideo(fileId);
        return Result.ok();
    }
    //客户端上传视频，上传签名生成
    //1. 申请上传签名
    //2. 使用 SDK 上传视频  （前端）
    @GetMapping("sign")
    @ApiOperation("上传签名生成")
    public Result sign(){
        Signature sign = new Signature();
        // 设置 App 的云 API 密钥 ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET
        sign.setSecretId(ConstantPropertiesUtil.ACCESS_KEY_ID);
        sign.setSecretKey(ConstantPropertiesUtil.ACCESS_KEY_SECRET);
        sign.setCurrentTime(System.currentTimeMillis() / 1000);
        sign.setRandom(new Random().nextInt(java.lang.Integer.MAX_VALUE));
        sign.setSignValidDuration(3600 * 24 * 2); // 签名有效期：2天
        try {
            String signature = sign.getUploadSignature();
            //7qouG2KFNAMMO6yOCBrc0wfZ0WdzZWNyZXRJZD1BS0lEMHNWcnZpRldXSG4zMmxFUU0xUVpSMk1RRGswMWxGVVQmY3VycmVudFRpbWVTdGFtcD0xNjU3NjI0NjkyJmV4cGlyZVRpbWU9MTY1Nzc5NzQ5MiZyYW5kb209MTQ1MzkzMjM3NA==
            System.out.println("signature : " + signature);
            return Result.ok(signature);
        } catch (Exception e) {
            System.out.print("获取签名失败");
            e.printStackTrace();
            return Result.fail("获取签名失败");
        }
    }
}
