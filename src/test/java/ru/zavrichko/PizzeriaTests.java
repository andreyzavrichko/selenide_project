package ru.zavrichko;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.zavrichko.widgetobjects.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@DisplayName("Основные сценарии")
@Issue("IDEA-291823")
@Link(url="https://docs.google.com/document/d/1XzEG0Co_f7wROWRZVYJjyXULTPaIoOvDaIpP0YVoZFQ/edit?usp=sharing",name="Ссылка на баг-репорт")
public class PizzeriaTests extends TestBase {

    @Test
    @DisplayName("Основные процессы покупки")
    @Epic("Основные процессы покупки")
    @Feature("Покупка")
    @Story("Покупка")
    void payTest() {
        var username = "sel" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMddHHmmss"));
        var email = username + "@ya.ru";
        var nextDate = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("ddMMyyyy"));
        step("Открыть сайт", () -> open(baseUrl));
        step("Перейти на страницу Пицца", () ->
            new Menu().openPizzaMenu());
        step("Добавить блюдо в корзину", () ->
            new PizzaPage().addPizza());
        step("Найти в поиске Какао", () ->
            new Search().searchFood("какао"));
        step("Добавить Какао в корзину", () ->
            new Product().addToCart());
        step("Проверить сумму и переходим в корзину", () ->
            new Cart().cartContentsClick("750"));
        step("Проверить детали продуктов", () ->
            new CheckProduct().productDetail("Ветчина и грибы", "Какао с маршмеллоу", "450,00₽", "300,00₽", "450,00₽", "300,00₽", "1", "1", "750,00₽"));
        step("Увеличить количество продуктов", () -> {
            new CheckProduct().addQuantity("2");
            new CheckProduct().addQuantityClick();
        });
        step("Повторно проверить сумму и переходим в корзину", () ->
            new CheckProduct().productDetail("Ветчина и грибы", "Какао с маршмеллоу", "450,00₽", "300,00₽", "900,00₽", "600,00₽", "2", "2", "1500,00₽"));
        step("Перейти к регистрации", () -> {
            new Menu().openMenu("Главная");
            new Menu().openMenu("Мой аккаунт");
            new Menu().openRegister();
        });
        step("Зарегистрировать пользователя", () -> {
            new Registration().register(username, email, "selenide123");
            new Header().welcomeMsg.shouldHave(text("Привет " + username));
        });
        step("Оформить заказ", () -> {
            new Menu().openMenu("Оформление заказа");
            new DetailOrder().makeOrder("Andrey", "Zavrichko", "Street Skillbox'ovskaya 56", "Voronezh", "Voronezhskaya", "887700", "+7493648456454", nextDate);
        });
        step("Проверить заказ", () ->
            new CheckOrder().productDetail("1500,00₽", "Andrey", "Zavrichko", "Street Skillbox'ovskaya 56", "Voronezh", "Voronezhskaya", "887700", "+7493648456454", email));
    }


    @Test
    @DisplayName("Позитивный сценарий промокода")
    @Epic("Работа промокода")
    @Feature("Промо")
    @Story("Промо")
    void promoPositiveTest() {
        step("Открыть сайт", () -> open(baseUrl));
        step("Перейти на страницу Пицца", () ->
            new Menu().openPizzaMenu());
        step("Добавить блюдо в корзину", () ->
            new PizzaPage().addPizza());
        step("Проверить сумму и переходим в корзину", () ->
            new Cart().cartContentsClick("450"));
        step("Применить купон", () ->
            new Coupon().addCoupon("GIVEMEHALYAVA"));
        step("Проверить уменьшение суммы", () ->
            new CheckProduct().checkPrice("45,00₽", "405,00₽"));
    }

    @Test
    @DisplayName("Негативный сценарий промокода")
    @Epic("Работа промокода")
    @Feature("Промо")
    @Story("Промо")
    void promoNegativeTest() {
        step("Открыть сайт", () -> open(baseUrl));
        step("Перейти на страницу Пицца", () ->
            new Menu().openPizzaMenu());
        step("Добавить блюдо в корзину", () ->
            new PizzaPage().addPizza());
        step("Проверить сумму и переходим в корзину", () ->
            new Cart().cartContentsClick("450"));
        step("Применить купон", () ->
            new Coupon().addCoupon("NONAMEPROMO"));
        step("Проверить сообщение ошибки", () ->
            new CheckProduct().checkError("Неверный купон."));
        step("Проверить сумму", () ->
            new CheckProduct().checkPriceFour("450,00₽"));
    }

    @Test
    @DisplayName("Сфокусированный тест на промокод")
    @Epic("Работа промокода")
    @Feature("Промо")
    @Story("Промо")
    @TmsLink("CLOUD-10918")
    void promoDoubleTest() {
        var username = "sel" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMddHHmmss"));
        var email = username + "@ya.ru";
        var nextDate = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("ddMMyyyy"));
        step("Открыть сайт", () -> open(baseUrl));
        step("Открыть акции", () ->
            new Menu().openMenu("Акции"));
        step("Проверить страницу промокодов", () ->
            new Promo().checkPromo("Промокод на первый заказ: GIVEMEHALYAVA"));
        step("Зарегистрировать нового пользователя", () -> {
            new Menu().openMenu("Мой аккаунт");
            new Menu().openRegister();
            new Registration().register(username, email, "selenide123");
            new Header().welcomeMsg.shouldHave(text("Привет " + username));
        });
        step("Создать первый заказ", () -> {
            new Menu().openPizzaMenu();
            new PizzaPage().addPizza();
            new Cart().cartContentsClick("450");
        });
        step("Применить промокод", () -> {
            new Coupon().addCoupon("GIVEMEHALYAVA");
            new CheckProduct().checkPrice("45,00₽", "405,00₽");
        });
        step("Оформить заказ", () -> {
            new Menu().openMenu("Оформление заказа");
            new DetailOrder().makeOrder("Andrey", "Zavrichko", "Street Skillbox'ovskaya 56", "Voronezh", "Voronezhskaya", "887700", "+7493648456454", nextDate);
        });
        step("Проверить заказ", () ->
            new CheckOrder().productDetail("405,00₽", "Andrey", "Zavrichko", "Street Skillbox'ovskaya 56", "Voronezh", "Voronezhskaya", "887700", "+7493648456454", email));
        step("Оформить второй заказ", () -> {
            new Menu().openMenu("Главная");
            new Menu().openPizzaMenu();
            new PizzaPage().addPizza();
            new Cart().cartContentsClick("450");
        });
        step("Добавить купон", () ->
            new Coupon().addCoupon("GIVEMEHALYAVA"));
        step("Проверить, что промокод второй раз не сработал.", () -> {
            new CheckProduct().checkMessage("Coupon code applied successfully.");
            new CheckProduct().checkPrice("45,00₽", "405,00₽");
        });
    }
}
