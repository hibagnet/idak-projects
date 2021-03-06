package fr.idak.tutorial.ws.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		 AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
	        
		    //Déclaration du la classe de configuration du context (dataSource, repositories, spring-security ...)
		    rootContext.register(ContextConfig.class);
	 
	        servletContext.addListener(new ContextLoaderListener(rootContext));
	        
	        //Création du distapcher de servlet
	        AnnotationConfigWebApplicationContext dispatcherServlet = new AnnotationConfigWebApplicationContext();
	        dispatcherServlet.register(WebMvcConfig.class);
	        
	        //Déclaration du distapcher de servlet
	        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(dispatcherServlet));
	        dispatcher.setLoadOnStartup(1);
	        dispatcher.addMapping("/");		
	}
}
