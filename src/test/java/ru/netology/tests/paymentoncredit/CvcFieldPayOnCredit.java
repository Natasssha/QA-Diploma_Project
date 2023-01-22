package ru.netology.tests.paymentoncredit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.pages.MainPage;
import ru.netology.pages.PurchaseForm;
import ru.netology.tests.TestBaseUI;
import static ru.netology.data.Data.*;

public class CvcFieldPayOnCredit extends TestBaseUI {
    private MainPage mainPage = new MainPage();
    private PurchaseForm purchaseForm = new PurchaseForm();

    @BeforeEach
    public void clickBuy() {
        mainPage.clickBuyOnCredit();
    }

    @Test
    public void testWithEmptyCvcFieldCreditByCard() {
        var cardData = getFormWithEmptyFieldCvc();
        purchaseForm.completedPaymentForm(cardData);
        purchaseForm.waitThisFieldIsRequired();
    }

    @Test
    public void testWithIncorrectFillingInTheCvcFieldCreditByCard() {
        var cardData = getFormWithIncorrectCvc();
        purchaseForm.completedPaymentForm(cardData);
        purchaseForm.waitIncorrectFormat();
    }
}
