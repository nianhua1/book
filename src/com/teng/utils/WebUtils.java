package com.teng.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

/**
 * @author
 * @create 2021-04-20 23:22
 */
public class WebUtils {
    public static <T> T copyParamToBean(Map value, T bean){
        System.out.println("注入之前"+bean);
        try {
            BeanUtils.populate(bean,value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("注入之前后"+bean);
        return bean;
    }

    public static int conventInt(String id,int defValue){
        try {
            return Integer.parseInt(id);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return defValue;
    }
}
