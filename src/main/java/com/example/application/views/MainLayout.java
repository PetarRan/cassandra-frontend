package com.example.application.views;


import com.example.application.data.model.User;
import com.example.application.feign_client.UserFeignClient;
import com.example.application.views.browse.BrowseView;
import com.example.application.views.cart.CartView;
import com.example.application.views.browse.HistoryView;
import com.example.application.views.cart.SellView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.Nav;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.server.VaadinServletService;
import com.vaadin.flow.theme.Theme;

/**
 * The main view is a top-level placeholder for other views.
 */
@PWA(name = "Souvenir Shop", shortName = "Souvenir Shop", enableInstallPrompt = false)
@Theme(themeFolder = "souvenirshop")
@PageTitle("Main")
public class MainLayout extends AppLayout {

    private final UserFeignClient userFeignClient;
    Dialog popUpDialog = new Dialog();

    /**
     * A simple navigation item component, based on ListItem element.
     */
    public static class MenuItemInfo extends ListItem {

        private final Class<? extends Component> view;

        public MenuItemInfo(String menuTitle, String iconClass, Class<? extends Component> view) {
            this.view = view;
            RouterLink link = new RouterLink();
            // Use Lumo classnames for various styling
            link.addClassNames("flex", "h-m", "items-center", "px-s", "relative", "text-secondary");
            link.setRoute(view);

            Span text = new Span(menuTitle);
            // Use Lumo classnames for various styling
            text.addClassNames("font-medium", "text-s", "whitespace-nowrap");

            link.add(new LineAwesomeIcon(iconClass), text);
            add(link);
        }

        public Class<?> getView() {
            return view;
        }

        /**
         * Simple wrapper to create icons using LineAwesome iconset. See
         * https://icons8.com/line-awesome
         */
        @NpmPackage(value = "line-awesome", version = "1.3.0")
        public static class LineAwesomeIcon extends Span {
            public LineAwesomeIcon(String lineawesomeClassnames) {
                // Use Lumo classnames for suitable font size and margin
                addClassNames("me-s", "text-l");
                if (!lineawesomeClassnames.isEmpty()) {
                    addClassNames(lineawesomeClassnames);
                }
            }
        }

    }

    public MainLayout(UserFeignClient userFeignClientl, UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;

        addToNavbar(createHeaderContent());
        usernamePopUp();
    }

    private void usernamePopUp() {
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
                if(userFeignClient.findByUsername(username.getValue()) != null){
                    notificationPop("Welcome back " + username.getValue());

                } else {
                    User user = new User();
                    user.setUserId(username.getValue());
                    userFeignClient.addUser(user);

                    notificationPop("User Created: " + username.getValue() + ". Welcome!");
                }
                VaadinServletService.getCurrentServletRequest().getSession()
                        .setAttribute("username", username.getValue());
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

    private void notificationPop(String s) {
        Notification notification = new Notification(s);
        notification.setDuration(5000);
        notification.setPosition(Notification.Position.TOP_CENTER);
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        notification.open();
    }

    private Component createHeaderContent() {
        Header header = new Header();
        header.addClassNames("bg-base", "border-b", "border-contrast-10", "box-border", "flex", "flex-col", "w-full");

        Div layout = new Div();
        layout.addClassNames("flex", "h-xl", "items-center", "px-l");

        H1 appName = new H1("Souvenir Shop");
        appName.addClassNames("my-0", "me-auto", "text-l");
        layout.add(appName);

        Nav nav = new Nav();
        nav.addClassNames("flex", "gap-s", "overflow-auto", "px-m");

        // Wrap the links in a list; improves accessibility
        UnorderedList list = new UnorderedList();
        list.addClassNames("flex", "list-none", "m-0", "p-0");
        nav.add(list);

        for (MenuItemInfo menuItem : createMenuItems()) {
            list.add(menuItem);

        }

        header.add(layout, nav);
        return header;
    }

    private MenuItemInfo[] createMenuItems() {
        return new MenuItemInfo[]{ //
                new MenuItemInfo("Browse", "la la-dollar-sign", BrowseView.class), //

                new MenuItemInfo("Cart", "la la-shopping-cart", CartView.class), //

                new MenuItemInfo("Sell Souvenirs", "la la-wallet", SellView.class), //

                new MenuItemInfo("My Listings", "la la-history", HistoryView.class), //

        };
    }

}
