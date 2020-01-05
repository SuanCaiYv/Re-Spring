import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author joker
 * @time 2020/1/5 下午6:57
 */
public class Main
{
    @Test
    public void main()
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanFactory.class);
        Printer printerOne = (Printer) context.getBean("printerOne");
        PrinterTwo printerTwo = (PrinterTwo) context.getBean("printerTwo");
        PrinterThree printerThree = (PrinterThree) context.getBean("printerThree");
        printerOne.print();
        printerTwo.print();
        printerTwo.say();
        printerThree.print();
    }
}
