package x.y.z.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/spring/*-context.xml"})
public class CsvLoadDAOTest {

	@Autowired
	CsvLoadDAO csvLoadDAO;
	
//	@Test
	public void saveToDB() throws FileNotFoundException, IOException {
		String path = "/Users/kangdonhee/Downloads/zipcode_utf.csv";
		List<String> lines = IOUtils.readLines(new FileInputStream(path));
	
		boolean rs  = csvLoadDAO.saveToDB(lines);
		Assert.assertTrue(rs);
	}

//	@Test
	public void saveToDB2() throws FileNotFoundException, IOException {
		String path = "/Users/kangdonhee/Downloads/zipcode_utf.csv";
		List<String> lines = IOUtils.readLines(new FileInputStream(path));
	
		boolean rs  = csvLoadDAO.saveToDB2(lines);
		Assert.assertTrue(rs);
	}


	@Test
	public void saveToDB3() throws FileNotFoundException, IOException {
		String path = "/Users/kangdonhee/Downloads/zipcode_utf.csv";
		boolean rs  = csvLoadDAO.saveToDB3(path);
		Assert.assertTrue(rs);
	}
}
