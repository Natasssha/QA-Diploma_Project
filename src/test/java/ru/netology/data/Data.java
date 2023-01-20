package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.NoArgsConstructor;
import lombok.Value;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@NoArgsConstructor
public class Data {

    public static Faker faker = new Faker(new Locale("en"));

    public static String generateMonth() {
        int month = faker.number().numberBetween(0, 13);
        String date = LocalDate.now().plusMonths(month).format(DateTimeFormatter.ofPattern("MM"));
        return date;
    }

    public static String generateYear() {
        int year = faker.number().numberBetween(1, 6);
        String date = LocalDate.now().plusYears(year).format(DateTimeFormatter.ofPattern("yy"));
        return date;
    }

    @Value
    public static class CardData {
        String number;
        String month;
        String year;
        String holder;
        String cvc;

    }
    //Позитивные сценарии, при которых все поля заполнены верно.


    // Сценарий 1.Отправить заявку на покупку тура с валидным заполнением всех полей.
    public static CardData getCorrectlyCompletedApprovedCardForm() {
        return new CardData("4444 4444 4444 4441", generateMonth(), generateYear(), "Ivanov Ivan", "214");
    }

    // Сценарий 2. Отправить заявку на покупку тура с валидным заполнением полей. (двойная фамилия).
    public static CardData getFormWithDoubleSurnameInFieldHolder() {
        return new CardData("4444 4444 4444 4441", generateMonth(), generateYear(), "Ivanov-Petrov Ivan", "214");
    }

    //Сценарий 3.Указание валидного имени и фамилии владельца карты .(длинная фамилия)
    public static CardData getCorrectlyCompletedApprovedCardFormLongLastName() {
        return new CardData("4444 4444 4444 4441", generateMonth(), generateYear(), "Wolfeschlegelsteinhausenbergerdorff Hubert", "214");
    }

    //Сценарий 4.Указать номер карты по которой операция должна отклониться
    public static CardData getCorrectlyCompletedDeclinedCardForm() {
        return new CardData("4444 4444 4444 4442", generateMonth(), generateYear(), "Ivanov Ivan", "214");
    }
    //Hегативные сценарии, при который одно или несколько из полей заполнены неверно.
//

    // Сценарий 1.Отправить заявку с несуществующим номером карты.
    public static CardData getFormWithIncorrectNumberCard() {
        return new CardData("4444 4444 4444 7777", generateMonth(), generateYear(), "Ivanov Ivan", "214");
    }

    //Сценарий 2. Отправить заявку с пустым полем "Номер карты".
    public static CardData getFormWithEmptyFieldNumberCard() {
        return new CardData("", generateMonth(), generateYear(), "Ivanov Ivan", "214");
    }

    //Сценарий 3.Отправить заявку с недостаточным количеством цифр в номере карты.
    public static CardData getFormWithDeficientNumberCard() {
        return new CardData("4444 4444 4444 444", generateMonth(), generateYear(), "Ivanov Ivan", "214");
    }
//

    //Сценарий 4. Отправить заявку с пустым полем "Месяц".
    public static CardData getFormWithEmptyFieldMonth() {
        return new CardData("4444 4444 4444 4441", "", generateYear(), "IVAN IVANOV", "214");
    }

    // Сценарий 5.Невалидный месяц действия карты.
    public static CardData getFormWithInvalidMonth() {
        return new CardData("4444 4444 4444 4441", "21", generateYear(), "IVAN IVANOV", "214");
    }

    // Сценарий 6.Некорректное написание месяца действия карты.(одна цифра)
    public static CardData getFormWithIncorrectFieldMonthOneNumber() {
        return new CardData("4444 4444 4444 4441", "3", generateYear(), "IVAN IVANOV", "214");
    }

