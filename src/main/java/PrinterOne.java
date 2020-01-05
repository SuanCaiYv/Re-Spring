import org.springframework.stereotype.Component;

/**
 * @author joker
 * @time 2020/1/5 下午6:57
 */
@Component
public class PrinterOne implements Printer
{
    @Override
    public void print()
    {
        System.out.println("一号打印机开始工作...");
    }
}
