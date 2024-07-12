package com.Tzj.lost_found_system.controller;

import com.Tzj.lost_found_system.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
@RestController
public class UploadController {
    //@Autowired
   // private StudentImpl studentImpl;

    @Value("${img.path}")
    private String imgbase;

    private String fName;

    @PostMapping( "/upload")
    // 此处的@RequestParam中的file名应与前端upload组件中的name的值保持一致
    public Result upload(@RequestParam("file") MultipartFile file) {
        String fileName=file.getOriginalFilename();
        String suffix=fileName.substring(fileName.lastIndexOf('.'));

        ;

       File dir= new File(imgbase);
       if(!dir.exists()){
           dir.mkdirs();
       }
       try {
           fName=fileName;

           file.transferTo(new File(imgbase+file.getOriginalFilename()));
       }catch (IOException e){
           e.printStackTrace();
       }
        String src=imgbase+fileName;
        log.info("asfeaefaw:{}",src);
        return Result.successAndObject(fileName,src);
    }
//
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response){
        log.info("saf:{}",name);
        try {
            FileInputStream fileInputStream= new FileInputStream(new File(imgbase+name));
            ServletOutputStream outputStream= response.getOutputStream();
//            response.setContentType("img/jpg");
            int let=0;
            byte[] bytes=new byte[1024];
            while ((let=fileInputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,let);
                outputStream.flush();
            }
            outputStream.close();
            fileInputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }



//    @PostMapping( "/beforeUpload")
//    // 此处的@RequestParam中的file名应与前端upload组件中的name的值保持一致
//    public Result beforeUpload(@RequestParam("file") MultipartFile file) throws IOException {
//        List<Student> studentList=ExcelUtil.getReader(file.getInputStream()).readAll(Student.class);
//        if(!CollectionUtils.isEmpty(studentList)){
//            for(Student stu:studentList){
//                try{
//                    studentImpl.insertStudent(stu);
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        }
//        return Result.success();
//    }

}
