package com.lysj.base;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lysj.fridge.domain.Staff;
import com.lysj.fridge.repository.StaffRepository;
import com.lysj.fridge.repository.UserRepository;

@Configuration
public class InterceptorConfiguration extends WebMvcConfigurerAdapter {
	 private final Logger logger = LoggerFactory.getLogger(this.getClass());  
	
	
	@Resource
	private StaffRepository staffRepository;
	@Resource
	private UserRepository userRepository;


	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(new HandlerInterceptorAdapter() {

			@Override
			public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
				// 允许跨域的配置
				response.addHeader("Access-Control-Allow-Origin", "*");
				response.addHeader("Access-Control-Allow-Methods", "*");
				response.addHeader("Access-Control-Max-Age", "100");
				response.addHeader("Access-Control-Allow-Headers", "Content-Type");
				response.addHeader("Access-Control-Allow-Credentials", "false");

				String remoteIP = request.getRemoteAddr();// 请求IP
				String URL = request.getRequestURI();

//				System.out.println(URL);
				// /////////放行一些固定有的权限/////////////// /wx/login
				if (URL.startsWith("/staff/")) {
					logger.info("Intercept:[" + remoteIP + "]-->" + URL + "-->True[none veryfiy]{staff}"); // 放行,免权限,staff模块
					return true;
				}
				// /////////开始token校验,User模块,token正确即放行///////////////
				String token = request.getParameter("token");
				// 开始校验管理员模块////////////////////////
				Staff staff = staffRepository.findByToken(token);
				if (staff == null || System.currentTimeMillis() > staff.getExpires()) {// 校验token是否正确
					logger.info("Intercept[" + remoteIP + "]-->" + URL + "-->False[Token Error]");
					response.getWriter().write("{\"code\":\"422\",\"msg\":\"Token Error\"}");
					return false;
				}
				request.setAttribute("staff", staff);
				return true;
				// /////////结束权限校验///////////////
			}
		}).addPathPatterns("/**");
	}
}
