package com.itzixi.web.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/***
 * Spring上下文操作
 */
@Component
public class SpringUtils implements ApplicationContextAware {
	
	/**上下文对象*/
    private static ApplicationContext context;

	@Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = context;
    }

    /**
     * 
     * 获取Spring上下文对象
     * 
     * @return
     */
    public static ApplicationContext getContext() {
        return context;
    }

}
