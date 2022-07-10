package pl.fis.lbd.day2.ProjectManagement;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Aspect
@Component
public class ProjectManagementAspect {

    private static final Logger LOG = LoggerFactory.getLogger(ProjectManagementAspect.class);

    @Around("execution(* pl.fis.lbd.day2.ProjectManagement.service..*(Object+,..))")
    public Object aroundAllServiceMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        StringBuilder stringBuilder = new StringBuilder();
        for(Object methodArg : joinPoint.getArgs()) {
            stringBuilder.append(methodArg.toString());
            stringBuilder.append(" ");
        }
        LOG.info("Method {} started with arguments {}", joinPoint.getSignature().getName(), stringBuilder.toString());
        Object result = joinPoint.proceed();
        LOG.info("Method {} returned with result {}", joinPoint.getSignature().getName(), Optional.ofNullable(result).orElse("void").toString());
        return result;
    }
}
