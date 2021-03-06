package com.whatsup.svcs;

/**
 * Created by DelMonroe on 7/5/17.
 */


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = UserWithRoles.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsLoader userDetails;

    @Bean(name = "passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/dashboards")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/logout")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout")
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/coupon/create",
                        "/coupon/?/edit",
                        "/coupon/?",
                        "/user/create",
                        "/user/?/edit",
                        "/user/?/delete",
                        "/user/?/savecoupon",
                        "/vendors",
                        "/vendor/?/profile",
                        "/users",
                        "/coupons",
                        "/places",
                        "/places/?",
                        "/places/create",
                        "/locations.json/{lon}/{lat}/{dist}",
                        "/maps",
                        "/maps/geo",
                        "/dashboards"


                )
                .authenticated()
        ;
//        REMOVE WHEN APPLICATION IS COMPLETE AND ENABLE SECURITY!!!
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetails).passwordEncoder(passwordEncoder());

    }

}
