package com.zero2ipo.eeh.Timetable.web;

import com.zero2ipo.common.web.BaseCtrl;
import com.zero2ipo.eeh.Timetable.bizc.ITimetableService;
import com.zero2ipo.eeh.Timetable.bo.TimetableBo;
import com.zero2ipo.eeh.Timetable.bo.TimetableContants;
import com.zero2ipo.eeh.grade.bizc.IGradeService;
import com.zero2ipo.eeh.grade.bo.GradeBo;
import com.zero2ipo.framework.exception.BaseException;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.framework.util.StringUtil;
import com.zero2ipo.plugins.role.biz.ISysRole;
import com.zero2ipo.plugins.role.bo.SysRole;
import com.zero2ipo.plugins.role.bo.UserRoleRela;
import com.zero2ipo.plugins.user.bo.User;
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
 * 教学楼ctrl
 * Created by Administrator on 2016/2/24.
 */
@Controller
@RequestMapping("/Timetable")
public class TimetableCtrl extends BaseCtrl {
    @Autowired
    @Qualifier("TimetableService")
    private ITimetableService TimetableService ;
    @Autowired
    @Qualifier("gradeService")
    private IGradeService gradeService;
    @Autowired
    @Qualifier("sysRole")
    private ISysRole sysRole;
    /**
     * 初始化页面
     * @return
     */
    @RequestMapping("forInit.shtml")
    public ModelAndView forInitPage() {
        ModelAndView mv = new ModelAndView("/eeh/Timetable/list.jsp");
        //首先判断当前登陆的用户的权限
       Object o=session.getAttribute("user");
        if(o instanceof User){
            User u=(User)o;
            String userId=u.getUserId();//根据userId查询roleId
            Map<String,Object> queryMap=new HashMap<String, Object>();
            queryMap.put("userId",userId);
            List<UserRoleRela> userRoleRelaList=sysRole.findUserRoleListByRoleId(queryMap);
            for(int i=0;i<userRoleRelaList.size();i++){
                String roleId=userRoleRelaList.get(i).getRoleId();
                //根据roleId查询角色
                SysRole role=sysRole.findRoleById(roleId);
                if(!StringUtil.isNullOrEmpty(role)){
                    String roleName=role.getRoleName();
                    if(roleName.equals(TimetableContants.super_name_3)){//班主任权限
                        mv.setViewName("/eeh/Timetable/teacherList.jsp");
                        break;
                    }
                }
            }

        }
        //查询所有班级列表
        getSelectList(mv);
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
    /**
     * 初始化json数据
     * @param curNo
     * @param curSize
     * @return
     */
    @RequestMapping("findAllList.shtml")
    @ResponseBody
    public Map<String,Object> findAllList(String curNo, String curSize,String gradeName){
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
            if(!StringUtil.isNullOrEmpty(gradeName)){
                map.put("gradeName",gradeName);
            }
            int total=0;
            total=TimetableService.getTotal(map);
            List<TimetableBo> list= null;
            if(total>0){
                list = TimetableService.findAllList(map, (skip-1)*max, max);
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
        ModelAndView mv = new ModelAndView("/eeh/Timetable/add.jsp");
        return mv;
    }
    /**
     * 添加保存数据
     * @param bo
     * @return
     */
    @RequestMapping("forAddAjax.shtml")
    @ResponseBody
    public Map<String,Object> addSave(TimetableBo bo) {
        Map<String,Object> result=new HashMap<String,Object>();
        boolean flg=false;
        try {
            flg=TimetableService.add(bo);
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
        ModelAndView mv = new ModelAndView("/eeh/Timetable/update.jsp");
        TimetableBo bo=TimetableService.findById(id);
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
    public Map<String,Object> updateSave(TimetableBo bo) {
        Map<String,Object> result=new HashMap<String,Object>();
        boolean flg=false;
        try {
            flg=TimetableService.update(bo);
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
            flg=TimetableService.delete(ids);
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
