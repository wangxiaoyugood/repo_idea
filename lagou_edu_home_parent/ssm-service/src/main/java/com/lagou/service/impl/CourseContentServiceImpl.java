package com.lagou.service.impl;

import com.lagou.dao.CourseContentMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class CourseContentServiceImpl implements CourseContentService {
    @Autowired
    private CourseContentMapper courseContentMapper;

    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(int courseId) {
        List<CourseSection> sectionAndLesson = courseContentMapper.findSectionAndLessonByCourseId(courseId);
        return sectionAndLesson;

    }

    @Override
    public Course findCourseByCourseId(int courseId) {

        Course course = courseContentMapper.findCourseByCourseId(courseId);
        return course;
    }

    @Override
    public void saveSection(CourseSection courseSection) {
        Date date = new Date();
        courseSection.setCreateTime(date);
        courseSection.setUpdateTime(date);
        courseContentMapper.saveSection(courseSection);
    }

    @Override
    public void updateSection(CourseSection courseSection) {
        Date date = new Date();
        courseSection.setUpdateTime(date);
        courseContentMapper.updateSection(courseSection);
    }

    @Override
    public void updateSectionStatus(int id, int status) {
        CourseSection courseSection = new CourseSection();
        courseSection.setId(id);
        courseSection.setStatus(status);
        courseSection.setUpdateTime(new Date());
        courseContentMapper.updateSectionStatus(courseSection);
    }
}
