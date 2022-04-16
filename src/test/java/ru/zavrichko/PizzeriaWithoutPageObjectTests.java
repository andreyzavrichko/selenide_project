package ru.zavrichko;

import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PizzeriaWithoutPageObjectTests extends TestBase {
    @Test
    void oneTest() {
        var nextDate = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("ddMMyyyy"));
        open(baseUrl);
        // Через меню выбирает пиццы.
        $("#menu-item-389").hover();
        $("#menu-item-390").click();
        // Петя любит ветчину, поэтому выбирает пиццу с ветчиной
        // и кладёт её в корзину.
        $("[data-product_id='419']").click();
        // Ещё Петя любит какао и ищет продукт «какао» через поиск на сайте.
        $("[name='s']").setValue("какао").pressEnter();
        // На странице продукта Петя добавляет какао в корзину.
        $("[name='add-to-cart']").click();
        // Теперь Петя готов заплатить, и он кликает для этого на иконку
        // с корзиной (предварительно проверив, что сумма заказа рядом с иконкой верна).
        $(".cart-contents").shouldHave(text("750"));
        $(".cart-contents").click();
        // Петя проверяет содержимое корзины (товары, суммы, количества).
        $(".product-name a").shouldHave(text("Ветчина и грибы"));
        $(".product-name a", 1).shouldHave(text("Какао с маршмеллоу"));
        $(".woocommerce-Price-amount").shouldHave(text("450,00₽"));
        $(".woocommerce-Price-amount", 2).shouldHave(text("300,00₽"));
        $(".woocommerce-Price-amount", 1).shouldHave(text("450,00₽"));
        $(".woocommerce-Price-amount", 3).shouldHave(text("300,00₽"));
        $(".qty").shouldHave(value("1"));
        $(".qty", 1).shouldHave(value("1"));
        $(".shop_table", 1).shouldHave(text("750,00₽"));
        // Петя вспоминает, что у него в гостях подруга Таня,
        // которая тоже любит пиццу и какао, и увеличивает прямо
        // на странице корзины количество пиццы и какао до двух штук.
        $(".qty").setValue("2");
        $(".qty", 1).setValue("2");
        $("[name=update_cart]").click();
        // Петя обновляет корзину и проверяет заново количество и стоимость заказа.
        $(".product-name a").shouldHave(text("Ветчина и грибы"));
        $(".product-name a", 1).shouldHave(text("Какао с маршмеллоу"));
        $(".woocommerce-Price-amount").shouldHave(text("450,00₽"));
        $(".woocommerce-Price-amount", 2).shouldHave(text("300,00₽"));
        $(".woocommerce-Price-amount", 1).shouldHave(text("900,00₽"));
        $(".woocommerce-Price-amount", 3).shouldHave(text("600,00₽"));
        $(".qty").shouldHave(value("2"));
        $(".qty", 1).shouldHave(value("2"));
        $(".shop_table", 1).shouldHave(text("1500,00₽"));
        // Всё сходится, и Петя готов перейти к оплате.
        // Петя очень любит нашу пиццу, поэтому решает зарегистрироваться и переходит для этого в меню «Мой аккаунт».
        $("#menu-primary-menu").$(Selectors.byText("Главная")).click();
        $("#menu-primary-menu").$(Selectors.byText("Мой аккаунт")).click();
        $(".custom-register-button").click();
        // Там он проходит регистрацию.
        var username = "sel" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMddHHmmss"));
        var email = username + "@ya.ru";
        $("#reg_username").setValue(username);
        $("#reg_email").setValue(email);
        $("#reg_password").setValue("slf5445SFdgfd");
        $("button[name=register]").click();
        /* Регистрация автоматически авторизирует Петю как пользователя, это он проверяет взглядом на верхнюю
        правую часть страницы, где его приветствуют по имени пользователя. */
        $("header .welcome-user").shouldHave(text("Привет " + username));
        // Петя переходит к оформлению заказа через соответствующее меню.
        $("#menu-primary-menu").$(Selectors.byText("Оформление заказа")).click();
        // Петя заполняет формуляр своими данными.
        $("#billing_first_name").setValue("Andrey");
        $("#billing_last_name").setValue("Zavrichko");
        $("#billing_address_1").setValue("Street Skillbox'ovskaya 56");
        $("#billing_city").setValue("Voronezh");
        $("#billing_state").setValue("Voronezhskaya");
        $("#billing_postcode").setValue("887700");
        $("#billing_phone").setValue("+7493648456454");
        // Петя выбирает дату оформления заказа на завтра, потому что сегодня не такой голодный.
        $("#order_date").setValue(nextDate);
        // Петя выбирает оплату по доставке и подтверждает заказ.
        $("#payment_method_cod").click();
        // Петя внимательно проверяет подтверждение заказа, общую сумму и свои личные данные.
        $("#terms").click();
        $("#place_order").click();
        $(".woocommerce-Price-amount", 4).shouldHave(text("1500,00₽"));
        $(".woocommerce-customer-details").shouldHave(text("Andrey"));
        $(".woocommerce-customer-details").shouldHave(text("Zavrichko"));
        $(".woocommerce-customer-details").shouldHave(text("Street Skillbox'ovskaya 56"));
        $(".woocommerce-customer-details").shouldHave(text("Voronezh"));
        $(".woocommerce-customer-details").shouldHave(text("Voronezhskaya"));
        $(".woocommerce-customer-details").shouldHave(text("887700"));
        $(".woocommerce-customer-details").shouldHave(text("+7493648456454"));
        $(".woocommerce-customer-details").shouldHave(text(email));
    }

    @Test
    void promoPositiveTest() {
        open(baseUrl);
        $("#menu-item-389").hover();
        $("#menu-item-390").click();
        $("[data-product_id='419']").click();
        $(".cart-contents").shouldHave(text("450"));
        $(".cart-contents").click();
        $("#coupon_code").setValue("GIVEMEHALYAVA").pressEnter();
        $(".woocommerce-Price-amount", 3).shouldHave(text("45,00₽"));
        $(".woocommerce-Price-amount", 4).shouldHave(text("405,00₽"));
    }

    @Test
    void promoNegativeTest() {
        open(baseUrl);
        $("#menu-item-389").hover();
        $("#menu-item-390").click();
        $("[data-product_id='419']").click();
        $(".cart-contents").shouldHave(text("450"));
        $(".cart-contents").click();
        $("#coupon_code").setValue("NONAMEPROMO").pressEnter();
        $(".woocommerce-error").shouldHave(text("Неверный купон."));
        $(".woocommerce-Price-amount", 3).shouldHave(text("450,00₽"));
    }

    @Test
    void promoDoubleTest() {
        var nextDate = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("ddMMyyyy"));
        open(baseUrl);
        // Зайти на страницу «Акции», проверить текстовую информацию о скидке GIVEMEHALYAVA.
        $("#menu-primary-menu").$(Selectors.byText("Акции")).click();
        $(".content-page").shouldHave(text("Промокод на первый заказ: GIVEMEHALYAVA"));
        // Создать нового пользователя.
        $("#menu-primary-menu").$(Selectors.byText("Мой аккаунт")).click();
        $(".custom-register-button").click();
        var username = "sel" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMddHHmmss"));
        var email = username + "@ya.ru";
        $("#reg_username").setValue(username);
        $("#reg_email").setValue(email);
        $("#reg_password").setValue("slf5445SFdgfd");
        $("button[name=register]").click();
        // Сделать первый заказ с промокодом GIVEMEHALYAVA на странице «Акции».
        $("#menu-item-389").hover();
        $("#menu-item-390").click();
        $("[data-product_id='419']").click();
        $(".cart-contents").shouldHave(text("450"));
        $(".cart-contents").click();
        $("#coupon_code").setValue("GIVEMEHALYAVA").pressEnter();
        $(".woocommerce-Price-amount", 3).shouldHave(text("45,00₽"));
        $(".woocommerce-Price-amount", 4).shouldHave(text("405,00₽"));
        $("#menu-primary-menu").$(Selectors.byText("Оформление заказа")).click();
        // Петя заполняет формуляр своими данными.
        $("#billing_first_name").setValue("Andrey");
        $("#billing_last_name").setValue("Zavrichko");
        $("#billing_address_1").setValue("Street Skillbox'ovskaya 56");
        $("#billing_city").setValue("Voronezh");
        $("#billing_state").setValue("Voronezhskaya");
        $("#billing_postcode").setValue("887700");
        $("#billing_phone").setValue("+7493648456454");
        // Петя выбирает дату оформления заказа на завтра, потому что сегодня не такой голодный.
        $("#order_date").setValue(nextDate);
        // Петя выбирает оплату по доставке и подтверждает заказ.
        $("#payment_method_cod").click();
        // Петя внимательно проверяет подтверждение заказа, общую сумму и свои личные данные.
        $("#terms").click();
        $("#place_order").click();
        $(".woocommerce-Price-amount", 4).shouldHave(text("405,00₽"));
        $(".woocommerce-customer-details").shouldHave(text("Andrey"));
        $(".woocommerce-customer-details").shouldHave(text("Zavrichko"));
        $(".woocommerce-customer-details").shouldHave(text("Street Skillbox'ovskaya 56"));
        $(".woocommerce-customer-details").shouldHave(text("Voronezh"));
        $(".woocommerce-customer-details").shouldHave(text("Voronezhskaya"));
        $(".woocommerce-customer-details").shouldHave(text("887700"));
        $(".woocommerce-customer-details").shouldHave(text("+7493648456454"));
        $(".woocommerce-customer-details").shouldHave(text(email));
        $("#menu-primary-menu").$(Selectors.byText("Главная")).click();
        // Сделать второй заказ с этим же промокодом.
        $("#menu-item-389").hover();
        $("#menu-item-390").click();
        $("[data-product_id='419']").click();
        $(".cart-contents").shouldHave(text("450"));
        $(".cart-contents").click();
        $("#coupon_code").setValue("GIVEMEHALYAVA").pressEnter();
        // Проверить, что промокод второй раз не сработал.
        $(".woocommerce-message").shouldHave(text("Coupon code applied successfully."));
        $(".woocommerce-Price-amount", 3).shouldHave(text("45,00₽"));
        $(".woocommerce-Price-amount", 4).shouldHave(text("405,00₽"));
    }
}
