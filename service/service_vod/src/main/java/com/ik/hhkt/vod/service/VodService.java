package com.ik.hhkt.vod.service;

public interface VodService {

    //上传视频
    String uploadVideo();

    //删除视频
    void deleteVideo(String fileId);
}
