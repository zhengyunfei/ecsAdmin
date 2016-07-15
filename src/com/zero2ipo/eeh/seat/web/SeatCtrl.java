package com.zero2ipo.eeh.seat.web;

import com.zero2ipo.common.web.BaseCtrl;
import com.zero2ipo.eeh.course.bizc.ICourseService;
import com.zero2ipo.eeh.grade.bizc.IGradeService;
import com.zero2ipo.eeh.grade.bo.GradeBo;
import com.zero2ipo.eeh.seat.bizc.ISeatService;
import com.zero2ipo.eeh.seat.bo.SeatBo;
import com.zero2ipo.eeh.seat.bo.SeatContants;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教学楼ctrl
 * Created by Administrator on 2016/2/24.
 */
@Controller
@RequestMapping("/Seat")
public class SeatCtrl extends BaseCtrl {
    @Autowired
    @Qualifier("SeatService")
    private ISeatService SeatService ;
    @Autowired
    @Qualifier("CourseService")
    private ICourseService CourseService;
    @Autowired
    @Qualifier("gradeService")
    private IGradeService gradeService;
    @Autowired
    @Qualifier("SubjectService")
    private ISubjectService SubjectService;

    /**
     * 日常座位表访问入口
     * @return
     */
    @RequestMapping("forInit.shtml")
    public ModelAndView forInitPage() {
        ModelAndView mv = new ModelAndView("/eeh/Seat/list.jsp");
        getSelectList(mv);
        return mv;
    }
    /**
     * 培优座位表访问入口
     * @return
     */
    @RequestMapping("forPewInit.shtml")
    public ModelAndView forPewInitPage() {
        ModelAndView mv = new ModelAndView("/eeh/Seat/pewList.jsp");
        getSelectList(mv);
        getSelectListByCourse(mv);
        return mv;
    }
    /**
     *培优座位ajax加载数据
     * @param curNo
     * @param curSize
     * @return
     */
    @RequestMapping("findAllList.shtml")
    @ResponseBody
    public Map<String,Object> findAllList(String curNo, String curSize){
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

            int total=0;
            total=SeatService.getTotal(map);
            List<SeatBo> list= null;
            if(total>0){
                list = SeatService.findAllList(map, (skip-1)*max, max);
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
     * 培优座位表设置
     * @return
     */
    @RequestMapping("forAddInitPage.shtml")
    public ModelAndView forAddInitPage(String id) {
        ModelAndView mv = new ModelAndView("/eeh/Seat/add.jsp");
        if(!StringUtil.isNullOrEmpty(id)){
            mv.addObject("id",id);//课程表id，courseId
        }
        return mv;
    }

    /**
     * 日常座位表设置
     * @return
     */
    @RequestMapping("setSeat.shtml")
    public ModelAndView setSeat(String id) {
        ModelAndView mv = new ModelAndView("/eeh/Seat/setSeat.jsp");
        if(!StringUtil.isNullOrEmpty(id)){
            mv.addObject("id",id);//课程表id，courseId
        }
        return mv;
    }
    /**
     * 日常座位表修改
     * @return
     */
    @RequestMapping("updSeat.shtml")
    public ModelAndView updSeat(String id,String seatType) {
        ModelAndView mv = new ModelAndView("/eeh/Seat/updSeat.jsp");
        if(!StringUtil.isNullOrEmpty(id)){
            mv.addObject("id",id);//课程表id，courseId
            mv.addObject("seatType",seatType);
        }
        return mv;
    }
    /******************************************查询 ************************************************************************/
    /**
     *查询所有班级
     * @param mv
     */
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
    /***************************************新增**************************************************************************/
    /**
     * 添加保存数据
     * @param bo
     * @return
     */
    @RequestMapping("forAddAjax.shtml")
    @ResponseBody
    public Map<String,Object> addSave(SeatBo bo) {
        Map<String,Object> result=new HashMap<String,Object>();
        boolean flg=false;
        try {
            flg=SeatService.add(bo);
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
        ModelAndView mv = new ModelAndView("/eeh/Seat/update.jsp");
        SeatBo bo=SeatService.findById(id);
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
    public Map<String,Object> updateSave(SeatBo bo) {
        Map<String,Object> result=new HashMap<String,Object>();
        boolean flg=false;
        try {
            flg=SeatService.update(bo);
        } catch (Exception e) {
            flg=false;
            e.printStackTrace();
            BaseLog.e(this.getClass(), "forUpdateAjax", e);
            throw new BaseException("forUpdateAjax 异常！");
        }
        result.put("success", flg);
        return result;
    }
    /******************************************查看************************************************************/
    @RequestMapping("forLookSeat.shtml")
    public ModelAndView forLookSeat(String id,String type) {
        ModelAndView result=new ModelAndView();
        result.setViewName("/eeh/Seat/lookSeat.jsp");
        List<SeatBo> list=new ArrayList<SeatBo>();
        List<String> heads=new ArrayList<String>();
        List<Integer> rows=new ArrayList<Integer>();
        Map<String,Object> queryMap=new HashMap<String, Object>();
        queryMap.put("id",id);
        queryMap.put("seatType",type);
        list=SeatService.findAllList(queryMap);
        result.addObject("seatList",list);
        int totalCells=0;
        int totalRows=0;
        if(list.size()>0){
            totalCells=list.get(0).getCell();
            totalRows=list.get(0).getRow();
            result.addObject("totalCells",totalCells);
            for(int i=totalCells;i>=1;i--){
                heads.add("第"+ SeatContants.bigLetters[i]+"组");
            }
            for(int i=1;i<totalRows;i++){
                rows.add(i);
            }
            result.addObject("headsList",heads);
            result.addObject("rows",totalRows);
            result.addObject("rowsList",rows);
            result.addObject("cells",totalCells);
        }

        return result;
    }
    /******************************************删除操作*********************************************************/

    @RequestMapping("delSave.shtml")
    @ResponseBody
    public Map<String,Object> delSave(String ids) {
        Map<String,Object> result=new HashMap<String,Object>();
        boolean flg=false;
        try {
            flg=SeatService.delete(ids);
        } catch (Exception e) {
            flg=false;
            e.printStackTrace();
            BaseLog.e(this.getClass(), "delSave", e);
            throw new BaseException("delSave 异常！");
        }
        result.put("success", flg);
        return result;
    }

    /**
     * 按条件删除
     * @param bo
     * @return
     */
    @RequestMapping("delByMap.shtml")
    @ResponseBody
    public Map<String,Object> delByMap(SeatBo bo) {
        Map<String,Object> result=new HashMap<String,Object>();
        boolean flg=false;
        try {
            flg=SeatService.deleteByMap(bo);
        } catch (Exception e) {
            flg=false;
            e.printStackTrace();
           // BaseLog.e(this.getClass(), "delSave", e);
            throw new BaseException("delSave 异常！");
        }
        result.put("success", flg);
        return result;
    }

}
