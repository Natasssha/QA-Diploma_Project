package ru.netology.tests.paymentoncredit;

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

public class HappyPathPayOnCredit extends TestBaseUI {
    private PurchaseForm purchaseForm = new PurchaseForm();
    private MainPage mainPage = new MainPage();

    @BeforeEach
    public void clickBuy() {
        mainPage.clickBuyOnCredit();
    }

    @Test
    public void successResultIfApprovedCardsCreditForm() {
        val cardData = getCorrectlyCompletedApprovedCardForm();
        purchaseForm.completedPaymentForm(cardData);
        purchaseForm.waitSuccessResult();

        val expected = "APPROVED";
        val actual = getCardStatusForCreditRequest();
        assertEquals(expected, actual);

        val bankIdExpected = getBankId();
        val paymentIdActual = getPaymentId();
        assertNotNull(bankIdExpected);
        assertNotNull(paymentIdActual);
        assertEquals(bankIdExpected, paymentIdActual);
    }

    @Test
    public void failResultIfDeclinedCardsCreditForm() {
        val cardData = getCorrectlyCompletedDeclinedCardForm();
        purchaseForm.completedPaymentForm(cardData);
        purchaseForm.waitSuccessResult();

        val statusExpected = "DECLINED";
        val statusActual = getCardStatusForCreditRequest();
        assertEquals(statusExpected, statusActual);

        val bankIdExpected = getBankId();
        val paymentIdActual = getPaymentId();
        assertNotNull(bankIdExpected);
        assertNotNull(paymentIdActual);
        assertEquals(bankIdExpected, paymentIdActual);
    }
}

