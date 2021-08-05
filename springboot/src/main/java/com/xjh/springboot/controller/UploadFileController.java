package com.xjh.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.xjh.springboot.dao.FileDao;
import com.xjh.springboot.pojo.File;
import org.springframework.web.bind.annotation.CrossOrigin;
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

    @CrossOrigin
    @RequestMapping("/uploadfile")
    public String uploadfile(@RequestParam MultipartFile file ,@RequestParam String name,@RequestParam String uid,@RequestParam String description){
        File f = new File();
        f.setName(name);
        f.setDescription(description);
        f.setUid(Integer.parseInt(uid));

        System.out.println(file.getName()+"=="+file.getOriginalFilename()+"=="+file.getContentType());

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
        String off = ".mp4" ;//videoPath.substring(videoPath.lastIndexOf('.'));
        try {
            byte[] bytes = file.getBytes();

            StringBuffer path =new StringBuffer( System.getProperty("user.dir"));

            SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSS");

            Date dtUitl = new Date();

            String filename = sf.format(dtUitl);

            path.append("/src/main/resources/static/res/videos/").append(f.getUid()).append("_").append(filename).append(off);

            out = new FileOutputStream(path.toString());

            out.write(bytes);
            
//            http://localhost:9000/videos/
            f.setPath("http://localhost:9000/videos/"+f.getUid()+"_"+filename+off);


            f.setUploadtime(dtUitl);

            fileDao.uploadFile(f);//保存到数据库

            flag = "succeed";
            System.out.println("======》上传成功");
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

    @CrossOrigin
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
