package com.zero2ipo.eeh.pewstudent.bizc.impl;

import com.zero2ipo.eeh.pewstudent.bizc.IPewStudentService;
import com.zero2ipo.eeh.pewstudent.bo.PewStudentBo;
import com.zero2ipo.eeh.student.bizc.IStudentService;
import com.zero2ipo.eeh.student.bo.StudentBo;
import com.zero2ipo.framework.FwConstant;
import com.zero2ipo.framework.db.bizc.IBaseDao;
import com.zero2ipo.framework.exception.BaseException;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.framework.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/2/24.
 */
@Service("PewStudentService")
public class PewStudentImpl implements IPewStudentService {
    @Autowired
    @Qualifier("baseDao")
    private IBaseDao baseDao;
    @Autowired
    @Qualifier("studentService")
    private IStudentService studentService;
    public final static String common="PewStudent";
    public final  static String ADD="add_"+common;
    public final static String  UPDATE="upd_"+common;
    public final static String DELETE="del_"+common;
    public final static String FINDBYID="find"+common+"ById";
    public final static String FINDALLLIST="find"+common+"List";
    public final static String FINDALLLISTCOUNT="find"+common+"ListCount";
    @Override
    public boolean add(PewStudentBo bo) {
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
    public boolean update(PewStudentBo bo) {
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
            baseDao.delObject(DELETE, map);
            // 删除成功
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            BaseLog.e(this.getClass(), "del_ClassRoom 删除出错", e);
            throw new BaseException("删除出错！", e);
        }
        return flag;
    }

    @Override
    public List<PewStudentBo> findAllList(Map<String, Object> queryMap) {
        List<PewStudentBo> list = null;
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
    public List<PewStudentBo> findAllList(Map<String, Object> queryMap, int skip, int max) {
        List<PewStudentBo> list = null;
        try {
            baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
            list = baseDao.findForList(FINDALLLIST, queryMap,skip,max);
            //根据班级+学号 查询学生表
            for(int i=0;i<list.size();i++){
                PewStudentBo bo=list.get(i);
                String className=bo.getClassName();
                String xhnum=bo.getXhnum();
                Map<String,Object> studentMap=new HashMap<String, Object>();
                studentMap.put("classId",className);
                studentMap.put("xhnum",xhnum);
                List<StudentBo> studentBos=studentService.findAllList(studentMap);
                if(!StringUtil.isNullOrEmpty(studentBos)&&studentBos.size()>0){
                    StudentBo studentBo=studentBos.get(0);
                    list.get(i).setName(studentBo.getName());
                    list.get(i).setXhnum(studentBo.getXhnum());
                    list.get(i).setSex(studentBo.getSex());
                    list.get(i).setType(studentBo.getStype());
                }
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
            BaseLog.e(this.getClass(), "findUserCouponListCount查询优惠券支付个数", e);
        }
        return count;
    }
    public PewStudentBo findById(String id){
        PewStudentBo bo=null;
        try {
            baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ;
            Map<String,Object> queryMap=new HashMap<String,Object>();
            queryMap.put("id", id);
            bo = (PewStudentBo) baseDao.findForObject(FINDBYID, queryMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bo;
    }
}
