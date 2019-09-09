package com.wonders.sys.utils;

import com.wonders.sys.dao.BaseDao;
import com.wonders.sys.model.BaseModel;
import com.wonders.sys.service.BaseService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

/**
 * @author : zhouyu
 * @date : 2019/9/9
 * @description : bean Factory
 */
@Component
public class BeanFactoryFixed implements BeanFactoryAware {

    private static BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        BeanFactoryFixed.beanFactory = beanFactory;
    }

    public static BeanFactory getBeanFactory() {
        return beanFactory;
    }

    /**
     * 获取BaseService
     * @param clazz clazz
     * @param <E> BaseModel
     * @return BaseService
     */
    public static <E extends BaseModel> BaseService<E> getBaseService(Class<E> clazz){
        String serviceName = clazz.getName().replace("model","service").concat("Service");
        serviceName = serviceName.substring(serviceName.lastIndexOf(".")+1);
        return (BaseService<E>) getBeanFactory().getBean(serviceName.substring(0,1).toLowerCase().concat(serviceName.substring(1)));
    }

    /**
     * getBaseDao
     * @date 2019/1/30
     * @param clazz clazz
     * @return top.ywhome.framework.basic.dao.BaseDao<E>
     **/
    public static <E extends BaseModel> BaseDao<E> getBaseDao(Class<E> clazz){
        String daoName = clazz.getName().replace("model","dao").concat("Dao");
        daoName = daoName.substring(daoName.lastIndexOf(".")+1);
        return (BaseDao<E>) getBeanFactory().getBean(daoName.substring(0,1).toLowerCase().concat(daoName.substring(1)));
    }

    /**
     * 获取ModelClass
     * @date 2019/1/30
     * @param clazz clazz
     * @return java.lang.Class<E>
     **/
    public static <E extends BaseModel> Class<E> getModelClass(Class clazz){
        if (Object.class.equals(clazz.getGenericSuperclass())){
            List<Type> types = Arrays.asList(clazz.getGenericInterfaces());
            for (Type type : types) {
                Class aClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[0];
                if (BaseModel.class.isAssignableFrom(aClass)) {
                    return aClass;
                }
            }
        }
        ParameterizedType parameterizedType = (ParameterizedType)clazz.getGenericSuperclass();
        return (Class<E>)parameterizedType.getActualTypeArguments()[0];
    }

}
