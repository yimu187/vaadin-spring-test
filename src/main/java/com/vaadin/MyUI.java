package com.vaadin;

import com.entities.universitypackage.UniversityBoImpl;
import com.entities.universitypackage.UniversityDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.layouts.GeneralMenuBar;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 */
@Theme("mytheme")
@Configurable(preConstruction = true)
public class MyUI extends UI {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private GeneralMenuBar menuBar;
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        ServletContext servletContext = ((HttpServletRequest) vaadinRequest).getSession().getServletContext();
        applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        menuBar = applicationContext.getBean(GeneralMenuBar.class);

    	final VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setMargin(true);
        setContent(mainLayout);
        
        mainLayout.addComponent(menuBar);
        menuBar.setVerticalLayout(mainLayout);
        
        FormLayout bodyForm = menuBar.getTemporaryForm();
        mainLayout.addComponent(bodyForm);
        mainLayout.setComponentAlignment(bodyForm, Alignment.MIDDLE_CENTER);
        
    }

    
}
