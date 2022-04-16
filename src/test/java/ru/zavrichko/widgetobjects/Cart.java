package ru.zavrichko.widgetobjects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class Cart {
    SelenideElement cartContents = $(".cart-contents");

    public void cartContentsClick(String value) {
        cartContents.shouldHave(text(value)).click();
    }
}
