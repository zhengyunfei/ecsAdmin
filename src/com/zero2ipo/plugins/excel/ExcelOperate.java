package com.zero2ipo.plugins.excel;

import com.zero2ipo.common.web.BaseCtrl;
import com.zero2ipo.eeh.Attendance.bizc.IAttendanceService;
import com.zero2ipo.eeh.Attendance.bo.AttendanceBo;
import com.zero2ipo.eeh.Timetable.bizc.ITimetableService;
import com.zero2ipo.eeh.Timetable.bo.TimetableBo;
import com.zero2ipo.eeh.classroom.bizc.IClassRoomService;
import com.zero2ipo.eeh.classroom.bo.ClassRoomBo;
import com.zero2ipo.eeh.course.bizc.ICourseService;
import com.zero2ipo.eeh.course.bo.CourseBo;
import com.zero2ipo.eeh.course.bo.CourseConstants;
import com.zero2ipo.eeh.grade.bizc.IGradeService;
import com.zero2ipo.eeh.grade.bo.GradeBo;
import com.zero2ipo.eeh.pewstudent.bizc.IPewStudentService;
import com.zero2ipo.eeh.pewstudent.bo.PewStudentBo;
import com.zero2ipo.eeh.seat.bizc.ISeatService;
import com.zero2ipo.eeh.seat.bo.SeatBo;
import com.zero2ipo.eeh.seat.bo.SeatContants;
import com.zero2ipo.eeh.student.bizc.IStudentService;
import com.zero2ipo.eeh.student.bo.StudentBo;
import com.zero2ipo.eeh.subject.bizc.ISubjectService;
import com.zero2ipo.eeh.subject.bo.SubjectBo;
import com.zero2ipo.eeh.teacher.bizc.ITeacherService;
import com.zero2ipo.eeh.teacher.bo.TeacherBo;
import com.zero2ipo.eeh.time.bizc.ITimeService;
import com.zero2ipo.eeh.time.bo.TimeBo;
import com.zero2ipo.framework.util.StringUtil;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/excelOperate")
public class ExcelOperate extends BaseCtrl {
    @Autowired
    @Qualifier("studentService")
    private IStudentService studentService;
    @Autowired
    @Qualifier("TimetableService")
    private ITimetableService TimetableService;
    @Autowired
    @Qualifier("TimeService")
    private ITimeService TimeService;
    @Autowired
    @Qualifier("CourseService")
    private ICourseService CourseService;
    @Autowired
    @Qualifier("PewStudentService")
    private IPewStudentService PewStudentService;
    @Autowired
    @Qualifier("SeatService")
    private ISeatService SeatService;
    @Autowired
    @Qualifier("gradeService")
    private IGradeService gradeService;
    @Autowired
    @Qualifier("classRoomService")
    private IClassRoomService classRoomService;
    @Autowired
    @Qualifier("teacherService")
    private ITeacherService teacherService;
    @Autowired
    @Qualifier("SubjectService")
    private ISubjectService SubjectService;
    @Autowired
    @Qualifier("AttendanceService")
    private IAttendanceService attendanceService;
    public static void main(String[] args) throws Exception {
       /* File file = new File("ExcelDemo.xls");
        String[][] result = getData(file, 1);
        int rowLength = result.length;
        for(int i=0;i<rowLength;i++) {
            for(int j=0;j<result[i].length;j++) {
                System.out.print(result[i][j]+"\t\t");
            }
            System.out.println();
        }*/

    }

