package ru.zavrichko.widgetobjects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class PizzaPage {
    SelenideElement pizza = $("[data-product_id='419']");

    public void addPizza() {
        pizza.click();
    }
}
