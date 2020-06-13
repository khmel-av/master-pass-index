package ru.khmel.mpi.configuration.security;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/**
 * @author Khmel Anton
 */
@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private String[] ignorePaths = null;

    private static final RequestMatcher PUBLIC_URLS = new OrRequestMatcher(new AntPathRequestMatcher("/**/public/**"));
    private static final RequestMatcher PROTECTED_URLS = new NegatedRequestMatcher(PUBLIC_URLS);

    public WebSecurityConfig(@Value("${Security.IgnorePaths:#{\"/**\"}}") String ignorePaths,
                             @Value("${Security.IgnorePathsSeparator:#{\";\"}}") String separator) {
        ignorePaths = ignorePaths.trim();
        List<String> ignoreList = new ArrayList<>();
        if (StringUtils.isNotBlank(ignorePaths)){
            this.ignorePaths = ignorePaths.split(separator);
            for (String s : this.ignorePaths){
                String resultIgnorePath = s.trim();
                if (StringUtils.isNotBlank(resultIgnorePath)){
                    ignoreList.add(resultIgnorePath);
                }
            }
        }
        log.info("Security list ignore paths: {}", ignoreList.toString());
        this.ignorePaths = ignoreList.toArray(this.ignorePaths);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        List<RequestMatcher> requestMatcherList = new ArrayList<>();
        for (String s : ignorePaths) {
            log.info("Security ignore: {}", s);
            requestMatcherList.add(new OrRequestMatcher(new AntPathRequestMatcher(s)));
        }
        web.ignoring().requestMatchers(requestMatcherList.toArray(new RequestMatcher[ignorePaths.length]));
    }

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Bean
    public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        List<RequestMatcher> requestMatcherList = new ArrayList<>();
        for (String s : ignorePaths) {
            log.info("Security ignore: {}", s);
            requestMatcherList.add(new NegatedRequestMatcher(new OrRequestMatcher(new AntPathRequestMatcher(s))));
        }
        http.sessionManagement()
          .sessionCreationPolicy(STATELESS)
          .and()
          .exceptionHandling()
          .defaultAuthenticationEntryPointFor(unauthorizedHandler, PROTECTED_URLS)
          .and()
          .authorizeRequests()
          .antMatchers("/publish", "/searchByParams", "/searchBySnils", "/searchByParamsAndPageNumber")
          .permitAll()
          .and()
          .addFilterBefore(authenticationTokenFilterBean(), AnonymousAuthenticationFilter.class)
          .authorizeRequests()
          .requestMatchers(requestMatcherList.toArray(new RequestMatcher[ignorePaths.length]))
          .authenticated()
          .and()
          .cors().disable()
          .csrf().disable()
          .formLogin().disable()
          .httpBasic().disable()
          .logout().disable();
    }
}
