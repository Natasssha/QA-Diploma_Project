package ru.netology.tests.paymentoncredit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.pages.MainPage;
import ru.netology.pages.PurchaseForm;
import ru.netology.tests.TestBaseUI;
import static ru.netology.data.Data.*;


public class HolderFieldPayOnCredit extends TestBaseUI {
    private MainPage mainPage = new MainPage();
    private PurchaseForm purchaseForm = new PurchaseForm();

    @BeforeEach
    public void clickBuy() {
        mainPage.clickBuyOnCredit();
    }


    @Test
    public void testEmptyOwnerField() {
        var cardData = getFormWithEmptyFieldHolder();
        purchaseForm.completedPaymentForm(cardData);
        purchaseForm.waitThisFieldIsRequired();
    }


    @Test
    public void testNumbersInTheOwnerField() {
        var cardData = getFormWithNumbersInFieldHolder();
        purchaseForm.completedPaymentForm(cardData);
        purchaseForm.waitIncorrectFormat();
    }

    @Test
    public void testCharactersInTheOwnerField() {
        var cardData = getFormSymbolBersInTheHolderField();
        purchaseForm.completedPaymentForm(cardData);
        purchaseForm.waitIncorrectFormat();
    }

    @Test
    public void testSpacesBetween() {
        var cardData = getFormWithManySpaceInFieldHolder();
        purchaseForm.completedPaymentForm(cardData);
        purchaseForm.waitIncorrectFormat();
    }

    @Test
    public void testCyrillicAlphabet() {
        var cardData = getFormWithHolderRu();
        purchaseForm.completedPaymentForm(cardData);
        purchaseForm.waitIncorrectFormat();
    }

    @Test
    public void test50Symbols() {
        var cardData = getFormWithLongNameInFieldHolder();
        purchaseForm.completedPaymentForm(cardData);
        purchaseForm.waitIncorrectFormat();
    }


    @Test
    public void testOneLetter() {
        var cardData = getFormWithShortNameInFieldHolder();
        purchaseForm.completedPaymentForm(cardData);
        purchaseForm.waitIncorrectFormat();
    }

}
