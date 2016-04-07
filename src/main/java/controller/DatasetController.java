package controller;

import java.util.List;
import java.util.Map;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
    		String str_columns = getPara("columns");
    		String datasetName = getPara("datasetName");
    		System.out.println("cplumns:"+ str_columns);
    		//String str_datasets = "{'dataset':[{'name':'2312312','type':'字符串'}]}";
    		JSONObject obj = (JSONObject) JSONObject.parse(str_columns);
    		System.out.println(str_columns);
    		String str_json_columns = obj.getJSONArray("column").toJSONString();
    	    List columns = JSON.parseArray(str_json_columns, DatasetColumn.class);
    	    DatasetService.createDataset(columns,datasetName);
    	} catch(Exception exception) {
    		exception.printStackTrace();
    	}
    	  
    } 
}
