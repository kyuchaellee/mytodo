package org.zerock.mytodo.listener;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebListener
@Log4j2
public class CountListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    public CountListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {  //웹어플리케이션이 처음에 뜰 때
        /* This method is called when the servlet context is initialized(when the Web application is deployed). */
        log.info("init../......................");
        log.info("init../......................");
        log.info("init../......................");
        log.info("init../......................");
    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
        log.info("destroyed.................................");
        log.info("destroyed.................................");
        log.info("destroyed.................................");
        log.info("destroyed.................................");
        sce.getServletContext().setAttribute("Sample",new StringBuffer());  // singleton 임
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is added to a session. */
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is removed from a session. */
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is replaced in a session. */
    }
}
