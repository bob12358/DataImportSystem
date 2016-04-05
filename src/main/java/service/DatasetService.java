package service;

import java.util.List;

import model.Dataset;

public class DatasetService {
    public List<Dataset> getDataSetList() {
    	return Dataset.dao.find("select * from dataset");
    }
}
