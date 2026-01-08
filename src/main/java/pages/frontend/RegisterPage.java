package pages.frontend;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class RegisterPage extends BasePage {
    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    private static final String URL = "https://inv.bg/";


    // =========================
    // Elements
    // =========================

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "firmname")
    private WebElement companyNameInput;

    @FindBy (id = "subdomain")
    private WebElement companyDomainInput;

    @FindBy(id = "agree")
    private WebElement checkBoxAgreeConditions;

    @FindBy(id = "regbtn")
    private WebElement submitRegistrationButton;

    // =========================
    // Basic actions
    // =========================



    public void openPage(){
        driver.get(URL);
    }


    private void enterEmail(String email){
        super.typeText(emailInput,email);
    }

    private void enterPassword(String password){
        super.typeText(passwordInput,password);
    }

    private void enterCompanyName(String companyName){
        super.typeText(companyNameInput,companyName);
    }

    private void enterCompanyDomainName(String companyDomainName){super.typeText(companyDomainInput, companyDomainName);}



    private void selectCheckbox(){
        if (!checkBoxAgreeConditions.isSelected()){
            super.click(checkBoxAgreeConditions);
        }
    }

    private void clickRegistrationButton(){
        super.click(submitRegistrationButton);
    }


    // =========================
    // Business action
    // =========================

    public void registerNewAccount(String email,String password,String companyName, String domainName){
        enterEmail(email);
        enterPassword(password);
        enterCompanyName(companyName);
        enterCompanyDomainName(domainName);
        selectCheckbox();
        clickRegistrationButton();

    }





}