    /**
     * 学生标准表导入
     * @param path
     * @param classId
     * @return
     */
    @RequestMapping("/readexcel.shtml")
    @ResponseBody
    public Map<String,Object> readexcel(String path,String classId){
        ImportExecl poi = new ImportExecl();
        // List<List<String>> list = poi.read("d:/aaa.xls");
        String filepath=session.getServletContext().getRealPath("/");
        path=path.replaceAll("\r\n","");
        filepath=filepath+path;
        filepath=filepath.replaceAll("\\\\","/");
        List<List<String>> list = poi.read(filepath);
        if (list != null)
        {
            for (int i = 1; i < list.size(); i++)
            {
                //System.out.print("第" + (i) + "行");
                //List<String> cellList = list.get(i);
                //for (int j = 0; j < cellList.size(); j++)
                // {
                // System.out.print("    第" + (j + 1) + "列值：");
                // System.out.print("    " + cellList.get(j));
                StudentBo studentBo=new StudentBo();
                String  banji=list.get(i).get(0).replace(".0","");//班级
                isExsitGrade(banji);
                String xuehao=list.get(i).get(1).replace(".0","");//学号
                String name=list.get(i).get(2);//姓名
                String sex=list.get(i).get(3);//性别
                String stype=list.get(i).get(4);//类型
                String cardNo=list.get(i).get(5).replace(".0","");//卡号
                studentBo.setName(name);
                studentBo.setClassId(banji);
                studentBo.setXhnum(xuehao);
                studentBo.setCardNo(cardNo);
                studentBo.setStype(stype);
                // studentBo.setGradeId(banji.substring(0,1));
                //导入前查询数据库是否存在此学生信息
                Map<String,Object> map=new HashMap<String, Object>();
                map.put("xhnum",xuehao);
                map.put("classId",banji);
                map.put("name",name);
                List<StudentBo> studentBos=studentService.findAllList(map);
                if(StringUtil.isNullOrEmpty(studentBos)||studentBos.size()==0) {
                    if (classId.equals(banji) && !StringUtil.isNullOrEmpty(classId)) {
                        studentService.add(studentBo);
                    } else {
                        studentService.add(studentBo);
                    }
                }

            }
        }
        Map<String,Object> resultMap=new HashMap<String, Object>();
        return resultMap;
    }
    /**
     * 日常课程表导入
     * @param path
     * @param classId
     * @return
     */
    @RequestMapping("/importExcelForTimetable.shtml")
    @ResponseBody
    public Map<String,Object> importExcelForTimetable(String path,String classId){
        ImportExecl poi = new ImportExecl();
        // List<List<String>> list = poi.read("d:/aaa.xls");
        String filepath=session.getServletContext().getRealPath("/");
        path=path.replaceAll("\r\n","");
        filepath=filepath+path;
        filepath=filepath.replaceAll("\\\\","/");
        List<List<String>> list = poi.read(filepath);
        if (list != null)
        {
            for (int i = 1; i < list.size(); i++)
            {
                TimetableBo timetableBo=new TimetableBo();
                String  week=list.get(i).get(0);//星期
                String gradeName=list.get(i).get(1).replace(".0", "");//班级
                // String teacher=list.get(i).get(2);//班主任
                String firstClass=list.get(i).get(2);//第1节课
                String secondClass=list.get(i).get(3);//第2节课
                String threeClass=list.get(i).get(4);//第2节课
                String fourClass=list.get(i).get(5);//第3节课
                String fiveClass=list.get(i).get(6);//第1节课
                String sixClass=list.get(i).get(7);//第1节课
                String sevenClass=list.get(i).get(8);//第1节课
                String eightClass=list.get(i).get(9);//第1节课
                String nineClass=list.get(i).get(10);//第1节课
                timetableBo.setWeek(week);
                timetableBo.setgradeName(gradeName);
                // timetableBo.setTeacher(teacher);
                timetableBo.setFirstClass(firstClass);
                timetableBo.setSecondClass(secondClass);
                timetableBo.setThreeClass(threeClass);
                timetableBo.setFourClass(fourClass);
                timetableBo.setFiveClass(fiveClass);
                timetableBo.setSixClass(sixClass);
                timetableBo.setSevenClass(sevenClass);
                timetableBo.setEightClass(eightClass);
                timetableBo.setNineClass(nineClass);
                if(!StringUtil.isNullOrEmpty(week)){
                    TimetableService.add(timetableBo);
                }

            }
        }
        Map<String,Object> resultMap=new HashMap<String, Object>();
        return resultMap;
    }
    /**
     * 单一课程表导入
     * @param path
     * @param gradeName
     * @return
     */
    @RequestMapping("/importExcelForTimetableSingle.shtml")
    @ResponseBody
    public Map<String,Object> importExcelForTimetableSingle(String path,String gradeName){
        ImportExecl poi = new ImportExecl();
        String filepath=session.getServletContext().getRealPath("/");
        path=path.replaceAll("\r\n","");
        filepath=filepath+path;
        filepath=filepath.replaceAll("\\\\","/");
        List<List<String>> list = poi.read(filepath);
        if (list != null)
        {
            for (int i = 1; i < list.size(); i++)
            {
                TimetableBo timetableBo=new TimetableBo();
                String  week=list.get(i).get(0);//星期
                //根据班级查询班主任
                Map<String,Object> queryMap=new HashMap<String, Object>();
                queryMap.put("name",gradeName);
                List<GradeBo> gradeBoList=gradeService.findAllList(queryMap);
                String teacher="";
                if(null!=gradeBoList&&gradeBoList.size()>0){
                    teacher=gradeBoList.get(0).getTeacherName();
                }
                //String teacher=list.get(i).get(2);//班主任
                String firstClass=list.get(i).get(1);//第1节课
                String secondClass=list.get(i).get(2);//第2节课
                String threeClass=list.get(i).get(3);//第3节课
                String fourClass=list.get(i).get(4);//第4节课
                String fiveClass=list.get(i).get(5);//第5节课
                String sixClass=list.get(i).get(6);//第6节课
                String sevenClass=list.get(i).get(7);//第7节课
                String eightClass=list.get(i).get(8);//第8节课
                String nineClass=list.get(i).get(9);//第9节课
                timetableBo.setWeek(week);
                timetableBo.setgradeName(gradeName);
                // timetableBo.setTeacher(teacher);
                timetableBo.setFirstClass(firstClass);
                timetableBo.setSecondClass(secondClass);
                timetableBo.setThreeClass(threeClass);
                timetableBo.setFourClass(fourClass);
                timetableBo.setFiveClass(fiveClass);
                timetableBo.setSixClass(sixClass);
                timetableBo.setSevenClass(sevenClass);
                timetableBo.setEightClass(eightClass);
                timetableBo.setNineClass(nineClass);
                if(!StringUtil.isNullOrEmpty(week)){
                    TimetableService.add(timetableBo);
                }

            }
        }
        Map<String,Object> resultMap=new HashMap<String, Object>();
        return resultMap;
    }

