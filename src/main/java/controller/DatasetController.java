package controller;

import java.util.List;

import com.sun.mail.handlers.message_rfc822;

import common.BaseController;
import model.Dataset;
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
}
