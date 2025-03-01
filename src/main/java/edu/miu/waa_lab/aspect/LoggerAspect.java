package edu.miu.waa_lab.aspect;

import edu.miu.waa_lab.entity.Logger;
import edu.miu.waa_lab.repository.LoggerRepo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Aspect
@Component
public class LoggerAspect {

    @Autowired
    private LoggerRepo loggerRepo;

    private static final String FAKE_USER = "fakeUser";

    //@Pointcut("execution(* edu.miu.waa_lab.service.*.*(..))")
    @Pointcut("execution(* edu.miu.waa_lab.controller.*.*(..))")
    public void serviceMethods() {}

    @After("serviceMethods()")
    public void logOperation(JoinPoint joinPoint) {
        Logger logger = new Logger();
        logger.setTransactionId(UUID.randomUUID().toString());
        logger.setDate(LocalDate.now());
        logger.setTime(LocalTime.now());
        logger.setPrinciple(FAKE_USER);
        logger.setOperation(joinPoint.getSignature() + " Operation executed");

        loggerRepo.save(logger);
    }

}
