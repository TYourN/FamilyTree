package cn.items.mssm.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.items.mssm.service.FSnameService;


@Controller
@RequestMapping("/ftest")
public class FTestController {
	@Resource
	private FSnameService fSnameService;
	
	@RequestMapping("/Test")
	public String Test(@RequestBody Map<String, String> map ,HttpServletResponse res){
		String content=map.get("content");
		System.out.println(content);
		return null;		
	}
}
