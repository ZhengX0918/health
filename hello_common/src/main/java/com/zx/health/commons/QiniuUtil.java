package com.zx.health.commons;

import com.alibaba.fastjson.JSON;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

public class QiniuUtil {

    //类似账号
    static String accessKey = "3Zy-khB5LooHTnLnGONlS8Nl3yoSMLOzUasXKJAf";
    //类似密码
    static String secretKey = "TbBP5xjKWh5ReslyzCz69pPLJDE95TgN9KybdoLK";
    //新建存储空间时输入的名称
    static String bucket = "health84";


    public static void upload(byte[] data,String key){
        //指定你新建存储空间选的存储区域
        Configuration cfg = new Configuration(Region.region0());
        UploadManager uploadManager = new UploadManager(cfg);

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(data,key, upToken);
            DefaultPutRet defaultPutRet = JSON.parseObject(response.bodyString(), DefaultPutRet.class);
            //解析上传成功的结果
            System.out.println(defaultPutRet.key);
            System.out.println(defaultPutRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }


    public static void delete(String key){
        //指定你新建存储空间选的存储区域
        Configuration cfg = new Configuration(Region.huadong());
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }

}

