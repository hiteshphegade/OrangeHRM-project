package OrangeHRM.HRM;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Utilities.ExcelUtils;

public class LoginPtvTest2 {
	WebDriver driver;
	SoftAssert sa = new SoftAssert();
	@BeforeClass
	public void startUP() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait myWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().window().maximize();
		//SoftAssert sa = new SoftAssert();
	}
	
	

	
	@Test(dataProvider = "getData")
	public void loginDDT(String uname, String pwd) throws InterruptedException {
		LoginPage lp1 = new LoginPage(driver);
		lp1.userName.sendKeys(uname);
		lp1.password.sendKeys(pwd);
		lp1.password.sendKeys(Keys.ENTER);
		
		if(verifyInvalidMsg() == true) {
			System.out.println("test is passed");
			sa.assertTrue(true);
		} else {
			System.out.println("test falied");
			sa.assertTrue(false);
			lp1.clickOnProfile();
			Thread.sleep(500);
			lp1.clickOnLogoutButton();
			
		}
		sa.assertAll();
		
	}
	
	public boolean verifyInvalidMsg() {
		LoginPage lp1 = new LoginPage(driver);
		try {
			lp1.invalidCredAlert.isDisplayed();
			return true;
		}
		catch(Exception e) {
			
			return false;
		}
	}
	
	
	@DataProvider(name = "getData")
	String [][] getData() throws IOException{
		String path = System.getProperty("user.dir") + "\\src\\main\\java\\Utilities\\OrangeHRM Login Test.xlsx";
		
		int rownum = ExcelUtils.getRowCount(path, "InvalidCreds");
		int colCount = ExcelUtils.getCellCount(path, "InvalidCreds", 1);
		
		String loginData [][] = new String [rownum][colCount];
		for(int i=1; i<=rownum;i++) {
			for(int j=0; j<colCount; j++) {
				loginData[i-1][j] = ExcelUtils.getCellData(path, "InvalidCreds", i, j);
				
			}
		}
		return loginData;
		
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	




}