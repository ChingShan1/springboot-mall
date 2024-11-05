package com.chingshan.springbootmail.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {

    @Autowired
    private MyOAuth2UserService myOAuth2UserService;

    @Autowired
    private MyOidcUserService myOidcUserService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {



        return http
                // 設定 Session 的創建機制
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                )

                // 設定 CSRF 保護
                .csrf(csrf -> csrf.disable())

//                // 設定 CORS 跨域
//                .cors(cors -> cors
//                        .configurationSource(createCorsConfig())
//                )

                // 設定 Http Basic 認證和表單認證
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())

                // oauth社交登入
                // OAuth 2.0 社交登入

                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(infoEndpoint -> infoEndpoint
                                .userService(myOAuth2UserService)
                                .oidcUserService(myOidcUserService)
                        )
                )
//                .oauth2Login(Customizer.withDefaults())

                // 設定 api 的權限控制
                .authorizeHttpRequests(request -> request
//                        // 註冊帳號功能
//                        .requestMatchers("/register").permitAll()
//                        .requestMatchers("/google/**").permitAll()
//
//
//                        // 登入功能
////                        .requestMatchers("/register").authenticated()
//                        .requestMatchers("/userLogin").authenticated()
//                        .requestMatchers("/").authenticated()
//                        .requestMatchers("/oauth2_users/**").authenticated()
//                        .requestMatchers("/users/**").authenticated()
//                        .requestMatchers("/products/**").authenticated()
//
//
//
//                        // 權限設定
////                        .requestMatchers("/products/**").hasAnyRole("NORMAL_MEMBER", "MOVIE_MANAGER", "ADMIN")
//

                        .anyRequest().permitAll()
                )

                .build();
    }
    private CorsConfigurationSource createCorsConfig() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://example.com"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowedMethods(List.of("*"));
        config.setAllowCredentials(true);
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }
}
