package com.springboot.manager.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;


@Configuration
public class DruidConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

    /**
     * 配置管理后台的service
     * @return
     */
    @Bean
    public ServletRegistrationBean statViewServlet () {
        //org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册.
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        //添加初始化参数：initParams
        HashMap<String,String> initParams = new HashMap<>(5);
        //登录查看信息的账号密码.
        initParams.put("loginUsername","root");
        initParams.put("loginPassword","password");
        //白名单
        initParams.put("allow","");
        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
        initParams.put("deny","localhost");
        // 禁用HTML页面上的“Reset All”功能
        initParams.put("resetEnable","false");
        servletRegistrationBean.setInitParameters(initParams);
        return servletRegistrationBean;
    }

    /**
     * 配置web监控的filter
     */
    @Bean
    public FilterRegistrationBean webStatFilter () {

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //添加初始化参数：initParams
        HashMap<String,String> initParams = new HashMap<>(1);
        initParams.put("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.setInitParameters(initParams);
        filterRegistrationBean.setName("druidWebStatFilter");
        //添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");

        return filterRegistrationBean;
    }
}
