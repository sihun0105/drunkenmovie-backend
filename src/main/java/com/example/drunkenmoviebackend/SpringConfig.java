package com.example.drunkenmoviebackend;

import com.example.drunkenmoviebackend.aop.TimeTraceAop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringConfig {

    @Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
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
