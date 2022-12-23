package ru.netology.tests.paymentbycard;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.pages.MainPage;
import ru.netology.pages.PurchaseForm;
import ru.netology.tests.TestBaseUI;

import static ru.netology.data.Data.*;

public class HolderFieldPayByCard extends TestBaseUI {
    private MainPage mainPage = new MainPage();
    private PurchaseForm purchaseForm = new PurchaseForm();

    @BeforeEach
    public void clickBuy() {
        mainPage.clickBuy();
    }

    @Test
    public void testValidData() {
        val cardData = getFormWithDoubleSurnameInFieldHolder();
        purchaseForm.completedPaymentForm(cardData);
        purchaseForm.waitSuccessResult();
    }

    @Test
    public void testValidDataLongLastName() {
        val cardData = getCorrectlyCompletedApprovedCardFormLongLastName();
        purchaseForm.completedPaymentForm(cardData);
        purchaseForm.waitSuccessResult();
    }

    @Test
    public void testEmptyOwnerField() {
        val cardData = getFormWithEmptyFieldHolder();
        purchaseForm.completedPaymentForm(cardData);
        purchaseForm.waitThisFieldIsRequired();
    }


    @Test
    public void testNumbersInTheOwnerField() {
        val cardData = getFormWithNumbersInFieldHolder();
        purchaseForm.completedPaymentForm(cardData);
        purchaseForm.waitIncorrectFormat();
    }

    @Test
    public void testCharactersInTheOwnerField() {
        val cardData = getFormSymbolBersInTheHolderField();
        purchaseForm.completedPaymentForm(cardData);
        purchaseForm.waitIncorrectFormat();
    }

    @Test
    public void testSpacesBetween() {
        val cardData = getFormWithManySpaceInFieldHolder();
        purchaseForm.completedPaymentForm(cardData);
        purchaseForm.waitIncorrectFormat();
    }

    @Test
    public void testCyrillicAlphabet() {
        val cardData = getFormWithHolderRu();
        purchaseForm.completedPaymentForm(cardData);
        purchaseForm.waitIncorrectFormat();
    }

    @Test
    public void test50Symbols() {
        val cardData = getFormWithLongNameInFieldHolder();
        purchaseForm.completedPaymentForm(cardData);
        purchaseForm.waitIncorrectFormat();
    }


    @Test
    public void testOneLetter() {
        val cardData = getFormWithShortNameInFieldHolder();
        purchaseForm.completedPaymentForm(cardData);
        purchaseForm.waitIncorrectFormat();
    }

}
