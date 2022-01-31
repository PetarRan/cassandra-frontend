package com.example.application.views.browse;

import com.example.application.data.model.MyListings;
import com.example.application.data.model.Product;
import com.example.application.data.model.User;
import com.example.application.feign_client.ListingsFeignClient;
import com.example.application.feign_client.ProductFeignClient;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

import java.math.BigDecimal;
import java.util.UUID;

@JsModule("./views/history/history-view-card.ts")
@Tag("history-view-card")
public class HistoryViewCard extends LitTemplate {

    @Id
    private Image image;

    @Id
    private Span header;

    @Id
    private Span continent1;

    @Id
    private Span country1;

    @Id
    private Span city1;

    @Id
    private Paragraph text;

    @Id
    private Paragraph code;

    @Id
    private Button badge;

    @Id("badge2")
    private Button badge2;

    Dialog popUpDialog;

    ListingsFeignClient listingsFeignClient;
    ProductFeignClient productFeignClient;

    public HistoryViewCard(String text, String url, String description, String price, String country, String city,
                           String continent, ListingsFeignClient listingsFeignClient, UUID uuid, String username,
                           ProductFeignClient productFeignClient, String codeId) {

        this.listingsFeignClient = listingsFeignClient;
        this.productFeignClient = productFeignClient;

        this.code.setText("Item code: " + codeId);
        this.image.setSrc(url);
        this.image.setAlt(text);
        this.header.setText(price);
        this.continent1.setText(continent);
        this.country1.setText(country);
        this.city1.setText(city);
        this.text.setText(description);
        this.badge.setIcon(VaadinIcon.TRASH.create());
        this.badge.setText(" Remove Listing");
        this.badge.addClickListener(buttonClickEvent -> {
            popUpDelete(username, continent, country, city, uuid);
        });
        this.badge.addThemeVariants(ButtonVariant.LUMO_ERROR);
        this.badge2.setIcon(VaadinIcon.EDIT.create());
        this.badge2.setText("Edit Listing");
        this.badge2.addClickListener(buttonClickEvent -> {
            popUp(uuid, username);
        });
    }

    private void popUpDelete(String username, String continent, String country, String city, UUID uuid) {
        popUpDialog = new Dialog();
        popUpDialog.setWidth("450px");
        popUpDialog.setHeight("250x");
        FormLayout formLayout = new FormLayout();

        Button closeBtn = new Button("Go back", VaadinIcon.ARROW_LEFT.create(), buttonClickEvent -> {
            popUpDialog.close();
        });

        Button delete = new Button("Delete", VaadinIcon.TRASH.create());

        delete.addClickListener(loginClick -> {
            MyListings myListings = this.listingsFeignClient.findByCode(username, continent, country, city, uuid.toString());
            if(myListings!=null){
                Product product = new Product();
                product.setId(myListings.getId());
                product.setCity(myListings.getCity());
                product.setCountry(myListings.getCountry());
                product.setPrice(myListings.getPrice());
                product.setImageUrl(myListings.getImageUrl());
                product.setContinent(myListings.getContinent());
                product.setDescription(myListings.getDescription());

                productFeignClient.deleteProduct(product, username);

                popUpDialog.close();
                UI.getCurrent().getPage().reload();
                Notification notification = new Notification();
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                notification.setText("Listing Deleted");
                notification.setDuration(5000);
                notification.open();
                notification.setPosition(Notification.Position.TOP_CENTER);
            }
            else{
                Notification notification = new Notification();
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                notification.setText("There was an error with deleting. Try again soon.");
                notification.setDuration(5000);
                notification.open();
                notification.setPosition(Notification.Position.TOP_CENTER);
            }

        });

        formLayout.add(new H3("Are you sure you want to delete this listing?"), delete);
        popUpDialog.add(formLayout, closeBtn);
        popUpDialog.open();
    }

    private void popUp(UUID uuid, String username) {
        popUpDialog = new Dialog();
        popUpDialog.setWidth("250px");
        popUpDialog.setHeight("250x");
        FormLayout formLayout = new FormLayout();

        MyListings myListings = listingsFeignClient.findByCode(username, continent1.getText(), country1.getText(),
                city1.getText(), uuid.toString());
        Product product = new Product();
        product.setId(myListings.getId());
        product.setCity(myListings.getCity());
        product.setCountry(myListings.getCountry());
        product.setPrice(myListings.getPrice());
        product.setImageUrl(myListings.getImageUrl());
        product.setContinent(myListings.getContinent());
        product.setDescription(myListings.getDescription());


        Button closeBtn = new Button("Close", VaadinIcon.CLOSE.create(), buttonClickEvent -> {
           popUpDialog.close();
        });
        closeBtn.addThemeVariants(ButtonVariant.LUMO_ERROR);

        NumberField priceField = new NumberField();
        priceField.setRequiredIndicatorVisible(true);
        priceField.setLabel("Price: ");
        priceField.setValue(product.getPrice().doubleValue());

        TextArea descField = new TextArea();
        descField.setRequired(true);
        descField.setLabel("Description ");
        descField.setValue(product.getDescription());

        Button change = new Button("Make Changes", VaadinIcon.ARROW_RIGHT.create());

        change.addClickListener(loginClick -> {
            if(!priceField.isEmpty() && !descField.isEmpty()){
                product.setPrice(BigDecimal.valueOf(priceField.getValue()));
                product.setDescription(descField.getValue());

                productFeignClient.updateProduct(product, username);
                UI.getCurrent().getPage().reload();
            }
            else{
                Notification notification = new Notification();
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                notification.setText("Please fill in all the fields.");
                notification.setDuration(5000);
                notification.open();
                notification.setPosition(Notification.Position.TOP_CENTER);
            }
        });

        formLayout.add(descField, priceField, change);
        popUpDialog.add(formLayout, closeBtn);
        popUpDialog.open();

    }
}
