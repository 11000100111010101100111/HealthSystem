package com.xjh.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.xjh.springboot.dao.FileDao;
import com.xjh.springboot.pojo.File;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
public class UploadFileController {

    @Resource(name = "fileDao")
    FileDao fileDao;

    @RequestMapping("/uploadfile")
    public String uploadfile(@RequestParam("file")MultipartFile file,@RequestParam("file") File f){
        OutputStream out = null;
        String flag = "error";
        if(file.isEmpty()){
            return "null";
        }
        int hasFile = fileDao.hasFileByName(f.getName());
        if(hasFile > 0){
            return "hasFile";
        }
        String videoPath = file.getName();
        String off = videoPath.substring(videoPath.lastIndexOf('.'));
        try {
            byte[] bytes = file.getBytes();

            StringBuffer path =new StringBuffer( System.getProperty("user.dir"));

            SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSS");

            Date date = new Date();
            String filename = sf.format(date);
//            path = path + "/../webapps/assets/res/videos/" + filename;

            path.append("/src/main/resources/static/res/videos/").append(f.getUid()).append("_").append(filename).append(off);

            out = new FileOutputStream(path.toString());

            out.write(bytes);



            f.setPath("http://localhost:9000/res/videos/static/"+f.getUid()+"_"+filename+off);
            java.sql.Date datesql = new java.sql.Date(new Date().getTime());
            f.setUploadtime(datesql);

            fileDao.uploadFile(f);//保存到数据库

            flag = "succeed";
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }
    @RequestMapping("/getVideo")
    public String getViedo(String key){
        //返回数据库对应视频地址（地址存储在数据库中）
        List<String> files = null;
        String flag = "error";
        try {
            files = fileDao.findFile(key);
            flag = "succeed";
        }catch (Exception e){

        }
        HashMap<String,Object> res = new HashMap<String ,Object>();
        res.put("res",files);
        res.put("flag",flag);
        String val = JSON.toJSONString(res);
        return val;
    }
}
