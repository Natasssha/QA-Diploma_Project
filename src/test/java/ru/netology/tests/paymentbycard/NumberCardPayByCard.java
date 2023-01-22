package ru.netology.tests.paymentbycard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.pages.MainPage;
import ru.netology.pages.PurchaseForm;
import ru.netology.tests.TestBaseUI;
import static ru.netology.data.Data.*;

public class NumberCardPayByCard extends TestBaseUI {
    private MainPage mainPage = new MainPage();
    private PurchaseForm purchaseForm = new PurchaseForm();

    @BeforeEach
    public void clickBuy() {
        mainPage.clickBuy();
    }


    @Test
    public void testForDeclinedCardPayByCard() {
        var cardData = getFormWithIncorrectNumberCard();
        purchaseForm.completedPaymentForm(cardData);
        purchaseForm.waitError();
    }

    @Test
    public void testWithDeficientNumbersCardPayByCard() {
        var cardData = getFormWithDeficientNumberCard();
        purchaseForm.completedPaymentForm(cardData);
        purchaseForm.waitThisFieldIsRequired();
    }


    @Test
    public void testWithEmptyNumberCardPayByCard() {
        var cardData = getFormWithEmptyFieldNumberCard();
        purchaseForm.completedPaymentForm(cardData);
        purchaseForm.waitIncorrectFormat();
    }

}