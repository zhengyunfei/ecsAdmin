package com.zero2ipo.eeh.grade.bizc;

import com.zero2ipo.eeh.grade.bo.GradeBo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/2/24.
 */
@Service
public interface IGradeService {
    public boolean add(GradeBo bo);
    public boolean update(GradeBo bo);
    public boolean delete(String id);
    public List<GradeBo> findAllList(Map<String, Object> queryMap);
    public List<GradeBo> findAllList(Map<String, Object> queryMap, int skip, int max);
    public int getTotal(Map<String, Object> queryMap);
    public GradeBo findById(String id);
}
