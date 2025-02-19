package com.bing.modules.monitor.config;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 * 监控权限配置
 *
 * @author Simeon
 */
@EnableWebSecurity
@Configuration
public class WebSecurityConfigurer
{
    private final AdminServerProperties adminServer;

    public WebSecurityConfigurer(AdminServerProperties adminServer)
    {
        this.adminServer = adminServer;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl(this.adminServer.getContextPath() + "/");
        return http
                .headers(headers -> headers
                        .frameOptions(frameOptions -> frameOptions.disable())  // 通过 lambda 设置 frameOptions
                )
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(this.adminServer.getContextPath() + "/assets/**",
                                this.adminServer.getContextPath() + "/login",
                                this.adminServer.getContextPath() + "/actuator/**",
                                this.adminServer.getContextPath() + "/instances/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage(this.adminServer.getContextPath() + "/login")
                        .successHandler(successHandler)
                )
                .logout(logout -> logout
                        .logoutUrl(this.adminServer.getContextPath() + "/logout")
                )
                .httpBasic(httpBasic -> {})  // 使用 lambda 表达式配置 httpBasic
                .csrf(csrf -> csrf.disable())  // 这里暂时禁用 CSRF，如果需要可以使用 CsrfTokenRequestAttributeHandler 和 CookieCsrfTokenRepository 来配置
                .build();
    }
}
