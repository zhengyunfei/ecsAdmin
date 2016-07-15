package com.zero2ipo.eeh.pewstudent.web;

import com.zero2ipo.common.web.BaseCtrl;
import com.zero2ipo.eeh.classroom.bizc.IClassRoomService;
import com.zero2ipo.eeh.classroom.bo.ClassRoomBo;
import com.zero2ipo.eeh.course.bizc.ICourseService;
import com.zero2ipo.eeh.course.bo.CourseBo;
import com.zero2ipo.eeh.grade.bizc.IGradeService;
import com.zero2ipo.eeh.grade.bo.GradeBo;
import com.zero2ipo.eeh.pewstudent.bizc.IPewStudentService;
import com.zero2ipo.eeh.pewstudent.bo.PewStudentBo;
import com.zero2ipo.eeh.student.bizc.IStudentService;
import com.zero2ipo.eeh.student.bo.StudentBo;
import com.zero2ipo.framework.exception.BaseException;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.framework.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 培优课选课管理ctrl
 * Created by Administrator on 2016/2/24.
 */
@Controller
@RequestMapping("/PewStudent")
public class PewStudentCtrl extends BaseCtrl {
    @Autowired
    @Qualifier("PewStudentService")
    private IPewStudentService PewStudentService ;
    @Autowired
    @Qualifier("CourseService")
    private ICourseService CourseService;
    @Autowired
    @Qualifier("studentService")
    private IStudentService studentService;
    @Autowired
    @Qualifier("gradeService")
    private IGradeService gradeService;
    @Autowired
    @Qualifier("classRoomService")
    private IClassRoomService classRoomService;
    /**
     * 学生选课列表
     * @return
     */
    @RequestMapping("forInitForStudent.shtml")
    public ModelAndView forInitForStudent(String id) {
        ModelAndView mv = new ModelAndView("/eeh/PewStudent/list_student.jsp");
        if(!StringUtil.isNullOrEmpty(id)){
            mv.addObject("classId",id);//班级id
        }
        //查询所有课程
        getSelectCourseList(mv);
        return mv;
    }
    /**
     * 老师选课列表
     * @return
     */
    @RequestMapping("forInitForTeacher.shtml")
    public ModelAndView forInitForTeacher(String id) {
        ModelAndView mv = new ModelAndView("/eeh/PewStudent/list_teacher.jsp");
        if(!StringUtil.isNullOrEmpty(id)){
            mv.addObject("classId",id);//班级id
        }
        //查询所有课程
        getSelectCourseList(mv);
        //获取班级列表
        getSelectGradeList(mv);
        return mv;
    }
    /**
     * 查询所有课程列表
     * @param mv
     */
    private void getSelectCourseList(ModelAndView mv){
        List<CourseBo> list=new ArrayList<CourseBo>();
        Map<String,Object> queryMap=new HashMap<String, Object>();
        list=CourseService.findAllList(queryMap);
        if(!StringUtil.isNullOrEmpty(list)){
            mv.addObject("courseList",list);
        }
    }
    /**
     * 初始化json数据
     * @param curNo
     * @param curSize
     * @return
     */
    @RequestMapping("findAllList.shtml")
    @ResponseBody
    public Map<String,Object> findAllList(String curNo, String curSize,String classId,String courseName,String name,String courseType){
        Map<String,Object> jsonMap = new HashMap<String, Object>();
        try {
            /************* 分页处理 ************/
            int skip;
            int max;
            if (StringUtil.isNullOrEmpty(curNo))
                curNo = "0";
            if (StringUtil.isNullOrEmpty(curSize))
                curSize = "20";
            skip = Integer.parseInt(curNo);
            max = Integer.parseInt(curSize);
            /************  分页处理结束 ***********/
            Map<String, Object> map = new HashMap<String, Object>();
            if(!StringUtil.isNullOrEmpty(classId)){
                map.put("classId",classId);//所在班级
            }
            if(!StringUtil.isNullOrEmpty(courseName)){
                map.put("electiveCourse",courseName);//课程名
            }
            if(!StringUtil.isNullOrEmpty(name)){
                map.put("name",name);//学生姓名
            }
            if(!StringUtil.isNullOrEmpty(courseType)){
                map.put("courseType",courseType);//选课类型 学生选课还是老师手动添加学生选课
            }
            int total=0;
            total=PewStudentService.getTotal(map);
            List<PewStudentBo> list= null;
            if(total>0){
                list = PewStudentService.findAllList(map, (skip-1)*max, max);
            }
            jsonMap.put("Rows", list);
            jsonMap.put("Total", total);
        } catch (Exception e) {
            BaseLog.e(this.getClass(), "forLinkTypeinitAjax.shtml:管理人分类信息初始化有误", e);
        }
        return jsonMap;
    }
 /******************************************新增*********************************************************/
    /**
     * 新增页面初始化
     * @return
     */
    @RequestMapping("forAddInitPage.shtml")
    public ModelAndView forAddInitPage(String courseName) {
        ModelAndView mv = new ModelAndView("/eeh/PewStudent/add.jsp");
        if(!StringUtil.isNullOrEmpty(courseName)){
            mv.addObject("courseName",courseName);
        }
        //获取班级列表
        getSelectGradeList(mv);
        return mv;
    }
    /**
     * 手动添加学生到培优列表
     * @return
     */
    @RequestMapping("forAddInitPageForPew.shtml")
    public ModelAndView forAddInitPageForPew(String courseName) {
        ModelAndView mv = new ModelAndView("/eeh/PewStudent/pewAdd.jsp");
        if(!StringUtil.isNullOrEmpty(courseName)){
            mv.addObject("courseName",courseName);
        }
        //获取班级列表
        getSelectGradeList(mv);
        return mv;
    }
    private void getSelectGradeList(ModelAndView mv){
        Map<String,Object> queryMap=new HashMap<String, Object>();
        List<GradeBo> gradeList=new ArrayList<GradeBo>();
        gradeList=gradeService.findAllList(queryMap);
        if(!StringUtil.isNullOrEmpty(gradeList)){
            mv.addObject("gradeList",gradeList);
        }
    }

