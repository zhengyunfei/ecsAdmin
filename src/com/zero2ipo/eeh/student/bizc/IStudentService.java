package com.zero2ipo.eeh.student.bizc;

import com.zero2ipo.eeh.student.bo.StudentBo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/2/24.
 */
@Service
public interface IStudentService {
    public boolean add(StudentBo bo);
    public boolean update(StudentBo bo);
    public boolean delete(String id);
    public List<StudentBo> findAllList(Map<String, Object> queryMap);
    public List<StudentBo> findAllList(Map<String, Object> queryMap, int skip, int max);
    public int getTotal(Map<String, Object> queryMap);
    public StudentBo findById(String id);
}
