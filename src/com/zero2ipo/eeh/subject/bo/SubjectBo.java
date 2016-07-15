package com.zero2ipo.eeh.subject.bo;

/**
 * 教师
 * Created by Administrator on 2016/2/24.
 */
public class SubjectBo implements  java.io.Serializable {
    public int id;//主键
    public String subjectClass;//科目分类
    public String name;//科目名称

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

    public String getSubjectClass() {
        return subjectClass;
    }

    public void setSubjectClass(String subjectClass) {
        this.subjectClass = subjectClass;
    }
}