    /**
     * 添加保存数据
     * @param courseName
     * @return
     */
    @RequestMapping("forAddAjax.shtml")
    @ResponseBody
    public Map<String,Object> addSave(String ids,String courseName) {
        Map<String,Object> result=new HashMap<String,Object>();
        boolean flg=false;
        try {
            if(!StringUtil.isNullOrEmpty(ids)){
                String studentIds[]=ids.split(",");
                int length=studentIds.length;
                for(int i=0;i<length;i++){
                    String id=studentIds[i];
                    //根据学生id查询学生信息
                    StudentBo studentBo=null;
                    studentBo=studentService.findById(id);
                    if(!StringUtil.isNullOrEmpty(studentBo)){
                        PewStudentBo bo=new PewStudentBo();
                        bo.setElectiveCourse(courseName);
                        bo.setClassName(studentBo.getClassId());
                        bo.setName(studentBo.getName());
                        bo.setXhnum(studentBo.getXhnum());
                        bo.setType(studentBo.getStype());
                        //bo.setStatus("0");
                      //  bo.setSchoolTime(studentBo.get);
                        flg=PewStudentService.add(bo);
                        //批量保存
                    }
                }
            }
        } catch (Exception e) {
            flg=false;
            e.printStackTrace();
            BaseLog.e(this.getClass(), "saveOrder", e);
            throw new BaseException("saveOrder 异常！");
        }
        result.put("success", flg);
        return result;
    }
    /******************************************修改*********************************************************/
    /**
     * 新增页面初始化
     * @return
     */
    @RequestMapping("forUpdateInitPage.shtml")
    public ModelAndView forUpdateInitPage(String id) {
        ModelAndView mv = new ModelAndView("/eeh/PewStudent/update.jsp");
        PewStudentBo bo=PewStudentService.findById(id);
        mv.addObject("bo",bo);
        return mv;
    }
    /**
     * 修改老师选课
     * @return
     */
    @RequestMapping("updateTeacherXuanKe.shtml")
    public ModelAndView forUpdateInitPageForTeacher(String id) {
        ModelAndView mv = new ModelAndView("/eeh/PewStudent/update_teachar.jsp");
        PewStudentBo bo=PewStudentService.findById(id);
        mv.addObject("bo",bo);
        getSelectGradeList(mv);//查询所有班级
        //查询所有教室
        List<ClassRoomBo> classRoomList=classRoomService.findAllList(new HashMap<String, Object>());
        if(!StringUtil.isNullOrEmpty(classRoomList)&&classRoomList.size()>0){
            mv.addObject("classRoomList",classRoomList);
        }
        return mv;
    }


    /**
     * 修改保存数据
     * @param bo
     * @return
     */
    @RequestMapping("forUpdateAjax.shtml")
    @ResponseBody
    public Map<String,Object> updateSave(PewStudentBo bo) {
        Map<String,Object> result=new HashMap<String,Object>();
        boolean flg=false;
        try {
            flg=PewStudentService.update(bo);
        } catch (Exception e) {
            flg=false;
            e.printStackTrace();
            BaseLog.e(this.getClass(), "forUpdateAjax", e);
            throw new BaseException("forUpdateAjax 异常！");
        }
        result.put("success", flg);
        return result;
    }
    /******************************************删除操作*********************************************************/

    @RequestMapping("delSave.shtml")
    @ResponseBody
    public Map<String,Object> delSave(String ids) {
        Map<String,Object> result=new HashMap<String,Object>();
        boolean flg=false;
        try {
            flg=PewStudentService.delete(ids);
        } catch (Exception e) {
            flg=false;
            e.printStackTrace();
            BaseLog.e(this.getClass(), "delSave", e);
            throw new BaseException("delSave 异常！");
        }
        result.put("success", flg);
        return result;
    }
}
