import org.springframework.stereotype.Component;

/**
 * @author joker
 * @time 2020/1/5 下午7:01
 */
@Component
public class PrinterTwo
{
    public void print()
    {
        System.out.println("二号打印机开始工作...");
    }
    public void say()
    {
        System.out.println("二号说话机开始工作...");
    }
}
