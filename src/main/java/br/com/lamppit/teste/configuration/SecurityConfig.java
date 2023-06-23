package br.com.lamppit.teste.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableWebSecurity
public class SecurityConfig {
//
//	@Autowired
//	private JwtAuthenticationFilter jwtAuthenticationFilter;
//
//	@Value("${allowedHosts}")
//	private String[] allowedOrigins;
//
//	@Bean
//	public PasswordEncoder getPasswordEncoder() {
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		return encoder;
//	}
//
//	@Bean
//	public CorsFilter corsFilter() {
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		CorsConfiguration config = new CorsConfiguration();
//		for (String origin : allowedOrigins) {
//			config.addAllowedOrigin(origin);
//		}
//		config.setAllowCredentials(true);
//		config.addAllowedHeader("*");
//		config.addAllowedMethod("*");
//
//		source.registerCorsConfiguration("/**", config);
//
//		return new CorsFilter(source);
//	}
//
//	@Bean
//	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
//			throws Exception {
//		return authenticationConfiguration.getAuthenticationManager();
//	}
//
//	@Bean
//	protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//		http
//				.csrf(csrf -> csrf.disable())
//				.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//				.authorizeHttpRequests(request -> request
//						.requestMatchers(new AntPathRequestMatcher("/auth/login")).permitAll()
//						.requestMatchers(new AntPathRequestMatcher("/v2/api-docs")).permitAll()
//						.requestMatchers(new AntPathRequestMatcher("/configuration/ui")).permitAll()
//						.requestMatchers(new AntPathRequestMatcher("/swagger-resources/**")).permitAll()
//						.requestMatchers(new AntPathRequestMatcher("/configuration/security")).permitAll()
//						.requestMatchers(new AntPathRequestMatcher("/swagger-ui.html")).permitAll()
//						.requestMatchers(new AntPathRequestMatcher("/webjars/**")).permitAll()
//						.anyRequest().permitAll());
//						// .authenticated());
//
//		return http.build();
//	}

}
