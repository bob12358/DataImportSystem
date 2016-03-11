package config;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;

import controller.HelloController;

public class DemoConfig extends JFinalConfig {
	
	
	@Override
	public void configConstant(Constants me) {
		me.setDevMode(true);
	}

	@Override
	public void configHandler(Handlers me) {
		
	}

	@Override
	public void configInterceptor(Interceptors me) {
		
	}

	@Override
	public void configPlugin(Plugins me) {
		DruidPlugin druidPlugin = new DruidPlugin("jdbc:mysql://localhost:3306/school", "root", "root");
		me.add(druidPlugin);
		ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin(druidPlugin);
	    me.add(activeRecordPlugin);
	}

	@Override
	public void configRoute(Routes me) {
		me.add("hello",HelloController.class);
	}

}
