import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author joker
 * @time 2020/1/5 下午6:57
 */
@Aspect
@Component
public class PrinterAOP
{
    //bean指定作用于哪一个Bean上
    @Pointcut("execution(* Printer.print(..)) && bean(printerOne)")
    public void cut1() {}
    @Pointcut("execution(* PrinterTwo.*(..)) && bean(printerTwo)")
    public void cut2() {}
    @Pointcut("execution(* PrinterThree.print())")
    public void cut3() {}
    @Before("cut1()")
    public void before1()
    {
        System.out.println("一号打印机即将开始打印...");
    }
    @After("cut1()")
    public void after1()
    {
        System.out.println("一号打印机打印完成!");
    }
    @AfterReturning("cut1()")
    public void afterReturning1()
    {
        System.out.println("一号打印机完美工作!");
    }
    @AfterThrowing("cut1()")
    public void afterThrowing1()
    {
        System.out.println("一号打印机遇到异常!");
    }
    @Before("cut2()")
    public void before2()
    {
        System.out.println("二号打印机即将开始工作...");
    }
    @Around("cut3()")
    public Object around3(ProceedingJoinPoint pjp)
    {
        try {
            System.out.println("三号打印机即将开始工作...");
            pjp.proceed();
            System.out.println("三号打印机完美工作!");
        } catch (Throwable throwable) {
            System.out.println("三号打印机遇到错误:"+throwable.getMessage());
        } finally {
            ;
        }
        return pjp;
    }
}
