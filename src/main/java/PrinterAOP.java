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

    /**
     * 切点1
     * bean指定作用于哪一个Bean上
     */
    @Pointcut("execution(* Printer.print(..)) && bean(printerOne)")
    public void cut1() {}
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

    /**
     * 切点2
     */
    @Pointcut("execution(* PrinterTwo.*(..)) && bean(printerTwo)")
    public void cut2() {}
    @Before("cut2()")
    public void before2()
    {
        System.out.println("二号打印机即将开始工作...");
    }

    /**
     * 切点3
     */
    @Pointcut("execution(* PrinterThree.print())")
    public void cut3() {}
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
    /**
     * 切点4
     * 获得了目标方法的String类型参数, 切点表达式中的参数名必须和cut()方法中的参数名一致
     */
    @Pointcut("execution(* PrinterFour.print(String)) && args(txt)")
    public void cut4(String txt) {}
    @Pointcut("execution(* PrinterFour.say(String))")
    public void cut40() {}
    @Before(value = "cut4(txt)", argNames = "txt")
    public void before4(String txt)
    {
        System.out.println("四号打印机即将开始打印如下内容: "+txt);
    }

    /**
     * 修改目标方法参数只有环绕通知才能实现
     * @param pjp
     * @return pjp
     */
    @Around(value = "cut40()")
    public Object around4(ProceedingJoinPoint pjp)
    {
        try {
            // 获取目标方法的参数
            Object[] args = pjp.getArgs();
            String newTxt = (String) args[0];
            // 修改参数
            args[0] = "(修改过的标记)"+newTxt;
            // 执行目标方法,别忘了带上参数
            pjp.proceed(args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally{
            ;
        }
        return pjp;
    }

    /**
     * 扩展类方法, 参数分别为: 目标类, 默认实现类(调用委托类), 以及一个静态变量, 指出接口是谁
     */
    @DeclareParents(value = "PrinterFive", defaultImpl = NewFuncImpl.class)
    public static NewFunc newFunc;
}
