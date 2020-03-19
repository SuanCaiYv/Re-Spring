package com.learn.webflux.server;

import com.learn.webflux.config.WebFluxConfig;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.JettyHttpHandlerAdapter;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.http.server.reactive.TomcatHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import reactor.netty.http.server.HttpServer;

import javax.servlet.Servlet;
import java.io.File;

import static org.springframework.web.reactive.function.server.RouterFunctions.toHttpHandler;

/**
 * @author SuanCaiYv
 * @time 2020/3/16 下午6:09
 */
@ComponentScan(basePackages = {"com.learn.webflux"})
public class Server implements Runnable
{
    private int CHOOSE;
    public static final int REACTOR_NETTY = 1;
    public static final int TOMCAT = 2;
    public static final int JETTY = 3;
    public static final int UNDERTOW = 4;

    private int PORT = 8190;
    private static String HOST = "localhost";

    private ApplicationContext applicationContext = new AnnotationConfigApplicationContext(WebFluxConfig.class);

    public Server(int CHOOSE)
    {
        this.CHOOSE = CHOOSE;
    }

    public Server(int CHOOSE, int PORT)
    {
        this.CHOOSE = CHOOSE;
        this.PORT = PORT;
    }

    @Override
    public void run()
    {
        switch (CHOOSE) {
            case REACTOR_NETTY: {
                RouterFunction<?> routerFunctionOne = (RouterFunction<?>) applicationContext.getBean("routerFunctionA");
                HttpHandler httpHandler = toHttpHandler(routerFunctionOne);
                ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(httpHandler);
                HttpServer server = HttpServer.create();
                server.host(HOST).port(PORT).handle(adapter).bind().block();
                break;
            }
            case TOMCAT: {
                // 屮，这个逼没配出来，爷心态崩了
                RouterFunction<?> routerFunctionOne = (RouterFunction<?>) applicationContext.getBean("routerFunctionA");
                HttpHandler httpHandler = RouterFunctions.toHttpHandler(routerFunctionOne);
                Servlet servlet = new TomcatHttpHandlerAdapter(httpHandler);
                Tomcat server = new Tomcat();
                File base = new File(System.getProperty("java.io.tmpdir"));
                Context rootContext = server.addContext("", base.getAbsolutePath());
                Tomcat.addServlet(rootContext, "main", servlet);
                rootContext.addServletMappingDecoded("/", "main");
                server.setPort(PORT);
                try {
                    server.start();
                } catch (LifecycleException e) {
                    e.printStackTrace();
                }
                break;
            }
            case JETTY: {
                RouterFunction<?> routerFunctionOne = (RouterFunction<?>) applicationContext.getBean("routerFunctionA");
                HttpHandler httpHandler = RouterFunctions.toHttpHandler(routerFunctionOne);
                Servlet servlet = new JettyHttpHandlerAdapter(httpHandler);
                org.eclipse.jetty.server.Server server = new org.eclipse.jetty.server.Server();
                ServletContextHandler contextHandler = new ServletContextHandler(server, "");
                contextHandler.addServlet(new ServletHolder(servlet), "/");
                try {
                    contextHandler.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ServerConnector connector = new ServerConnector(server);
                connector.setHost(HOST);
                connector.setPort(PORT);
                server.addConnector(connector);
                try {
                    server.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case UNDERTOW: {
                break;
            }
            default: {
                break;
            }
        }
    }
}
