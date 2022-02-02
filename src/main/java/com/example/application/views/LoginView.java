package com.example.application.views;

import com.example.application.feign_client.CartFeignClient;
import com.example.application.feign_client.ProductFeignClient;
import com.example.application.feign_client.UserFeignClient;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.VaadinServletService;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

@PageTitle("Login")
@Route(value = "login", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)

public class LoginView extends Div {


    public LoginView() {

        addClassNames("cart-view", "flex", "flex-col", "h-full");
    }


}
