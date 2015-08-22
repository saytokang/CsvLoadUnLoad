package x.y.z.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/spring/*-context.xml"})
public class SampleServiceTest {

	
	@Resource(name="sampleService")
	SampleService sampleService;
	
	
	@Test
	public void getToday() {
		System.out.println(sampleService.getToday());
	}

//	@Test
	public void testDBTransactionSuccess() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("no", 2);
		params.put("id", "100");
		boolean isSuccess = sampleService.saveTowTables(params);
		Assert.assertTrue(isSuccess);
	}
	
	

}
