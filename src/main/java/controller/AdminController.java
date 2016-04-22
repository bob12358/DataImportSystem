package controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.kit.JsonKit;
import com.jfinal.kit.PropKit;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Config;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.DbKit;
import com.jfinal.plugin.druid.DruidPlugin;

import common.BaseController;
import interceptor.AdminInterceptor;
import model.Admin;
import model.BaseStudent;
import model.Dataset;
import model.Student;
import service.DatasetService;
import service.ExcelService;
import service.StudentService;

import java.io.Console;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.poi.hssf.extractor.ExcelExtractor;

public class AdminController extends BaseController {

	/**
	 * 管理后台 Created by chao on 16/3/22.
	 */

	public void index() {
		render("getdata.html");
	}
	
    @Before(AdminInterceptor.class)
	public void preview() {
		List<Dataset> datasets = DatasetService.getDataSetList();
		setAttr("datasets", datasets);
		renderFreeMarker("preview.html");
	}
    
    @Before(AdminInterceptor.class)
	public void export() {
		renderFreeMarker("export.html");
	}

    @Before(AdminInterceptor.class)
	public void importexcel() {
		renderFreeMarker("importexcel.html");
	}

	public void getdata() {
		List<Dataset> datasets = new DatasetService().getDataSetList();
		setAttr("datasets", datasets);
		renderFreeMarker("getdata.html");
	}
	

	public void login() {
		String username = this.getPara("username");
		String password = this.getPara("password");
		String message = "";
		Admin admin = null;
		if (StrKit.isBlank(username)) {
			message = "用户名不能为空！";
			error(message);
			return;
		}
		if (StrKit.isBlank(password)) {
			message = "密码不能为空！";
			error(message);
			return;
		}
		try {
			admin = (Admin) Admin.dao.findFirst("select id from admin where username=? and password =?", username,
					password);

			if (admin == null) {
				message = "用户名或密码错误！";
				error(message);
				return;
			} else {
				String adminJson = JsonKit.toJson(admin);
				setSessionAttr("admin",	 adminJson);
				success(admin);
				return;
			}
		} catch (Exception err) {
			err.printStackTrace();
		}
	}
	
	public  void logout() {
		getSessionAttr("admin");
		removeSessionAttr("admin");
		redirect("/login");
	}
	
	public void uploadExcel() {
		try {
			String uploadFilePath = getFile().getUploadPath()+"\\"+ getFile().getFileName();
//			String datasetId = getPara("datasetId");
//			System.out.println("sdsad:"+datasetId);
			List list = new ExcelService().readExcel(uploadFilePath);
			DatasetService.addDataToDataset(list,"dbclass");
		} catch (Exception e) {
			error("导入出错");
			e.printStackTrace();
		}
	}
	
	
	
}
