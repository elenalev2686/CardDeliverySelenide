package ru.netology.service;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryFormTest {
    @Test
    void shouldTest() {

        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id='city'] input").setValue("Красноярск");
        form.$("[data-test-id='date'] input").setValue("2023-10-21");
        form.$("[data-test-id=name] input").setValue("Иванов Василий");
        form.$("[data-test-id=phone] input").setValue("+72930000000");
        form.$("[data-test-id=agreement] ").click();
        form.$(".button").click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15));
    }
}