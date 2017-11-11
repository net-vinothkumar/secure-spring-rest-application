## Securing REST Application Using Spring Security :

# How to Secure Spring Boot REST Application - Basic Authentication ?

# What is Authentication ?

Authentication is used to reliably determine the identity of an end user and give access to the resources based on the correctly identified user.

# What is Basic Authentication ?

Basic Authentication is the simplest way to enforce access controling to resources. Here, the HTTP user agent provides the username and the password when making a request. The string containing the username and password separated by a colon is Base64 encoded before sending to the backend when authentication is required.

@EnableWebSecurity annotation and configuration class is extended from the WebSecurityConfigurerAdapter.

The EnableWebSecurity annotation will enable Spring-Security web security support.

@Configuration
@EnableSwagger2
@EnableWebSecurity
public class ApplicationConfig extends WebSecurityConfigurerAdapter

# How to inform to Spring Security Framework which URL paths to secure ?

Override configure(HttpSecurity) method to define which URL paths should be secured and which should not be.

In the example "/" and "/guest" paths are not required any authentication
and any other paths(ex:  "admin") should be authenticated with basic auth.

@Override
protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/", "/guest/**").permitAll()
        .anyRequest().authenticated();
        http.httpBasic().authenticationEntryPoint(basicAuthenticationPoint);
}

# How the user name / password are validated in Spring REST Application ?

In the configureGlobal(AuthenticationManagerBuilder) method, I have created an in-memory user name and password i.e user name = 'learn' & password = "share".

@Autowired
public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	auth.inMemoryAuthentication().withUser("learn").password("share").roles("USER");
}

# What is the purpose of BasicAuthenticationPoint ?

The purpose of the BasicAuthenticationEntryPoint class is to set the "WWW-Authenticate" header to the response. So, web browsers will display a dialog to enter usename and password based on basic authentication mechanism(WWW-Authenticate header).





