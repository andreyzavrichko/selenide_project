package ru.zavrichko.widgetobjects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class DetailOrder {

    SelenideElement nameInput = $("#billing_first_name");
    SelenideElement lastnameInput = $("#billing_last_name");
    SelenideElement addressInput = $("#billing_address_1");
    SelenideElement cityInput = $("#billing_city");
    SelenideElement regionInput = $("#billing_state");
    SelenideElement postInput = $("#billing_postcode");
    SelenideElement phoneInput = $("#billing_phone");
    SelenideElement dataInput = $("#order_date");
    SelenideElement orderButton = $("#place_order");
    SelenideElement paymentMethodButton = $("#payment_method_cod");
    SelenideElement termsButton = $("#terms");

    public void makeOrder(String name, String lastName, String address, String city, String region, String post, String phone, String date) {
        nameInput.setValue(name);
        lastnameInput.setValue(lastName);
        addressInput.setValue(address);
        cityInput.setValue(city);
        regionInput.setValue(region);
        postInput.setValue(post);
        phoneInput.setValue(phone);
        dataInput.setValue(date);
        paymentMethodButton.click();
        termsButton.click();
        orderButton.click();
    }
}
