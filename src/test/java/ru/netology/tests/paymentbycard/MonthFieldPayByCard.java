package ru.netology.tests.paymentbycard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.pages.MainPage;
import ru.netology.pages.PurchaseForm;
import ru.netology.tests.TestBaseUI;
import static ru.netology.data.Data.*;

public class MonthFieldPayByCard extends TestBaseUI {
    private MainPage mainPage = new MainPage();
    private PurchaseForm purchaseForm = new PurchaseForm();

    @BeforeEach
    public void clickBuy() {
        mainPage.clickBuy();
    }

    @Test
    public void testTheMonthFieldIsEmpty() {
        var cardData = getFormWithEmptyFieldMonth();
        purchaseForm.completedPaymentForm(cardData);
        purchaseForm.waitThisFieldIsRequired();
    }


    @Test
    public void testIncorrectSpellingOfTheCardValidityMonth() {
        var cardData = getFormWithInvalidMonth();
        purchaseForm.completedPaymentForm(cardData);
        purchaseForm.waitInvalidCardExpirationDate();
    }

    @Test
    public void InvalidMonthOfCardValidit() {
        var cardData = getFormWithIncorrectFieldMonthOneNumber();
        purchaseForm.completedPaymentForm(cardData);
        purchaseForm.waitIncorrectFormat();
    }

    @Test
    public void testSendARequestWithAnInvalidValueInTheFieldZero() {
        var cardData = getFormWithIncorrectFieldMonthZero();
        purchaseForm.completedPaymentForm(cardData);
        purchaseForm.waitInvalidCardExpirationDate();
    }

}