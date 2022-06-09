package com.exam.core;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect		//AOP 설정을 담고 있는 클래스임을 의미
@Component	//컨테이너에 빈생성
public class MyAdvice {
	
	@Before(value="execution(* com.exam.core.*.*(..))") //xml에 쓰인 pointcut을 기입
	public void beforeTargetMethod(JoinPoint thisJoinPoint) {//thisJoinPoint: 해당 메소드가 끼어들 포인트가 저장된 객체
	 
	        //실행될 타겟메서드가 속한 객체의 클래스명
	        String className = thisJoinPoint.getTarget().getClass().getSimpleName();
	        //실행될 타겟메서드명
	        String methodName = thisJoinPoint.getSignature().getName();
	 
	        System.out.println("MyAdvice beforeTargetMethod 실행 >> "+className + "." + methodName );
	    }

	 //@After("execution(* com.exam.core.*.*(..))")
	 public void afterTargetMethod(JoinPoint thisJoinPoint) {//thisJoinPoint: 해당 메소드가 끼어들 포인트가 저장된 객체
		 
	        //실행될 타겟메서드가 속한 객체의 클래스명
	        String className = thisJoinPoint.getTarget().getClass().getSimpleName();
	        //실행될 타겟메서드명
	        String methodName = thisJoinPoint.getSignature().getName();
	 
	        System.out.println("MyAdvice afterTargetMethod 실행 >> "+className + "." + methodName );
	    }
	 
	 @Around("MyServicePoint()")
	 public Object aroundTargetMethod(ProceedingJoinPoint thisJoinPoint) throws Throwable {
		 
	        System.out.println("MyAdvice.aroundTargetMethod  "+thisJoinPoint.getSignature().getName()+"  "+"start.");
	        long time1 = System.currentTimeMillis();
	        Object retVal = thisJoinPoint.proceed();//타겟메서드를 실행(이거 없으면 타겟 메서드 실행 안함)
	 
	        System.out.println("ProceedingJoinPoint executed. return value is ["+ retVal + "]");
	 
	        retVal = retVal +" ."+ thisJoinPoint.getSignature().getName();
	        System.out.println("return value modified to [" + retVal + "]");
	 
	        long time2 = System.currentTimeMillis();
	        System.out.println("MyAdvice.aroundTargetMethod end. Time(" + (time2 - time1) + ")");
	        System.out.println("MyAdvice.aroundTargetMethod  "+thisJoinPoint.getSignature().getName()+"  "+"start.");
	        return retVal;
	    }
	 
	 //<aop:pointcut expression="execution(* com.exam.core.MyService.*(..))" id="MyServicePoint"/> 처럼 pointcut을 변수로 치환
	 //반복적으로 사용하는 포인트컷을 별도로 정의하고, ("메소드명()")와 같이 사용 가능
	 @Pointcut(value="execution(* com.exam.core.*.*(..))")
	 private void MyServicePoint() {}	//반환타입은 void, 메서드 내용 불필요
	 
}
