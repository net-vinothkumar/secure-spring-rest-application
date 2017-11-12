## Securing REST Application Using Spring Security :

# How to Secure Spring Boot REST Application - Basic Authentication ?

# What is Authentication ?

Authentication is used to reliably determine the identity of an end user and give access to the resources based on the correctly identified user.

# How Spring Security - Basic Authentication Works ?

![alt tag](https://user-images.githubusercontent.com/30971809/32694124-cb1e4e0a-c75d-11e7-9a61-fba76f1a4343.png)

**Example :** 

```Secured API : http://localhost:8080/admin/greet/David
Not Secured API : http://localhost:8080/guest/greet/John
```

# What is Basic Authentication ?

Basic Authentication is the simplest way to enforce access controling to resources. Here, the HTTP user agent provides the **username** and the **password** when making a request. The string containing the username and password separated by a colon is **Base64 encoded** before sending to the backend when authentication is required.

```@EnableWebSecurity``` annotation and configuration class is extended from the **WebSecurityConfigurerAdapter**.

The EnableWebSecurity annotation will enable **Spring-Security web security** support.

```
@Configuration
@EnableSwagger2
@EnableWebSecurity
public class ApplicationConfig extends WebSecurityConfigurerAdapter
```

# How to inform to Spring Security Framework which URL paths to secure ?

Override **configure(HttpSecurity)** method to define which URL paths should be secured and which should not be.

In the example **"/" and "/guest"** paths are not required any authentication
and any other paths(ex:  **"admin"**) should be authenticated with basic auth.

```
@Override
protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/", "/guest/**").permitAll()
        .anyRequest().authenticated();
        http.httpBasic().authenticationEntryPoint(basicAuthenticationPoint);
}
```

# How the user name / password are validated in Spring REST Application ?

In the **configureGlobal(AuthenticationManagerBuilder)** method, I have created an in-memory user name and password i.e **user name = 'learn' & password = "share"**.

```
@Autowired
public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	auth.inMemoryAuthentication().withUser("learn").password("share").roles("USER");
}
```

# What is the purpose of BasicAuthenticationPoint ?

The purpose of the **BasicAuthenticationEntryPoint** class is to set the **"WWW-Authenticate"** header to the response. So, web browsers will display a dialog to enter usename and password based on basic authentication mechanism(WWW-Authenticate header).

# How to start the Secured Spring REST Application ?
```
$ mvn spring-boot:run

[INFO] Scanning for projects...
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] Building securerest 0.0.1-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO]
[INFO] >>> spring-boot-maven-plugin:1.5.6.RELEASE:run (default-cli) > test-compile @ securerest >>>
[INFO]
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ securerest ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 1 resource
[INFO] Copying 0 resource
[INFO]
[INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ securerest ---
[INFO] Nothing to compile - all classes are up to date
[INFO]
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ securerest ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory E:\CreateV\securerest\secure-spring-rest-application\src\test\resources
[INFO]
[INFO] --- maven-compiler-plugin:3.1:testCompile (default-testCompile) @ securerest ---
[INFO] Nothing to compile - all classes are up to date
[INFO]
[INFO] <<< spring-boot-maven-plugin:1.5.6.RELEASE:run (default-cli) < test-compile @ securerest <<<
[INFO]
[INFO] --- spring-boot-maven-plugin:1.5.6.RELEASE:run (default-cli) @ securerest ---

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
![alt tag](  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v1.5.6.RELEASE)

2017-11-12 03:11:52.991  INFO 10756 --- [           main] c.l.securerest.SecureRESTAPIApplication  : Starting SecureRESTAPIApplication on badboysantu with PID 10756 (E:\CreateV\securerest\secure-spring-rest-application\target\classes started by santosh in E:\CreateV\securerest\secure-spring-rest-application)
2017-11-12 03:11:52.996  INFO 10756 --- [           main] c.l.securerest.SecureRESTAPIApplication  : No active profile set, falling back to default profiles: default2017-11-12 03:11:53.125  INFO 10756 --- [           main] ationConfigEmbeddedWebApplicationContext : Refreshing org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext@38e11556: startup date [Sun Nov 12 03:11:53 IST 2017]; root of context hierarchy2017-11-12 03:11:56.149  INFO 10756 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat initialized with port(s): 8080 (http)
2017-11-12 03:11:56.173  INFO 10756 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2017-11-12 03:11:56.173  INFO 10756 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet Engine: Apache Tomcat/8.5.16
2017-11-12 03:11:56.461  INFO 10756 --- [ost-startStop-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2017-11-12 03:11:56.462  INFO 10756 --- [ost-startStop-1] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 3342 ms
2017-11-12 03:11:56.756  INFO 10756 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'characterEncodingFilter' to: [/*]
2017-11-12 03:11:56.757  INFO 10756 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'hiddenHttpMethodFilter' to: [/*]
2017-11-12 03:11:56.757  INFO 10756 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'httpPutFormContentFilter' to: [/*]
2017-11-12 03:11:56.757  INFO 10756 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'requestContextFilter' to: [/*]
2017-11-12 03:11:56.758  INFO 10756 --- [ost-startStop-1] .s.DelegatingFilterProxyRegistrationBean : Mapping filter: 'springSecurityFilterChain' to: [/*]
2017-11-12 03:11:56.759  INFO 10756 --- [ost-startStop-1] o.s.b.w.servlet.ServletRegistrationBean  : Mapping servlet: 'dispatcherServlet' to [/]
2017-11-12 03:11:57.491  INFO 10756 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/admin/greet/{name}],methods=[GET],produces=[application/json]}" onto public com.learnshare.securerest.bean.HelloMessage com.learnshare.securerest.controllers.AdminController.sayHello(java.lang.String)
2017-11-12 03:11:57.494  INFO 10756 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/guest/greet/{name}],methods=[GET],produces=[application/json]}" onto public com.learnshare.securerest.bean.HelloMessage com.learnshare.securerest.controllers.GuestUserController.message(java.lang.String)
2017-11-12 03:11:57.503  INFO 10756 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/swagger-resources]}" onto public org.springframework.http.ResponseEntity<java.util.List<springfox.documentation.swagger.web.SwaggerResource>> springfox.documentation.swagger.web.ApiResourceController.swaggerResources()
2017-11-12 03:11:57.504  INFO 10756 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/swagger-resources/configuration/ui]}" onto public org.springframework.http.ResponseEntity<springfox.documentation.swagger.web.UiConfiguration> springfox.documentation.swagger.web.ApiResourceController.uiConfiguration()
2017-11-12 03:11:57.505  INFO 10756 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/swagger-resources/configuration/security]}" onto public org.springframework.http.ResponseEntity<springfox.documentation.swagger.web.SecurityConfiguration> springfox.documentation.swagger.web.ApiResourceController.securityConfiguration()
2017-11-12 03:11:57.513  INFO 10756 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error]}" onto public org.springframework.http.ResponseEntity<java.util.Map<java.lang.String, java.lang.Object>> org.springframework.boot.autoconfigure.web.BasicErrorController.error(javax.servlet.http.HttpServletRequest)
2017-11-12 03:11:57.514  INFO 10756 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error],produces=[text/html]}" onto public org.springframework.web.servlet.ModelAndView org.springframework.boot.autoconfigure.web.BasicErrorController.errorHtml(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)
2017-11-12 03:11:57.719  INFO 10756 --- [           main] pertySourcedRequestMappingHandlerMapping : Mapped URL path [/v2/api-docs] onto method [public org.springframework.http.ResponseEntity<springfox.documentation.spring.web.json.Json> springfox.documentation.swagger2.web.Swagger2Controller.getDocumentation(java.lang.String,javax.servlet.http.HttpServletRequest)]
2017-11-12 03:11:57.985  INFO 10756 --- [           main] o.s.s.web.DefaultSecurityFilterChain     : Creating filter chain: org.springframework.security.web.util.matcher.AnyRequestMatcher@1, [org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter@7d92b557, org.springframework.security.web.context.SecurityContextPersistenceFilter@1cd5e049, org.springframework.security.web.header.HeaderWriterFilter@68304e66, org.springframework.security.web.authentication.logout.LogoutFilter@2860475a, org.springframework.security.web.authentication.www.BasicAuthenticationFilter@2db35213, org.springframework.security.web.savedrequest.RequestCacheAwareFilter@43de8c26, org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter@278fa7da, org.springframework.security.web.authentication.AnonymousAuthenticationFilter@3e1cc2ad, org.springframework.security.web.session.SessionManagementFilter@4f6be6e3, org.springframework.security.web.access.ExceptionTranslationFilter@1d859e3c, org.springframework.security.web.access.intercept.FilterSecurityInterceptor@15e4afec]
2017-11-12 03:11:58.274  INFO 10756 --- [           main] s.w.s.m.m.a.RequestMappingHandlerAdapter : Looking for @ControllerAdvice: org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext@38e11556: startup date [Sun Nov 12 03:11:53 IST 2017]; root of context hierarchy
2017-11-12 03:11:58.363  INFO 10756 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/webjars/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2017-11-12 03:11:58.363  INFO 10756 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2017-11-12 03:11:58.420  INFO 10756 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**/favicon.ico] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2017-11-12 03:11:58.625  INFO 10756 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
2017-11-12 03:11:58.635  INFO 10756 --- [           main] o.s.c.support.DefaultLifecycleProcessor  : Starting beans in phase 2147483647
2017-11-12 03:11:58.636  INFO 10756 --- [           main] d.s.w.p.DocumentationPluginsBootstrapper : Context refreshed
2017-11-12 03:11:58.662  INFO 10756 --- [           main] d.s.w.p.DocumentationPluginsBootstrapper : Found 1 custom documentation plugin(s)
2017-11-12 03:11:58.720  INFO 10756 --- [           main] s.d.s.w.s.ApiListingReferenceScanner     : Scanning for api listing references
2017-11-12 03:11:58.994  INFO 10756 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8080 (http)
2017-11-12 03:11:59.000  INFO 10756 --- [           main] c.l.securerest.SecureRESTAPIApplication  : Started SecureRESTAPIApplication in 6.627 seconds (JVM running for 17.659)
```
# How to access Secured Spring REST Application ?

**Using Postman client application**

![alt tag](https://user-images.githubusercontent.com/30971809/32693939-dee281e4-c759-11e7-8a5a-f01e26b870a0.png)


