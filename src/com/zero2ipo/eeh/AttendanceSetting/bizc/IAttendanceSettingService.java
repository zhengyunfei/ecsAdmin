package com.zero2ipo.eeh.AttendanceSetting.bizc;

import com.zero2ipo.eeh.AttendanceSetting.bo.AttendanceSettingBo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/2/24.
 */
@Service
public interface IAttendanceSettingService {
    public boolean add(AttendanceSettingBo bo);
    public boolean update(AttendanceSettingBo bo);
    public boolean delete(String id);
    public List<AttendanceSettingBo> findAllList(Map<String, Object> queryMap);
    public List<AttendanceSettingBo> findAllList(Map<String, Object> queryMap, int skip, int max);
    public int getTotal(Map<String, Object> queryMap);
    public AttendanceSettingBo findById(String id);
}
