package cmc.hackaton.server.common.logging;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("execution(* cmc.hackaton.server.controller..*(..))")
    public void controllerPoint(){}

    @Pointcut("execution(* cmc.hackaton.server.service..*(..))")
    public void servicePoint(){}

    @Pointcut("execution(* cmc.hackaton.server.repository..*(..))")
    public void repositoryPoint(){}
}
