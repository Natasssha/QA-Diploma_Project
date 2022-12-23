package ru.netology.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.Data;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class PurchaseForm {
    private final SelenideElement numberCardField = $("input[placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthField = $("input[placeholder='08']");
    private final SelenideElement yearField = $("input[placeholder='22']");
    private final ElementsCollection fieldSet = $$(".input__control");
    private final SelenideElement holderField = fieldSet.get(3);
    private final SelenideElement cvcField = $("input[placeholder='999']");

    private final SelenideElement successResult = $(withText("Операция одобрена Банком."));
    private final SelenideElement invalidCardExpirationDate = $(withText("Неверно указан срок действия карты"));
    private final SelenideElement incorrectFormat = $(withText("Неверный формат"));
    private final SelenideElement error = $(withText("Ошибка! Банк отказал в проведении операции."));
    private final SelenideElement cardExpired = $(withText("Истёк срок действия карты"));
    private final SelenideElement thisFieldIsRequired = $(withText("Поле обязательно для заполнения"));
    private final SelenideElement continueField = $(withText("Продолжить"));

    public void completedPaymentForm(Data.CardData cardData) {
        numberCardField.setValue(cardData.getNumber());
        monthField.setValue(cardData.getMonth());
        yearField.setValue(cardData.getYear());
        holderField.setValue(cardData.getHolder());
        cvcField.setValue(cardData.getCvc());
        continueField.click();
    }

    public void waitSuccessResult() {
        successResult.waitUntil(Condition.visible, 12000);
    }

    public void waitInvalidCardExpirationDate() {
        invalidCardExpirationDate.waitUntil(Condition.visible, 12000);
    }

    public void waitIncorrectFormat() {
        incorrectFormat.waitUntil(Condition.visible, 12000);
    }

    public void waitError() {
        error.waitUntil(Condition.visible, 12000);
    }

    public void waitCardExpired() {
        cardExpired.waitUntil(Condition.visible, 12000);
    }

    public void waitThisFieldIsRequired() {
        thisFieldIsRequired.waitUntil(Condition.visible, 12000);
    }
}