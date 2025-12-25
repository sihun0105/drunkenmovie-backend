package com.example.drunkenmoviebackend;

import com.example.drunkenmoviebackend.repository.JPAMemberRepository;
import com.example.drunkenmoviebackend.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberRepository memberRepository() {
        return new JPAMemberRepository(em);
    }
}
