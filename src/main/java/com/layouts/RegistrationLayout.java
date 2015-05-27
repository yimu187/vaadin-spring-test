package com.layouts;

import com.entities.Student;
import com.entities.University;
import com.entities.studentpackage.StudentBoImpl;
import com.entities.universitypackage.UniversityBoImpl;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;


/**
 * Created by TOSHIBA on 6.5.2015.
 */

@Component
@Scope("prototype")
public class RegistrationLayout extends FormLayout {

    private static final long serialVersionUID = 1L;

    @Autowired
    StudentBoImpl studentBoImpl;

    @Autowired
    UniversityBoImpl universityBoImpl;

    Student formStudent = new Student();
    BeanFieldGroup<Student> fieldGroupStudent = new BeanFieldGroup<Student>(Student.class);
    BeanItemContainer<University> containerUniversity;

    @PropertyId("studentId")
    TextField txtStudentId;

    @PropertyId("firstName")
    TextField txtFirstName;

    @PropertyId("lastName")
    TextField txtLastName;

    @PropertyId("university")
    ComboBox comboUniversity;

    @PropertyId("department")
    TextField txtDepartment;

    @PropertyId("mail")
    TextField txtMail;

    @PropertyId("sex")
    OptionGroup opnSex;

    Button btnSave;
    Button btnPrint;
    Button btnPrintList;
    Button btnBinding;
    Button btnSpring;

    Label lblPrintScreen;
    Label lblPrintScreenList;

    public RegistrationLayout() {
    }


    @PostConstruct
    private void buildLayout() {
        setSizeUndefined();
        setMargin(true);

        txtStudentId = new TextField();
        txtStudentId.setCaption("Öğrenci No");
        addComponent(txtStudentId);

        txtFirstName = new TextField();
        txtFirstName.setCaption("İsim");
        addComponent(txtFirstName);

        txtLastName = new TextField();
        txtLastName.setCaption("Soyisim");
        addComponent(txtLastName);

        comboUniversity = new ComboBox();
        comboUniversity.setCaption("Üniversite");
        //HibernateMethods h = new HibernateMethods();
        //containerUniversity= new BeanItemContainer<University>(University.class, h.listOfUniversity());
        containerUniversity = new BeanItemContainer<University>(University.class, universityBoImpl.listOfUniversity());
        comboUniversity.setContainerDataSource(containerUniversity);
        addComponent(comboUniversity);

        txtDepartment = new TextField();
        txtDepartment.setCaption("Bölüm");
        addComponent(txtDepartment);

        txtMail = new TextField();
        txtMail.setCaption("E-Mail");
        addComponent(txtMail);
        txtMail.addValidator(new EmailValidator("Lütfen example@example.com şeklinde bir mail adresi giriniz!"));

        opnSex = new OptionGroup();
        opnSex.addItems("Erkek", "Kadın");
        opnSex.setCaption("Cinsiyet");
        addComponent(opnSex);

        fieldGroupStudent.setItemDataSource(formStudent);
        fieldGroupStudent.bindMemberFields(this);

        btnSave = new Button();
        btnSave.setCaption("Kayıt");
        btnSave.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {

                boolean success = txtMail.isValid();

                if (success) {
                    formStudent.setStudentId(Integer.parseInt(txtStudentId.getValue()));
                    formStudent.setFirstName((String) txtFirstName.getValue());
                    formStudent.setLastName((String) txtLastName.getValue());
                    formStudent.setUniversity((University) comboUniversity.getValue());
                    formStudent.setDepartment((String) txtDepartment.getValue());
                    formStudent.setMail((String) txtMail.getValue());
                    formStudent.setSex((String) opnSex.getValue());

                    studentBoImpl.save(formStudent);

                    Notification.show("Kayıt başarıyla eklendi.");
                }
            }
        });


        btnPrint = new Button();
        btnPrint.setCaption("Yazdır");
        btnPrint.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (txtFirstName.isEmpty() || txtLastName.isEmpty())
                    Notification.show("Lütfen boş alan bırakmayınız.");
                else {
                    lblPrintScreen.setValue(formStudent.toString());
                    Notification.show("Kayıt yazdırıldı.");
                }
            }
        });


        btnPrintList = new Button();
        btnPrintList.setCaption("Listele");
        btnPrintList.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                String listOfStudent = "";

                List<Student> students = studentBoImpl.listStudentOnDatabase();

                for (Student temporaryStudent : students) {
                    listOfStudent += temporaryStudent.toString();
                }

                lblPrintScreenList.setValue(listOfStudent);
                Notification.show("Kayıtlar Listelendi.");
            }
        });

        btnBinding = new Button();
        btnBinding.setCaption("Bingding");
        btnBinding.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                try {
                    fieldGroupStudent.commit();

                    studentBoImpl.save(formStudent);

                    System.out.println(formStudent);

                    Notification.show("Kayıt başarıyla eklendi.");

                } catch (CommitException e) {
                    Notification.show("Validted Faild : ", Notification.Type.ERROR_MESSAGE);
                }

            }
        });

        btnSpring = new Button();
        btnSpring.setCaption("Spring");
        btnSpring.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                try {
                    fieldGroupStudent.commit();

                    studentBoImpl.save(formStudent);

                    Notification.show("Kayıt başarıyla eklendi.");

                } catch (CommitException e) {
                    Notification.show("Validted Faild : ", Notification.Type.ERROR_MESSAGE);
                }

            }
        });

        HorizontalLayout action = new HorizontalLayout(btnSave, btnPrint, btnPrintList, btnBinding, btnSpring);
        addComponent(action);

        lblPrintScreen = new Label();
        lblPrintScreen.setContentMode(ContentMode.HTML);
        addComponent(lblPrintScreen);

        lblPrintScreenList = new Label();
        lblPrintScreenList.setContentMode(ContentMode.HTML);
        addComponent(lblPrintScreenList);


    }
}