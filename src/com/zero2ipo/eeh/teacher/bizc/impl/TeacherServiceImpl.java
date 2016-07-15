package com.zero2ipo.eeh.teacher.bizc.impl;

import com.zero2ipo.eeh.grade.bizc.IGradeService;
import com.zero2ipo.eeh.grade.bo.GradeBo;
import com.zero2ipo.eeh.teacher.bizc.ITeacherService;
import com.zero2ipo.eeh.teacher.bo.TeacherBo;
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
@Service("teacherService")
public class TeacherServiceImpl implements ITeacherService {
    @Autowired
    @Qualifier("baseDao")
    private IBaseDao baseDao;
    @Autowired
    @Qualifier("gradeService")
    private IGradeService gradeService;
    public final static String common="Teacher";
    public final  static String ADD="add_"+common;
    public final static String  UPDATE="upd_"+common;
    public final static String DELETE="del_"+common;
    public final static String FINDBYID="find"+common+"ById";
    public final static String FINDALLLIST="find"+common+"List";
    public final static String FINDALLLISTCOUNT="find"+common+"ListCount";
    @Override
    public boolean add(TeacherBo bo) {
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
    public boolean update(TeacherBo bo) {
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
    public List<TeacherBo> findAllList(Map<String, Object> queryMap) {
        List<TeacherBo> list = null;
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
    public List<TeacherBo> findAllList(Map<String, Object> queryMap, int skip, int max) {
        List<TeacherBo> list = null;
        try {
            baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
            list = baseDao.findForList(FINDALLLIST, queryMap,skip,max);
            //根据班级1501查询年级表所在年级
            for(int i=0;i<list.size();i++){
                String banji=list.get(i).getClassId();
                Map<String,Object> gradeMap=new HashMap<String, Object>();
                gradeMap.put("name",banji);
                List<GradeBo> gradeBoList=gradeService.findAllList(gradeMap);
                if(!StringUtil.isNullOrEmpty(gradeBoList)&&gradeBoList.size()>0){
                    GradeBo gradeBo=gradeBoList.get(0);
                    list.get(i).setGradeBo(gradeBo);
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
    public TeacherBo findById(String id){
        TeacherBo bo=null;
        try {
            baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ;
            Map<String,Object> queryMap=new HashMap<String,Object>();
            queryMap.put("id", id);
            bo = (TeacherBo) baseDao.findForObject(FINDBYID, queryMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bo;
    }
}
