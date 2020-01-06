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
        System.out.println("#######################");
        printerTwo.print();
        System.out.println("#######################");
        printerTwo.say();
        System.out.println("#######################");
        printerThree.print();
        System.out.println("#######################");
        PrinterFour printerFour = (PrinterFour) context.getBean("printerFour");
        printerFour.print("我是文本");
        printerFour.say("我是话");
    }
}
