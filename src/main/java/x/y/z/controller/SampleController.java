package x.y.z.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import x.y.z.service.SampleService;

@Controller
public class SampleController {

	@Resource(name="sampleService")
	private SampleService sampleService;
	
	@RequestMapping(value="/sampleJson.do", produces = "application/json; charset=utf8")
	@ResponseBody
	public Map<String, String> getJsonData(@RequestParam Map<String, String> params){
		
		params.put("add_server_v1", "값 1");
		params.put("add_server_v2", "값 2");
		params.put("add_server_v3", "값 3");
		
		// service alll result
		System.out.println(params);
		return params;
	}
	
	@RequestMapping("/home.do")
	public String home(Model model){
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id", new Date());
		model.addAttribute("val", data);
		System.out.println("call home");
		
		return "home";
	}
	
	@RequestMapping(value="/log4jdbc_example.do")
	public String log4jdbcExcample(ModelMap model) {
		List<Map<String, Object>> list = sampleService.getSampleDBList();
		model.addAttribute("list", list);
		
		return "log4jdbc";
	}
	
}
