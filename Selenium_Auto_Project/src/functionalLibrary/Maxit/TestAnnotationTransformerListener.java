package functionalLibrary.Maxit;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;




public class TestAnnotationTransformerListener implements IAnnotationTransformer {
	String strParamPages;
	@Override
	public void transform(ITestAnnotation annotation, Class testClass,Constructor testConstructor, Method testMethod){
		//ITestContext contxt;
		//String strParamPages = contxt.getCurrentXmlTest().getParameter("DataVal_Pages");
		//strParamPages = "Ledger;Realized;Unrealized";
		System.out.println("strParamPages:"+strParamPages);
			System.out.println("Within Listener,setting enabled falg to Method::"+testMethod.getName());
			if(!testMethod.getName().equalsIgnoreCase("PreReq_CheckPoint")){
				if(!strParamPages.contains(testMethod.getName())){			
					System.out.println("Disable " + testMethod.getName());
					annotation.setEnabled(false);
				}
				
			}

	}//End of transform method
	
//	@Parameters({"DataVal_Pages"});
//	public void readITestContext(String strSuiteParam){
//		strParamPages = strSuiteParam;
//	}
	
	
}
