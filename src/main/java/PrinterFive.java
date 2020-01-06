import java.util.Scanner;

/**
 * @author joker
 * @time 2020/1/6 下午12:51
 */
public class PrinterFive
{
    public Object[] print()
    {
        Object[] args = new Object[2];
        int a, b;
        System.out.println("五号打印机开始工作, 请输入两个数: ");
        Scanner scanner = new Scanner(System.in);
        a = scanner.nextInt();
        b = scanner.nextInt();
        args[0] = a;
        args[1] = b;
        return args;
    }
}
