import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author joker
 * @time 2020/1/5 下午6:57
 */
@Aspect
@Component
public class PrinterAOP
{
    @Pointcut("execution(* Printer.print(..)) && bean(printerOne)")
    public void cut1() {}
    @Pointcut("execution(* PrinterTwo.*(..)) && bean(printerTwo)")
    public void cut2() {}
    @Before("cut1()")
    public void before1()
    {
        System.out.println("即将开始打印...");
    }
    @After("cut1()")
    public void after1()
    {
        System.out.println("打印完成");
    }
    @Before("cut2()")
    public void before2()
    {
        System.out.println("即将开始工作...");
    }
}
