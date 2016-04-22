package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.ICallback;
import com.jfinal.plugin.activerecord.Record;
import com.mysql.jdbc.CallableStatement;

import common.Constants;
import model.Dataset;
import model.DatasetColumn;

public class DatasetService {
    public static List<Dataset> getDataSetList() {
    	return Dataset.dao.find("select * from dataset");
    }
    
    
    public static int createDataset(List<DatasetColumn> columns,String datasetName){
    	Dataset dataset = new Dataset();
    	dataset.setCreatedTime(Integer.parseInt(String.valueOf(new Date().getTime()/1000)));
    	dataset.setName(datasetName);
    	dataset.save();
    	datasetName = Constants.DATASETPREFIX + datasetName;
    	String showTableSql = "show tables like '"+datasetName+"'";
    	List list = Db.query(showTableSql);
    	if(list.size()>0) {
    		return -1;
    	}
    	String sql = "create table "+ datasetName +"( id int(11) NOT NULL AUTO_INCREMENT ,";
    	for(int i = 0;i<columns.size();i++) {
    		 String name = columns.get(i).getName();
    		 String type = columns.get(i).getType();
    		 sql +=name+ getType(type);
    	 }
    	sql += "  PRIMARY KEY (`id`) "
    			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
    	System.out.println(sql);
    	Db.update(sql);
    	return 1;
    } 
    
    public static String getType(String type) {
    	String typeString = "";
    	switch(type) {
    	case "string": 
    		typeString = " varchar(255) DEFAULT NULL,";
    	    break;
    	case "int": 
    		typeString = " int(11)  DEFAULT NULL,";
    		break;
    	case "double": 
    		typeString = " double  DEFAULT NULL,";
    		break;
    	case "datetime": 
    		typeString = " datetime  DEFAULT NULL,";
    		break;
    	}
    	return typeString;
    }
    
    public static void addDataToDataset(List<Object> data ,String datasetName) {
    	List<String> columns = new ArrayList<String>(); 
    	List<String> sqls = new ArrayList<String>();
    	Dataset dataset = Dataset.dao.findFirst("select * from dataset where name=?",datasetName);
    	datasetName = Constants.DATASETPREFIX + datasetName;
    	if(dataset !=null) {
        	String sql = "desc "+ datasetName;
        	List<Record> list = Db.find(sql);
        	sql = "insert into "+ datasetName + "(";
        	for(int i = 1;i<list.size();i++) {
        	  Record record = list.get(i);
        	  sql += (String) record.get("Field")  +",";
        	}
        	//去掉最后一个,
        	sql = sql.substring(0,sql.length() - 1);
        	sql += ") values(";
        	
        	for(int i = 1;i<list.size();i++) {
        		sql += "'%s',";
        	}
        	sql = sql.substring(0,sql.length() - 1);
        	sql += ");";
        	for(int i = 0;i<data.size();i++) {
        		List<Object> objects = (List<Object>) data.get(i);
        		Object[] params = new Object[10];
        		for(int j =0;j<objects.size();j++) {
        			params[j] = objects.get(j);
        		}
        		String sql_new = String.format(sql, params);
        		sqls.add(sql_new);
        		
        	}
        	Db.batch(sqls, 5000);	
    	}
    	
    }
    
}
