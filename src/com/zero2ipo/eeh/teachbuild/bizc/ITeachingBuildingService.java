package com.zero2ipo.eeh.teachbuild.bizc;

import com.zero2ipo.eeh.teachbuild.bo.TeachingBuildingBo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/2/24.
 */
@Service
public interface ITeachingBuildingService {
    public boolean add(TeachingBuildingBo bo);
    public boolean update(TeachingBuildingBo bo);
    public boolean delete(String id);
    public List<TeachingBuildingBo> findAllList(Map<String,Object> queryMap);
    public List<TeachingBuildingBo> findAllList(Map<String,Object> queryMap,int skip,int max);
    public int getTotal(Map<String,Object> queryMap);
    public TeachingBuildingBo findById(String id);
}
