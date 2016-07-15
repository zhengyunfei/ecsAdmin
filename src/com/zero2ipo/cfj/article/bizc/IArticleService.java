package com.zero2ipo.cfj.article.bizc;

import com.zero2ipo.cfj.article.bo.ArticleBo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/2/24.
 */
@Service
public interface IArticleService {
    public boolean add(ArticleBo bo);
    public boolean update(ArticleBo bo);
    public boolean delete(String id);
    public List<ArticleBo> findAllList(Map<String, Object> queryMap);
    public List<ArticleBo> findAllList(Map<String, Object> queryMap, int skip, int max);
    public int getTotal(Map<String, Object> queryMap);
    public ArticleBo findById(String id);
}
