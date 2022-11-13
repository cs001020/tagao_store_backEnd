package com.chen.admin.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * 文件跑龙套
 *
 * @author CHEN
 * @date 2022/11/14
 */
@Component
public class FileUtils {

    @Value("${kodo.accessKey}")
    private String accessKey;
    @Value("${kodo.secretKey}")
    private String secretKey;
    @Value("${kodo.bucket}")
    private String bucket;

    public void fileUpload(String fileName, InputStream file) throws Exception {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration();
        // 指定分片上传版本
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        //上传文件
        uploadManager.put(file, "store/" + fileName, upToken, null, null);
    }
}
