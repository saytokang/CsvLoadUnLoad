package x.y.z.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
								"classpath:/spring/*-context.xml"})
@WebAppConfiguration
public class SampleControllerTest {

	 private MockMvc mockMvc;
	   
	 @Autowired
	 private WebApplicationContext wac;

	 @Before
	 public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	 }

//	 @Test
	 public void testHome() throws Exception {
	    mockMvc.perform(get("/home.do"))
	            .andExpect(status().isOk());
	 }

//	 @Test
	 public void testSampleJson() throws Exception {
	    mockMvc.perform(get("/sampleJson.do"))
	            .andExpect(status().isOk());
	 }
	 
	 @Test
	 public void log4jdbcExcample() throws Exception {
		    mockMvc.perform(get("/log4jdbc_example.do"))
		            .andExpect(status().isOk());
		 }
		
}
