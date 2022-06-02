package com.exam.core;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class MyAdvice {
	
	 public void beforeTargetMethod(JoinPoint thisJoinPoint) {//thisJoinPoint: 해당 메소드가 끼어들 포인트가 저장된 객체
	 
	        //실행될 타겟메서드가 속한 객체의 클래스명
	        String className = thisJoinPoint.getTarget().getClass().getSimpleName();
	        //실행될 타겟메서드명
	        String methodName = thisJoinPoint.getSignature().getName();
	 
	        System.out.println("MyAdvice beforeTargetMethod 실행 >> "+className + "." + methodName );
	    }
	 
	 public void afterTargetMethod(JoinPoint thisJoinPoint) {//thisJoinPoint: 해당 메소드가 끼어들 포인트가 저장된 객체
		 
	        //실행될 타겟메서드가 속한 객체의 클래스명
	        String className = thisJoinPoint.getTarget().getClass().getSimpleName();
	        //실행될 타겟메서드명
	        String methodName = thisJoinPoint.getSignature().getName();
	 
	        System.out.println("MyAdvice afterTargetMethod 실행 >> "+className + "." + methodName );
	    }
	 
	 public Object aroundTargetMethod(ProceedingJoinPoint thisJoinPoint) throws Throwable {
		 
	        System.out.println("MyAdvice.aroundTargetMethod start.");
	        long time1 = System.currentTimeMillis();
	        Object retVal = thisJoinPoint.proceed();//타겟메서드를 실행(이거 없으면 타겟 메서드 실행 안함)
	 
	        System.out.println("ProceedingJoinPoint executed. return value is ["+ retVal + "]");
	 
	        retVal = retVal +" ."+ thisJoinPoint.getSignature().getName();
	        System.out.println("return value modified to [" + retVal + "]");
	 
	        long time2 = System.currentTimeMillis();
	        System.out.println("MyAdvice.aroundTargetMethod end. Time(" + (time2 - time1) + ")");
	        return retVal;
	    }

}
