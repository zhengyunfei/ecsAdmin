package com.zero2ipo.eeh.grade.bizc.impl;

import com.zero2ipo.eeh.grade.bizc.IGradeService;
import com.zero2ipo.eeh.grade.bo.GradeBo;
import com.zero2ipo.eeh.student.bizc.IStudentService;
import com.zero2ipo.framework.FwConstant;
import com.zero2ipo.framework.db.bizc.IBaseDao;
import com.zero2ipo.framework.exception.BaseException;
import com.zero2ipo.framework.log.BaseLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/2/24.
 */
@Service("gradeService")
public class GradeServiceImpl implements IGradeService {

    @Autowired
    @Qualifier("baseDao")
    private IBaseDao baseDao;
    @Autowired
    @Qualifier("studentService")
    private IStudentService studentService;
    public final  static String ADD="add_Grade";
    public final static String  UPDATE="upd_Grade";
    public final static String DELETE="del_Grade";
    public final static String FINDBYID="findGradeById";
    public final static String FINDALLLIST="findGradeList";
    public final static String FINDALLLISTCOUNT="findGradeListCount";
    public final static String DELETE_STUDENT="del_Student_By_GradeIds";
    @Override
    public boolean add(GradeBo bo) {
        boolean flag=false;
        try{
            baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
            baseDao.addObject(ADD, bo);
            flag=true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean update(GradeBo bo) {
        boolean flag=false;
        try{
            baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
            baseDao.updObject(UPDATE, bo);
            flag=true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean delete(String ids) {
        boolean flag=false;
        try {
            Map map = new HashMap();
            map.put("id", ids.split(","));
            baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
            //先删除该年级下面的学生信息
            baseDao.delObject(DELETE_STUDENT, map);
            // 删除成功
            flag = true;
            if(flag){
                //再删除该年级
                baseDao.delObject(DELETE, map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            BaseLog.e(this.getClass(), "del_teaching_building 删除出错", e);
            throw new BaseException("删除出错！", e);
        }
        return flag;
    }

    @Override
    public List<GradeBo> findAllList(Map<String, Object> queryMap) {
        List<GradeBo> list = null;
        try {
            baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
            list = baseDao.findForList(FINDALLLIST, queryMap);
        } catch (Exception e) {
            e.printStackTrace();
            BaseLog.e(this.getClass(), "findAllList 查询列表", e);
            throw new BaseException("查询列表出错！", e);
        }
        return list;
    }

    @Override
    public List<GradeBo> findAllList(Map<String, Object> queryMap, int skip, int max) {
        List<GradeBo> list = null;
        try {
            baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
            list = baseDao.findForList(FINDALLLIST, queryMap,skip,max);
            //查询班级实际人数
            int total=list.size();
            for(int i=0;i<total;i++){
                GradeBo bo=list.get(i);
                String  name=bo.getName();
                //根据班级名称查询其总人数
                Map<String,Object> studentMap=new HashMap<String, Object>();
                studentMap.put("classId",name);
                int size=studentService.getTotal(studentMap);
                bo.setPeopleCount(size);
            }
        } catch (Exception e) {
            e.printStackTrace();
            BaseLog.e(this.getClass(), "findAllList 查询列表", e);
            throw new BaseException("查询列表出错！", e);
        }
        return list;
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        int count = 0;
        try {
            count = (Integer) baseDao
                    .findForObject(FINDALLLISTCOUNT, queryMap);
        } catch (Exception e) {
            e.printStackTrace();
            BaseLog.e(this.getClass(), "findGradeListCount查询优惠券支付个数", e);
        }
        return count;
    }
    public GradeBo findById(String id){
        GradeBo bo=null;
        try {
            baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ;
            Map<String,Object> queryMap=new HashMap<String,Object>();
            queryMap.put("id", id);
            bo = (GradeBo) baseDao.findForObject(FINDBYID, queryMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bo;
    }
}
