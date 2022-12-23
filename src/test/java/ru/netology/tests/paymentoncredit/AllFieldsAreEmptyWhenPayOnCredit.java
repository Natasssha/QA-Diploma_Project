package ru.netology.tests.paymentoncredit;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.pages.MainPage;
import ru.netology.pages.PurchaseForm;
import ru.netology.tests.TestBaseUI;

import static com.codeborne.selenide.Selenide.$$;
import static ru.netology.data.Data.getFormWithAllEmptyFields;

public class AllFieldsAreEmptyWhenPayOnCredit extends TestBaseUI {
    private MainPage mainPage = new MainPage();
    private PurchaseForm purchaseForm = new PurchaseForm();

    @BeforeEach
    public void clickBuy() {
        mainPage.clickBuyOnCredit();
    }

    @Test
    public void testWithEmptyAllFieldsPayOnCredit() {
        val cardData = getFormWithAllEmptyFields();
        purchaseForm.completedPaymentForm(cardData);
        final ElementsCollection emptyField = $$(".input__sub");
        final SelenideElement cardNumberField = emptyField.get(1);
        final SelenideElement monthField = emptyField.get(2);
        final SelenideElement yearField = emptyField.get(3);
        final SelenideElement holderField = emptyField.get(4);
        final SelenideElement cvcField = emptyField.get(5);
        cardNumberField.shouldHave(Condition.text("Поле обязательно для заполнения"));
        monthField.shouldHave(Condition.text("Поле обязательно для заполнения"));
        yearField.shouldBe(Condition.text("Поле обязательно для заполнения"));
        holderField.shouldBe(Condition.text("Поле обязательно для заполнения"));
        cvcField.shouldBe(Condition.visible.text("Поле обязательно для заполнения"));
    }
}
