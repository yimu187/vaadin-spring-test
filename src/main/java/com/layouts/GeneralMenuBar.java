package com.layouts;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("prototype")
public class GeneralMenuBar extends VerticalLayout {

    private MenuBar menubar = new MenuBar();

    @Autowired
    private RegistrationLayout registrationLayout;

    @Autowired
    private PrintAllStudentLayout printAllStudentLayout;

    private FormLayout temporaryForm;

    private VerticalLayout verticalLayout;

    public GeneralMenuBar() {

        final MenuBar.MenuItem processes = menubar.addItem("İşlemler", null);

        processes.addItem("Kayıt Et", mycommand);
        processes.addSeparator();
        processes.addItem("Tüm Öğrenciler", mycommand);

        addComponent(menubar);
    }

    public FormLayout getTemporaryForm() {

        if(temporaryForm == null) {
            temporaryForm = registrationLayout;
        }

        return temporaryForm;
    }

    public VerticalLayout getVerticalLayout() {
        return verticalLayout;
    }

    public void setVerticalLayout(VerticalLayout v) {
        this.verticalLayout = v;
    }

    private Command mycommand = new Command() {

        @Override
        public void menuSelected(MenuItem selectedItem) {

            if (selectedItem.getText().equals("Kayıt Et")) {

                temporaryForm = registrationLayout;

                verticalLayout.removeComponent(printAllStudentLayout);
                verticalLayout.removeComponent(registrationLayout);

                verticalLayout.addComponent(temporaryForm);
                verticalLayout.setComponentAlignment(temporaryForm, Alignment.MIDDLE_CENTER);

            } else if (selectedItem.getText().equals("Tüm Öğrenciler")) {

                temporaryForm = printAllStudentLayout;

                verticalLayout.removeComponent(printAllStudentLayout);
                verticalLayout.removeComponent(registrationLayout);

                verticalLayout.addComponent(temporaryForm);
                verticalLayout.setComponentAlignment(temporaryForm, Alignment.MIDDLE_CENTER);
            }
        }
    };

}
