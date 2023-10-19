package ru.netology.service;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryFormTest {
    public String generateDate(int days) {
        return LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("23.10.2023"));
    }
    @Test
    void shouldTest() {
        String planningDate = generateDate(4);

        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id='city'] input").setValue("Красноярск");
        form.$("[data-test-id='name'] input").setValue("Иванов Василий");
        form.$("[data-test-id='phone'] input").setValue("+72930000000");
        form.$("[data-test-id='agreement'] ").click();
        form.$(".button").click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15)).shouldBe(visible);
    }
}