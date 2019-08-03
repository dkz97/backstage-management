package dkz97.controller;

import dkz97.domain.SysLog;
import dkz97.service.impl.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.misc.Request;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

// 这是一个aop的一个拦截器，aspect这个注解就表明是一个拦截器，before就是在代码发生之前做的事情
// 主要是要获取使用者的名称、ip、使用时长，还有要访问的url
@Component
@Aspect
public class LogAop {

    private Date visiTime; //开始时间
    private Class clazz; //目标要访问的类
    private Method method; //访问的方法

    // 在web.xml中已经配置好的监听器，可以获取对象HttpServletrequest
    @Autowired
    private HttpServletRequest request;

    // 使用service，把东西封装到里面去
    @Autowired
    private SysLogService sysLogService;


    //springmvc的拦截器，before主要就是在拦截之前做的事情
    // joinPoint就是连接点的对象，就是在进行切面配置的时候，通过拦截器的对象,封装了代理方法信息的对象
    @Before("execution(* dkz97.controller.*.*(..))") // 拦截所有的controller对象
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        //前置通知主要获取开始时间，要访问的类，要访问的方法
        visiTime = new Date();

        // 获取具体要访问的类
        clazz = jp.getTarget().getClass();

        // 获取访问的方法，比较复杂，先获取访问的参数，然后获取访问的方法名称，再根据是否有参数来进行获取方法
        Object[] args = jp.getArgs(); // 获取访问参数

        String methodName = jp.getSignature().getName(); // 获取要访问的方法名称
        if (args == null || args.length == 0) {
            method = clazz.getMethod(methodName);
        } else {
            Class[] classArgs = new Class[args.length];
            for (int i =0;i<args.length;i++) {
                classArgs[i] = args[i].getClass();
            }
            method = clazz.getMethod(methodName,classArgs); //因为如果方法有参数的话，就需要传入参数的class列表
        }
    }

    // 后置通知
    @After("execution(* dkz97.controller.*.*(..))")
    public void doAfter(){
        // 获取操作的时间
        long time = new Date().getTime() - visiTime.getTime();


        // 获取访问的url路径，使用反射，类和方法来获取注解
        String url = "";
        if(clazz!=null&&method!=null&&clazz != LogAop.class) {
            // 获取类的Url路径
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (classAnnotation != null) {
                String[] classUrl = classAnnotation.value();

                // 再判断方法中的注解
                RequestMapping methodAnnotation = (RequestMapping) method.getAnnotation(RequestMapping.class);

                if (methodAnnotation != null) {
                    String[] methodUrl = methodAnnotation.value();

                    url = classUrl[0] + methodUrl[0];
                }
            }
        }


        // 获取使用者ip地址，现在web.xml中配置listener 获取request对象
        String ip = request.getRemoteAddr();

        // 获取使用者的名称,使用spring-security框架中的对象来用户对象，然后再获取名称
        SecurityContext context = SecurityContextHolder.getContext();
        User user = (User) context.getAuthentication().getPrincipal();
        String username = user.getUsername();


        //把相关信息封装到syslog对象中
        // 日志的实体类对象，获取的东西封装到里面去
        SysLog sysLog = new SysLog();
        sysLog.setExecutionTime(time); // 执行时长
        sysLog.setIp(ip);
        sysLog.setMethod("类名: " + clazz.getName() + " 方法名: " + method.getName());
        sysLog.setUrl(url);
        sysLog.setUsername(username);
        sysLog.setVisitTime(visiTime);
        System.out.println(sysLog);
        sysLogService.save(sysLog);
    }
}
