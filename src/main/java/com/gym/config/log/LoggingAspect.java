package com.gym.config.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Around("execution(* com.gym..*Controller.*(..))")
    public Object logExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        String metodo = joinPoint.getSignature().toShortString();
        log.info("INÍCIO -> {} | args={}", metodo, joinPoint.getArgs());

        long inicio = System.currentTimeMillis();
        try {
            Object resultado = joinPoint.proceed();
            log.info("FIM -> {} | tempo={}ms | retorno={}", metodo,
                    System.currentTimeMillis() - inicio, resultado);
            return resultado;
        } catch (Throwable ex) {
            log.error("ERRO -> {} | tempo={}ms | msg={}", metodo,
                    System.currentTimeMillis() - inicio, ex.getMessage());
            throw ex;
        }
    }

}
