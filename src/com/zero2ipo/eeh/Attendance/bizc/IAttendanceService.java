package com.zero2ipo.eeh.Attendance.bizc;

import com.zero2ipo.eeh.Attendance.bo.AttendanceBo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/2/24.
 */
@Service
public interface IAttendanceService {
    public boolean add(AttendanceBo bo);
    public boolean update(AttendanceBo bo);
    public boolean delete(String id);
    public List<AttendanceBo> findAllList(Map<String, Object> queryMap);
    public List<AttendanceBo> findAllList(Map<String, Object> queryMap, int skip, int max);
    public int getTotal(Map<String, Object> queryMap);
    public AttendanceBo findById(String id);
}
