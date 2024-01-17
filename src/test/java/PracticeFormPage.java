import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PracticeFormPage {

    public WebDriver driver;

    public PracticeFormPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }

    public PracticeFormPage typeFirstName(String firstName) {
        driver.findElement(By.id("firstName")).sendKeys(firstName);

        return this;
    }

    public PracticeFormPage typeLastName(String lastName) {
        driver.findElement(By.id("lastName")).sendKeys(lastName);

        return this;
    }

    public PracticeFormPage typeEmail(String email) {
        driver.findElement(By.id("userEmail")).sendKeys(email);

        return this;
    }

    public PracticeFormPage clickMaleGender() {
        //new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(By.id("gender-radio-2"))).click();
        Actions genderAction = new Actions(driver);
        genderAction.moveToElement(driver.findElement(By.id("gender-radio-1"))).click().perform();

        return this;
    }

    public PracticeFormPage typeMobile(String mobile) {
        driver.findElement(By.id("userNumber")).sendKeys(mobile);

        return this;
    }

    public PracticeFormPage selectDateOfBirth() {

        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("dateOfBirthInput")));
        driver.findElement(By.id("dateOfBirthInput")).click();

        Select months = new Select(driver.findElement(By.xpath
                ("//*[@id=\"dateOfBirth\"]/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div[1]/select")));
        months.selectByValue(String.valueOf(0));

        Select years = new Select(driver.findElement(By.xpath
                ("//*[@id=\"dateOfBirth\"]/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div[2]/select")));
        years.selectByValue(String.valueOf(1992));

        Actions dateOfBirthAction = new Actions(driver);
        dateOfBirthAction.moveToElement(driver.findElement(By.xpath(
                "//*[@id=\"dateOfBirth\"]/div[2]/div[2]/div/div/div[2]/div[2]/div[2]/div[3]"))).click().perform();

        return this;
    }

    public PracticeFormPage typeSubjects(String subjects) {
        driver.findElement(By.xpath("//*[@id=\"subjectsContainer\"]/div/div[1]/div[2]/div")).sendKeys(subjects);

        return this;
    }

    public PracticeFormPage clickMusicHobby() {
        Actions hobbyAction = new Actions(driver);
        hobbyAction.moveToElement(driver.findElement(By.id("hobbies-checkbox-3"))).click();

        return this;
    }

    public PracticeFormPage selectPicture(String pictureAddress) {
        driver.findElement(By.id("uploadPicture")).sendKeys(pictureAddress);

        return this;
    }

    public PracticeFormPage typeAddress(String address) {
        driver.findElement(By.id("currentAddress")).sendKeys(address);

        return this;
    }

    public PracticeFormPage selectStateHaryana() {
        Actions stateAction = new Actions(driver);
        stateAction.moveToElement(driver.findElement(By.id("state"))).click().moveByOffset(0, 150).click().perform();

        return this;
    }

    public PracticeFormPage selectCityPanipat() {
        Actions cityAction = new Actions(driver);
        cityAction.moveToElement(driver.findElement(By.id("city"))).click().moveByOffset(0, 100).click().perform();

        return this;
    }

    public PracticeFormPage clickSubmit() {
        driver.findElement(By.id("submit")).click();

        return this;
    }

    public String getSubmittingMessageText() {
        //String message = driver.findElement(By.xpath("//*[@id=\"example-modal-sizes-title-lg\"]")).getText();
        String message = driver.findElement(By.cssSelector("#example-modal-sizes-title-lg")).getText();

        return message;
    }

    public String getValuesText() {
        String tableText = "";
        for (int i = 1; i <= 10; i++) {
            tableText = tableText + driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[2]")).getText() + " ";
        }

        return tableText;
    }
}
