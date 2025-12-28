package com.example.drunkenmoviebackend;

import com.example.drunkenmoviebackend.aop.TimeTraceAop;
import com.example.drunkenmoviebackend.global.provider.JwtProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.time.Duration;

@Configuration
@EnableWebSecurity
public class SpringConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // CSRF 비활성화 (REST API)
                .csrf(csrf -> csrf.disable())

                // 인증 정책
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/members/login",
                                "/members/new",
                                "/members/oauth-login",
                                "/members/validate-email",
                                "/members/validate-nickname"
                        ).permitAll()
                        .anyRequest().authenticated()
                )

                // 기본 로그인 폼 비활성화
                .formLogin(form -> form.disable())

                // http basic 비활성화
                .httpBasic(basic -> basic.disable());

        return http.build();
    }

    @Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtProvider jwtProvider(
            @Value("${JWT_ACCESS_SECRET}") String accessSecret,
            @Value("${ACCESS_TOKEN_EXPIRE_TIME}") Duration accessExpire,
            @Value("${JWT_REFRESH_SECRET}") String refreshSecret,
            @Value("${REFRESH_TOKEN_EXPIRE_TIME}") Duration refreshExpire
    ) {
        return new JwtProvider(
                accessSecret,
                accessExpire,
                refreshSecret,
                refreshExpire
        );
    }
//    private final EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }
//
//    @Bean
//    public MemberRepository memberRepository() {
//        return new JPAMemberRepository(em);
//    }
}
