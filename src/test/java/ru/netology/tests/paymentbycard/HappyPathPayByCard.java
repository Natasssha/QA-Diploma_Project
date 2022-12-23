package ru.netology.tests.paymentbycard;

import lombok.val;
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
        val cardData = getCorrectlyCompletedApprovedCardForm();
        purchaseForm.completedPaymentForm(cardData);
        purchaseForm.waitSuccessResult();

        val statusExpected = "APPROVED";
        val statusActual = getCardStatusForPayment();
        assertEquals(statusExpected, statusActual);

        val expectedAmount = "45000";
        val actualAmount = getAmountPayment();
        assertEquals(expectedAmount, actualAmount);

        val expectedId = getTransactionId();
        val actualId = getPaymentId();
        assertNotNull(actualId);
        assertNotNull(expectedId);
        assertEquals(expectedId, actualId);
    }

    @Test
    public void failResultIfDeclinedCardBuyForm() {
        val cardData = getCorrectlyCompletedDeclinedCardForm();
        purchaseForm.completedPaymentForm(cardData);
        purchaseForm.waitSuccessResult();

        val statusExpected = "DECLINED";
        val statusActual = getCardStatusForPayment();
        assertEquals(statusExpected, statusActual);

        val expectedId = getBankId();
        val actualId = getPaymentId();
        assertNotNull(expectedId);
        assertNotNull(actualId);
        assertEquals(expectedId, actualId);
    }
}

