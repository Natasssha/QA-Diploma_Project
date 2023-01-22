package ru.netology.tests.paymentoncredit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.pages.MainPage;
import ru.netology.pages.PurchaseForm;
import ru.netology.tests.TestBaseUI;
import static ru.netology.data.Data.*;

public class YearFieldPayOnCredit extends TestBaseUI {
    private MainPage mainPage = new MainPage();
    private PurchaseForm purchaseForm = new PurchaseForm();

    @BeforeEach
    public void clickBuy() {
        mainPage.clickBuyOnCredit();
    }

    @Test
    public void testInvalidCardActionCode() {
        var cardData = getFormWithInvalidYear();
        purchaseForm.completedPaymentForm(cardData);
        purchaseForm.waitInvalidCardExpirationDate();
    }

    @Test
    public void IndicationOfTheExpiredValidityPeriodOfTheCard() {
        var cardData = getFormWithExpiredYear();
        purchaseForm.completedPaymentForm(cardData);
        purchaseForm.waitCardExpired();
    }

    @Test
    public void testSendARequestWithAnEmptyField() {
        var cardData = getFormWithEmptyYear();
        purchaseForm.completedPaymentForm(cardData);
        purchaseForm.waitThisFieldIsRequired();
    }

    @Test
    public void testSendARequestWithAnIncorrectValueInTheField() {
        var cardData = getFormWithIncorrectFieldYearOneNumber();
        purchaseForm.completedPaymentForm(cardData);
        purchaseForm.waitIncorrectFormat();
    }

}
