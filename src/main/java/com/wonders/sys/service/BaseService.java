package com.wonders.sys.service;

import com.wonders.sys.dao.BaseDao;
import com.wonders.sys.model.BaseModel;
import com.wonders.sys.utils.BeanFactoryFixed;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @author : zhouyu
 * @date : 2019/9/9
 * @description : 基本service BaseService
 */
@Transactional(rollbackFor = Exception.class)
public abstract class BaseService<E extends BaseModel> implements Serializable{

    private static final long serialVersionUID = -4368225178875587359L;

    public BaseDao<E> getBaseDao(){
       return BeanFactoryFixed.getBaseDao(BeanFactoryFixed.getModelClass(this.getClass()));
    }

    public int insert(E e){
       return getBaseDao().insert(e);
    }

    public int updateByPrimaryKeySelective(E e){
        return getBaseDao().updateByPrimaryKeySelective(e);
    }

    public List<E> findByModel(E e){
        return getBaseDao().findByModel(e);
    }

    public int deleteById(String id){
        return getBaseDao().deleteById(id);
    }

    public E findById(String id){
        return getBaseDao().findById(id);
    }

}
