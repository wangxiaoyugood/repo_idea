package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface CourseService {
    //多条件查询
    public List<Course> findCourseByCondition(CourseVO vo);


    //添加课程和讲师
    public void saveCourseOrTeacher(CourseVO vo) throws InvocationTargetException, IllegalAccessException;

    //根据ID回显课程和讲师
    public CourseVO findCourseById(int id);

    //更新课程及讲师信息
    public void updateCourseOrTeacher(CourseVO vo) throws InvocationTargetException, IllegalAccessException;

    //修改状态
    public void updateCourseStatus(int id, int status);
}
