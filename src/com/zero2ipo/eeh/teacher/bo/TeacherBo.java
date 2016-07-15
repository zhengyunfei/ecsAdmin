package com.zero2ipo.eeh.teacher.bo;

import com.zero2ipo.eeh.grade.bo.GradeBo;

/**
 * 教师
 * Created by Administrator on 2016/2/24.
 */
public class TeacherBo implements  java.io.Serializable {
    public int id;//主键
    public String name;//老师姓名
    public String subjectId;//所属科目
    public String gradeId;//所属年级
    public String classId;//授课班级
    public GradeBo gradeBo;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public GradeBo getGradeBo() {
        return gradeBo;
    }

    public void setGradeBo(GradeBo gradeBo) {
        this.gradeBo = gradeBo;
    }
}
