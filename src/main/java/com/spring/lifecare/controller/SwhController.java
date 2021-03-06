package com.spring.lifecare.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.lifecare.persistence.UserDAO;
import com.spring.lifecare.service.DeepLearningService;
import com.spring.lifecare.service.KakaoLoginService;
import com.spring.lifecare.service.LoginService;
import com.spring.lifecare.service.NaverLoginService;
import com.spring.lifecare.vo.UserVO;

@Controller
public class SwhController {
	
	@Autowired
	KakaoLoginService kakao;
	
	@Autowired
	NaverLoginService naver;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	DeepLearningService dservice;

	//메인페이지
	@RequestMapping(value= {"/","/guest/main","/main"})
	public String main(Model model) {
		
		return "main";
	}
	
	//404 에러 페이지
	@RequestMapping("/error_404")
	public String error_404(HttpServletRequest request, Model model) {
		return "error/error_404";
	}
	
	//500 에러 페이지
	@RequestMapping("/error_500")
	public String error_500(HttpServletRequest request, Model model) {
		return "error/error_500";
	}
	
	//카카오 로그인
	@RequestMapping("/kakaoLogin")
    public String kakaoLogin(@RequestParam("code") String code, HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) 
    		throws IOException {
        String access_Token = kakao.getAccessToken(code);
        System.out.println("controller access_token : " + access_Token);
        
        HashMap<String, Object> userKakaoInfo = kakao.getUserInfo(access_Token);
        System.out.println("userKakaoInfo : " + userKakaoInfo);
        
        Map<String, String> userInfo = kakao.checkKakaoId(userKakaoInfo);
        
        if(userInfo == null  ||userInfo.get("USERNAME").equals("") || userInfo.get("USERNAME")==null ) {//데이터베이스에 카카오 로그인 정보가 없을경우
        	String errormsg = "카카오와 연동된 아이디가 없습니다";
		   	model.addAttribute("fail", "1");
			model.addAttribute("errMsg", errormsg);
        	
        	return "guest/login";//로그인 페이지로 이동
        }else {//데이터베이스에 카카오 로그인 정보가 있을경우
        		//클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록
            if (userInfo.get("email") != null) {
                //session.setAttribute("userId", userInfo.get("email"));
                //session.setAttribute("access_Token", access_Token);
            	model.addAttribute("email", userInfo.get("email"));
            }
          //권한 부여
            kakao.kakaoAutehntication(request, response, userInfo);
            
        }
        model.addAttribute("trash", "trash");
        System.out.println("model : " +model.toString());
        return "main";
    }

	//네이버 로그인
	@RequestMapping("/naverLogin")
	public String naverLogin(HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		HashMap<String, Object> userInfo= null;
		Map<String, String> user = null;
		
		try {
			userInfo = naver.callback(request);
		}catch(UnsupportedEncodingException e) {
			System.out.println("encodingException");
		}
		
		System.out.println("userInfo : "+ userInfo);
		
		if(userInfo != null) {
			user = naver.checkNaverId(userInfo);
		}
		
		System.out.println("user" + user);
		
		if(user == null  ||user.get("USERNAME").equals("") || user.get("USERNAME")==null ) {//데이터베이스에 카카오 로그인 정보가 없을경우
			
			String errormsg = "네이버와 연동된 아이디가 없습니다";
		   	model.addAttribute("fail", "1");
			model.addAttribute("errMsg", errormsg);
		   	
		   	return "guest/login";//로그인 페이지로 이동
		}else {//데이터베이스에 카카오 로그인 정보가 있을경우
		   		//클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록
		       if (userInfo.get("email") != null) {
		           //session.setAttribute("userId", userInfo.get("email"));
		           //session.setAttribute("access_Token", access_Token);
		       	model.addAttribute("email", userInfo.get("email"));
		       }
		     //권한 부여
		     naver.naverAutehntication(request, response, user);
		}
		return "main";
	}
	
	@RequestMapping("/main_reset")
	public String main_reset(HttpServletRequest request, Model model) {
		return "/";
	}
	
	@RequestMapping("/customer/test")
	public String test(HttpServletRequest request, Model model, HttpSession session) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		String sName = "";
		Enumeration attEnum = session.getAttributeNames();
		while(attEnum.hasMoreElements()) {
		     sName=(String)attEnum.nextElement();
		     //if (sName.indexOf("star.") >=0) { session.removeAttribute(sName); }
		     String sValue = "";
		     try {
		    	 sValue = (String)session.getAttribute(sName);
		    	 
		    	 if (sName.equals("SPRING_SECURITY_CONTEXT")) {

					SecurityContext value = (SecurityContext) session.getAttribute(sName);

					Authentication authentication = value.getAuthentication();

					UserVO principal = (UserVO) authentication.getPrincipal();

					WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();

					String username = authentication.getName();

					String password = (String) authentication.getCredentials();
					
					System.out.println("Security Context principal : "+ principal);
					System.out.println("Security Context userName : " + username);
					System.out.println("Security Context password : "+ password);
					System.out.println("Security Context detail : " + details);
			     }
		     }catch(Exception e) {
		    	 System.out.println(e.getMessage());
		     }
		      
		     System.out.println("session : "+sName +"/ value : "+sValue);
		}
		
		Object principal = auth.getPrincipal();	
		String name = "";
		if(principal != null && principal instanceof UserVO){
			name = ((UserVO)principal).getUserid();
		}
		
		System.out.println("principal : "+principal);
		System.out.println("detail : "+auth.getDetails());
		System.out.println("Name :"+auth.getName());
		System.out.println("check :"+auth.isAuthenticated());

		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		String test = authorities.toString();
		System.out.print("권한 : "+test);
		
		return "customer/test";
	}
	
	@RequestMapping("/doctor/test")
	public String test2(HttpServletRequest request, Model model, HttpSession session) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		String username ="";
		if (principal instanceof UserDetails) {
		  username = ((UserDetails)principal).getUsername();
		} else {
		  username = principal.toString();
		}
		System.out.println(username);
		
		return "customer/test";
	}
	
	
	//////////////////////////////안드로이드 관련
	@ResponseBody
	@RequestMapping("/android/androidSignIn")
	public Map<String, String> androidSignIn(HttpServletRequest req){
		Map<String, String>  map = loginService.androidLogin(req);
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/android/kakaoSignIn")
	public Map<String, String> kakaoSignIn(HttpServletRequest req){
		Map<String, String> map = loginService.kakaoSignIn(req);
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/android/naversignIn")
	public Map<String, String> naversignIn(HttpServletRequest req){
		Map<String, String> map = loginService.naversignIn(req);
		return map;
	}
	
	/////////////////////////// 딥러닝 관련
	@RequestMapping("/guest/covidTestTest")
	public String covidTestTest(HttpServletRequest req, Model model) {
		dservice.covidTestTest(req);
			
		
		return "doctor/covidTest";
	}
	
	//딥러닝 
	@ResponseBody
	@RequestMapping("/doctor/DeepLearningCorona")
	public Map<String, Object> DeepLearningCorona(MultipartHttpServletRequest req, Model model) {
		
		return dservice.DeepLearningCorona(req, model);
	}
}


