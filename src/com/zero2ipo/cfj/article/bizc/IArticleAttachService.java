package com.zero2ipo.cfj.article.bizc;

import com.zero2ipo.cfj.article.bo.AttachBo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/2/24.
 */
@Service
public interface IArticleAttachService {
    public boolean add(AttachBo bo);
    public boolean update(AttachBo bo);
    public boolean delete(String id);
    public List<AttachBo> findAllList(Map<String, Object> queryMap);
    public List<AttachBo> findAllList(Map<String, Object> queryMap, int skip, int max);
    public int getTotal(Map<String, Object> queryMap);
    public AttachBo findById(String id);
}
