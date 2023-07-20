# login-service-sb311

A simple project which demonstrates usage of securiftFilterChain instead of extending WebSecurityConfigurerAdaptor as with latest spring security 6.1.1 which came with springboot3.1.1 marked the usage as depricated and which will be removed in later versions.
Also I have used lambda expressions for disabling csrf, cors, authorizeHttpRequests, httpBasic, sessionManagement and headers which might come handly for fellow developers to quickly start.

In this project, I have allowed "/" for every user to access and limited "/home" for autheticated user having USER role and "/admin/home" for a user with ADMIN role.  
