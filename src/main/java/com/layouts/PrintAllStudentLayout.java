package com.layouts;


import com.entities.Student;
import com.entities.studentpackage.StudentBoImpl;
import com.entities.universitypackage.UniversityBoImpl;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
@Scope("prototype")
public class PrintAllStudentLayout extends FormLayout {

    private static final long serialVersionUID = 1L;

    @Autowired
    StudentBoImpl studentBoImpl;

    @Autowired
    UniversityBoImpl universityBoImpl;

    BeanItemContainer<Student> containerStudent;
    Table studentPrintTable;

    public PrintAllStudentLayout() {
    }

    @PostConstruct
    private void buildLayout() {
        setSizeUndefined();
        setMargin(true);

        containerStudent = new BeanItemContainer<Student>(Student.class, studentBoImpl.listStudentOnDatabase());
        studentPrintTable = new Table();
        studentPrintTable.setCaption("Tüm Öğrenciler");
        studentPrintTable.setContainerDataSource(containerStudent);
        studentPrintTable.setColumnHeader("department", "Bölüm");
        studentPrintTable.setColumnHeader("firstName", "İsim");
        studentPrintTable.setColumnHeader("lastName", "Soyad");
        studentPrintTable.setColumnHeader("mail", "E-mail");
        studentPrintTable.setColumnHeader("sex", "Cinsiyet");
        studentPrintTable.setColumnHeader("studentId", "Öğrenci No");
        studentPrintTable.setColumnHeader("university", "Üniversite");
        addComponent(studentPrintTable);
    }

}
