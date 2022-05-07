package com.hz.controller;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.hz.service.OrderssService;
import com.hz.utils.ALiYun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController    /* 使返回的数据转为 json 格式的数据 */
@RequestMapping("/imgg")
public class ImgController {

    @Autowired
    private OrderssService orderssService;

    @RequestMapping("/uploadImg")
    public Map<String, String> uploadImg(MultipartFile file, RedirectAttributes attr) {
        ALiYun aly = new ALiYun();
        Map<String, String> outmap = new HashMap<>();

        String url="";
        if (file != null) {
            String imgname = file.getOriginalFilename();    /*此时获得的是该照片上传时的名字，如：1234.jpg或 345.png或。。。*/
           /* System.out.println(imgname);*/
            try {
                /*获取上传目前时间*/
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); //设置日期格式
                String dote = df.format(new Date());    // 目前时间

                InputStream input = file.getInputStream();

                // 初始化阿里云oss链接，上传图片到服务器
                OSS ossClient = new OSSClientBuilder().build(aly.getENDPOINT(), aly.getACCESSKEYID(), aly.getACCESSKEYSECRET());
                // 上传文件流
                ossClient.putObject(aly.getBUCKETNAME(), imgname, input);
                // 关闭OSSClient。
                ossClient.shutdown();

                // 设置图片处理样式。
                Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 100);
                GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(aly.getBUCKETNAME(), imgname, HttpMethod.GET);
                req.setExpiration(expiration);
                URL signedUrl = ossClient.generatePresignedUrl(req);
                url = signedUrl.toString();
              /*  System.out.println(signedUrl.toString());*/
            } catch (IOException e) {
                System.out.println("OMG,我捕捉到你了!");
            } finally {
                outmap.put("resultFlag",url);
            }

        }
        return outmap;
    }
}
