package com.zero2ipo.eeh.teacher.bizc;

import com.zero2ipo.eeh.teacher.bo.TeacherBo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/2/24.
 */
@Service
public interface ITeacherService {
    public boolean add(TeacherBo bo);
    public boolean update(TeacherBo bo);
    public boolean delete(String id);
    public List<TeacherBo> findAllList(Map<String, Object> queryMap);
    public List<TeacherBo> findAllList(Map<String, Object> queryMap, int skip, int max);
    public int getTotal(Map<String, Object> queryMap);
    public TeacherBo findById(String id);
}
