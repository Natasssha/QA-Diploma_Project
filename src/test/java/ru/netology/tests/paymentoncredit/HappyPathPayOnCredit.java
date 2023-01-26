package ru.netology.tests.paymentoncredit;

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
    PurchaseForm purchaseForm = new PurchaseForm();
    MainPage mainPage = new MainPage();

    @BeforeEach
    public void clickBuy() {
        mainPage.clickBuyOnCredit();
    }

    @Test
    public void successResultIfApprovedCardsCreditForm() {
        var cardData = getCorrectlyCompletedApprovedCardForm();
        purchaseForm.completedPaymentForm(cardData);
        purchaseForm.waitSuccessResult();

        var expected = "APPROVED";
        var creditRequestInfo = getCardStatusForCreditRequest();
        var orderInfo = getPaymentId();


        assertEquals(expected, creditRequestInfo.getStatus());
        assertEquals(creditRequestInfo.getBank_id(), orderInfo.getCredit_id());

    }

    @Test
    public void failResultIfDeclinedCardsCreditForm() {
        var cardData = getCorrectlyCompletedDeclinedCardForm();
        purchaseForm.completedPaymentForm(cardData);
        purchaseForm.waitSuccessResult();

        var expected = "DECLINED";
        var creditRequestInfo = getCardStatusForCreditRequest();
        var orderInfo = getPaymentId();


        assertEquals(expected, creditRequestInfo.getStatus());
        assertEquals(creditRequestInfo.getBank_id(), orderInfo.getCredit_id());


    }
}