    /**
     * 导入教师表
     * @param path
     * @param gradeName
     * @return
     */
    @RequestMapping("/importExcelForTeachar.shtml")
    @ResponseBody
    public Map<String,Object> importExcelForTeachar(String path,String gradeName){
        ImportExecl poi = new ImportExecl();
        String filepath=session.getServletContext().getRealPath("/");
        path=path.replaceAll("\r\n","");
        filepath=filepath+path;
        filepath=filepath.replaceAll("\\\\","/");
        List<List<String>> list = poi.read(filepath);
        if (list != null)
        {
            for (int i = 1; i < list.size(); i++)
            {
                TeacherBo teacherBo=new TeacherBo();
                String  name=list.get(i).get(0);//老师
                String subjectId=list.get(i).get(1);//授课科目
                String classId=list.get(i).get(2).replace(".0","");//授课班级
                isExsitGrade(gradeName);
                //判断科目是否存在
                isExsitSubject(subjectId);
                teacherBo.setName(name);
                teacherBo.setSubjectId(subjectId);
                teacherBo.setClassId(classId);
                if(!StringUtil.isNullOrEmpty(classId)){
                    String gradeId=classId.substring(0,1);
                    teacherBo.setGradeId(gradeId);
                }
                if(!StringUtil.isNullOrEmpty(name)){
                    teacherService.add(teacherBo);
                }

            }
        }
        Map<String,Object> resultMap=new HashMap<String, Object>();
        return resultMap;
    }
    /**
     * 导入培优课程表 表格四:培优课程表
     * @param path
     * @param classId
     * @return
     */
    @RequestMapping("/importExcelForCourse.shtml")
    @ResponseBody
    public Map<String,Object> importExcelForCourse(String path,String classId){
        ImportExecl poi = new ImportExecl();
        // List<List<String>> list = poi.read("d:/aaa.xls");
        String filepath=session.getServletContext().getRealPath("/");
        path=path.replaceAll("\r\n","");
        filepath=filepath+path;
        filepath=filepath.replaceAll("\\\\","/");
        List<List<String>> list = poi.read(filepath);
        if (list != null)
        {
            for (int i = 1; i < list.size(); i++)
            {
                CourseBo bo=new CourseBo();
                String  week=list.get(i).get(0);//星期
                String schoolTime=list.get(i).get(1);//6:40-7:40针对这种时间格式前面执行补零操作
                if(schoolTime.length()==9){
                    schoolTime=schoolTime.substring(0,4)+"-0"+schoolTime.substring(5,9);
                    schoolTime="0"+schoolTime.replaceAll("：",":");
                }
                String classRoom=list.get(i).get(2).replace(".0", "");
                String teacher=list.get(i).get(3);//老师
                String courseName=list.get(i).get(4);//课程名
                String peopleMax=list.get(i).get(5).replace(".0","");//人数
                String kemu=list.get(i).get(6);//科目
                isExsitTeacher(teacher);
                isExsitSubject(kemu);


                isExsitClassRomm(classRoom);

                bo.setWeek(week);
                bo.setSchoolTime(schoolTime);
                bo.setClassRoom(classRoom);
                bo.setTeacher(teacher);
                bo.setKemu(kemu);
                bo.setPeopleMax(peopleMax);
                bo.setCourseName(courseName);
                if(!StringUtil.isNullOrEmpty(week)){
                    CourseService.add(bo);
                }

            }
        }
        Map<String,Object> resultMap=new HashMap<String, Object>();
        return resultMap;
    }

