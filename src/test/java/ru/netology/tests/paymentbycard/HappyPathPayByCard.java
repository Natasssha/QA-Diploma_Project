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
    private PurchaseForm purchaseForm = new PurchaseForm();
    private MainPage mainPage = new MainPage();

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
        var statusActual = getCardStatusForPayment();
        assertEquals(statusExpected, statusActual);

        var expectedAmount = "45000";
        var actualAmount = getAmountPayment();
        assertEquals(expectedAmount, actualAmount);

        var expectedId = getTransactionId();
        var actualId = getPaymentId();
        assertNotNull(actualId);
        assertNotNull(expectedId);
        assertEquals(expectedId, actualId);
    }

    @Test
    public void failResultIfDeclinedCardBuyForm() {
        var cardData = getCorrectlyCompletedDeclinedCardForm();
        purchaseForm.completedPaymentForm(cardData);
        purchaseForm.waitSuccessResult();

        var statusExpected = "DECLINED";
        var statusActual = getCardStatusForPayment();
        assertEquals(statusExpected, statusActual);

        var expectedId = getBankId();
        var actualId = getPaymentId();
        assertNotNull(expectedId);
        assertNotNull(actualId);
        assertEquals(expectedId, actualId);
    }
}

