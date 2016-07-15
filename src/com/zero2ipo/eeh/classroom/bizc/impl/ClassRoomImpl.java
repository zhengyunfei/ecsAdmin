package com.zero2ipo.eeh.classroom.bizc.impl;

import com.zero2ipo.eeh.classroom.bizc.IClassRoomService;
import com.zero2ipo.eeh.classroom.bo.ClassRoomBo;
import com.zero2ipo.eeh.teachbuild.bizc.ITeachingBuildingService;
import com.zero2ipo.eeh.teachbuild.bo.TeachingBuildingBo;
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
@Service("classRoomService")
public class ClassRoomImpl implements IClassRoomService {

    @Autowired
    @Qualifier("baseDao")
    private IBaseDao baseDao;
    @Autowired
    @Qualifier("teachingBuildingService")
    private ITeachingBuildingService teachingBuildingService;
    @Override
    public boolean add(ClassRoomBo bo) {
        boolean flag=false;
        try{
            baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
            baseDao.addObject("add_ClassRoom", bo);
            flag=true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean update(ClassRoomBo bo) {
        boolean flag=false;
        try{
            baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
            baseDao.updObject("upd_ClassRoom", bo);
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
            baseDao.delObject("del_ClassRoom", map);
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
    public List<ClassRoomBo> findAllList(Map<String, Object> queryMap) {
        List<ClassRoomBo> list = null;
        try {
            baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
            list = baseDao.findForList("findClassRoomList", queryMap);
        } catch (Exception e) {
            e.printStackTrace();
            BaseLog.e(this.getClass(), "findAllList 查询列表", e);
            throw new BaseException("查询列表出错！", e);
        }
        return list;
    }

    @Override
    public List<ClassRoomBo> findAllList(Map<String, Object> queryMap, int skip, int max) {
        List<ClassRoomBo> list = null;
        try {
            baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
            list = baseDao.findForList("findClassRoomList", queryMap,skip,max);
            int size=list.size();
            for(int i=0;i<size;i++){
                String tbId=list.get(i).getTbId();
                //根据教学楼id查询教学楼名称
                TeachingBuildingBo teachingBuildingBo=teachingBuildingService.findById(tbId);
                list.get(i).setTeachingBuildingBo(teachingBuildingBo);
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
                    .findForObject("findClassRoomListCount", queryMap);
        } catch (Exception e) {
            BaseLog.e(this.getClass(), "findUserCouponListCount查询优惠券支付个数", e);
        }
        return count;
    }
    public ClassRoomBo findById(String id){
        ClassRoomBo bo=null;
        try {
            baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ;
            Map<String,Object> queryMap=new HashMap<String,Object>();
            queryMap.put("id", id);
            bo = (ClassRoomBo) baseDao.findForObject("findClassRoomById", queryMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bo;
    }
}
