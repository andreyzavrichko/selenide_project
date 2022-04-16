package ru.zavrichko.widgetobjects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Header {
    public SelenideElement welcomeMsg = $("header .welcome-user");
}
