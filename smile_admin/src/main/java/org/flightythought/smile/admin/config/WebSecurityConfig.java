package org.flightythought.smile.admin.config;

import org.flightythought.smile.admin.framework.handler.MyAccessDeniedHandler;
import org.flightythought.smile.admin.framework.handler.MyLoginFailureHandler;
import org.flightythought.smile.admin.framework.handler.MyLoginSuccessHandler;
import org.flightythought.smile.admin.framework.security.CustomerMetaDataSource;
import org.flightythought.smile.admin.framework.security.UrlAccessDecisionManager;
import org.flightythought.smile.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.web.cors.CorsUtils;

/**
 * @author lilei
 */
@Configurable
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;
    @Autowired
    private CustomerMetaDataSource metadataSource;
    @Autowired
    private UrlAccessDecisionManager urlAccessDecisionManager;
    @Autowired
    private MyLoginFailureHandler failureHandler;
    @Autowired
    private MyLoginSuccessHandler successHandler;
    @Autowired
    private MyAccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/index.html", "/image/**", "/static/**", "/login_p", "/logout_p", "/session/invalid");
        web.ignoring().antMatchers("/swagger-ui.html").antMatchers("/webjars/**").antMatchers("/v2/**").antMatchers("/swagger-resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                //处理跨域请求中的Preflight请求
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll().and().authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(metadataSource);
                        o.setAccessDecisionManager(urlAccessDecisionManager);
                        return o;
                    }
                })
                .and().formLogin().loginPage("/login_p").loginProcessingUrl("/login")
                .usernameParameter("username").passwordParameter("password")
                .failureHandler(failureHandler)
                .successHandler(successHandler)
                .permitAll()
                .and()
                .sessionManagement().invalidSessionUrl("/session/invalid")
                .and()
                .logout().logoutSuccessUrl("/logout_p").permitAll().and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }
}
