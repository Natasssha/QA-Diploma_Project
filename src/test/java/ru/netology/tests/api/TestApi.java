package ru.netology.tests.api;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.netology.data.Api.*;
import static ru.netology.data.Data.getCorrectlyCompletedApprovedCardForm;
import static ru.netology.data.Data.getCorrectlyCompletedDeclinedCardForm;

public class TestApi {

    @Test
    void approvedCardBuyForm() {
        var validApprovedCard = getCorrectlyCompletedApprovedCardForm();
        var status = buyForm(validApprovedCard);
        assertTrue(status.contains("APPROVED"));
    }

    @Test
    void declinedCardBuyForm() {
        var validDeclinedCard = getCorrectlyCompletedDeclinedCardForm();
        var status = buyForm(validDeclinedCard);
        assertTrue(status.contains("DECLINED"));
    }

    @Test
    void approvedCardCreditForm() {
        var validApprovedCard = getCorrectlyCompletedApprovedCardForm();
        var status = creditForm(validApprovedCard);
        assertTrue(status.contains("APPROVED"));
    }

    @Test
    void declinedCardCreditForm() {
        var validDeclinedCard = getCorrectlyCompletedDeclinedCardForm();
        var status = creditForm(validDeclinedCard);
        assertTrue(status.contains("DECLINED"));
    }
}
