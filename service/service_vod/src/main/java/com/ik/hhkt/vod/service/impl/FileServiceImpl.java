package com.ik.hhkt.vod.service.impl;

import com.alibaba.fastjson.JSON;
import com.ik.hhkt.vod.config.ConstantPropertiesUtil;
import com.ik.hhkt.vod.service.FileService;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * TODO
 *
 * @className: FileServiceImpl
 * @author: weishihuan
 * @date: 2022-07-08 17:41
 **/
@Service
public class FileServiceImpl implements FileService {
    @Override
    public String upload(MultipartFile file) {
        String secretId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String secretKey = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的地域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region endpoint = new Region(ConstantPropertiesUtil.END_POINT);
        ClientConfig clientConfig = new ClientConfig(endpoint);
        // 这里建议设置使用 https 协议
        // 从 5.6.54 版本开始，默认使用了 https
        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);
        // 存储桶的命名格式为 BucketName-APPID，此处填写的存储桶名称必须为此格式
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
        try {
            // 这里创建一个 ByteArrayInputStream 来作为示例，实际中这里应该是您要上传的 InputStream 类型的流
            InputStream inputStream = file.getInputStream();

            // 对象键(Key)是对象在存储桶中的唯一标识。
            String key = UUID.randomUUID().toString().replaceAll("-","")+file.getOriginalFilename();
            String dataUrl = new DateTime().toString("yyyy/MM/dd");
            key=dataUrl+"/"+key;
            ObjectMetadata objectMetadata = new ObjectMetadata();
// 上传的流如果能够获取准确的流长度，则推荐一定填写 content-length
// 如果确实没办法获取到，则下面这行可以省略，但同时高级接口也没办法使用分块上传了
//            objectMetadata.setContentLength(inputStreamLength);

            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, inputStream, objectMetadata);
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            System.out.println("bucketName="+bucketName);
            System.out.println("endpoint="+endpoint);
            System.out.println("key="+key);
            //https://ggkt-atguigu-1310644373.cos.ap-beijing.myqcloud.com/01.jpg
            String url = "https://"+bucketName+"."+"cos"+"."+endpoint+".myqcloud.com"+"/"+key;
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}