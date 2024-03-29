package com.example.application.views.browse;

import com.example.application.data.model.MyListings;
import com.example.application.data.model.User;
import com.example.application.feign_client.ListingsFeignClient;
import com.example.application.feign_client.ProductFeignClient;
import com.example.application.feign_client.UserFeignClient;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinServletService;

import java.util.*;

@PageTitle("My Listings")
@Route(value = "history", layout = MainLayout.class)
@Tag("history-view")
@JsModule("./views/history/history-view.ts")
public class HistoryView extends LitTemplate implements HasComponents, HasStyle {

    private static final Set<String> countries = new LinkedHashSet<>();
    private static final Set<String> continents= new LinkedHashSet<>();

    static {
        countries.addAll(Arrays.asList("Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola",
                "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia",
                "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize",
                "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana", "Bouvet Island",
                "Brazil", "British Indian Ocean Territory", "British Virgin Islands", "Brunei Darussalam", "Bulgaria",
                "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands",
                "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands",
                "Colombia", "Comoros", "Congo", "Cook Islands", "Costa Rica", "Croatia", "Cuba", "Cyprus",
                "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador",
                "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands",
                "Faroe Islands", "Federated States of Micronesia", "Fiji", "Finland", "France", "French Guiana",
                "French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana",
                "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea",
                "Guinea-Bissau", "Guyana", "Haiti", "Heard Island and McDonald Islands", "Honduras", "Hong Kong",
                "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy", "Ivory Coast",
                "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kuwait", "Kyrgyzstan", "Laos",
                "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau",
                "Macedonia", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands",
                "Martinique", "Montenegro", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Moldova", "Monaco", "Mongolia",
                "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands",
                "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue",
                "Norfolk Island", "North Korea", "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau",
                "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn", "Poland", "Portugal",
                "Puerto Rico", "Qatar", "Reunion", "Romania", "Russia", "Rwanda", "Saint Kitts and Nevis",
                "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe",
                "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia",
                "Solomon Islands", "Somalia", "South Africa", "South Georgia and the South Sandwich Islands",
                "South Korea", "Spain", "Sri Lanka", "St. Helena", "St. Pierre and Miquelon", "Sudan", "Suriname",
                "Svalbard and Jan Mayen Islands", "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic",
                "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago",
                "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine",
                "United Arab Emirates", "United Kingdom", "United States", "United States Minor Outlying Islands",
                "United States Virgin Islands", "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City State", "Venezuela",
                "Vietnam", "Wallis and Futuna Islands", "Western Sahara", "Yemen", "Zaire (D.R.C.)", "Zambia",
                "Zimbabwe"));
        continents.addAll(Arrays.asList("Europe", "Australia", "Asia", "Africa", "South America", "North America"));
    }

    @Id("searchPrimary1")
    private Select<String> searchPrimary;
    @Id("searchSecondary1")
    private Select<String> searchSecondary;
    @Id("searchTernary1")
    private TextField searchTernary;
    @Id("searchButton1")
    private Button search;
    @Id("itemList1")
    private OrderedList content;
    @Id("userText")
    private H2 userText;

    private ListingsFeignClient listingsFeignClient;
    private UserFeignClient userFeignClient;
    private ProductFeignClient productFeignClient;

    public HistoryView(ListingsFeignClient listingsFeignClient, UserFeignClient userFeignClient, ProductFeignClient
                       productFeignClient) {
        this.listingsFeignClient = listingsFeignClient;
        this.userFeignClient = userFeignClient;
        this.productFeignClient = productFeignClient;
        userText.setText(VaadinServletService.getCurrentServletRequest().getSession().getAttribute("username")
                .toString());
        content.add();

        searchPrimary.setItems(continents);
        searchSecondary.setItems(countries);


        addClassNames("browse-view", "flex", "flex-col", "h-full");
        listingsFeignClient.findMyListings(VaadinServletService.getCurrentServletRequest().getSession().getAttribute("username")
                .toString()).forEach(listing -> {
                    content.add(new HistoryViewCard(listing.getDescription(),
                            listing.getImageUrl(), listing.getDescription(), listing.getPrice().toString() + "€", listing.getCountry(),
                            listing.getCity(), listing.getContinent(), listingsFeignClient, listing.getId()
                            , VaadinServletService.getCurrentServletRequest().getSession().getAttribute("username")
                            .toString(), productFeignClient,
                            listing.getId().toString()));
        });
        setupSearch();
    }

    private void setupSearch() {
        searchSecondary.setEnabled(false);
        searchTernary.setEnabled(false);

        searchPrimary.addValueChangeListener(change -> {
            searchSecondary.setEnabled(!change.getValue().isEmpty());
        });

        searchSecondary.addValueChangeListener(change -> {
            searchTernary.setEnabled(!change.getValue().isEmpty());
        });

        searchTernary.setPrefixComponent(VaadinIcon.SEARCH.create());
        searchTernary.setPlaceholder("...");
        searchTernary.setClearButtonVisible(true);

        search.setIcon(VaadinIcon.ARROW_RIGHT.create());
        search.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_SUCCESS);
        search.addClickListener(click -> {
            if(!searchPrimary.isEmpty()){
                if(searchSecondary != null){
                    if(!searchTernary.isEmpty()){
                        content.removeAll();
                        listingsFeignClient.findByCity(userText.getText(), searchPrimary.getValue(),
                                searchSecondary.getValue(), searchTernary.getValue()).forEach(product -> {
                            content.add(new HistoryViewCard(product.getDescription(),
                                    product.getImageUrl(), product.getDescription(), product.getPrice().toString() + "€", product.getCountry(),
                                    product.getCity(), product.getContinent(), listingsFeignClient, product.getId()
                                    , VaadinServletService.getCurrentServletRequest().getSession().getAttribute("username")
                                    .toString(), productFeignClient,
                                    product.getId().toString()));
                        });
                        //findByCity
                    }
                    else{
                        content.removeAll();
                        listingsFeignClient.findByCountry(userText.getText(), searchPrimary.getValue(), searchSecondary.getValue()).forEach(product -> {
                            content.add(new HistoryViewCard(product.getDescription(),
                                    product.getImageUrl(), product.getDescription(), product.getPrice().toString() + "€", product.getCountry(),
                                    product.getCity(), product.getContinent(), listingsFeignClient, product.getId()
                                    , VaadinServletService.getCurrentServletRequest().getSession().getAttribute("username")
                                    .toString(), productFeignClient,
                                    product.getId().toString()));
                        });
                        //findByCountry
                    }
                }
                else{
                    content.removeAll();
                    listingsFeignClient.findByContinent(userText.getText(), searchPrimary.getValue()).forEach(product -> {
                        content.add(new HistoryViewCard(product.getDescription(),
                                product.getImageUrl(), product.getDescription(), product.getPrice().toString() + "€", product.getCountry(),
                                product.getCity(), product.getContinent(), listingsFeignClient, product.getId(),
                                VaadinServletService.getCurrentServletRequest().getSession().getAttribute("username")
                                        .toString(), productFeignClient,
                                product.getId().toString()));
                    });
                    //findByContinent
                }
            }
        });
    }


    private void notificationPop(String s) {
        Notification notification = new Notification(s);
        notification.setDuration(5000);
        notification.setPosition(Notification.Position.TOP_CENTER);
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        notification.open();
    }
}
