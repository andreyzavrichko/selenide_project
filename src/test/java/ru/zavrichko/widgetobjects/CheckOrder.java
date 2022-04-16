package ru.zavrichko.widgetobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CheckOrder {
    SelenideElement orderPrice = $(".woocommerce-Price-amount", 4);
    SelenideElement orderDetail = $(".woocommerce-customer-details");

    public void productDetail(String price, String name, String surname, String street, String city, String state, String post, String phone, String email) {
        orderPrice.shouldHave(Condition.text(price));
        orderDetail.shouldHave(Condition.text(name));
        orderDetail.shouldHave(Condition.text(surname));
        orderDetail.shouldHave(Condition.text(street));
        orderDetail.shouldHave(Condition.text(city));
        orderDetail.shouldHave(Condition.text(state));
        orderDetail.shouldHave(Condition.text(post));
        orderDetail.shouldHave(Condition.text(phone));
        orderDetail.shouldHave(Condition.text(email));
    }
}
