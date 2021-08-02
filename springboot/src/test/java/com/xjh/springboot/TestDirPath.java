package com.xjh.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDirPath {
    @Test
    public void showDirPath(){
        String path = System.getProperty("user.dir");
        System.out.println(path);
    }
    @Test
    public void uploadVideo(){
        String viedoPath= "D:\\xjhProjects\\javaSEProjectByIdea\\stuTxt.txt";
        File f = new File(viedoPath);
        String off = viedoPath.substring(viedoPath.lastIndexOf('.'));
        OutputStream out = null;
//        String flag = "error";
//        if(file.isEmpty()){
//            return "null";
//            System.out.println("null");
//        }
        FileInputStream file = null;
        try {
            file = new FileInputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            byte[] bytes = file.readAllBytes();
            String path = System.getProperty("user.dir");
            SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSS");
            Date date = new Date();
            String filename = sf.format(date);
            path = path + "/src/main/resources/static/res/videos/" + filename + off;
            out = new FileOutputStream(path);
            out.write(bytes);
//            flag = "succeed";
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
//        return flag;
    }
}