    private void isExsitSubject(String kemu) {
        //判断科目是否存在
        Map<String,Object> subjectMap=new HashMap<String, Object>();
        subjectMap.put("name",kemu);
        int subjectCount= SubjectService.getTotal(subjectMap);
        if(subjectCount==0){
            //新增科目
            SubjectBo subjectBo=new SubjectBo();
            subjectBo.setName(kemu);
        }
    }

    private void isExsitTeacher(String teacher) {
        //判断教师是否存在，如果不存在,就新增
        Map<String,Object> teacharMap=new HashMap<String, Object>();
        teacharMap.put("name",teacher);
        int teacherBosCount=teacherService.getTotal(teacharMap);
        if(teacherBosCount==0){
            TeacherBo teacherBo=new TeacherBo();
            teacherBo.setName(teacher);
        }
    }

    private void isExsitClassRomm(String classRoom) {
        //判断教室是否存在
        Map<String,Object> classRoomMap=new HashMap<String, Object>();
        classRoomMap.put("name",classRoom);
        int classRoomBCount=classRoomService.getTotal(classRoomMap);
        if(classRoomBCount==0){
            //新增教室
            ClassRoomBo classRoomBo=new ClassRoomBo();
            classRoomBo.setName(classRoom);
            classRoomService.add(classRoomBo);
        }
    }

    /**
     * 导入培优学生表 表格五：培优课学生表
     * @param path
     * @param classId
     * @return
     */
    @RequestMapping("/importExcelForPewStudent.shtml")
    @ResponseBody
    public Map<String,Object> importExcelForPewStudent(String path,String classId){
        ImportExecl poi = new ImportExecl();
        // List<List<String>> list = poi.read("d:/aaa.xls");
        String filepath=session.getServletContext().getRealPath("/");
        path=path.replaceAll("\r\n","");
        filepath=filepath+path;
        filepath=filepath.replaceAll("\\\\","/");
        List<List<String>> list = poi.read(filepath);
        if (list != null)
        {
            for (int i = 1; i < list.size(); i++)
            {
                PewStudentBo bo=new PewStudentBo();
                String  className=list.get(i).get(0).replace(".0","");
                isExsitGrade(className);
                //String name=list.get(i).get(1);
                String xhnum=list.get(i).get(1).replace(".0", "");
                //根据班级+学号判断学生是否存在,如果不存在，就新增 先暂时不做
               // String sex=list.get(i).get(3);
               // String type=list.get(i).get(4);
                String electiveCourse=list.get(i).get(2);
                String classRoom=list.get(i).get(3).replace(".0","");
                //判断教室是否存在，如果不存在，就新增
                isExsitClassRomm(classRoom);
                String seatNumer=list.get(i).get(4).replace(".0","");
                String schoolTime=list.get(i).get(5);
                isExsitTime(schoolTime);

                bo.setClassName(className);
               // bo.setName(name);
                bo.setXhnum(xhnum);
               // bo.setType(type);
                bo.setCourseType(CourseConstants.SEAT_STATUS_1+"");
                bo.setElectiveCourse(electiveCourse);
                isExsitSubjectCourse(electiveCourse);
                bo.setClassRoom(classRoom);
                bo.setSeatNumer(seatNumer);
                bo.setSchoolTime(schoolTime);
                bo.setStatus(CourseConstants.pew_shenhe_status_1);
                //判断是否已经存在，如果不存在再新增
                Map<String,Object> pewMap=new HashMap<String, Object>();
                pewMap.put("className",className);
                pewMap.put("xhnum",xhnum);
                pewMap.put("electiveCourse",electiveCourse);
                pewMap.put("classRoom",classRoom);
             //、、 List<PewStudentBo> pewStudentBoList=PewStudentService.findAllList(pewMap);
              //  if(null==pewStudentBoList||pewStudentBoList.size()==0){
                    if(!StringUtil.isNullOrEmpty(className)){
                        PewStudentService.add(bo);
                    }
               // }

            }
        }
        Map<String,Object> resultMap=new HashMap<String, Object>();
        return resultMap;
    }

