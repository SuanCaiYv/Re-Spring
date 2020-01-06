/**
 * @author joker
 * @time 2020/1/6 下午12:50
 */
public class NewFuncImpl implements NewFunc
{
    @Override
    public void newMethod(Object[] args)
    {
        int a = (int) args[0];
        int b = (int) args[1];
        System.out.println(a+"+"+b+"="+(a+b));
    }
}
