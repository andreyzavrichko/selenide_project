package ru.zavrichko.widgetobjects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class Menu {
    SelenideElement menu = $("#menu-primary-menu");
    SelenideElement mainMenu = $("#menu-item-389");
    SelenideElement pizzaMenu = $("#menu-item-390");
    SelenideElement registerButton = $(".custom-register-button");

    public void openMenu(String name) {
        menu.find(byText(name)).click();
    }

    public void openPizzaMenu() {
        mainMenu.hover();
        pizzaMenu.click();
    }

    public void openRegister() {
        registerButton.click();
    }
}