    // Сценарий 7.Отправить заявку с невадидным значением в поле "Месяц" (месяц - "00").
    public static CardData getFormWithIncorrectFieldMonthZero() {
        return new CardData("4444 4444 4444 4441", "00", generateYear(), "IVAN IVANOV", "214");
    }
//

    //Сценарий 8.Невалидный год действия карты.
    public static CardData getFormWithInvalidYear() {
        return new CardData("4444 4444 4444 4441", generateMonth(), "30", "IVAN IVANOV", "214");
    }

    //Сценарий 9.Указание просроченного срока действия карты.
    public static CardData getFormWithExpiredYear() {
        return new CardData("4444 4444 4444 4441", generateMonth(), "21", "IVAN IVANOV", "214");
    }

    //Сценарий 10.Отправить заявку с пустым полем "Год".
    public static CardData getFormWithEmptyYear() {
        return new CardData("4444 4444 4444 4441", generateMonth(), "", "IVAN IVANOV", "214");
    }

    // Сценарий 11.Отправить заявку с некорректным значением в поле "Год".
    public static CardData getFormWithIncorrectFieldYearOneNumber() {
        return new CardData("4444 4444 4444 4441", generateMonth(), "4", "IVAN IVANOV", "214");
    }
//

    //Сценарий 12.Отправить заявку с пустым полем "Владелец".
    public static CardData getFormWithEmptyFieldHolder() {
        return new CardData("4444 4444 4444 4441", generateMonth(), generateYear(), "", "214");
    }

    //Сценарий 13.Отправить заявку некорректным заполнением поля "Владелец"(цифрами).
    public static CardData getFormWithNumbersInFieldHolder() {
        return new CardData("4444 4444 4444 4441", generateMonth(), generateYear(), "4444", "214");
    }
    // Сценарий 14.Отправить заявку некорректным заполнением поля "Владелец"(символоми).

    public static CardData getFormSymbolBersInTheHolderField() {
        return new CardData("4444 4444 4444 4441", generateMonth(), generateYear(), "№%?*)", "214");
    }

    //Сценарий 15.Отправить заявку некорректным заполнением поля "Владелец" (c 3-мя пробелами).
    public static CardData getFormWithManySpaceInFieldHolder() {
        return new CardData("4444 4444 4444 4441", generateMonth(), generateYear(), " IVAN   IVANOV", "333");
    }
    //Сценарий 16. Указание владельца карты на кириллице.

    public static CardData getFormWithHolderRu() {
        return new CardData("4444 4444 4444 4441", generateMonth(), generateYear(), "Иванов Иван", "214");
    }
    //Сценарий 17.Отправить заявку с невалидным значением в поле "Владелец" (заполнение поля 50 букв).

    public static CardData getFormWithLongNameInFieldHolder() {
        return new CardData("4444 4444 4444 4441", generateMonth(), generateYear(), "SDFGHJKLASDFGZXCVBNASDFGHQWERTYHBGVCFXDSAZWERTYGDV", "333");
    }
    //Сценарий 18.Отправить заявку с невалидным значением в поле "Владелец" (заполнение поля 1 буква).

    public static CardData getFormWithShortNameInFieldHolder() {
        return new CardData("4444 4444 4444 4441", generateMonth(), generateYear(), "S", "333");
    }
//

    //Сценарий 19. Отправить заявку с пустым полем "CVC/CVV".

    public static CardData getFormWithEmptyFieldCvc() {
        return new CardData("4444 4444 4444 4441", generateMonth(), generateYear(), "IVAN IVANOV", "");
    }

    // Сценарий 20. Отправить заявку с невалидным значением в поле: "CVC/CVV".
    public static CardData getFormWithIncorrectCvc() {
        return new CardData("4444 4444 4444 4441", generateMonth(), generateYear(), "IVAN IVANOV", "64");
    }
//

    //Сценарий 21. Отправить заявку с пустыми полями
    public static CardData getFormWithAllEmptyFields() {
        return new CardData("", "", "", "", "");
    }
}






















