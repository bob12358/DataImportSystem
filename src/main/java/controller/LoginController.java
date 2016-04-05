package controller;

import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;

import common.BaseController;
import model.Admin;

public class LoginController extends BaseController {
	public void index(){
		render("/login.html");
	} 
}
