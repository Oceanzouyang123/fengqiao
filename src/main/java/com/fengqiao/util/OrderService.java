package com.fengqiao.util;

import java.io.FileInputStream;
import java.io.InputStream;

import com.sf.csim.express.service.CallExpressServiceTools;

public class OrderService {

	public synchronized String order(String path){
		String reqXml = "";
		try{
			@SuppressWarnings("resource")
			InputStream is = new FileInputStream(path);//下订单
			//InputStream is = new FileInputStream("2.order_query.txt");//查订单
			//InputStream is = new FileInputStream("3.order_confirm.txt");//订单取消		
			//InputStream is = new FileInputStream("4.order_filter.txt");//订单筛选		  
			//InputStream is = new FileInputStream("5.route_queryByMailNo.txt");//路由查询-通过运单号
			//InputStream is = new FileInputStream("5.route_queryByOrderNo.txt");//路由查询-通过订单号 
			//InputStream is = new FileInputStream("7.orderZD.txt");  //子单号申请
		    byte[] bs = new byte[is.available()];
	  		is.read(bs);
	  		reqXml = new String(bs);
		}catch(Exception e){
			
		}		
		//丰桥平台公共测试账号  
		//SLKJ2019
		//FBIqMkZjzxbsZgo7jTpeq7PD8CVzLT4Q
	    String reqURL="https://bsp-oisp.sf-express.com/bsp-oisp/sfexpressService";
		String clientCode="5192010122";//此处替换为您在丰桥平台获取的顾客编码
        String checkword="X6pT2X5OOeuXk84zKPig3nsjh6CKIYwp";//此处替换为您在丰桥平台获取的校验码
        CallExpressServiceTools client=CallExpressServiceTools.getInstance();     
        String myReqXML=reqXml.replace("SLKJ2019",clientCode);
        System.out.println("请求报文："+myReqXML);
        String respXml= client.callSfExpressServiceByCSIM(reqURL, myReqXML, clientCode, checkword);
      
		if (respXml != null) {
			System.out.println("--------------------------------------");
            System.out.println("返回报文: "+ respXml);
            System.out.println("--------------------------------------");
        }		
		return myReqXML+"ψ"+respXml;
	}
	
	/*********************标准返回报文参考*****************************/
	//1.下订单-请求返回结果
	// <?xml version='1.0' encoding='UTF-8'?><Response service="OrderService"><Head>OK</Head><Body><OrderResponse filter_result="2" destcode="020" mailno="444000010085" origincode="755" orderid="QIAO-20171017001"/></Body></Response>

   		
	//2.订单结果查询 -请求返回结果
	//<?xml version='1.0' encoding='UTF-8'?><Response service="OrderSearchService"><Head>OK</Head><Body><OrderResponse filter_result="2" destcode="020" mailno="444000010085,819000008006,819000008015" origincode="755" orderid="QIAO-20171017001"/></Body></Response>

	
	//3.订单取消-请求返回结果
	// <?xml version='1.0' encoding='UTF-8'?><Response service="OrderConfirmService"><Head>OK</Head><Body><OrderConfirmResponse res_status="2" orderid="QIAO-20171017001"/></Body></Response>
	
	//4.订单筛选-请求返回结果
	// <?xml version='1.0' encoding='UTF-8'?><Response service="OrderFilterService"><Head>OK</Head><Body><OrderFilterResponse filter_result="1" orderid="QIAO-20171017001"/></Body></Response>

	//5.路由查询-请求返回结果
	// <?xml version='1.0' encoding='UTF-8'?><Response service="RouteService"><Head>OK</Head><Body><RouteResponse mailno="444000010085" orderid="QIAO-20171017001"><Route remark="已经收件" accept_time="2017-08-31 02:01:44" accept_address="广东省深圳市软件产业基地" opcode="50"/><Route remark="已经收件" accept_time="2017-08-31 02:01:44" accept_address="广东省深圳市软件产业基地" opcode="80"/></RouteResponse></Body></Response>
	
	//6.路由推送
	
	//7.子单号申请-请求返回结果
	//<?xml version='1.0' encoding='UTF-8'?><Response service="OrderZDService"><Head>OK</Head><Body><OrderZDResponse><OrderZDResponse main_mailno="444000010085" mailno_zd="819000008006,819000008015" orderid="QIAO-20171017001"/></OrderZDResponse></Body></Response>


}
