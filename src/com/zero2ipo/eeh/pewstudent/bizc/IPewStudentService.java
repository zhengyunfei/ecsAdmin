package com.zero2ipo.eeh.pewstudent.bizc;

import com.zero2ipo.eeh.pewstudent.bo.PewStudentBo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/2/24.
 */
@Service
public interface IPewStudentService {
    public boolean add(PewStudentBo bo);
    public boolean update(PewStudentBo bo);
    public boolean delete(String id);
    public List<PewStudentBo> findAllList(Map<String, Object> queryMap);
    public List<PewStudentBo> findAllList(Map<String, Object> queryMap, int skip, int max);
    public int getTotal(Map<String, Object> queryMap);
    public PewStudentBo findById(String id);
}
