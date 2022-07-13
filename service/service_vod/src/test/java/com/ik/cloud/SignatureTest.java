package com.ik.cloud;

import com.ik.hhkt.vod.config.ConstantPropertiesUtil;
import com.ik.hhkt.vod.config.Signature;

import java.util.Random;

/**
 * TODO
 *
 * @className: Signature
 * @author: weishihuan
 * @date: 2022-07-12 19:07
 **/
public class SignatureTest {
    public static void main(String[] args) {
        Signature sign = new Signature();
        // 设置 App 的云 API 密钥 ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET
        sign.setSecretId("AKID0sVrviFWWHn32lEQM1QZR2MQDk01lFUT");
        sign.setSecretKey("kDQM2RZQ1MPBftqWv3uTlnY1D8nAG4ex");
        sign.setCurrentTime(System.currentTimeMillis() / 1000);
        sign.setRandom(new Random().nextInt(java.lang.Integer.MAX_VALUE));
        sign.setSignValidDuration(3600 * 24 * 2); // 签名有效期：2天
        try {
            String signature = sign.getUploadSignature();
            //7qouG2KFNAMMO6yOCBrc0wfZ0WdzZWNyZXRJZD1BS0lEMHNWcnZpRldXSG4zMmxFUU0xUVpSMk1RRGswMWxGVVQmY3VycmVudFRpbWVTdGFtcD0xNjU3NjI0NjkyJmV4cGlyZVRpbWU9MTY1Nzc5NzQ5MiZyYW5kb209MTQ1MzkzMjM3NA==
            System.out.println("signature : " + signature);
        } catch (Exception e) {
            System.out.print("获取签名失败");
            e.printStackTrace();
        }
    }
}
