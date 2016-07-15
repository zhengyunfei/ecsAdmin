package com.zero2ipo.eeh.classroom.bizc;

import com.zero2ipo.eeh.classroom.bo.ClassRoomBo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/2/24.
 */
@Service
public interface IClassRoomService {
    public boolean add(ClassRoomBo bo);
    public boolean update(ClassRoomBo bo);
    public boolean delete(String id);
    public List<ClassRoomBo> findAllList(Map<String, Object> queryMap);
    public List<ClassRoomBo> findAllList(Map<String, Object> queryMap, int skip, int max);
    public int getTotal(Map<String, Object> queryMap);
    public ClassRoomBo findById(String id);
}
