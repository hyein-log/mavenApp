package com.kosta.mavenApp.section4.aop2;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//Advice : 보조업무 
@Component
@Aspect //advise + pointcut
public class LoggingAdvice {
	@Pointcut("execution(* add(int))|| execution(* add(int, int))")
	public void targetMethod() {
		
	}
	@Around("targetMethod()")
	public Object around(ProceedingJoinPoint jp) throws Throwable {
		//1.
		System.out.println("주업무 전에 수행"+jp.getSignature().getName() + "메서드 호출 전");
		//2. before
		//3. 주업무
		Object obj = jp.proceed(); //주 업무 호출하기 
		//4. afterReturning
		//5. after
		System.out.println("주업무 후에 수행"+jp.getSignature().getName() + "메서드 호출 후");
		return obj;//return 주업무를 안해주면 안됨 무조건 해야함 당연하지 주업무니까
	}
	
	@Before("targetMethod()")
	public void test(JoinPoint jp) {
		System.out.println("!before! : "+jp.getSignature().getName());
	}
	
	@After("targetMethod()")
	public void test2(JoinPoint jp) {
		System.out.println("!After! : "+jp.getSignature().getName());
	}
	
	@AfterReturning("targetMethod()")
	public void test3(JoinPoint jp) {
		System.out.println("!AfterReturning! : "+jp.getSignature().getName());
	}
}