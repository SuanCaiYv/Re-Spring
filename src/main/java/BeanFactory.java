import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author joker
 * @time 2020/1/5 下午7:09
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("java")
public class BeanFactory
{
    @Bean("printerOne")
    public PrinterOne printerOne()
    {
        return new PrinterOne();
    }
    @Bean("printerTwo")
    public PrinterTwo printerTwo()
    {
        return new PrinterTwo();
    }
    @Bean("printerThree")
    public PrinterThree printerThree()
    {
        return new PrinterThree();
    }
    @Bean("printerFour")
    public PrinterFour printerFour()
    {
        return new PrinterFour();
    }
    @Bean("printerFive")
    public PrinterFive printerFive()
    {
        return new PrinterFive();
    }
    @Bean("printerAop")
    public PrinterAOP printerAop()
    {
        return new PrinterAOP();
    }
}
