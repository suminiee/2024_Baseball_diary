package com.baseball.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect //부가기능 정의(Advice)  + 여기에 적용(Pointcut) => 합침
@Component
public class TimeTraceAop {

    @Around("execution(* com.baseball..*(..))") //package 적용 범위 (pointCut) 언제 어디에 적용할건지
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        // joinpoint 타겟이 적용하는 메소드

        //시간 측정
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        //본래 할 일 수행
        Object proceedObj = joinPoint.proceed();

        stopWatch.stop();
        log.info("###### 실행 시간: {}", stopWatch.getTotalTimeMillis());
        log.info("$$$$$$$$$" + stopWatch.prettyPrint());
        return proceedObj;
    }
}
