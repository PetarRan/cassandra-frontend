package com.example.application.views.browse;

import com.example.application.data.model.Cart;
import com.example.application.feign_client.CartFeignClient;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.template.Id;

@JsModule("./views/browse/browse-view-card.ts")
@Tag("browse-view-card")
public class BrowseViewCard extends LitTemplate {

    @Id
    private Image image;

    @Id
    private Span header;

    @Id
    private Span continent;

    @Id
    private Span country;

    @Id
    private Span city;

    @Id
    private Paragraph text;

    @Id
    private Button badge;

    public BrowseViewCard(String text, String url, String description, String price, String country, String city,
                          String continent, CartFeignClient cartFeignClient, String username) {
        this.image.setSrc(url);
        this.image.setAlt(text);
        this.header.setText(price);

        this.continent.setText(continent);
        this.country.setText(country);
        this.city.setText(city);

        this.text.setText(description);
        this.badge.setIcon(VaadinIcon.CART.create());
        this.badge.setText(" Add to Cart");
        this.badge.addClickListener(click -> {
            Cart cart = new Cart();
            cart.setDescription(text);
            cart.setLocation(continent + " " + country + " " + city);
            cart.setUserId(username);

            Notification notification = new Notification();
            notification.setText(cartFeignClient.findAll().toString());
        });
    }
}
