package functionalLibrary.Global;

import java.util.HashMap;
import org.openqa.selenium.support.ui.WebDriverWait;

//ManagerDriver is a Singleton class. i.e., we can create only one object/instance at any point of time.
// Use getManageDriver method to create the object to this class.
public class GlobalObjectsFactory {
	
	private static GlobalObjects globalObjects;
	private static HashMap<Long, GlobalObjects> threadGlobalObjMap = null;
		
	private GlobalObjectsFactory(){
		
	}
	
	//getGlobalObjects is a method to return new object in first call and returns existing object for subsequent calls, corresponds to current thread id.
	public static GlobalObjects getGlobalObjects(){
		long threadId = Thread.currentThread().getId();
		
		if (threadGlobalObjMap == null){
			threadGlobalObjMap = new HashMap<Long, GlobalObjects>();
			globalObjects = null;
		}
		else{
			globalObjects = threadGlobalObjMap.get(threadId);
		}
		
		if (globalObjects == null){
			globalObjects = new GlobalObjects();
		}
		
		threadGlobalObjMap.put(threadId, globalObjects);
		return globalObjects;
	}
	
}
