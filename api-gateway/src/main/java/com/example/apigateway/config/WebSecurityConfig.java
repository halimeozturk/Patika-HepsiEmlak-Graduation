package com.example.apigateway.config;

import com.example.apigateway.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
public class WebSecurityConfig {

	@Autowired
	private JwtFilter filter;

	@Bean
	SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
		//// @formatter:off
		http
		.httpBasic().disable()
		.formLogin().disable()
		.csrf().disable();
		return http.build();
		// @formatter:on

	}

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		System.out.println("hello " + filter);
		return builder.routes()
				.route("user",
						r -> r.path("/auth/**")
								.uri("http://localhost:8085"))
				.route("user",
						r -> r.path("/users/**")
								.uri("http://localhost:8085"))
				.route("advert",
						r -> r.method(HttpMethod.POST,HttpMethod.PUT)
								.and()
								.path(("/adverts/**"))
								.filters(f -> f.filter(filter)).uri("http://localhost:8086"))

				.route("purchase",
						r -> r.method(HttpMethod.GET,HttpMethod.POST,HttpMethod.PUT)
								.and()
								.path("/purchases/**")
								.filters(f -> f.filter(filter)).uri("http://localhost:8088"))
				.route("purchase",
						r -> r.method(HttpMethod.GET,HttpMethod.POST,HttpMethod.PUT)
								.and()
								.path("/purchase-counts/**")
								.filters(f -> f.filter(filter)).uri("http://localhost:8088"))
				.route("payment",
						r -> r.method(HttpMethod.POST,HttpMethod.PUT,HttpMethod.GET)
								.and()
								.path("/payment/**")
								.filters(f -> f.filter(filter)).uri("http://localhost:8080"))
				.build();
	}

}
