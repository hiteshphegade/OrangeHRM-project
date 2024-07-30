package OrangeHRM.HRM;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class LoginPage {
	public WebDriver driver;
	@FindBy(xpath = "//*[@name=\"username\"]")
	public  WebElement userName;
	
	@FindBy(xpath = "//*[@name=\"password\"]")
	public WebElement password;
	
	@FindBy(xpath = "//*[@type=\"submit\"]")
	private WebElement loginButton;
	
	@FindBy(xpath = "//img[@class=\"oxd-userdropdown-img\"]")
	private WebElement profilePic;
	
	@FindBy(xpath = "(//a[@role=\"menuitem\"])[4]")
	private WebElement logOut;
	
	@FindBy(xpath = "//div[@role=\"alert\"]")
	public WebElement invalidCredText;
	
	@FindBy(xpath ="//*[@class=\"oxd-text oxd-text--p oxd-alert-content-text\"]")
	public WebElement invalidCredAlert;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void logout() {
		profilePic.click();
		logOut.click();
	}
	
	public void clickOnProfile() {
		profilePic.click();
	}
	
	public void clickOnLogoutButton() {
		logOut.click();
	}
}
