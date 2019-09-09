package com.wonders.sys.dao;


import com.wonders.sys.model.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * @author : ywhome
 * @date : 2019/1/30
 * @description : base dao
 */
public interface BaseDao<E extends BaseModel> extends Serializable {

    /**
     * 插入
     *
     * @param model e
     * @return int
     */
    int insert(E model);

    /**
     * 根据id更新数据
     *
     * @param model e
     * @return int
     */
    int updateByPrimaryKeySelective(E model);

    /**
     * 根据id 查询
     * @param model e
     * @return list
     */
    List<E> findByModel(E model);

    /**
     * 根据id删除
     * @param id id
     * @return int
     */
    int deleteById(String id);

    /**
     * 根据id返回对象
     * @param id
     * @return
     */
    E findById(String id);
}
