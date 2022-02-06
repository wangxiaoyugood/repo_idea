package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.Teacher;

import java.util.List;

public interface CourseMapper {

    //多条件课程查询
    public List<Course> findCourseByCondition(CourseVO vo);

    //新增课程
    public void saveCourse(Course course);

    //新增讲师
    public void saveTeacher(Teacher teacher);

    //根据ID查询课程信息及讲师信息
    public CourseVO findCourseById(int id);

    //更新课程信息
    public void updateCourse(Course course);

    //更新讲师信息
    public void updateTeacher(Teacher teacher);

    //状态管理
    public void updateCourseStatus(Course course);

}
