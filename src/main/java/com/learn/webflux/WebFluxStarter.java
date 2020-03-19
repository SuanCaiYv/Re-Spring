package com.learn.webflux;

import com.learn.webflux.server.Server;
import org.springframework.context.annotation.ComponentScan;


/**
 * @author SuanCaiYv
 * @time 2020/3/15 下午7:18
 */
@ComponentScan(basePackages = {"com.learn.webflux"})
public class WebFluxStarter
{
    public static void main(String[] args) throws Exception
    {
        Server server = new Server(Server.REACTOR_NETTY);
        server.run();
        System.out.println("Press ENTER to exit.");
        var input = System.in.read();
    }

}
