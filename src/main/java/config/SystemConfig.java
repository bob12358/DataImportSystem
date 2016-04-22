package config;

import javax.sql.DataSource;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.json.IJsonFactory;
import com.jfinal.json.Json;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.druid.DruidPlugin;

import common.MyJson;
import controller.AdminController;
import controller.DatasetController;
import controller.LoginController;
import model.Student;
import model._MappingKit;

public class SystemConfig extends JFinalConfig {
	
	
	@Override
	public void configConstant(Constants me) {
		me.setEncoding("UTF-8");
		PropKit.use("config.txt");
		me.setDevMode(PropKit.getBoolean("devMode",false));
		
		me.setJsonFactory(new IJsonFactory() {
			
			@Override
			public Json getJson() {
				return new MyJson();
			}
		});
	}

	@Override
	public void configHandler(Handlers me) {
		
	}

	@Override
	public void configInterceptor(Interceptors me) {
		
	}
	
	public static DruidPlugin createDruidPlugin() {
		return new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
	}

	@Override
	public void configPlugin(Plugins me) {
		DruidPlugin druidPlugin = createDruidPlugin();
		DataSource dataSource = druidPlugin.getDataSource();
		me.add(druidPlugin);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
	    me.add(arp);
	    _MappingKit.mapping(arp);
	}

	@Override
	public void configRoute(Routes me) {
		me.add("login",LoginController.class,"/");
		me.add("admin",AdminController.class,"/admin");
		me.add("dataset",DatasetController.class,"/dataset");
	}

}
