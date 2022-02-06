package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;
    //多条件查询课程
    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVO vo){
        List<Course> list = courseService.findCourseByCondition(vo);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", list);
        System.out.println(list);
        return result;
    }

    //图片上传
    @RequestMapping("/courseUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        //判断接受文件是否为空
        if (file.isEmpty()){
            throw new RuntimeException();
        }

        //获取项目部署路径
        String realPath = request.getServletContext().getRealPath("/");

        String substring = realPath.substring(0, realPath.indexOf("ssm-web"));

        //获取原文件名
        String originalFilename = file.getOriginalFilename();

        //生成新文件名
        String newFileName = System.currentTimeMillis()+originalFilename.substring(originalFilename.lastIndexOf("."));

        //文件上传
        String uploadPath = substring + "upload\\";
        File filePath = new File(uploadPath, newFileName);

        //如果文件目录不存在就创建
        if(!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录:"+filePath);
        }
        //上传
        file.transferTo(filePath);

        //将文件名和文件路径返回，进行响应
        Map<String, String> map = new HashMap<>();
        map.put("fileName", newFileName);
        map.put("filePath","http://localhost:8080/upload/"+newFileName);
        ResponseResult result = new ResponseResult(true, 200, "上传成功", map);
        return result;

    }

    //新增课程及讲师
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVO vo ) throws InvocationTargetException, IllegalAccessException {
        if(vo.getId() ==null ){
            //新增操作
            courseService.saveCourseOrTeacher(vo);
        } else{
            courseService.updateCourseOrTeacher(vo);
        }
        ResponseResult result = new ResponseResult(true, 200, "响应成功", null);
        return result;

    }

    //根据ID回显课程和讲师
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(int id){
        CourseVO vo = courseService.findCourseById(id);
        ResponseResult result = new ResponseResult(true, 200, "查询成功", vo);
        return result;
    }

    //课程状态管理
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(int id, int status){
        courseService.updateCourseStatus(id,status);
        Map<String, Object> map = new HashMap<>();
        map.put("status",status);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
        return result;

    }


}
