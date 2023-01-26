package ru.netology.tests.paymentbycard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.pages.MainPage;
import ru.netology.pages.PurchaseForm;
import ru.netology.tests.TestBaseUI;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ru.netology.data.Data.getCorrectlyCompletedApprovedCardForm;
import static ru.netology.data.Data.getCorrectlyCompletedDeclinedCardForm;
import static ru.netology.data.SQL.*;

public class HappyPathPayByCard extends TestBaseUI {
    PurchaseForm purchaseForm = new PurchaseForm();
    MainPage mainPage = new MainPage();

    @BeforeEach
    public void clickBuy() {
        mainPage.clickBuy();
    }

    @Test
    public void successResultIfApprovedCardsBuyForm() {
        var cardData = getCorrectlyCompletedApprovedCardForm();
        purchaseForm.completedPaymentForm(cardData);
        purchaseForm.waitSuccessResult();

        var statusExpected = "APPROVED";
        var paymentInfo = getCardStatusForPayment();
        var orderInfo = getPaymentId();


        assertEquals(statusExpected, paymentInfo.getStatus());
        assertEquals(paymentInfo.getTransaction_id(), orderInfo.getPayment_id());

    }

    @Test
    public void failResultIfDeclinedCardBuyForm() {
        var cardData = getCorrectlyCompletedDeclinedCardForm();
        purchaseForm.completedPaymentForm(cardData);
        purchaseForm.waitSuccessResult();

        var statusExpected = "DECLINED";
        var paymentInfo = getCardStatusForPayment();
        var orderInfo = getPaymentId();


        assertEquals(statusExpected, paymentInfo.getStatus());
        assertEquals(paymentInfo.getTransaction_id(), orderInfo.getPayment_id());

    }
}