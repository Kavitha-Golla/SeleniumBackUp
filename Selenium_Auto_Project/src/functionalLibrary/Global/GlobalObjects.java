package functionalLibrary.Global;

import org.testng.ITestContext;

public class GlobalObjects {
	
	private ITestContext iTestContext;
	
	public ITestContext getiTestContext() {
		return iTestContext;
	}

	public void setiTestContext(ITestContext iTestContext) {
		this.iTestContext = iTestContext;
	}
	
}
