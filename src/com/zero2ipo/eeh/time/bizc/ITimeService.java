package com.zero2ipo.eeh.time.bizc;

import com.zero2ipo.eeh.time.bo.TimeBo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/2/24.
 */
@Service
public interface ITimeService {
    public boolean add(TimeBo bo);
    public boolean update(TimeBo bo);
    public boolean delete(String id);
    public List<TimeBo> findAllList(Map<String, Object> queryMap);
    public List<TimeBo> findAllList(Map<String, Object> queryMap, int skip, int max);
    public int getTotal(Map<String, Object> queryMap);
    public TimeBo findById(String id);
}
