package com.zero2ipo.eeh.course.bizc;

import com.zero2ipo.eeh.course.bo.CourseBo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/2/24.
 */
@Service
public interface ICourseService {
    public boolean add(CourseBo bo);
    public boolean update(CourseBo bo);
    public boolean delete(String id);
    public List<CourseBo> findAllList(Map<String, Object> queryMap);
    public List<CourseBo> findAllList(Map<String, Object> queryMap, int skip, int max);
    public int getTotal(Map<String, Object> queryMap);
    public CourseBo findById(String id);
}
