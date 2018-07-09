package com.fengqiao.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fengqiao.entity.Orderbill;
import com.fengqiao.entity.Waybill;
import com.fengqiao.mapper.OrderbillDao;
import com.fengqiao.util.ExcelUtil;
import com.fengqiao.util.OrderService;
import com.fengqiao.util.Util;
import com.fengqiao.util.XMLDone;

/**
 * 
 *  Author:zouyang
 * 	Data:2018-7-2
 *
 */

@Controller
public class UtilController {
	
	ExcelUtil excelUtil = new ExcelUtil();//解析excel文件

	OrderService orderService = new OrderService();//订单接口类
	
	XMLDone xmlDone = new XMLDone();
	
	@Autowired
	OrderbillDao orderbillDao;
	
	//打印
	@RequestMapping("print")
	@ResponseBody()
	public String print() throws Exception{
		System.out.println("print...");
		Util util = new Util();
		util.WayBillPrinterTools();
		return "index";
	}
	
	//导入下订单数据
	@RequestMapping("orderbatch")
	public String orderbatch(ModelMap modelMap,HttpServletRequest request, HttpServletResponse response, @RequestParam("file") MultipartFile[] files) throws UnsupportedEncodingException, DocumentException{
		System.out.println("下载下订单数据");
		MultipartFile file;
		for (int i = 0; i < files.length; i++) {
			System.out.println(files.length+"==多文件名"+files[i].getOriginalFilename());
			file = files[i];
			String path = "";
			String os = System.getProperty("os.name");  
			if(os.toLowerCase().startsWith("win")){  
				path = "D:/orderUploadFile";
				System.out.println("当前操作系统为: "+os);  
			} else{
				path = "/home/sfadmin/uploadFile";
				System.out.println("当前操作系统为: "+os);
			}
			path = path+"/"+file.getOriginalFilename();
			try {
				excelUtil.upload(path,file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("文件上传完毕");
			try {
				Thread.sleep(1000);
				System.out.println("waiting for upload excel 1sec...");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//获取下订单信息和返回信息
			String xmlOrder = orderService.order(path);
			//System.out.println("xmlOrder=="+xmlOrder);
			String[] xmlOrderSplit = xmlOrder.split("ψ");
			String orderService = xmlOrderSplit[0];//下订单信息
			String returnOrderService = xmlOrderSplit[1];//返回信息
			//解析xml格式			
			Orderbill orderbill = xmlDone.OrderServiceXML(orderService);
			if(returnOrderService.contains("ERROR")){
				modelMap.addAttribute("error", "");
			}else{
				//插入数据库
				orderbillDao.addOrderService(orderbill);
			}
		}		
		return "redirect:/orderIndex";
	}
	
	//
	@RequestMapping("batch")
	public String batch(HttpServletRequest request, HttpServletResponse response, @RequestParam("file") MultipartFile[] files){
		MultipartFile file;
		for (int i = 0; i < files.length; i++) {
			System.out.println(files.length+"==多文件名"+files[i].getOriginalFilename());
			file = files[i];
			String path = "";
			String os = System.getProperty("os.name");  
			if(os.toLowerCase().startsWith("win")){  
				path = "D:/printUploadFile";
				System.out.println("当前操作系统为: "+os);  
			} else{
				path = "/home/sfadmin/uploadFile";
				System.out.println("当前操作系统为: "+os);
			}
			path = path+"/"+file.getOriginalFilename();
			try {
				excelUtil.upload(path,file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("文件上传完毕");
			try {
				Thread.sleep(1000);
				System.out.println("waiting for upload excel 1sec...");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			File getFile = new File(path);
			//读取excel表格
			ArrayList<ArrayList<Object>> result = ExcelUtil.readExcel(getFile);
			ArrayList<Waybill> waybillList = new ArrayList<Waybill>();
			 for(int h=0 ;h<result.size();h++){//行
				 for(int l=0;l<result.get(h).size();l++){//列
					 System.out.println(result.get(h).get(l).toString());
					 //插入数据库
					 Waybill waybill = new Waybill();
					 waybill.setAppId(result.get(h).get(0).toString());
					 waybill.setAppKey(result.get(h).get(1).toString());
					 waybillList.add(waybill);
					// waybillDao.insertBatch(waybillList);
				 }
			 }
		}
		return "index";  
	}
	
}
