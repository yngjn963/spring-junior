package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
// 스프링 빈으로 등록해야 한다.
//@Component // OR SpringConfig에 빈 등록
public class TimeTraceAop {
    @Around("execution(* hello.hellospring..*(..))") // classpath(parameter)
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());

        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
    /*
    AOP 적용 후 의존관계
    AOP를 적용하면 적용한 대상의 가짜 Controller, Service, Repository를 만든다: 프록시
    그 후 joinPoint.proceed()로 진짜를 실행한다.
    - 프록시 방식의 AOP
     */
}
