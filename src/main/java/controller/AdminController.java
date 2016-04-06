package controller;

import com.jfinal.aop.Clear;
import com.jfinal.kit.StrKit;

import common.BaseController;
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
import java.util.List;

import org.apache.poi.hssf.extractor.ExcelExtractor;

public class AdminController extends BaseController {

	/**
	 * 管理后台 Created by chao on 16/3/22.
	 */

	public void index() {
		render("getdata.html");
	}

	public void getdata() {
		renderFreeMarker("getdata.html");
	}

	public void importexcel() {
		renderFreeMarker("importexcel.html");
	}

	public void preview() {
		List<Dataset> datasets = new DatasetService().getDataSetList();
		setAttr("datasets", datasets);
		renderFreeMarker("preview.html");
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
			System.out.println(admin);

			System.out.println("admin:" + admin);
			if (admin == null) {
				message = "用户名或密码错误！";
				error(message);
				return;
			} else {
				//setSessionAttr(admin.getStr("id"), admin);
				//System.out.println(((Admin) getSessionAttr("1")).getInt("id"));
				success(admin);
				return;
			}
		} catch (Exception err) {
			err.printStackTrace();
		}
	}
	
	public void uploadExcel() {
		
		String uploadFilePath = getFile().getUploadPath();
		try {
			List<BaseStudent> students = new ExcelService().readExcel(uploadFilePath);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
