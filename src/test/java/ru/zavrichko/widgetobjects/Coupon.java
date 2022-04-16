package ru.zavrichko.widgetobjects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Coupon {
    SelenideElement couponCode = $("#coupon_code");

    public void addCoupon(String value) {
        couponCode.setValue(value).pressEnter();
    }
}