    private void isExsitSubjectCourse(String electiveCourse) {
        //判断课程名是否存在，如果不存在，新增课程名到科目管理里面，并且类型为配有课程
        Map<String,Object> courseMap=new HashMap<String, Object>();
        courseMap.put("name",electiveCourse);
        courseMap.put("subjectClass",CourseConstants.SubjectClass_PYKC);
        int totalCourse=SubjectService.getTotal(courseMap);
        if(totalCourse==0){
            SubjectBo subjectBo=new SubjectBo() ;
            subjectBo.setName(electiveCourse);
            subjectBo.setSubjectClass(CourseConstants.SubjectClass_PYKC);
            SubjectService.add(subjectBo);
        }
    }

    private void isExsitGrade(String className) {
        Map<String,Object> gradeMap=new HashMap<String, Object>();
        //判断班级是否存在，如不存在，那么新增班级
        gradeMap.put("name",className);
        int gradeCount= gradeService.getTotal(gradeMap);
        if(gradeCount==0){
            //新增班级
            GradeBo gradeBo=new GradeBo();
            gradeBo.setName(className);
            gradeService.add(gradeBo);
        }
    }

    private void isExsitTime(String schoolTime) {
        //判断上课时段是否存在，如果不存在，那么新增
        Map<String,Object> schoolTimeMap=new HashMap<String, Object>();
        TimeBo timeBo=new TimeBo();
        int length=schoolTime.length();
        if(length>=14){
            String week=schoolTime.substring(0,3);
            schoolTimeMap.put("week",week);
            timeBo.setName(week);
            String start=schoolTime.substring(4,8);
            schoolTimeMap.put("startTime",start);
            timeBo.setStartTime(start);
            String end=schoolTime.substring(9,14);
            schoolTimeMap.put("endTime",end);
            timeBo.setEndTime(end);
            timeBo.setTime(start+"-"+end);
        }
        schoolTimeMap.put("type", CourseConstants.TIME_TYPE_1);
        timeBo.setType(CourseConstants.TIME_TYPE_1+"");
        int timeBoCount=TimeService.getTotal(schoolTimeMap);
        if(timeBoCount==0){
            TimeService.add(timeBo);
        }
    }

