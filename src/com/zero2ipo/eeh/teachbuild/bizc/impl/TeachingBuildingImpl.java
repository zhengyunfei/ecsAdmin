package com.zero2ipo.eeh.teachbuild.bizc.impl;

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
@Service("teachingBuildingService")
public class TeachingBuildingImpl implements ITeachingBuildingService {

    @Autowired
    @Qualifier("baseDao")
    private IBaseDao baseDao;

    @Override
    public boolean add(TeachingBuildingBo bo) {
        boolean flag=false;
        try{
            baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
            baseDao.addObject("add_teaching_building", bo);
            flag=true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean update(TeachingBuildingBo bo) {
        boolean flag=false;
        try{
            baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
            baseDao.updObject("upd_teaching_building", bo);
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
            baseDao.delObject("del_teaching_building", map);
            // 删除成功
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            BaseLog.e(this.getClass(), "del_teaching_building 删除出错", e);
            throw new BaseException("删除出错！", e);
        }
        return flag;
    }

    @Override
    public List<TeachingBuildingBo> findAllList(Map<String, Object> queryMap) {
        List<TeachingBuildingBo> list = null;
        try {
            baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
            list = baseDao.findForList("findEeh_teachingbuildingList", queryMap);
        } catch (Exception e) {
            e.printStackTrace();
            BaseLog.e(this.getClass(), "findAllList 查询列表", e);
            throw new BaseException("查询列表出错！", e);
        }
        return list;
    }

    @Override
    public List<TeachingBuildingBo> findAllList(Map<String, Object> queryMap, int skip, int max) {
        List<TeachingBuildingBo> list = null;
        try {
            baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
            list = baseDao.findForList("findEeh_teachingbuildingList", queryMap,skip,max);
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
                    .findForObject("findteachingbuildingListCount", queryMap);
        } catch (Exception e) {
            BaseLog.e(this.getClass(), "findteachingbuildingListCount查询个数", e);
        }
        return count;
    }
    public TeachingBuildingBo findById(String id){
        TeachingBuildingBo bo=null;
        try {
            baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ;
            Map<String,Object> queryMap=new HashMap<String,Object>();
            queryMap.put("id", id);
            bo = (TeachingBuildingBo) baseDao.findForObject("findteachingbuildingById", queryMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bo;
    }
}
