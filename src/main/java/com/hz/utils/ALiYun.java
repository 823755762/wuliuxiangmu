package com.hz.utils;

public class ALiYun {
    /*Endpoint 是区域地址，地域节点*/
    public  String ENDPOINT = "http://oss-cn-beijing.aliyuncs.com";
    /*阿里云主账号AccessKeyid 拥有所有API的访问权限, 是访问阿里云的秘钥，*/
    public  String ACCESSKEYID = "LTAI5tPdUA9kdHbUsgmskrSa";
    /*阿里云主账号AccessKeysecret 拥有所有API的访问权限 是访问阿里云的秘钥，*/
    public  String ACCESSKEYSECRET = "qe0xvCl94GOrUNHxim6l8nilCzb4yr";
    /*你创建的 bucket 名称*/
    public  String BUCKETNAME = "wuliuli";
    /*先建立的一个文件夹名称，也可以说是相册名称*/
    public  String KEY = "images/";

    public String getENDPOINT(){
        return  ENDPOINT;
    }
    public String getACCESSKEYID(){
        return ACCESSKEYID;
    }
    public String getACCESSKEYSECRET(){
        return ACCESSKEYSECRET;
    }
    public  String getKEY(){
        return KEY;
    }
    public String getBUCKETNAME(){
        return BUCKETNAME;
    }
}
