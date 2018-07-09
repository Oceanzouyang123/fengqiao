package com.fengqiao.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sf.dto.CargoInfoDto;
import com.sf.dto.WaybillDto;
import com.sf.util.Base64ImageTools;
import com.sf.util.MyJsonUtil;

public class Util {

	//打印电子运单
	public String WayBillPrinterTools() throws Exception{
		
		/*********2联单**************/
		/**
		 * 调用打印机 不弹出窗口 适用于批量打印【二联单】
		 */
		String url1 = "http://localhost:4040/sf/waybill/print?type=V2.0_poster_100mm150mm&output=noAlertPrint";
		/**
		 * 调用打印机 弹出窗口 可选择份数 适用于单张打印【二联单】
		 */
		String url2 = "http://localhost:4040/sf/waybill/print?type=V2.0_poster_100mm150mm&output=print";

		/**
		 * 直接输出图片的BASE64编码字符串 可以使用html标签直接转换成图片【二联单】
		 */
		String url3 = "http://localhost:4040/sf/waybill/print?type=V2.0_poster_100mm150mm&output=image";
        
		
		/*********3联单**************/
		/**
		 * 调用打印机 不弹出窗口 适用于批量打印【三联单】
		 */
		String url4 = "http://localhost:4040/sf/waybill/print?type=V3.0_poster_100mm210mm&output=noAlertPrint";
		/**
		 * 调用打印机 弹出窗口 可选择份数 适用于单张打印【三联单】
		 */
		String url5 = "http://localhost:4040/sf/waybill/print?type=V3.0_poster_100mm210mm&output=print";

		/**
		 * 直接输出图片的BASE64编码字符串 可以使用html标签直接转换成图片【三联单】
		 */
		String url6 = "http://localhost:4040/sf/waybill/print?type=V3.0_poster_100mm210mm&output=image";
        
		
		/*********2联150 丰密运单**************/
		/**
		 * 调用打印机 不弹出窗口 适用于批量打印【二联单】
		 */
		String url7 = "http://localhost:4040/sf/waybill/print?type=V2.0.FM_poster_100mm150mm&output=noAlertPrint";
		/**
		 * 调用打印机 弹出窗口 可选择份数 适用于单张打印【二联单】
		 */
		String url8 = "http://localhost:4040/sf/waybill/print?type=V2.0.FM_poster_100mm150mm&output=print";

		/**
		 * 直接输出图片的BASE64编码字符串 可以使用html标签直接转换成图片【二联单】
		 */
		String url9 = "http://localhost:4040/sf/waybill/print?type=V2.0.FM_poster_100mm150mm&output=image";
		
		
		/*********3联210 丰密运单**************/
		/**
		 * 调用打印机 不弹出窗口 适用于批量打印【三联单】
		 */
		String url10 = "http://localhost:4040/sf/waybill/print?type=V3.0.FM_poster_100mm210mm&output=noAlertPrint";
		/**
		 * 调用打印机 弹出窗口 可选择份数 适用于单张打印【三联单】
		 */
		String url11 = "http://localhost:4040/sf/waybill/print?type=V3.0.FM_poster_100mm210mm&output=print";

		/**
		 * 直接输出图片的BASE64编码字符串 可以使用html标签直接转换成图片【三联单】
		 */
		String url12 = "http://localhost:4040/sf/waybill/print?type=V3.0.FM_poster_100mm210mm&output=image";
       
		
		//根据业务需求确定请求地址
		String reqURL=url2;
		
		//电子面单顶部是否需要logo
		boolean topLogo=false;//true 需要logo  false 不需要logo
		if(reqURL.contains("V2.0")&&topLogo){
			reqURL=reqURL.replace("V2.0", "V2.1");
		}
		
		if(reqURL.contains("V3.0")&&topLogo){
			reqURL=reqURL.replace("V3.0", "V3.1");
		}
	    
		System.out.println(reqURL);
		
        /**注意 需要使用对应业务场景的url  **/
		URL myURL = new URL(reqURL);
	     
		//其中127.0.0.1:4040为打印服务部署的地址（端口如未指定，默认为4040），
		//type为模板类型（支持两联、三联，尺寸为100mm*150mm和100mm*210mm，type为poster_100mm150mm和poster_100mm210mm）
		//A5 poster_100mm150mm   A5 poster_100mm210mm
		//output为输出类型,值为print或image，如不传，
		//默认为print（print 表示直接打印，image表示获取图片的BASE64编码字符串）
		//V2.0/V3.0模板顶部是带logo的  V2.1/V3.1顶部不带logo
		
		HttpURLConnection httpConn = (HttpURLConnection) myURL.openConnection();
		httpConn.setDoOutput(true);
		httpConn.setDoInput(true);
		httpConn.setUseCaches(false);
		httpConn.setRequestMethod("POST");
		httpConn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
		//httpConn.setRequestProperty("Content-type","application/x-www-form-urlencoded;charset=UTF-8");
		httpConn.setConnectTimeout(5000);
		httpConn.setReadTimeout(2*5000);
		
		List<WaybillDto> waybillDtoList = new ArrayList<>();
		WaybillDto dto = new WaybillDto();
				
	    //这个必填 
		dto.setAppId("5192010122");//对应clientCode
        dto.setAppKey("X6pT2X5OOeuXk84zKPig3nsjh6CKIYwp");//对应checkWord
	    
        dto.setMailNo("755123456789");
        //dto.setMailNo("755123456788,001000000002");//子母单方式
				
		//收件人信息  
        String province = "江苏省";
        //province = unicode(province);
        //URLEncoder.encode(str, "utf-8");
		dto.setConsignerProvince(province);
		dto.setConsignerCity("常州市");
		dto.setConsignerCounty("新北区");
		dto.setConsignerAddress("通江中路100号-101室"); //详细地址建议最多30个字  字段过长影响打印效果
		dto.setConsignerCompany("寄件公司");
		dto.setConsignerMobile("15893799999");
		dto.setConsignerName("XXX");
		dto.setConsignerShipperCode("518052");
		dto.setConsignerTel("0755-33123456");
				
		//寄件人信息
		dto.setDeliverProvince("浙江省");
		dto.setDeliverCity("杭州");
		dto.setDeliverCounty("西湖区");
		dto.setDeliverCompany("阿里巴巴");
		dto.setDeliverAddress("阿里巴巴商务楼");//详细地址建议最多30个字  字段过长影响打印效果
		dto.setDeliverName("马云公司");
		dto.setDeliverMobile("15881234567");
		dto.setDeliverShipperCode("310000");
		dto.setDeliverTel("0571-26508888");
				
		dto.setDestCode("755");//目的地代码 参考顺丰地区编号
		dto.setZipCode("571");//原寄地代码 参考顺丰地区编号
		
		//签回单号  签单返回服务 会打印两份快单 其中第二份作为返寄的单
		//如客户使用签单返还业务则需打印“POD”字段，用以提醒收派员此件为签单返还快件。	
		// dto.setReturnTrackingNo("755123457778");
		
		//陆运E标示
		//业务类型为“电商特惠、顺丰特惠、电商专配、陆运件”则必须打印E标识，用以提示中转场分拣为陆运	
		dto.setElectric("");
				
		//快递类型	
		//1 ：标准快递   2.顺丰特惠   3： 电商特惠   5：顺丰次晨  6：顺丰即日  7.电商速配   15：生鲜速配		
		dto.setExpressType(1);
				
		//COD代收货款金额,只需填金额, 单位元- 此项和月结卡号绑定的增值服务相关
		//dto.setCodValue("0.0");
		
		dto.setInsureValue("501");//声明货物价值的保价金额,只需填金额,单位元
		dto.setMonthAccount("7550385912");//月结卡号  
		dto.setPayMethod(1);// 1-寄付 2-到付 3-第三方支付
		
		/**丰密运单相关-如非使用丰密运单模板 不需要设置以下值**/
		//dto.setDestRouteLabel("755WE-571A3");
		//dto.setPrintIcon("1111");
		//dto.setProCode("T6");
		//dto.setAbFlag("A");
		//dto.setXbFlag("XB");
		//dto.setCodingMapping("F33");
		//dto.setCodingMappingOut("1A");
		//dto.setDestTeamCode("012345678");
		//dto.setSourceTransferCode("021WTF");
		//对应下订单设置路由标签返回字段twoDimensionCode 该参数是丰密面单的二维码图
		//dto.setQRCode("MMM={'k1':'755WE','k2':'755BF','k3':'','k4':'T6','k5':'755123456789','k6':'A'}");
		
		//加密项
		dto.setEncryptCustName(true);//加密寄件人及收件人名称
		dto.setEncryptMobile(true);//加密寄件人及收件人联系手机		
		
		CargoInfoDto cargo = new CargoInfoDto();
		cargo.setCargo("7S");
		cargo.setCargoCount(1);
		cargo.setCargoUnit("2");
		cargo.setSku("00015645");
		cargo.setRemark("3");
		
		CargoInfoDto cargo2 = new CargoInfoDto();
		cargo2.setCargo("macbookpro");
		cargo2.setCargoCount(1);
		cargo2.setCargoUnit("j");
		cargo2.setSku("00015646");
		cargo2.setRemark("k");
		
		List<CargoInfoDto> cargoInfoList = new ArrayList<>();
		cargoInfoList.add(cargo2);
		cargoInfoList.add(cargo);
		
		dto.setCargoInfoDtoList(cargoInfoList);
		waybillDtoList.add(dto);
		
		System.out.println("请求参数： "+MyJsonUtil.object2json(dto));
		
		ObjectMapper objectMapper = new ObjectMapper();
		StringWriter stringWriter = new StringWriter();
		objectMapper.writeValue(stringWriter,waybillDtoList);
		
		httpConn.getOutputStream().write(stringWriter.toString().getBytes());
		httpConn.getOutputStream().flush();
		httpConn.getOutputStream().close();
		InputStream in = httpConn.getInputStream();
		BufferedReader in2=new BufferedReader(new InputStreamReader(in));
		
		String y="";
		 
		String strImg="";
		while((y=in2.readLine())!=null){			 
			strImg=y.substring(y.indexOf("[")+1,y.length()-"]".length()-1);
			if(strImg.startsWith("\"")){
				strImg=strImg.substring(1,strImg.length());
			}
			if(strImg.endsWith("\"")){
				strImg=strImg.substring(0,strImg.length()-1);
			}				  
		}
	    
	    //将换行全部替换成空    
		strImg=strImg.replace("\\n", ""); 	    
		//System.out.println(strImg); 
		
	    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-HHmmss");
		String dateStr = format.format(new Date());
		
		if(strImg.contains("\",\"")){
			//如子母单及签回单需要打印两份或者以上
			String[] arr = strImg.split("\",\""); 
			/**输出图片到本地 支持.jpg、.png格式**/		
		    for(int i = 0; i < arr.length; i++) {  
		    	Base64ImageTools.generateImage(arr[i].toString(), "D:\\qiaoWay"+dateStr+"-"+i+".jpg");	
		    }
		}else{
			System.out.println("生成图片not contains=="+dateStr);
			Base64ImageTools.generateImage(strImg, "D:\\qiaoWaybill"+dateStr+".jpg");			
		}	
		return "1";
	}
	
	//字符编码转换 
	public String unicode(String str){
		try {
			str = new String(str.toString().getBytes("utf-8"), "gbk");//发布服务器使用编码转换
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
	
}
