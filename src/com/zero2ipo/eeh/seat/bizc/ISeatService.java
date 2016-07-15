package com.zero2ipo.eeh.seat.bizc;

import com.zero2ipo.eeh.seat.bo.SeatBo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/2/24.
 */
@Service
public interface ISeatService {
    public boolean add(SeatBo bo);
    public boolean update(SeatBo bo);
    public boolean delete(String id);
    public List<SeatBo> findAllList(Map<String, Object> queryMap);
    public List<SeatBo> findAllList(Map<String, Object> queryMap, int skip, int max);
    public int getTotal(Map<String, Object> queryMap);
    public SeatBo findById(String id);
    public boolean deleteByMap(SeatBo bo);
}