    /**
     * 导入教室
     * @param path
     * @param tbId 所在教学楼
     * @return
     */
    @RequestMapping("/importExcelForClassRoom.shtml")
    @ResponseBody
    public Map<String,Object> importExcelForClassRoom(String path,String tbId){
        ImportExecl poi = new ImportExecl();
        String filepath=session.getServletContext().getRealPath("/");
        path=path.replaceAll("\r\n","");
        filepath=filepath+path;
        filepath=filepath.replaceAll("\\\\","/");
        List<List<String>> list = poi.read(filepath);
        if (list != null)
        {
            for (int i = 1; i < list.size(); i++)
            {
                ClassRoomBo bo=new ClassRoomBo();
                String  name=list.get(i).get(0).replace(".0","");//教室名称
                String floors=list.get(i).get(1).replace(".0", "");
                String homeNum=list.get(i).get(2).replace(".0", "");
                String ip=list.get(i).get(3);
                if(!StringUtil.isNullOrEmpty(floors)){
                    bo.setFloors(floors);
                }
                bo.setName(name);
                bo.setHomeNum(homeNum);
                bo.setTbId(tbId);
                if(!StringUtil.isNullOrEmpty(name)){
                    classRoomService.add(bo);
                }

            }
        }
        Map<String,Object> resultMap=new HashMap<String, Object>();
        return resultMap;
    }
    /**
     * 导入培优座位表
     * @param path
     * @param courseId 课程id
     * @return
     */
    @RequestMapping("/importExcelForSeat.shtml")
    @ResponseBody
    public Map<String,Object> importExcelForSeat(String path,String courseId){
        ImportExecl poi = new ImportExecl();
        String filepath=session.getServletContext().getRealPath("/");
        path=path.replaceAll("\r\n","");
        filepath=filepath+path;
        filepath=filepath.replaceAll("\\\\","/");
        List<List<String>> list = poi.read(filepath);
        int totalRows= poi.getTotalRows();
        int totalCells=poi.getTotalCells();
        //根据课程id查询课程信息
        CourseBo currentCourse=CourseService.findById(courseId);
        if (list != null)
        {
            for (int i = 1; i < list.size(); i++)
            {

                for(int j=totalCells-1;j>=0;j--){
                    String name=list.get(i).get(j);//姓名
                    int seatNo=(totalCells-j)+(i-1)*totalCells;//座位号
                    SeatBo bo=new SeatBo();
                    bo.setName(name);
                    bo.setSeatNo(seatNo);
                    bo.setId(courseId);
                    bo.setRow(totalRows);
                    bo.setCell(totalCells);
                    bo.setSeatType(CourseConstants.SEAT_TYPE_1+"");//培优座位表
                    if(!StringUtil.isNullOrEmpty(currentCourse)){
                        bo.setClassRoom(currentCourse.getClassRoom());
                        bo.setWeek(currentCourse.getWeek());
                        String schoolTime=currentCourse.getSchoolTime();
                        bo.setSchoolTime(schoolTime);
                        if(!StringUtil.isNullOrEmpty(schoolTime)){
                            schoolTime= schoolTime.replaceAll("-","-");
                            String times[]=schoolTime.split("-");
                            if(times.length>1){
                                String start=times[0];//上课开始时间
                                String end=times[1];//上课结束时间
                                bo.setStart(start);
                                bo.setEnd(end);
                            }
                        }

                    }
                    if(!StringUtil.isNullOrEmpty(name)){
                        //导入座位表前,修改课程表的状态
                        CourseBo courseBo=new CourseBo();
                        courseBo.setId(Integer.parseInt(courseId));
                        courseBo.setSeatSetStatus(CourseConstants.SEAT_STATUS_1+"");
                        SeatService.add(bo);//先导入再更新状态
                        CourseService.update(courseBo);

                    }
                }
            }
        }
        Map<String,Object> resultMap=new HashMap<String, Object>();
        return resultMap;
    }
    /**
     * 导入日常座位表
     * @param path
     * @param gradeId 课程id
     * @return
     */
    @RequestMapping("/importExcelForGradeSeat.shtml")
    @ResponseBody
    public Map<String,Object> importExcelForGradeSeat(String path,String gradeId){
        ImportExecl poi = new ImportExecl();
        String filepath=session.getServletContext().getRealPath("/");
        path=path.replaceAll("\r\n","");
        filepath=filepath+path;
        filepath=filepath.replaceAll("\\\\","/");
        List<List<String>> list = poi.read(filepath);
        int totalRows= poi.getTotalRows();
        int totalCells=poi.getTotalCells();
        //根据班级id查询班级信息
        GradeBo gradeBo=gradeService.findById(gradeId);
        if (list != null)
        {
            for (int i = 1; i < list.size(); i++)
            {

                for(int j=totalCells-1;j>=0;j--){
                    String name=list.get(i).get(j);//姓名
                    int seatNo=(totalCells-j)+(i-1)*totalCells;//座位号
                    SeatBo bo=new SeatBo();
                    bo.setName(name);
                    bo.setSeatNo(seatNo);
                    //这里不能存放年级id，要存放教室名称如1501,1502
                   // GradeBo grade=gradeService.findById(gradeId);
                   // if(!StringUtil.isNullOrEmpty(grade)){
                        bo.setId(gradeId);
                   // }
                    bo.setRow(totalRows);
                    bo.setCell(totalCells);
                    if(!StringUtil.isNullOrEmpty(gradeBo)){
                        bo.setClassRoom(gradeBo.getName());
                        bo.setSeatType(CourseConstants.SEAT_TYPE_0+"");//日常座位表
                    }
                    //if(!StringUtil.isNullOrEmpty(name)){
                        //导入座位表前,修改班级是否导入座位表的状态
                        GradeBo g=new GradeBo();
                        g.setId(Integer.parseInt(gradeId));
                        g.setSeatStatus(CourseConstants.SEAT_STATUS_1 + "");//更改状态设置已导入
                            SeatService.add(bo);//先导入再更新状态
                            gradeService.update(g);

                    //}
                }
            }
        }
        Map<String,Object> resultMap=new HashMap<String, Object>();
        return resultMap;
    }
    /**
     * 读取Excel的内容，第一维数组存储的是一行中格列的值，二维数组存储的是多少个行
     * @return 读出的Excel中数据的内容
     * @throws FileNotFoundException
     * @throws IOException
     */


