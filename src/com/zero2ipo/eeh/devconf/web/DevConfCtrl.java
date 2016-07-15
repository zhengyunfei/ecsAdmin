package com.zero2ipo.eeh.devconf.web;

import com.zero2ipo.common.web.BaseCtrl;
import com.zero2ipo.eeh.devconf.bizc.IDevConfService;
import com.zero2ipo.eeh.devconf.bo.DevConfBo;
import com.zero2ipo.eeh.grade.bizc.IGradeService;
import com.zero2ipo.eeh.grade.bo.GradeBo;
import com.zero2ipo.eeh.subject.bizc.ISubjectService;
import com.zero2ipo.eeh.subject.bo.SubjectBo;
import com.zero2ipo.framework.exception.BaseException;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.framework.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 设备配置
 * Created by Administrator on 2016/2/24.
 */
@Controller
@RequestMapping("/DevConf")
public class DevConfCtrl extends BaseCtrl {
    @Autowired
    @Qualifier("DevConfService")
    private IDevConfService DevConfService ;
    @Autowired
    @Qualifier("gradeService")
    private IGradeService gradeService;
    @Autowired
    @Qualifier("SubjectService")
    private ISubjectService SubjectService;
    /**
     * 考勤列表
     * @return
     */
    @RequestMapping("forKaoqinInit.shtml")
    public ModelAndView forKaoqinInit() {
        ModelAndView mv = new ModelAndView("/eeh/DevConf/kaoqin.jsp");
        getSelectListByCourse(mv);
        return mv;
    }
    /**
     * 按年级统计
     * @return
     */
    @RequestMapping("forInit.shtml")
    public ModelAndView forInitPage() {
        ModelAndView mv = new ModelAndView("/eeh/DevConf/list.jsp");
        getSelectList(mv);
        return mv;
    }

    /**
     * 按课程统计
     * @return
     */
    @RequestMapping("forInitForCourse.shtml")
    public ModelAndView forInitForCourse() {
        ModelAndView mv = new ModelAndView("/eeh/DevConf/listByCourse.jsp");
        getSelectListByCourse(mv);
        return mv;
    }
    private void getSelectList(ModelAndView mv) {
        //查询所有班级
        Map<String,Object> queryMap=new HashMap<String, Object>();
        List<GradeBo> list=gradeService.findAllList(queryMap);
        if(!StringUtil.isNullOrEmpty(list)){
            mv.addObject("gradeList",list);
        }
    }
    private void getSelectListByCourse(ModelAndView mv) {
        //查询所有科目
        Map<String,Object> queryMap=new HashMap<String, Object>();
        List<SubjectBo> list=SubjectService.findAllList(queryMap);
        if(!StringUtil.isNullOrEmpty(list)){
            mv.addObject("subjectList",list);
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
    public Map<String,Object> findAllList(String curNo, String curSize,String className,String name,String sex,String type,String courseName){
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
            if(!StringUtil.isNullOrEmpty(className)){
                map.put("courseName",className);
            }
            if(!StringUtil.isNullOrEmpty(name)){
                map.put("name",name);
            }
            if(!StringUtil.isNullOrEmpty(sex)){
                map.put("sex",sex);
            }
            if(!StringUtil.isNullOrEmpty(type)){
                map.put("type",type);
            }
            if(!StringUtil.isNullOrEmpty(courseName)){
                map.put("courseName",courseName);
            }
            int total=0;
            total=DevConfService.getTotal(map);
            List<DevConfBo> list= null;
            if(total>0){
                list = DevConfService.findAllList(map, (skip-1)*max, max);
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
    public ModelAndView forAddInitPage() {
        ModelAndView mv = new ModelAndView("/eeh/DevConf/add.jsp");
        return mv;
    }
    /**
     * 添加保存数据
     * @param bo
     * @return
     */
    @RequestMapping("forAddAjax.shtml")
    @ResponseBody
    public Map<String,Object> addSave(DevConfBo bo) {
        Map<String,Object> result=new HashMap<String,Object>();
        boolean flg=false;
        try {
            flg=DevConfService.add(bo);
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
        ModelAndView mv = new ModelAndView("/eeh/DevConf/update.jsp");
        DevConfBo bo=DevConfService.findById(id);
        mv.addObject("bo",bo);
        return mv;
    }
    /**
     * 修改保存数据
     * @param bo
     * @return
     */
    @RequestMapping("forUpdateAjax.shtml")
    @ResponseBody
    public Map<String,Object> updateSave(DevConfBo bo) {
        Map<String,Object> result=new HashMap<String,Object>();
        boolean flg=false;
        try {
            flg=DevConfService.update(bo);
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
            flg=DevConfService.delete(ids);
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
