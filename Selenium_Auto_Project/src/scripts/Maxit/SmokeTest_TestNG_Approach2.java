package scripts.Maxit;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageObjects.Maxit.SearchPage_NotUsing;
import functionalLibrary.Global.ManageDriver;
import functionalLibrary.Maxit.Login;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;



public class SmokeTest_TestNG_Approach2 {
	WebDriver driver;
	boolean strRetStatus;

@BeforeTest
public void beforeTest(ITestContext context ) throws Exception {
	System.out.println("This is @BeforeTest annotation");
	Login browser = new Login("firefox"); //Create an object for Class "Login"
	strRetStatus = browser.fnLogin("QA","firefox", "STR",context);
	driver = ManageDriver.getManageDriver().getDriver();
}


@Test
  public void TestFlow() {
	System.out.println("This is @Test annotation");
	
	//Navigate to page
	strRetStatus = SearchPage_NotUsing.VerifySearchLinkExists(driver);
	if(strRetStatus){
		SearchPage_NotUsing.ClickSearchLink(driver);
		SearchPage_NotUsing.VerifyViewList(driver);
	}
	//Search
	//validate results
	
	if(strRetStatus){
		
	}
		
	
  }

@AfterTest
public void afterTest() {
	System.out.println("This is @AfterTest");
}

}//End of <Class: SmokeTest_TestNG>

