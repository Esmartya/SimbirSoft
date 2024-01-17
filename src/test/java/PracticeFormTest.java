import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PracticeFormTest {
    private static final String URL = "https://demoqa.com/automation-practice-form";
    private static final String FIRST_NAME = "Пётр";
    private static final String LAST_NAME = "Иванов";
    private static final String EMAIL = "mail@mail.ru";
    private static final String MOBILE = "8002000500";
    private static final String SUBJECTS = "QA";
    private static final String PICTURE_ADDRESS = "C:/Users/Jenny/Downloads/bug.jpg";
    private static final String PICTURE_NAME = "bug.jpg";
    private static final String CURRENT_ADDRESS = "Лермонтова, 276";

    private static final String requiredValues = FIRST_NAME + " " + LAST_NAME + " " + EMAIL + " " + "Male" + " " + MOBILE + " "
        + "07 January,1992" + " " + SUBJECTS + " " + "Music" + " " + PICTURE_NAME + " " + CURRENT_ADDRESS + " " + "Haryana Panipat" + " ";

    private static final String requiredValues1 = FIRST_NAME + " " + LAST_NAME + " " + EMAIL + " " + "Male" + " " + MOBILE + " "
            + "07 January,1992" + "   " + PICTURE_NAME + " " + CURRENT_ADDRESS + " " + "Haryana Panipat" + " ";


    @Test
    public void testRegistrationFormAllFields() {
        WebDriver driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().setSize(new Dimension(760, 900));

        PracticeFormPage practiceFormPage = new PracticeFormPage(driver)
                .typeFirstName(FIRST_NAME)
                .typeLastName(LAST_NAME)
                .typeEmail(EMAIL)
                .clickMaleGender()
                .typeMobile(MOBILE)
                .selectDateOfBirth()
                .typeSubjects(SUBJECTS) // на сайте после ввода в поле значение в поле не сохраняется - баг
                .clickMusicHobby()   // после ввода в поле значение в итоговой таблице не сохраняется - баг
                .selectPicture(PICTURE_ADDRESS)
                .typeAddress(CURRENT_ADDRESS)
                .selectStateHaryana()
                .selectCityPanipat()
                .clickSubmit();

        String message = new PracticeFormPage(driver).getSubmittingMessageText();
        String values = new PracticeFormPage(driver).getValuesText();

        Assert.assertEquals(message,"Thanks for submitting the form");
        Assert.assertEquals(values, requiredValues); //проверка заполнения всех полей итоговой формы, падает из-за багов сайта
    }

    @Test
    public void testRegistrationFormValidFields() {
        WebDriver driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().setSize(new Dimension(760, 900));

        PracticeFormPage practiceFormPage = new PracticeFormPage(driver)
                .typeFirstName(FIRST_NAME)
                .typeLastName(LAST_NAME)
                .typeEmail(EMAIL)
                .clickMaleGender()
                .typeMobile(MOBILE)
                .selectDateOfBirth()
                //.typeSubjects(SUBJECTS) - на сайте после ввода в поле значение не сохраняется (убрано из теста для зеленого прогона) - баг
                .clickMusicHobby()   // после ввода в поле значение в итоговой таблице не сохраняется - баг
                .selectPicture(PICTURE_ADDRESS)
                .typeAddress(CURRENT_ADDRESS)
                .selectStateHaryana()
                .selectCityPanipat()
                .clickSubmit();

        String message = new PracticeFormPage(driver).getSubmittingMessageText();
        String values = new PracticeFormPage(driver).getValuesText();

        Assert.assertEquals(message,"Thanks for submitting the form");
        Assert.assertEquals(values, requiredValues1); // проверка заполнения полей итоговой формы, кроме Subjects, Hobbies
    }
}