    public Map<String,Object> getData(String path)
    {
        int ignoreRows=1;
        String filepath=session.getServletContext().getRealPath("/");
        path=path.replaceAll("\r\n","");
        filepath=filepath+path;
        filepath=filepath.replaceAll("\\\\","/");
        File file = new File(filepath);
        List<String[]> result = new ArrayList<String[]>();
        int rowSize = 0;
        try{
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
            // 打开HSSFWorkbook
            POIFSFileSystem fs = new POIFSFileSystem(in);
            XSSFWorkbook wb = new XSSFWorkbook(String.valueOf(fs));
            XSSFCell cell = null;
            for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
                XSSFSheet st = wb.getSheetAt(sheetIndex);
                // 第一行为标题，不取
                for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
                    XSSFRow row = st.getRow(rowIndex);
                    if (row == null) {
                        continue;
                    }
                    int tempRowSize = row.getLastCellNum() + 1;
                    if (tempRowSize > rowSize) {
                        rowSize = tempRowSize;
                    }
                    String[] values = new String[rowSize];
                    Arrays.fill(values, "");
                    boolean hasValue = false;
                    for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
                        String value = "";
                        cell = row.getCell(columnIndex);
                        if (cell != null) {
                            // 注意：一定要设成这个，否则可能会出现乱码
                            //cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                            switch (cell.getCellType()) {
                                case XSSFCell.CELL_TYPE_STRING:
                                    value = cell.getStringCellValue();
                                    break;
                                case XSSFCell.CELL_TYPE_NUMERIC:
                                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                        Date date = cell.getDateCellValue();
                                        if (date != null) {
                                            value = new SimpleDateFormat("yyyy-MM-dd")
                                                    .format(date);
                                        } else {
                                            value = "";
                                        }
                                    } else {
                                        value = new DecimalFormat("0").format(cell
                                                .getNumericCellValue());
                                    }
                                    break;
                                case XSSFCell.CELL_TYPE_FORMULA:
                                    // 导入时如果为公式生成的数据则无值
                                    if (!cell.getStringCellValue().equals("")) {
                                        value = cell.getStringCellValue();
                                    } else {
                                        value = cell.getNumericCellValue() + "";
                                    }
                                    break;
                                case XSSFCell.CELL_TYPE_BLANK:
                                    break;
                                case XSSFCell.CELL_TYPE_ERROR:
                                    value = "";
                                    break;
                                case XSSFCell.CELL_TYPE_BOOLEAN:
                                    value = (cell.getBooleanCellValue() == true ? "Y"
                                            : "N");
                                    break;
                                default:
                                    value = "";
                            }
                        }
                        if (columnIndex == 0 && value.trim().equals("")) {
                            break;
                        }
                        values[columnIndex] = rightTrim(value);
                        hasValue = true;
                    }
                    if (hasValue) {
                        result.add(values);
                    }
                }
            }
            in.close();
            String[][] returnArray = new String[result.size()][rowSize];
            for (int i = 0; i < returnArray.length; i++) {
                returnArray[i] = (String[]) result.get(i);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        Map<String,Object> resultMap=new HashMap<String, Object>();
        return resultMap;
    }
    /**

     * 去掉字符串右边的空格
     * @param str 要处理的字符串
     * @return 处理后的字符串
     */

    public static String rightTrim(String str) {

        if (str == null) {
            return "";
        }
        int length = str.length();
        for (int i = length - 1; i >= 0; i--) {
            if (str.charAt(i) != 0x20) {
                break;
            }
            length--;
        }
        return str.substring(0, length);
    }

    /**
     * 导出座位表到excel
     */
    @RequestMapping("/exportSeatToExcel.shtml")
    @ResponseBody
    public Map<String,Object> exportSeatToExcel(String seatType,String id){
        Map<String,Object> result=new HashMap<String, Object>();
        // 第一步，创建一个webbook，对应一个Excel文件
        XSSFWorkbook  wb = new XSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
         Sheet sheet = wb.createSheet("座位表.xlsx");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
         Row row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
         CellStyle style  = wb.createCellStyle();
          Cell cell = row.createCell((short) 0);
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
        Map<String,Object> queryMap=new HashMap<String, Object>();
        queryMap.put("seatType",seatType);
        queryMap.put("id",id);
        List<SeatBo> list = SeatService.findAllList(queryMap);
        int rowCount=0;
        int columnCount=0;
        if(!StringUtil.isNullOrEmpty(list)&&list.size()>0){
            SeatBo seatBo=list.get(0);
             rowCount= seatBo.getRow();
             columnCount=seatBo.getCell();
            for(int i=1;i<=columnCount;i++){
                cell= row.createCell(i-1);
                cell.setCellValue("第" + SeatContants.bigLetters[columnCount-i+1] + "组");
                cell.setCellStyle(style);
            }
        }
        for (int i = 0; i <rowCount; i++)
        {
            row = sheet.createRow((int) i+1);
           // SeatBo seatBo = (SeatBo) list.get(i);
            // 第四步，创建单元格，并设置值
            for(int j=0;j<columnCount;j++){
                Cell c=row.createCell(j);
                c.setCellStyle(style);
                for(int m=0;m<list.size();m++){
                    int seatNum=(columnCount-j)+(i)*columnCount;//座位号
                    SeatBo seatBo=list.get(m);
                    if(seatNum==seatBo.getSeatNo()){
                        c.setCellValue(seatBo.getName());
                    }
                }

            }



        }
        // 第六步，将文件存到指定位置
        try
        {
            String path="seat.xlsx";
            String filepath=session.getServletContext().getRealPath("/");
            path=path.replaceAll("\r\n","");
            filepath=filepath+"/upload/"+path;
            filepath=filepath.replaceAll("\\\\","/");
            System.out.println(filepath);
            result.put("path","upload/"+path);
            FileOutputStream fout = new FileOutputStream(filepath);
            wb.write(fout);
            fout.close();
            result.put("success",true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }



    /**
     * 导出统计表
     */
    @RequestMapping("/exportTongjiToExcel.shtml")
    @ResponseBody
    public Map<String,Object> exportTongjiToExcel(){
        Map<String,Object> result=new HashMap<String, Object>();
        // 第一步，创建一个webbook，对应一个Excel文件
        XSSFWorkbook  wb = new XSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        Sheet sheet = wb.createSheet("统计表.xlsx");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        Row row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        CellStyle style  = wb.createCellStyle();
        Cell cell = row.createCell((short) 0);
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
        Map<String,Object> queryMap=new HashMap<String, Object>();
        List<AttendanceBo> list = attendanceService.findAllList(queryMap);
        // 创建一个workbook 对应一个excel应用文件
        XSSFWorkbook workBook = new XSSFWorkbook();
        // 在workbook中添加一个sheet,对应Excel文件中的sheet
        // 构建表头
        String [] titles={"卡号","课程","教室","打卡时间","考勤状态"};
        for (int i = 0; i < 5; i++)
        {
            cell = row.createCell(i);
            cell.setCellValue(titles[i]);
        }
        // 构建表体数据
        if (list != null && list.size() > 0)
        {
            for (int j = 0; j < list.size(); j++)
            {
                Row bodyRow = sheet.createRow(j + 1);
                AttendanceBo bo = list.get(j);

                cell = bodyRow.createCell(0);
                cell.setCellValue(bo.getCardNo());

                cell = bodyRow.createCell(1);
                cell.setCellValue(bo.getCourseName());

                cell = bodyRow.createCell(2);
                cell.setCellValue(bo.getClassRoom());

                cell = bodyRow.createCell(3);
                cell.setCellValue(bo.getSchoolTime());

                cell = bodyRow.createCell(4);
                String type=bo.getType();
                if("1".equals(type)) {
                    cell.setCellValue("迟到");
                }
                if("2".equals(type)) {
                    cell.setCellValue("请假");
                }
                if("3".equals(type)) {
                    cell.setCellValue("早退");
                }
                if("4".equals(type)) {
                    cell.setCellValue("缺勤");
                }
                if("5".equals(type)) {
                    cell.setCellValue("正常");
                }

            }
        }

    // 第六步，将文件存到指定位置
        try
        {
            String path="tongji.xlsx";
            String filepath=session.getServletContext().getRealPath("/");
            path=path.replaceAll("\r\n","");
            filepath=filepath+"/upload/"+path;
            filepath=filepath.replaceAll("\\\\","/");
            System.out.println(filepath);
            result.put("path","upload/"+path);
            FileOutputStream fout = new FileOutputStream(filepath);
            wb.write(fout);
            fout.close();
            result.put("success",true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

}

