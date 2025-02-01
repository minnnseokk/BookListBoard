package bookBoard.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

// Aspect - Aop 의 모듈
// Slf4j - 로그 어노테이션
// Component - 컴폰넌트 단위의 모듈 구조
@Aspect
@Slf4j
@Component
public class LoggerAspect {
	// Pointcut - Aspect가 적용될 범주 설정한 메서드 생성
	// execution(* bookBoard..controller.*Controller.*(..))
	// bookBoard 패키지 및 하위 패키지의 모든 controller 패키지에서 
	// 이름이 Controller로 끝나는 클래스의 모든 메서드에 Aspect 적용
	@Pointcut("execution(* bookBoard..controller.*Controller.*(..)) || execution(* bookBoard..service.*ServiceImpl.*(..)) || execution(* bookBoard..mapper.*Mapper.*(..))")
	private void loggerTarget() {};
	// Around를 통해 위에서 선언한 포인트컷을 연결해줬다.
	@Around("loggerTarget()")
	public Object logPrinter(ProceedingJoinPoint joinPoint) throws Throwable{
		String type = "";
		String className = joinPoint.getSignature().getDeclaringTypeName();
		String methodName = joinPoint.getSignature().getName();
		
		if(className.indexOf("Controller") > -1) {
			type = "[Controller]";
		} else if (className.indexOf("Service") > -1) {
			type = "[Service]";
		} else if (className.indexOf("Mapper") > -1) {
			type = "[Mapper]";
		}
		log.debug(type + " " + className + "." + methodName);
		return joinPoint.proceed();
	}	
	
	
}
