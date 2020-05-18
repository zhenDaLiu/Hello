package yuntongxun.ytx.fast.config;

import com.yuntongxun.ytx.fast.filter.ControllerLogFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置Filter过滤条件
 * @author tangxy
 * @date 2019-03-21
 **/
@Configuration
public class RestFilterConfig {

    @Bean
    public FilterRegistrationBean registerControllerLogBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new ControllerLogFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("controllerLogFilter");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }

}
