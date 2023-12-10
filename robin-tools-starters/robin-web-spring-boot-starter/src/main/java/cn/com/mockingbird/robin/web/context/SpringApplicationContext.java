package cn.com.mockingbird.robin.web.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;

import java.util.Arrays;

/**
 * Spring 应用上下文
 *
 * @author zhaopeng
 * @date 2023/10/2 2:05
 **/
@SuppressWarnings("unused")
public class SpringApplicationContext implements ApplicationContextAware {

    public static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        SpringApplicationContext.applicationContext = applicationContext;
    }

    /**
     * 根据 cn.com.mockingbird.robin.common.util.bean‘s name 获取实例
     * @param name cn.com.mockingbird.robin.common.util.bean's name
     * @return cn.com.mockingbird.robin.common.util.bean 实例
     */
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    /**
     * 根据 cn.com.mockingbird.robin.common.util.bean's class 获取实例
     * @param clazz cn.com.mockingbird.robin.common.util.bean's class
     * @return cn.com.mockingbird.robin.common.util.bean 实例
     * @param <T> 实例泛型
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * 根据 cn.com.mockingbird.robin.common.util.bean's name and cn.com.mockingbird.robin.common.util.bean's class 获取实例
     * @param name cn.com.mockingbird.robin.common.util.bean's name
     * @param clazz cn.com.mockingbird.robin.common.util.bean's class
     * @return cn.com.mockingbird.robin.common.util.bean 实例
     * @param <T> 实例泛型
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return applicationContext.getBean(name, clazz);
    }

    /**
     * 是否包括指定 cn.com.mockingbird.robin.common.util.bean
     * @param name cn.com.mockingbird.robin.common.util.bean's name
     * @return true-是；false-否
     */
    public static boolean contains(String name) {
        return applicationContext.containsBean(name);
    }

    /**
     * 指定 cn.com.mockingbird.robin.common.util.bean 是否为单例
     * @param name cn.com.mockingbird.robin.common.util.bean's name
     * @return true-是；false-否
     */
    public static boolean isSingleton(String name) {
        return applicationContext.isSingleton(name);
    }

    /**
     * 获取 cn.com.mockingbird.robin.common.util.bean 类型
     * @param name cn.com.mockingbird.robin.common.util.bean's name
     * @return cn.com.mockingbird.robin.common.util.bean's class
     */
    public static Class<?> getClass(String name) {
        return applicationContext.getType(name);
    }

    /**
     * 获取当前环境
     * @return 当前环境信息
     */
    public static String getActiveProfile() {
        return Arrays.toString(applicationContext.getEnvironment().getActiveProfiles());
    }

}
