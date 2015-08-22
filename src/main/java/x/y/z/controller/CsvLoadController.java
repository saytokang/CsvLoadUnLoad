package x.y.z.controller;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import x.y.z.service.CsvLoadService;

@Controller
public class CsvLoadController {

	@Autowired
	CsvLoadService csvLoadService;
	
	@RequestMapping(value="/load.do")
	public String loadCsvFile(@RequestParam("file") MultipartFile file) throws IOException {
		List<String> lines = IOUtils.readLines(file.getInputStream());
		boolean rs = csvLoadService.saveToDB(lines);
		return "completeLoad";
	}
}
