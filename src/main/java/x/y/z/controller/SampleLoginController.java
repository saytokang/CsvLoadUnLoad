package x.y.z.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import x.y.z.service.CommonService;

@Controller
public class SampleLoginController {

	// 공통 기능을 위한 서비스. : 여기서는 메시지 처리 기능을 위해 사용되었음.
	@Resource(name="commonService")
	private CommonService commonService;
	
	
	@RequestMapping(value="/user/welcom.do")
	public String welcomUser(HttpSession session, Model model){
		SecurityContext ctx = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
		Authentication auth=ctx.getAuthentication();
		model.addAttribute("user", auth.getName());
		
		return "user/welcom";
	}
	
	@RequestMapping(value="/admin/welcom.do")
	public String welcomAdmin(HttpSession session, Model model){
		SecurityContext ctx = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT_ADMIN_FORM");
		Authentication auth=ctx.getAuthentication();
		model.addAttribute("user", auth.getName());
		
		return "admin/welcom";
	}
		
	
	@RequestMapping(value="/non-auth/login.do")
	public String login(){
		return "login";
	}

	@RequestMapping(value="/admin/non-auth/login.do")
	public String loginAdmin(){
		return "admin/login";
	}
	
	@RequestMapping(value="/logout.do")
	public String logout(HttpSession session){
		session.invalidate();
		System.out.println("call  user logout");
		return "redirect:/non-auth/login.do";
	}

	@RequestMapping(value="/admin/logout.do")
	public String logoutAdmin(){
		System.out.println("call admin logout");
		return "redirect:/admin/non-auth/login.do";
	}	
	
}
