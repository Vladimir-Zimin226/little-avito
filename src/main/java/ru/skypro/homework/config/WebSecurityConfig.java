package ru.skypro.homework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private OurSecutiryDetailsService ourSecutiryDetailsService;


    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v3/api-docs",
            "/webjars/**",
            "/login",
            "/register"
    };


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeHttpRequests(
                        authorization ->
                                authorization
                                        .mvcMatchers(AUTH_WHITELIST).permitAll()
                                        // разрещили доступ к получению всех объявлений и отдельного объявления по ID для всех пользователей
                                        .mvcMatchers("/ads", "/ads/{id}").permitAll()

                                        // разрешили доступ к добавлению объявления только для авторизованных пользователей
                                        .mvcMatchers("/ads").hasRole("USER")

                                        // доступ к изменению или удалению объявлений и картинок объявления только для авторизованных пользователей
                                        .mvcMatchers("/ads/{id}", "/ads/{id}/image").authenticated()

                                        // доступ к просмотру и добавлению комментариев доступен для авторизованных пользователей
                                        .mvcMatchers("/ads/{id}/comments").hasRole("USER")

                                        // доступ к изменению и удалению комментариев доступен только авторизованным пользователям, при этом авторизованный пользователь может редактировать и удалять только свои комментарии
                                        .mvcMatchers("/ads/{id}/comments/{commentId}").authenticated()

                                        // доступ к информации о текущем пользователе и обновлению данных пользователя
                                        .mvcMatchers("/users/me").authenticated()
                                        .mvcMatchers("/users/me/image").authenticated()

                                        // доступ к смене пароля доступен только для авторизованных пользователей
                                        .mvcMatchers("/users/set_password").authenticated()

                                        // доступ к получению объявлений и их обновлению (авторизованный пользователь может получать свои объявления, а администратор - все)
                                        .mvcMatchers("/ads/me").authenticated()

                                        // админ имеет доступ к удалению и редактированию объявлений и комментариев любых пользователей
                                        .mvcMatchers("/ads/{id}", "/ads/{id}/comments/{commentId}").hasRole("ADMIN")

                                        // разрешил доступ к ресурсам только после аутентификации, если не указаны конкретные разрешения
                )
                .cors()
                .and()
                .httpBasic(withDefaults());
        return http.build();
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(ourSecutiryDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
