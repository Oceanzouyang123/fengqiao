package com.fengqiao.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fengqiao.entity.Orderbill;
import com.fengqiao.entity.Waybill;
import com.fengqiao.mapper.OrderbillDao;
import com.fengqiao.mapper.WaybillDao;
import com.fengqiao.util.ExcelUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 *  Author:zouyang
 * 	Data:2018-7-2
 *
 */


@Controller
public class fengqiaoController {

	ExcelUtil excelUtil = new ExcelUtil();
	
	//打印电子运单
	@Autowired
	WaybillDao waybillDao;
	
	//下订单
	@Autowired
	OrderbillDao orderbillDao;
	
	//打印首页
	@RequestMapping("printIndex")
	public String test(){
		System.out.println("进入打印首页...");
		return "index";
	}
	
	//下订单首页
	@RequestMapping("orderIndex")
	public String orderIndex(){
		System.out.println("进入下订单首页");
		return "orderService";
	}
	
	//下订单分页+条件查询
	@RequestMapping("orderListByPager")
	@ResponseBody
	public JSONArray orderListByPager(int currentPage){
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		Orderbill orderbill = new Orderbill();
		int pageSize = 15;//初始化值，每页显示几行数据，当前页为第一页
		int totalRows = orderbillDao.getOrderbillCountSqlwhere(orderbill);
		int countPage = (totalRows + pageSize-1)/pageSize;
		int rowStart = (currentPage - 1) * pageSize;
		orderbill.setPageSize(pageSize);
		orderbill.setRowStart(rowStart);
		List<Orderbill> orderbillList = orderbillDao.getOrderbillPagerContent(orderbill);
		jsonObject.put("billcountPage", countPage);
		jsonObject.put("billcurrentPage", currentPage);
		jsonObject.put("billNum",totalRows);
		jsonObject.put("data", orderbillList);		
		jsonArray.add(jsonObject);
		return jsonArray;
	}

	//分页+条件查询电子运单
	@RequestMapping("queryWaybillList")
	@ResponseBody
	public synchronized JSONArray billList(int currentPage){
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		Waybill waybill = new Waybill();
		int pageSize = 15;//初始化值，每页显示几行数据，当前页为第一页
		//自定义算法分页
		int totalRows = waybillDao.getCountSqlwhere(waybill);
		int countPage = (totalRows + pageSize-1)/pageSize;
		int rowStart = (currentPage - 1) * pageSize;
		waybill.setPageSize(pageSize);
		waybill.setRowStart(rowStart);
		List<Waybill> waybillList = waybillDao.getWaybillPagerContent(waybill);
		jsonObject.put("countPage", countPage);
		jsonObject.put("currentPage", currentPage);
		jsonObject.put("Num",totalRows);
		jsonObject.put("data", waybillList);	
		jsonArray.add(jsonObject);
		return jsonArray;
	}
}
