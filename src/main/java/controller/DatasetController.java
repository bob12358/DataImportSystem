package controller;

import java.io.File;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.VariableElement;
import javax.sql.DataSource;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.jfinal.kit.HttpKit;
import com.jfinal.kit.JsonKit;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.Config;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.DbKit;
import com.jfinal.plugin.activerecord.Record;

import common.BaseController;
import common.Constants;
import model.Dataset;
import model.DatasetColumn;
import service.DatasetService;

public class DatasetController extends BaseController {
	public void getDataset() {
		List<Dataset> datasets = new DatasetService().getDataSetList();
		if (datasets == null) {
			String message = "数据集为空";
			error(message);
		}
		success(datasets);
		return;
	}

	@ActionKey("/dataset")
	public void getDatasetById() {
		Integer id = getParaToInt(0);
		Dataset dataset = Dataset.dao.findFirst("select * from dataset where id=?", id);
		if (dataset != null) {
			String tblName = Constants.DATASETPREFIX + dataset.getName();
			String sql = "select * from " + tblName;
			List<Record> data = Db.find(sql);
			List<Object> objects = new ArrayList<Object>();

			sql = "desc " + tblName;
			List<String> columns = new ArrayList<String>();
			List<Record> list = Db.find(sql);
			for (int i = 0; i < list.size(); i++) {
				Record record = list.get(i);
				columns.add(record.getStr("Field"));
			}

			for (int i = 0; i < data.size(); i++) {
				List<Object> dataList = new ArrayList<Object>();
				for (String field : columns) {
					dataList.add(data.get(i).get(field));
				}
				objects.add(dataList);
			}
			setAttr("columns", columns);
			setAttr("datasets", objects);
			setAttr("dataset", dataset);
			renderFreeMarker("/admin/getdata.html");
		}
	}

	public void add() {
		try {
			String str_columns = getPara("columns");
			String datasetName = getPara("datasetName");
			// String str_datasets =
			// "{'dataset':[{'name':'2312312','type':'字符串'}]}";
			JSONObject obj = (JSONObject) JSONObject.parse(str_columns);
			String str_json_columns = obj.getJSONArray("column").toJSONString();
			List columns = JSON.parseArray(str_json_columns, DatasetColumn.class);
			int result = DatasetService.createDataset(columns, datasetName);
			if(result > 0) {
				success("添加数据集成功！");
			} else {
				error("已存在相同数据集！");
			}
			
		} catch (Exception exception) {
			error("添加数据集错误！");
			exception.printStackTrace();
		}
	}

	public void importExcel() {

	}

	public void download() throws Exception {
		
		String url = "jdbc:mysql://"+getPara("server_url")+"?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull";
		String username = getPara("server_username");
		String password = getPara("server_pwd");
		String tableName = getPara("server_tableName");
		String filepath = PathKit.getWebRootPath() + "/download/" + System.currentTimeMillis() + ".xlsx";
		String sqlpath = "\"" + filepath + "\"";
		sqlpath = sqlpath.replaceAll("\\\\", "/");
		Map<String, String> map = new HashMap();
		map.put("url", url);
		map.put("username", username);
		map.put("password", password);
		try {
			DataSource dataSource = DruidDataSourceFactory.createDataSource(map);
			String configName = "test";
			DbKit.removeConfig(configName);
			
			Config config = new Config(configName, dataSource);
			DbKit.addConfig(config);
			String sql = "select * from " + tableName + " into outfile " + sqlpath;
			Db.use(configName).query(sql);
			success("获取Excel数据成功！");
			renderFile(new File(filepath));
		} catch (Exception ex) {
			ex.printStackTrace();
			error("导出出现异常!");
		}

	}
}
