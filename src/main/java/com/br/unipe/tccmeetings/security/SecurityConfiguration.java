package com.br.unipe.tccmeetings.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;
import com.br.unipe.tccmeetings.utils.ServicePath;

@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    public static final String AUTH_ADMIN = "ROLE_ADMIN";

    public static final String AUTH_DISCENTE = "ROLE_DISCENTE";

    public static final String AUTH_DOCENTE = "ROLE_DOCENTE";

    @Autowired
    private UserDetailsService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HeaderHandler headerHandler;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userService).passwordEncoder(this.passwordEncoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(ServicePath.DOCENTE_PATH)
                .antMatchers(ServicePath.DISCENTE_PATH);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests()
                // Global Authority to OPTIONS (permit all).
                .antMatchers(HttpMethod.OPTIONS, ServicePath.ALL).permitAll()
                // Public (permit all).
                .antMatchers(ServicePath.PUBLIC_ROOT_PATH + ServicePath.ALL).permitAll()
        		// Admin Authorities.
                .antMatchers(HttpMethod.GET, ServicePath.USER_PATH).hasAnyAuthority(AUTH_ADMIN)
                .antMatchers(HttpMethod.POST, ServicePath.USER_PATH).hasAnyAuthority(AUTH_ADMIN)
                .antMatchers(HttpMethod.PUT, ServicePath.USER_PATH).hasAnyAuthority(AUTH_ADMIN)
                .antMatchers(HttpMethod.DELETE, ServicePath.USER_PATH).hasAnyAuthority(AUTH_ADMIN)
        		// Permission Authorities.
                .antMatchers(HttpMethod.GET, ServicePath.PERMISSION_PATH).hasAnyAuthority(AUTH_ADMIN)
                // Docente Authorities
                .antMatchers(HttpMethod.GET, ServicePath.DOCENTE_PATH).hasAnyAuthority(AUTH_ADMIN)
                .antMatchers(HttpMethod.GET, ServicePath.DOCENTE_PATH + "/findAll").permitAll()
                .antMatchers(HttpMethod.GET, ServicePath.DOCENTE_PATH + ServicePath.ALL).hasAnyAuthority(AUTH_ADMIN)
                .antMatchers(HttpMethod.PUT, ServicePath.DOCENTE_PATH).hasAnyAuthority(AUTH_DOCENTE)
                .antMatchers(HttpMethod.DELETE, ServicePath.DOCENTE_PATH).hasAnyAuthority(AUTH_DOCENTE)
                .antMatchers(HttpMethod.POST, ServicePath.DOCENTE_PATH).permitAll()
                // Discente Authorities
                .antMatchers(HttpMethod.GET, ServicePath.DISCENTE_PATH).hasAnyAuthority(AUTH_ADMIN)
                .antMatchers(HttpMethod.GET, ServicePath.DISCENTE_PATH + "/findAll").permitAll()
                .antMatchers(HttpMethod.PUT, ServicePath.DISCENTE_PATH).hasAnyAuthority(AUTH_DISCENTE)
                .antMatchers(HttpMethod.DELETE, ServicePath.DISCENTE_PATH).hasAnyAuthority(AUTH_DISCENTE)
                .antMatchers(HttpMethod.POST, ServicePath.DISCENTE_PATH).permitAll()
                // Curso Authorities
                .antMatchers(HttpMethod.GET,ServicePath.CURSO_PATH).permitAll()
                .antMatchers(HttpMethod.GET,ServicePath.CURSO_PATH + ServicePath.ALL).hasAnyAuthority( AUTH_ADMIN)
                .antMatchers(HttpMethod.POST, ServicePath.CURSO_PATH).hasAnyAuthority(AUTH_ADMIN)
                .antMatchers(HttpMethod.PUT, ServicePath.CURSO_PATH).hasAnyAuthority(AUTH_ADMIN)
                .antMatchers(HttpMethod.DELETE, ServicePath.CURSO_PATH).hasAnyAuthority(AUTH_ADMIN)
                // Disciplina Authorities
                .antMatchers(HttpMethod.GET, ServicePath.DISCIPLINA_PATH).hasAnyAuthority(AUTH_ADMIN)
                .antMatchers(HttpMethod.GET, ServicePath.DISCIPLINA_PATH + ServicePath.ALL).hasAnyAuthority(AUTH_ADMIN)
                .antMatchers(HttpMethod.POST, ServicePath.DISCIPLINA_PATH).hasAnyAuthority(AUTH_ADMIN)
                .antMatchers(HttpMethod.PUT, ServicePath.DISCIPLINA_PATH).hasAnyAuthority(AUTH_ADMIN)
                .antMatchers(HttpMethod.DELETE, ServicePath.DISCIPLINA_PATH).hasAnyAuthority(AUTH_ADMIN)
                // Reuniao Authorities
                .antMatchers(HttpMethod.GET, ServicePath.REUNIAO_PATH).hasAnyAuthority(AUTH_DOCENTE,AUTH_DISCENTE)
                .antMatchers(HttpMethod.GET, ServicePath.REUNIAO_PATH + ServicePath.ALL).hasAnyAuthority(AUTH_DOCENTE)
                .antMatchers(HttpMethod.POST, ServicePath.REUNIAO_PATH).hasAnyAuthority(AUTH_DOCENTE,AUTH_DISCENTE)
                .antMatchers(HttpMethod.PUT, ServicePath.REUNIAO_PATH).hasAnyAuthority(AUTH_DOCENTE,AUTH_DISCENTE)
                .antMatchers(HttpMethod.DELETE, ServicePath.REUNIAO_PATH).hasAnyAuthority(AUTH_ADMIN)
                .anyRequest().fullyAuthenticated().and()
                // Logout configuration.
                .logout().logoutRequestMatcher(new AntPathRequestMatcher(ServicePath.LOGOUT_PATH)).logoutSuccessHandler(headerHandler).and()
                // CSRF configuration.
                .csrf().csrfTokenRepository(csrfTokenRepository()).and()
                .addFilterAfter(csrfHeaderFilter(), CsrfFilter.class)
                .addFilterAfter(headerHandler, ChannelProcessingFilter.class);
    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }

    private Filter csrfHeaderFilter() {
        return new OncePerRequestFilter() {

            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain filterChain) throws ServletException, IOException {
                CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());

                if (csrf != null) {
                    Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
                    String token = csrf.getToken();

                    if (cookie == null || token != null && !token.equals(cookie.getValue())) {
                        cookie = new Cookie("XSRF-TOKEN", token);
                        cookie.setPath("/");
                        response.addCookie(cookie);
                    }
                }

                filterChain.doFilter(request, response);
            }

        };
    }

}
