package com.fzb.blog.controlle;

import java.util.Map;
import java.util.Map.Entry;

import com.fzb.blog.model.LogNav;
import com.fzb.blog.model.WebSite;

public class WebSiteControl extends ManageControl
{
	public void update(){
		Map<String,String[]> tparamMap=getParaMap();
		for (Entry<String, String[]>  param: tparamMap.entrySet()) {
			new WebSite().updateByKV(param.getKey(),param.getValue()[0]);
		}
		getData().put("success", true);
		renderJson(getData());
		// 更新缓存数据
		BaseControl.refreshCache();
	}

	@Override
	public void add() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void queryAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}
}

