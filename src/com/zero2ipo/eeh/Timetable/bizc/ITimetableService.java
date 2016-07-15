package com.zero2ipo.eeh.Timetable.bizc;

import com.zero2ipo.eeh.Timetable.bo.TimetableBo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/2/24.
 */
@Service
public interface ITimetableService {
    public boolean add(TimetableBo bo);
    public boolean update(TimetableBo bo);
    public boolean delete(String id);
    public List<TimetableBo> findAllList(Map<String, Object> queryMap);
    public List<TimetableBo> findAllList(Map<String, Object> queryMap, int skip, int max);
    public int getTotal(Map<String, Object> queryMap);
    public TimetableBo findById(String id);
}
