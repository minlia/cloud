package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.UUID;

@SpringBootApplication
@RestController
@EnableResourceServer
public class ResourceApplication extends WebMvcConfigurerAdapter {


    @RequestMapping(value = "/v2/hello")
    public HelloResponseBody home() {
//		[id: UUID.randomUUID().toString(), content: 'Hello Resource']
        HelloResponseBody body = new HelloResponseBody();
        body.setId(UUID.randomUUID().toString());
        body.setContent("Hello Resource");
        return body;
    }


    public static void main(String[] args) {
        SpringApplication.run(ResourceApplication.class, args);
    }

    public void configure(AuthorizationServerSecurityConfigurer oauthServer)
            throws Exception {
        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess(
                "isAuthenticated()");
    }




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
