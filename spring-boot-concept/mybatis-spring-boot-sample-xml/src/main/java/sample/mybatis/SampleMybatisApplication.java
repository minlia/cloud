/*
 *    Copyright 2010-2015 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package sample.mybatis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;

//
//@SpringBootApplication
//public class SampleMybatisApplication implements CommandLineRunner {
//
//	@Autowired
//	private CityMapper cityMapper;
//
//	public static void main(String[] args) {
//		SpringApplication.run(SampleMybatisApplication.class, args);
//	}
//
//	@Override
//	public void run(String... args) throws Exception {
//		System.out.println(this.cityMapper.selectCityById(1));
//	}
//
//}

/**
 * Created by user on 11/14/15.
 */
@SpringBootApplication
public class SampleMybatisApplication {

	private static final Logger log = LoggerFactory.getLogger(SampleMybatisApplication.class);

	@Autowired
	private Environment env;

	@PostConstruct
	public void initApplication() throws IOException {
		if (env.getActiveProfiles().length == 0) {
			log.warn("No Spring profile configured, running with default configuration");
		} else {
			log.info("Running with Spring profile(s) : {}", Arrays.toString(env.getActiveProfiles()));
			Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
			if (activeProfiles.contains("dev") && activeProfiles.contains("prod")) {
				log.error("You have misconfigured your application! " +
						"It should not run with both the 'dev' and 'prod' profiles at the same time.");
			}
			if (activeProfiles.contains("prod") && activeProfiles.contains("fast")) {
				log.error("You have misconfigured your application! " +
						"It should not run with both the 'prod' and 'fast' profiles at the same time.");
			}
			if (activeProfiles.contains("dev") && activeProfiles.contains("cloud")) {
				log.error("You have misconfigured your application! " +
						"It should not run with both the 'dev' and 'cloud' profiles at the same time.");
			}
		}
	}

	/**
	 * Main method, used to run the application.
	 */
	public static void main(String[] args) throws UnknownHostException {
		SpringApplication app = new SpringApplication(SampleMybatisApplication.class);
		SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(args);
		addDefaultProfile(app, source);
		Environment env = app.run(args).getEnvironment();
		String info=String.format("Access URLs:\n----------------------------------------------------------\n\t" +
						"Local: \t\thttp://127.0.0.1:%s\n\t" +
						"External: \thttp://%s:%s\n----------------------------------------------------------",
//        String info=String.format("Access URLs:\n----------------------------------------------------------\n\t" +
//                        "Local: \t\thttp://127.0.0.1:{}\n\t" +
//                        "External: \thttp://{}:{}\n----------------------------------------------------------",
				env.getProperty("server.port"),
				InetAddress.getLocalHost().getHostAddress(),
				env.getProperty("server.port"));
		System.out.println(info);

	}

	/**
	 * If no profile has been configured, set by default the "dev" profile.
	 */
	private static void addDefaultProfile(SpringApplication app, SimpleCommandLinePropertySource source) {
		if (!source.containsProperty("spring.profiles.active") &&
				!System.getenv().containsKey("SPRING_PROFILES_ACTIVE")) {
			app.setAdditionalProfiles("dev");
		}
	}



//    @Bean
//    InitializingBean usersAndGroupsInitializer(final IdentityService identityService) {
//
//        return new InitializingBean() {
//            public void afterPropertiesSet() throws Exception {
//
//                Group group = identityService.newGroup("user");
//                group.setName("users");
//                group.setType("security-role");
//                identityService.saveGroup(group);
//
//                User admin = identityService.newUser("admin");
//                admin.setPassword("admin");
//                identityService.saveUser(admin);
//
//            }
//        };
//    }

}

