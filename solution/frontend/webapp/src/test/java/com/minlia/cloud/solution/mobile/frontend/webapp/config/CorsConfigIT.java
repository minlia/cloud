package com.minlia.cloud.solution.mobile.frontend.webapp.config;


import com.minlia.cloud.solution.mobile.frontend.webapp.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

@RunWith(SpringJUnit4ClassRunner.class)
@WebIntegrationTest("server.port:0")
@SpringApplicationConfiguration(classes = {Application.class, CorsConfiguration.class})
public class CorsConfigIT {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        final Filter simpleCorsFilter = this.wac.getBean(CorsConfiguration.SimpleCorsFilter.class);
        assertThat(simpleCorsFilter, is(notNullValue()));

        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                .dispatchOptions(true).addFilters(simpleCorsFilter)
                .build();
    }

    @Test
    public void testCorsHeaders() throws Exception {
        mockMvc.perform(get("/hello")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE"))
                .andExpect(header().string("Access-Control-Max-Age", "3600"))
                .andExpect(header().string("Access-Control-Allow-Headers", "x-requested-with"))
                .andExpect(header().doesNotExist("Access-Control-Allow-Credentials"));
    }

    @Test
    public void testCorsHeadersWithOriginHeader() throws Exception {
        String origin = "foo.example.com";
        mockMvc.perform(get("/hello")
                .header("Origin", origin)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Access-Control-Allow-Origin", origin))
                .andExpect(header().string("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE"))
                .andExpect(header().string("Access-Control-Max-Age", "3600"))
                .andExpect(header().string("Access-Control-Allow-Headers", "x-requested-with"))
                .andExpect(header().string("Access-Control-Allow-Credentials", "true"));
    }
}
