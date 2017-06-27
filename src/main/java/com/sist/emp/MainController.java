package com.sist.emp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@RequestMapping("main/main.do")
	public String main_main(){
		return "main/main";
	}
	
	/*
	 *<context:annotation-config/>
	<!-- DI
		=====객체주소값 주입
		@Autowired
		@Resource

		=====메소드 호		
		@Postconstruct (init-method)
		@PreDestroy(destroy-method)
	 <bean id="" class="" destroy-method=""></bean>
		
	 -->	
	
	<context:component-scan base-package="com.sist.*"/>
	<!-- 클래스 메모리 할당
		@Component
		@Repository
		@Service
		@Controller
		 => 사용자 정의
		
		사용자 정의(Annotation)
		라이브러리(XML => bean)
	 -->
	
	<bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver"
		p:prefix="/"
		p:suffix=".jsp"
	/> 
	 * 
	 * */
	
}
