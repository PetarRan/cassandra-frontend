package com.example.application.views.browse;

import com.example.application.data.model.MyListings;
import com.example.application.feign_client.ListingsFeignClient;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.template.Id;

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
    private Span subtitle;

    @Id
    private Paragraph text;

    @Id
    private Button badge;

    @Id("badge2")
    private Button badge2;

    ListingsFeignClient listingsFeignClient;

    public HistoryViewCard(String text, String url, String description, String price, String country, String city,
                           String continent, ListingsFeignClient listingsFeignClient, UUID uuid, String username) {

        this.listingsFeignClient = listingsFeignClient;

        this.image.setSrc(url);
        this.image.setAlt(text);
        this.header.setText(price);
        this.subtitle.setText(country + " " + city + " - " + continent);
        this.text.setText(description);
        this.badge.setIcon(VaadinIcon.TRASH.create());
        this.badge.setText(" Remove Listing");
        this.badge.addClickListener(buttonClickEvent -> {
            MyListings myListings = new MyListings();
            myListings.setCity(city);
            myListings.setContinent(continent);
            myListings.setCountry(country);
            myListings.setDescription(description);
            myListings.setId(uuid);
            myListings.setImageUrl(url);
            myListings.setPrice(BigDecimal.valueOf(Double.parseDouble(price.substring(0, price.length()-1))));
            myListings.setUserId(username);

            listingsFeignClient.deleteListing(myListings);
        });
        this.badge.addThemeVariants(ButtonVariant.LUMO_ERROR);
        this.badge2.setIcon(VaadinIcon.EDIT.create());
        this.badge2.setText("Edit Listing");
        this.badge2.addClickListener(buttonClickEvent -> {

        });
    }
}
