package com.zero2ipo.eeh.devconf.bizc;

import com.zero2ipo.eeh.devconf.bo.DevConfBo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/2/24.
 */
@Service
public interface IDevConfService {
    public boolean add(DevConfBo bo);
    public boolean update(DevConfBo bo);
    public boolean delete(String id);
    public List<DevConfBo> findAllList(Map<String, Object> queryMap);
    public List<DevConfBo> findAllList(Map<String, Object> queryMap, int skip, int max);
    public int getTotal(Map<String, Object> queryMap);
    public DevConfBo findById(String id);
}
