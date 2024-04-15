package dev.tripsuggesterjr.tripSuggesterJr.config

//import org.springframework.context.annotation.Configuration
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//
//@Configuration
//@EnableWebSecurity
//class SecurityConfig : WebSecurityConfigurerAdapter() {
//
//    override fun configure(http: HttpSecurity) {
//        http
//            .authorizeRequests()
//            .antMatchers("/", "/home").permitAll() // Allow access to these URLs without authentication
//            .anyRequest().authenticated() // Require authentication for all other URLs
//            .and()
//            .formLogin() // Use form-based authentication
//            .loginPage("/login") // Customize the login page URL
//            .permitAll() // Allow access to the login page without authentication
//            .and()
//            .logout() // Configure logout
//            .permitAll() // Allow access to the logout URL without authentication
//    }
//}