package ru.netology.tests.paymentbycard;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.pages.MainPage;
import ru.netology.pages.PurchaseForm;

import ru.netology.tests.TestBaseUI;

import static ru.netology.data.Data.*;

public class YearFieldPayByCard extends TestBaseUI {
    private MainPage mainPage = new MainPage();
    private PurchaseForm purchaseForm = new PurchaseForm();

    @BeforeEach
    public void clickBuy() {
        mainPage.clickBuy();
    }

    @Test
    public void testInvalidCardActionCode() {
        val cardData = getFormWithInvalidYear();
        purchaseForm.completedPaymentForm(cardData);
        purchaseForm.waitInvalidCardExpirationDate();
    }

    @Test
    public void IndicationOfTheExpiredValidityPeriodOfTheCard() {
        val cardData = getFormWithExpiredYear();
        purchaseForm.completedPaymentForm(cardData);
        purchaseForm.waitCardExpired();
    }

    @Test
    public void testSendARequestWithAnEmptyField() {
        val cardData = getFormWithEmptyYear();
        purchaseForm.completedPaymentForm(cardData);
        purchaseForm.waitThisFieldIsRequired();
    }

    @Test
    public void testSendARequestWithAnIncorrectValueInTheField() {
        val cardData = getFormWithIncorrectFieldYearOneNumber();
        purchaseForm.completedPaymentForm(cardData);
        purchaseForm.waitIncorrectFormat();
    }

}
