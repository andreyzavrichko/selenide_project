package ru.zavrichko.widgetobjects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class Promo {
    SelenideElement promoText = $(".content-page");

    public void checkPromo(String name) {
        promoText.shouldHave(text(name));
    }
}
