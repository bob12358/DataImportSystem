package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.ICallback;
import com.mysql.jdbc.CallableStatement;

import model.Dataset;
import model.DatasetColumn;

public class DatasetService {
    public List<Dataset> getDataSetList() {
    	return Dataset.dao.find("select * from dataset");
    }
    
    
    public static void createDataset(List<DatasetColumn> columns,String datasetName){
    	Dataset dataset = new Dataset();
    	dataset.setCreatedTime(Integer.parseInt(String.valueOf(new Date().getTime()/1000)));
    	dataset.setName(datasetName);
    	dataset.save();
    	Integer datasetId = dataset.findFirst("select * from dataset where name = ?", datasetName).getId();
    	String tableName = "tbl_"+datasetId;
    	String sql = "create table "+ tableName +"( id int(11) NOT NULL AUTO_INCREMENT ,";
    	System.out.println("das:"+columns.size());
    	for(int i = 0;i<columns.size();i++) {
    		System.out.println("dsadsa");
    		 String name = columns.get(i).getName();
    		 String type = columns.get(i).getType();
    		 sql +=name+ getType(type);
    	 }
    	sql += "  PRIMARY KEY (`id`) "
    			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
    	System.out.println(sql);
    	Db.update(sql);
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
    
}
