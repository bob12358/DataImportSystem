package common;


import org.apache.log4j.Logger;
import com.jfinal.core.Controller;


public class BaseController extends Controller {
	private static Logger log = Logger.getLogger(BaseController.class);
	
	public void success(Object object) {
		renderJson(new Result(Constants.CODE_SUCCESS, Constants.DESC_SUCCESS,object));
	}
	
	public void error (String message){
		renderJson(new Result(Constants.CODE_FAILURE,message, null));
	} 
}
