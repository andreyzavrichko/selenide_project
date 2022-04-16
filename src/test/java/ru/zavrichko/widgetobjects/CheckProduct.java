package ru.zavrichko.widgetobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CheckProduct {

    SelenideElement productTitleOne = $(".product-name a");
    SelenideElement productTitleTwo = $(".product-name a", 1);
    SelenideElement productPriceOne = $(".woocommerce-Price-amount");
    SelenideElement productPriceTwo = $(".woocommerce-Price-amount", 2);
    SelenideElement productPriceThree = $(".woocommerce-Price-amount", 1);
    SelenideElement productPriceFour = $(".woocommerce-Price-amount", 3);
    SelenideElement productPriceFive = $(".woocommerce-Price-amount", 4);
    SelenideElement productQuantityOne = $(".qty");
    SelenideElement productQuantityTwo = $(".qty", 1);
    SelenideElement productTotal = $(".shop_table", 1);
    SelenideElement updateCartButton = $("[name=update_cart]");
    SelenideElement errorMessage = $(".woocommerce-error");
    SelenideElement textMessage = $(".woocommerce-message");


    public void productDetail(String titleOne, String titleTwo, String priceOne, String priceTwo, String priceThree, String priceFour, String quantityOne, String quantityTwo, String totalProduct) {
        productTitleOne.shouldHave(Condition.text(titleOne));
        productTitleTwo.shouldHave(Condition.text(titleTwo));
        productPriceOne.shouldHave(Condition.text(priceOne));
        productPriceTwo.shouldHave(Condition.text(priceTwo));
        productPriceThree.shouldHave(Condition.text(priceThree));
        productPriceFour.shouldHave(Condition.text(priceFour));
        productQuantityOne.shouldHave(Condition.value(quantityOne));
        productQuantityTwo.shouldHave(Condition.value(quantityTwo));
        productTotal.shouldHave(Condition.text(totalProduct));
    }

    public void addQuantity(String value) {
        productQuantityOne.setValue(value);
        productQuantityTwo.setValue(value);
    }

    public void addQuantityClick() {
        updateCartButton.click();
    }

    public void checkPrice(String price, String totalPrice) {
        productPriceFour.shouldHave(Condition.text(price));
        productPriceFive.shouldHave(Condition.text(totalPrice));
    }

    public void checkError(String value) {
        errorMessage.shouldHave(Condition.text(value));
    }

    public void checkMessage(String value) {
        textMessage.shouldHave(Condition.text(value));
    }

    public void checkPriceFour(String value) {
        productPriceFour.shouldHave(Condition.text(value));
    }
}
