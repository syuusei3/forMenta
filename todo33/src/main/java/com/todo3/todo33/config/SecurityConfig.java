package com.todo3.todo33.config;

import java.util.Arrays;

import com.todo3.todo33.handler.AuthenticationSuccessHandler;
import com.todo3.todo33.handler.FailureHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .mvcMatchers("/login").permitAll()//login permit
            .anyRequest().authenticated()//それ以外のリクエストは認証必要
            .and()
            .formLogin()
                .successHandler(new AuthenticationSuccessHandler())
                .failureHandler(new FailureHandler())
            .loginPage("/login")//loginPageのパス
//            .defaultSuccessUrl("/board", true)
            .and()
            .csrf().disable()
            .cors()
            .configurationSource(this.corsConfigurationSource());
    }
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        var configuration = new CorsConfiguration();

        // Access-Control-Allow-Origin
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080"));
        // Access-Control-Allow-Methods
        configuration.setAllowedMethods(Arrays.asList("*"));
        // Access-Control-Allow-Headers
        configuration.setAllowedHeaders(Arrays.asList("*"));
        // Access-Control-Allow-Credentials
        configuration.setAllowCredentials(true);
        var source = new UrlBasedCorsConfigurationSource();
        // COSR設定を行う範囲のパスを指定する。この例では全てのパスに対して設定が有効になる
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //     auth.inMemoryAuthentication()
    //         .withUser("user")
    //         .password(passwordEncoder().encode("123456"))
    //         .roles("USER");
    // }
}
