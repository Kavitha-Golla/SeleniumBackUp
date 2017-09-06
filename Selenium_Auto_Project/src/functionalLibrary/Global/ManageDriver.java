package functionalLibrary.Global;

import java.util.HashMap;

//import org.apache.log4j.Logger;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

//ManagerDriver is a Singleton class. i.e., we can create only one object/instance at any point of time.
// Use getManageDriver method to create the object to this class.
public class ManageDriver {
	static ManageDriver manageDriver;
	//static Logger log = Logger.getLogger(ManageDriver.class);
	private WebDriver driver;
	public WebDriverWait wait ; 
	private HashMap<Long, WebDriver> threadDriverMap = null;
		
	//getManageDriver is a factory method and returns new object in first call and returns existing object for subsequent calls.
	public static ManageDriver getManageDriver(){
		if (manageDriver == null)
			manageDriver = new ManageDriver();
		
		return manageDriver;
	}
	
//###################################################################################################################################################################  
//Function name		: initializeDriver(String strBrowser)
//Class name		: ManageDriver
//Description 		: Generic function to initialize browser
//Parameters 		: strBrowser: browser name (chrome / firefox / ie)
//Assumption		: None
//Developer			: Kavitha Golla
//###################################################################################################################################################################			
	public void initializeDriver(String strBrowser){		
		long id = Thread.currentThread().getId();
		driver = null;		
		if (threadDriverMap != null)
			driver = threadDriverMap.get(id);
		else
			threadDriverMap = new HashMap<Long, WebDriver>();		
		if (driver == null) {			
			switch (strBrowser.toLowerCase()) {
			case "chrome" : 
				System.setProperty("webdriver.chrome.driver", "C://SVN_Automation_New//Trunk//Selenium_Automation//Selenium_Auto_Project//BrowserDrivers//chromedriver.exe");
				driver = new ChromeDriver();
				break;
			case "firefox" : 				
				driver = new FirefoxDriver();
				break;
			case "ie":
				System.setProperty("webdriver.ie.driver", "C://SVN_Automation_New//Trunk//Selenium_Automation//Selenium_Auto_Project//BrowserDrivers//IEDriverServer_64bit.exe" );
				driver = new InternetExplorerDriver();
				break;
			default: System.out.println("Incorrect browser type, default browser is IE");	
			System.setProperty("webdriver.ie.driver", "C://SVN_Automation_New//Trunk//Selenium_Automation//Selenium_Auto_Project//BrowserDrivers//IEDriverServer_64bit.exe" );
			driver = new InternetExplorerDriver();

			}
			
			threadDriverMap.put(id, driver);
		}
	}
//###################################################################################################################################################################  
//Function name		: initializeDriver(String strBrowser,String strSetProxy)
//Class name		: ManageDriver
//Description 		: Generic function to initialize browser based on proxy requirement(This is a method overloading) used for Maxit
//Parameters 		: strBrowser: browser name (chrome / firefox / ie)
//Assumption		: None
//Developer			: Kavitha Golla
//###################################################################################################################################################################			
public void initializeDriver(String strBrowser,String strSetProxy){		
	long id = Thread.currentThread().getId();
	DesiredCapabilities cap;
	
	if(strSetProxy.equalsIgnoreCase("setProxy_ON")){
		cap = setProxy_ON();
	}
	else{
		cap = setProxy_OFF();
	}
	if (threadDriverMap != null)
		driver = threadDriverMap.get(id);
	else
		threadDriverMap = new HashMap<Long, WebDriver>();		
	if (driver == null) {
		//DesiredCapabilities cap = setProxy_ON();
		switch (strBrowser.toLowerCase()) {
		case "chrome" : 
			System.setProperty("webdriver.chrome.driver", "C://SVN_Automation_New//Trunk//Selenium_Automation//Selenium_Auto_Project//BrowserDrivers//chromedriver.exe");
			driver = new ChromeDriver(cap);
			break;
		case "firefox" : 							
			driver = new FirefoxDriver(cap);
			break;
		case "ie":
			System.setProperty("webdriver.ie.driver", "C://SVN_Automation_New//Trunk//Selenium_Automation//Selenium_Auto_Project//BrowserDrivers//IEDriverServer_64bit.exe" );
			driver = new InternetExplorerDriver(cap);
			break;
		default: System.out.println("Incorrect browser type, default browser is IE");	
		System.setProperty("webdriver.ie.driver", "C://SVN_Automation_New//Trunk//Selenium_Automation//Selenium_Auto_Project//BrowserDrivers//IEDriverServer_64bit.exe" );
		driver = new InternetExplorerDriver(cap);

		}
		
		threadDriverMap.put(id, driver);
	}
}
	
//###################################################################################################################################################################  
//Function name		: getDriver()
//Class name		: ManageDriver
//Description 		: Generic function to return the driver object based on Thread id hash map
//Assumption		: None
//Developer			: Kavitha Golla
//###################################################################################################################################################################				
	public WebDriver getDriver(){
		long id = Thread.currentThread().getId();
		driver = threadDriverMap.get(id);
		return driver;
	}

//###################################################################################################################################################################  
//Function name		: quit()
//Class name		: ManageDriver
//Description 		: Generic function to quit the driver based on thread id hash map.
//Assumption		: None
//Developer			: Kavitha Golla
//###################################################################################################################################################################				
	public void quit(){
		long id = Thread.currentThread().getId();
		System.out.println("HashMap Values in thread:"+threadDriverMap);
		driver = threadDriverMap.get(id);
		driver.quit();
		threadDriverMap.remove(id);
	}

//###################################################################################################################################################################  
//Function name		: setProxy_ON()
//Class name		: ManageDriver
//Description 		: Generic function to set the proxy ON(mainly used for Maxit Endeavour clients) to set the proxy to akira
//Assumption		: None
//Developer			: Kavitha Golla
//###################################################################################################################################################################	
	public DesiredCapabilities setProxy_ON(){
		
		Proxy prox = new Proxy();
		prox.setProxyType(ProxyType.PAC);
		prox.setProxyAutoconfigUrl("C:\\SVN_Automation_New\\Trunk\\Selenium_Automation\\Selenium_Auto_Project\\BrowserDrivers\\ProxyConfig_Proxy.txt");
		//prox.setNoProxy("");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.PROXY, prox);
		System.out.println("Turning the Proxy ON");
		//log.info("Turning the Proxy ON");
		return cap;
	}
	
//###################################################################################################################################################################  
//Function name		: setProxy_OFF()
//Class name		: ManageDriver
//Description 		: Generic function to set the proxy OFF(mainly used for Maxit non-Endeavour clients) to set the proxy to local host.
//Assumption		: None
//Developer			: Kavitha Golla
//###################################################################################################################################################################	
	public DesiredCapabilities setProxy_OFF(){
		Proxy prox = new Proxy();
		prox.setProxyType(ProxyType.PAC);
		prox.setProxyAutoconfigUrl("C:\\SVN_Automation_New\\Trunk\\Selenium_Automation\\Selenium_Auto_Project\\BrowserDrivers\\ProxyConfig_Direct.txt");
		//prox.setNoProxy("");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.PROXY, prox);
		System.out.println("Turning the Proxy OFF");
		//log.info("Turning the Proxy OFF");
		return cap;
	}
	
//############################ ***** End of Methods for class "ManageDriver" ******###############################################
}//End of "ManageDriver" Class	
//############################ ********************** END **********************###############################################
