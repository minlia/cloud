package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@SpringBootApplication
@RestController
//@SessionAttributes("authorizationRequest")
//@EnableResourceServer
public class ApiApplication   {

	@RequestMapping(value = "/v2/hello")
	public HelloResponseBody home() {
//		[id: UUID.randomUUID().toString(), content: 'Hello Resource']
		HelloResponseBody body = new HelloResponseBody();
		body.setId(UUID.randomUUID().toString());
		body.setContent("Hello Api");
		return body;
	}

//	@RequestMapping("/user")
//	@ResponseBody
//	public Principal user(Principal user) {
//		return user;
//	}
//
//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/login").setViewName("login");
//		registry.addViewController("/oauth/confirm_access").setViewName("authorize");
//	}

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}
//
//	@Configuration
//	@Order(-20)
//	protected static class LoginConfig extends WebSecurityConfigurerAdapter {
//
//		@Autowired
//		private AuthenticationManager authenticationManager;
//
//		@Override
//		protected void configure(HttpSecurity http) throws Exception {
//			// @formatter:off
//			http
//				.formLogin().loginPage("/login").permitAll()
//			.and()
//				.requestMatchers().antMatchers("/login", "/oauth/authorize", "/oauth/confirm_access")
//			.and()
//				.authorizeRequests().anyRequest().authenticated();
//			// @formatter:on
//		}
//
//		@Override
//		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//			auth.parentAuthenticationManager(authenticationManager);
//		}
//	}
//
//	@Configuration
//	@EnableAuthorizationServer
//	protected static class OAuth2AuthorizationConfig extends
//			AuthorizationServerConfigurerAdapter {
//
//		@Autowired
//		private AuthenticationManager authenticationManager;
//
//		@Bean
//		public JwtAccessTokenConverter jwtAccessTokenConverter() {
//			JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//			KeyPair keyPair = new KeyStoreKeyFactory(
//					new ClassPathResource("keystore.jks"), "foobar".toCharArray())
//					.getKeyPair("test");
//			converter.setKeyPair(keyPair);
//			return converter;
//		}
//
//		@Override
//		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//			clients.inMemory()
//					.withClient("acme")
//					.secret("acmesecret")
//					.authorizedGrantTypes("authorization_code", "refresh_token",
//							"password").scopes("openid");
//		}
//
//		@Override
//		public void configure(AuthorizationServerEndpointsConfigurer endpoints)
//				throws Exception {
//			endpoints.authenticationManager(authenticationManager).accessTokenConverter(
//					jwtAccessTokenConverter());
//		}
//
//		@Override
//		public void configure(AuthorizationServerSecurityConfigurer oauthServer)
//				throws Exception {
//			oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess(
//					"isAuthenticated()");
//		}
//
//	}





	class HelloResponseBody {
		private String id;
		private String content;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}
	}
}
