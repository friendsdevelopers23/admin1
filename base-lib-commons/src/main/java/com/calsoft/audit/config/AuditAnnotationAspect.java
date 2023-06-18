//package com.calsoft.audit.config;
//
//import com.calsoft.audit.service.AuditService;
//import com.calsoft.pos.model.audit.AuditMe;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Method;
//import java.lang.reflect.Parameter;
//import java.lang.reflect.Type;
//import java.util.Arrays;
//import java.util.List;
//
//@Aspect
//@Configuration
//@Slf4j
//public class AuditAnnotationAspect {
//
//    @Autowired
//    private AuditService auditService;
//
//    @Around("@annotation(com.calsoft.pos.model.audit.AuditMe)")
//    public void processAudit(ProceedingJoinPoint joinPoint) throws Throwable {
//        log.info("Audit Operation :: AuditMe annotation advice invoked...");
//        AuditMe auditMe = null;
//        Object result = null;
//        try {
//            result = joinPoint.proceed();
//            auditMe = (AuditMe) getAnnotation(joinPoint, AuditMe.class);
//            if (auditMe != null) {
//                Class<?> saudit = auditMe.saudit();
//                final String pkField = auditMe.pkField();
//                final Object[] args = joinPoint.getArgs();
//                Arrays.stream(args).forEach(arg -> saveAuditData(arg, saudit, pkField));
//            }
//        } catch (Exception exp) {
//            log.error("Exception while trying...", exp);
//        }
//    }
//
//    private void saveAuditData(Object arg, Class<?> saudit, String pkField) {
//        log.info("----------------<  Annotation Data >---------------");
//        try {
//            if (arg instanceof List) {
//                final List<Type> items = (List<Type>) arg;
//                log.info(" Total Type to be audited >> {} ", items.size());
//                for (Object sObj : items) {
//                    auditService.saveAudit(sObj, pkField);
//                }
//            } else {
//                auditService.saveAudit(arg, pkField);
//            }
//        } catch (Exception auditCastExpection) {
//            log.error("Exception while trying to cast to be audited class :: ", auditCastExpection);
//        }
//    }
//
//    private Annotation getAnnotation(ProceedingJoinPoint joinPoint, Class type) {
//        MethodSignature methodSignature = MethodSignature.class.cast(joinPoint.getSignature());
//        Method method = methodSignature.getMethod();
//        final Parameter[] parameters = method.getParameters();
//        return method.getAnnotation(type);
//    }
//
//}
