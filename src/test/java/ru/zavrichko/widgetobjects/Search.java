package ru.zavrichko.widgetobjects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Search {
    SelenideElement searchInput = $("[name='s']");

    public void searchFood(String value) {
        searchInput.setValue(value).pressEnter();
    }
}
