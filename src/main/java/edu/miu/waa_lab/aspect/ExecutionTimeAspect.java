package edu.miu.waa_lab.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionTimeAspect {

    /*@Pointcut("@annotation(edu.miu.waa_lab.aspect.annotation.ExecutionTime)")
    public void executionTimeAnnotation() {
    }*/

    // This advice will run around methods annotated with @ExecutionTime
    @Around("@annotation(edu.miu.waa_lab.aspect.annotation.ExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis(); // Start time

        Object result = joinPoint.proceed(); // Execute the method

        long endTime = System.currentTimeMillis(); // End time
        long executionTime = endTime - startTime; // Calculate execution time

        // Log the execution time
        System.out.println("ExecutionTimeAspect: " + joinPoint.getSignature() + " executed in " + executionTime + "ms");

        return result; // Return the result of the method
    }
}
