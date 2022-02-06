package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;

    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findCourseAndLessonByCourseId(int courseId){
        List<CourseSection> sectionAndLesson = courseContentService.findSectionAndLessonByCourseId(courseId);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", sectionAndLesson);
        return result;
    }

    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(int courseId){
        Course course = courseContentService.findCourseByCourseId(courseId);
        ResponseResult result = new ResponseResult(true, 200, "回享成功", course);
        return result;
    }


    //新增和更新章节信息
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection){
        if (courseSection.getId() == null){
            courseContentService.saveSection(courseSection);
        }else {
            courseContentService.updateSection(courseSection);
        }
        ResponseResult result = new ResponseResult(true, 200, "响应成功", null);
        return result;
    }

    //更新章节状态
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(int id, int status){
        courseContentService.updateSectionStatus(id,status);
        Map<String, Integer> map = new HashMap<>();
        map.put("status",status);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
        return result;

    }




}
