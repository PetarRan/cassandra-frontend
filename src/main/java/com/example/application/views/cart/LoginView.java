package com.example.application.views.cart;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

@PageTitle("Login")
@Route(value = "login", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class LoginView extends Div {

    Dialog popUpDialog;

    public LoginView(){
        popUp();
    }

    private void popUp() {
        popUpDialog = new Dialog();
        popUpDialog.setWidth("250px");
        popUpDialog.setHeight("250x");
        FormLayout formLayout = new FormLayout();
        TextField username = new TextField();
        username.setRequired(true);
        username.setLabel("Choose a Username: ");
        formLayout.add(username);
        Button login = new Button("Login", VaadinIcon.ARROW_RIGHT.create());

        login.addClickListener(loginClick -> {
           if(!username.isEmpty()){
               UI.getCurrent().navigate("Browse");
               popUpDialog.close();
           }
        });

        formLayout.add(username, login);
        popUpDialog.setCloseOnEsc(false);
        popUpDialog.setCloseOnOutsideClick(false);
        popUpDialog.add(formLayout);
        popUpDialog.open();

    }
}
