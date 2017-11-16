package cc.oauth.security;

import cc.oauth.jpa.service.api.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    //完成自定义认证实体注入
//    @Bean
//    UserDetailsService userService() {
//        return new UserInfoServiceImpl();
//    }
    @Autowired
    private UserInfoService userService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // set encoder
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/", "/home").permitAll()
                    .anyRequest().authenticated()
                    //.anyRequest().permitAll()
                    .and()
                .formLogin()
                    .loginPage("/sso/login")
                    .failureUrl("/sso/login?error")
                    .permitAll()
                    .and()
                .logout()
                    //.logoutUrl("/sso/logout")
                    //.logoutSuccessUrl("/index")
                    // .logoutSuccessHandler(logoutSuccessHandler)
                    // .invalidateHttpSession(true)
                    // .addLogoutHandler(logoutHandler)
                    // .deleteCookies(cookieNamesToClear)
                    .permitAll();
    }

}
