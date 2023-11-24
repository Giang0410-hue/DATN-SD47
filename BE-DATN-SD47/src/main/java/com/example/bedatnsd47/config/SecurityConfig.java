package com.example.bedatnsd47.config;

import com.example.bedatnsd47.entity.TaiKhoan;
import com.example.bedatnsd47.repository.TaiKhoanRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    PasswordEncoderConfig passwordEncoder;

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(Collections.singletonList(authenticationProvider()));
    }

    @Bean
    //authentication
    public UserDetailsService userDetailsService() {
//        UserDetails admin = User.withUsername("Basant")
//                .password(encoder.encode("Pwd1"))
//                .roles("ADMIN")
//                .build();
//        UserDetails user = User.withUsername("John")
//                .password(encoder.encode("Pwd2"))
//                .roles("USER","ADMIN","HR")
//                .build();
//        return new InMemoryUserDetailsManager(admin, user);
        return new UserInfoUserDetailsService();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new SimpleUrlAuthenticationSuccessHandler() {
            @Autowired
            private TaiKhoanRepository taiKhoanRepository; // Đây là repository hoặc service để truy vấn đối tượng TaiKhoan

            @Override
            protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                TaiKhoan user = taiKhoanRepository.findByTenTaiKhoan(userDetails.getUsername()).orElse(null);
                System.out.println(userDetails+"****");
                if (user != null) {
                    System.out.println("*****null");
                }
                if (user == null) {
                    System.out.println("*****null");
                }
                if (user != null && user.getTrangThai() != null && user.getTrangThai() == 1) {
                    return "/login-error"; // Redirect to a login error page if the user's trangThai is 1
                } else {
                    if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
                        System.out.println(userDetails.getPassword());
                        System.out.println(user + "*****");

                        return "/ban-hang-tai-quay/hoa-don";
                    } else if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
                        return "/home";
                    } else {
                        return "/someDefaultPath";
                    }
                }
            }
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/products/welcome", "/products/new", "/login", "/user/shop", "/test/**", "/register", "/saveTaiKhoan", "/verify", "/quen-mat-khau",
                        "/them-tai-khoan", "/verify", "/reset-mat-khau", "/xac-minh", "/xac-minh/check").permitAll()
                .requestMatchers("/style/", "/static/css/**", "/static/fonts/**", "/static/img/**", "/static/js/**", "/static/scss/**", "/static/vendor/**").permitAll()
                .requestMatchers("/**").permitAll()
                .requestMatchers("/admin/**").permitAll()
                .and()
                .authorizeHttpRequests()
//                .requestMatchers("/ban-hang-tai-quay/**").hasAnyAuthority("ROLE_ADMIN")
//                .requestMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")
//                .requestMatchers("/admin/**").permitAll()
//                .requestMatchers("/user/**").hasAnyAuthority("ROLE_USER")
//                .requestMatchers("/user/**").permitAll()
                .requestMatchers("/products/**")
                .authenticated().and()
                .formLogin()
                .loginProcessingUrl("/login")
                .loginPage("/login")
                .successHandler(authenticationSuccessHandler())
                .failureUrl("/login?error=true")
                // Khi đăng nhập sai username và password thì nhập lại
//                .usernameParameter("username")// tham số này nhận từ form login ở bước 3 có input  name='username'
//                .passwordParameter("password")// tham số này nhận từ form login ở bước 3 có input  name='password
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .clearAuthentication(true) // Xóa thông tin đăng nhập
                .invalidateHttpSession(true) // Hủy phiên đăng nhập
                .deleteCookies("JSESSIONID") // Xóa cookies nếu cần
                .permitAll()
                .and().build();
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/products/welcome","/products/new").permitAll()
//                .and()
//                .authorizeHttpRequests().requestMatchers("/products/**")
//                .authenticated().and().formLogin().and().build();
//    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder.passwordEncoder());
        return authenticationProvider;
    }
}
