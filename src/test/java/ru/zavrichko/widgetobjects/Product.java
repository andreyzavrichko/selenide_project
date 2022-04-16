package ru.zavrichko.widgetobjects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Product {
    SelenideElement addToCartButton = $("[name='add-to-cart']");

    public void addToCart() {
        addToCartButton.click();
    }
}
