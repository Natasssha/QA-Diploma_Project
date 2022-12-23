package ru.netology.tests.api;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.netology.data.Api.*;
import static ru.netology.data.Data.getCorrectlyCompletedApprovedCardForm;
import static ru.netology.data.Data.getCorrectlyCompletedDeclinedCardForm;

public class TestApi {

    @Test
    void approvedCardBuyForm() {
        val validApprovedCard = getCorrectlyCompletedApprovedCardForm();
        val status = buyForm(validApprovedCard);
        assertTrue(status.contains("APPROVED"));
    }

    @Test
    void declinedCardBuyForm() {
        val validDeclinedCard = getCorrectlyCompletedDeclinedCardForm();
        val status = buyForm(validDeclinedCard);
        assertTrue(status.contains("DECLINED"));
    }

    @Test
    void approvedCardCreditForm() {
        val validApprovedCard = getCorrectlyCompletedApprovedCardForm();
        val status = creditForm(validApprovedCard);
        assertTrue(status.contains("APPROVED"));
    }

    @Test
    void declinedCardCreditForm() {
        val validDeclinedCard = getCorrectlyCompletedDeclinedCardForm();
        val status = creditForm(validDeclinedCard);
        assertTrue(status.contains("DECLINED"));
    }
}
