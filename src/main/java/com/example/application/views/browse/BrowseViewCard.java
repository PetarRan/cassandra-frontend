package com.example.application.views.browse;

import com.example.application.data.model.Cart;
import com.example.application.data.model.Product;
import com.example.application.feign_client.CartFeignClient;
import com.example.application.feign_client.ProductFeignClient;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.template.Id;

import java.util.UUID;

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
    private Paragraph codePrimary;

    @Id("badge3")
    private Button badge3;

    ProductFeignClient productFeignClient;
    CartFeignClient cartFeignClient;

    public BrowseViewCard(String text, String url, String description, String price, String country, String city,
                          String continent, CartFeignClient cartFeignClient, String username, String codeId,
                          ProductFeignClient productFeignClient) {

        this.productFeignClient = productFeignClient;
        this.cartFeignClient = cartFeignClient;

        this.codePrimary.setText("Unique Code: " + codeId);
        this.image.setSrc(url);
        this.image.setAlt(text);
        this.header.setText(price);

        this.continent.setText(continent);
        this.country.setText(country);
        this.city.setText(city);

        this.text.setText(description);
        this.badge3.setIcon(VaadinIcon.CART.create());
        this.badge3.setText(" Add to Cart");
        this.badge3.addClickListener(click -> {
            Product product = productFeignClient.findByCode(continent, country, city, codeId);
            Cart cart = new Cart();
            cart.setId(product.getId());
            cart.setDescription(product.getDescription());
            cart.setLocation(product.getContinent() + " " + product.getCountry() + " " + product.getCity());
            cart.setUserId(username);
            cart.setPrice(product.getPrice());
            productFeignClient.addToCart(cart);

            Notification notification = new Notification();
            notification.setText("Added " + description + " to Cart.");
            notification.setPosition(Notification.Position.TOP_CENTER);
            notification.setDuration(5000);
            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            notification.open();

        });
    }
}
