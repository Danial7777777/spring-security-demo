package com.security.boot.boot_security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 * @ClassNameSecurityConfig
 * @Description   自定义进行配置
 * @Author
 * @Date2020/7/24 9:33
 **/
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 配置认证用户
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //自定义配置 认证的账号密码角色
         auth
             //内存级别的账号密码 认证InMemoryUserDetailsManager
             .inMemoryAuthentication()
             //不采用 密码的编码器
             .passwordEncoder(NoOpPasswordEncoder.getInstance())
              //定义管理者
             .withUser("admin").password("admin").roles("ADMIN")
              //定义普通用户
             .and().withUser("normal").password("normal").roles("NORMAL");
    }


    /**
     * 配置认证权限
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //配置请求抵制权限
        http
            .authorizeRequests()
            //所有用户可访问
            .antMatchers("/test/demo").permitAll()
            //admin
            .antMatchers("/test/admin").hasRole("ADMIN")
            //normal
            .antMatchers("/test/normal").access("hasRole('ROLE_NORMAL')")
            //任何请求都需要认证
            .anyRequest().authenticated()

            //表单登陆  登出所有用户可访问
            .and()
            //表单登陆
            .formLogin()
//            .loginPage("/login") //自定义登陆url地址
            .permitAll()

            .and()
            .logout()
//            .logoutUrl("/logout")//自定义登出url地址
            .permitAll();

    }
}
