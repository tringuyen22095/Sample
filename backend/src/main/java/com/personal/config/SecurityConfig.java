package com.personal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig {
	private static final String[] WHITELIST = {
		"/auth/sign-in",
		"/public/**",
		"/web/**",
		"/css/**",
		"/js/**",
		"/languages/**",
		"/images/**"
	};

	@Value("${com.personal.security.http-cache.enabled:false}")
	private boolean httpCacheEnabled;

	@Autowired
	private TokenAuthenticationFilter tokenAuthenticationFilter;

	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors().disable();
        if (this.httpCacheEnabled) {
            http.headers().cacheControl();
        }

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        	.addFilterBefore(this.tokenAuthenticationFilter, AnonymousAuthenticationFilter.class);

        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry urlRegistry =
                http.authorizeRequests()
                        .antMatchers("/api/**").authenticated()
                        .antMatchers(WHITELIST).permitAll();

        urlRegistry.and()
                .formLogin().disable()
                .httpBasic().disable()
                .logout().disable();

        return http.build();
	}

}
