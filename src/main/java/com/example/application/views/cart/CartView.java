package com.example.application.views.cart;

import com.example.application.data.model.User;
import com.example.application.feign_client.CartFeignClient;
import com.example.application.feign_client.ProductFeignClient;
import com.example.application.feign_client.UserFeignClient;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

@PageTitle("Cart")
@Route(value = "cart", layout = MainLayout.class)
public class CartView extends Div {

    private static final Set<String> states = new LinkedHashSet<>();
    private static final Set<String> countries = new LinkedHashSet<>();
    private static final Set<String> continents= new LinkedHashSet<>();

    static {
        states.addAll(Arrays.asList("Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut",
                "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas",
                "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi",
                "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York",
                "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island",
                "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington",
                "West Virginia", "Wisconsin", "Wyoming"));

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
                "Puerto Rico", "Qatar", "Reunion", "Romania", "Russian Federation", "Rwanda", "Saint Kitts and Nevis",
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

    Dialog popUpDialog;
    UserFeignClient userFeignClient;
    TextField name = new TextField("Name");
    UnorderedList ul = new UnorderedList();

    CartFeignClient cartFeignClient;
    ProductFeignClient productFeignClient;

    public CartView(UserFeignClient userFeignClient, CartFeignClient cartFeignClient,
                    ProductFeignClient productFeignClient) {

        this.userFeignClient = userFeignClient;
        this.cartFeignClient = cartFeignClient;
        this.productFeignClient = productFeignClient;

        addClassNames("cart-view", "flex", "flex-col", "h-full");

        Main content = new Main();
        content.addClassNames("grid", "gap-xl", "items-start", "justify-center", "max-w-screen-md", "mx-auto", "pb-l",
                "px-l");

        content.add(createCheckoutForm());
        content.add(createAside());
        add(content);
    }

    private Component createCheckoutForm() {
        popUp();

        Section checkoutForm = new Section();
        checkoutForm.addClassNames("flex", "flex-col", "flex-grow");

        H2 header = new H2("Checkout");
        header.addClassNames("mb-0", "mt-xl", "text-3xl");
        Paragraph note = new Paragraph("All fields are required unless otherwise noted");
        note.addClassNames("mb-xl", "mt-0", "text-secondary");
        checkoutForm.add(header, note);

        checkoutForm.add(createPersonalDetailsSection());
        checkoutForm.add(new Hr());
        checkoutForm.add(createFooter());

        return checkoutForm;
    }

    private Section createPersonalDetailsSection() {
        Section personalDetails = new Section();
        personalDetails.addClassNames("flex", "flex-col", "mb-xl", "mt-m");

        Paragraph stepOne = new Paragraph("Checkout");
        stepOne.addClassNames("m-0", "text-s", "text-secondary");

        H3 header = new H3("Personal details");
        header.addClassNames("mb-m", "mt-s", "text-2xl");

        name.setRequiredIndicatorVisible(true);
        name.setPattern("[\\p{L} \\-]+");
        name.addClassNames("mb-s");

        personalDetails.add(stepOne, header, name);
        return personalDetails;
    }


    private Footer createFooter() {
        Footer footer = new Footer();
        footer.addClassNames("flex", "items-center", "justify-between", "my-m");

        Button cancel = new Button("Cancel order");
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        cancel.addClickListener(click -> {
           UI.getCurrent().navigate("Browse");
        });

        Button pay = new Button("Pay securely", new Icon(VaadinIcon.LOCK));
        pay.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
        pay.addClickListener(click ->{
           //TODO clear Products(ccc + uuid), clear Cart
        });

        footer.add(cancel, pay);
        return footer;
    }

    private Aside createAside() {
        Aside aside = new Aside();
        aside.addClassNames("bg-contrast-5", "box-border", "p-l", "rounded-l", "sticky");
        Header headerSection = new Header();
        headerSection.addClassNames("flex", "items-center", "justify-between", "mb-m");
        H3 header = new H3("Cart");
        header.addClassNames("m-0");

        Button buttonRemove = new Button("Clear Cart", VaadinIcon.TRASH.create());
        buttonRemove.addThemeVariants(ButtonVariant.LUMO_ERROR);
        buttonRemove.addClickListener(click ->{
            productFeignClient.deleteFromCart(name.getValue());
            UI.getCurrent().getPage().reload();
        });

        headerSection.add(header, new Icon(VaadinIcon.CART), buttonRemove);

        ul.addClassNames("list-none", "m-0", "p-0", "flex", "flex-col", "gap-m");

        aside.add(headerSection, ul);
        return aside;
    }

    private ListItem createListItem(String primary, String secondary, String price) {
        ListItem item = new ListItem();
        item.addClassNames("flex", "justify-between");

        Div subSection = new Div();
        subSection.addClassNames("flex", "flex-col");

        subSection.add(new Span(primary));
        Span secondarySpan = new Span(secondary);
        secondarySpan.addClassNames("text-s text-secondary");
        subSection.add(secondarySpan);

        Span priceSpan = new Span(price);

        item.add(subSection, priceSpan);
        return item;
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
                User user = userFeignClient.findByUsername(username.getValue());
                name.setValue(username.getValue());
                popUpDialog.close();
                productFeignClient.getMyCart(username.getValue()).forEach(cartItem -> {
                    ul.add(createListItem(cartItem.getDescription(), cartItem.getLocation(),
                            cartItem.getPrice().toString() + "€"));
                });
            }
        });

        formLayout.add(username, login);
        popUpDialog.setCloseOnEsc(false);
        popUpDialog.setCloseOnOutsideClick(false);
        popUpDialog.add(formLayout);
        popUpDialog.open();

    }
}
