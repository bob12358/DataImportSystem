package controller;

import java.util.List;
import java.util.Map;

import com.jfinal.kit.HttpKit;
import com.jfinal.kit.JsonKit;

import common.BaseController;
import model.Dataset;
import model.DatasetColumn;
import service.DatasetService;

public class DatasetController extends BaseController {
    public void getDataset() {
    	List<Dataset> datasets = new DatasetService().getDataSetList();
    	if(datasets == null) {
    		String message = "数据集为空";
    		error(message);
    	}
    	success(datasets);
    	return;
    } 
    
    public void add(){
    	try {
    		String test = HttpKit.readData(getRequest());
    		String request = getPara("request");
	    	Map<String,String> map = JsonKit.parse(test,Map.class);
	    	for (String value : map.values()) {  
	    	    System.out.println("Value = " + value);  
	    	}
    	} catch(Exception exception) {
    		exception.printStackTrace();
    	}
    	  
    } 
}
