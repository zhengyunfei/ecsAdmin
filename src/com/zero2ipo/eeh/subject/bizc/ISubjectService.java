package com.zero2ipo.eeh.subject.bizc;

import com.zero2ipo.eeh.subject.bo.SubjectBo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/2/24.
 */
@Service
public interface ISubjectService {
    public boolean add(SubjectBo bo);
    public boolean update(SubjectBo bo);
    public boolean delete(String id);
    public List<SubjectBo> findAllList(Map<String, Object> queryMap);
    public List<SubjectBo> findAllList(Map<String, Object> queryMap, int skip, int max);
    public int getTotal(Map<String, Object> queryMap);
    public SubjectBo findById(String id);
}
