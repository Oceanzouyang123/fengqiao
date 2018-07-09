package com.fengqiao.util;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.fengqiao.entity.Orderbill;
import com.fengqiao.entity.Waybill;

public class XMLDone {

	//下订单解析
	public synchronized Orderbill OrderServiceXML(String xmlDoc) throws DocumentException, UnsupportedEncodingException{
		Orderbill orderbill = new Orderbill();
		Document doc = null;  
		SAXReader xmlReader = new SAXReader();
		//doc = xmlReader.read(xmlDoc);		
		doc = xmlReader.read(new ByteArrayInputStream(xmlDoc.getBytes("UTF-8")));
        //doc = DocumentHelper.parseText(xmlDoc);
        Element rootElt = doc.getRootElement(); // 获取根节点
        String service = rootElt.attributeValue("service");
        System.out.println(rootElt.getName()+"-->"+"service="+service);
        //String lang = rootElt.attributeValue("lang");
        
        //head节点
        Iterator iterHead = rootElt.elementIterator("Head");        
        while (iterHead.hasNext()) {
        	Element recordHead = (Element) iterHead.next();
        	System.out.println(recordHead.getName()+"--"+recordHead.getText());
        }
        
        //body节点
        Iterator iterBody = rootElt.elementIterator("Body");
        while (iterBody.hasNext()) {
        	Element recordBody = (Element) iterBody.next();
        	//body中如果有参数
        	//Attribute bodyid = recordBody.attribute("bodyid");
        	//if(null != bodyid){
        	//	  String bodykey = bodyid.getName();
        	//    String bodyValue = bodyid.getValue();
        	//    System.out.println(bodykey + ": " + bodyValue);
        	//}
        	//获取Order标签
        	Iterator iterOrder = recordBody.elementIterator("Order");
        	while(iterOrder.hasNext()){
        		Element recordOrder = (Element)iterOrder.next();
        		//orderid
        		Attribute orderid = recordOrder.attribute("orderid");
        		String orderidKey = orderid.getName();
        		String orderidValue = orderid.getValue();
        		System.out.println(orderidKey + ": " +orderidValue);
        		orderbill.setOrderid(orderidValue);
        		//j_company
        		Attribute j_company = recordOrder.attribute("j_company");
        		String j_companyKey = j_company.getName();
        		String j_companyValue = j_company.getValue();
        		System.out.println(j_companyKey+": "+j_companyValue);
        		orderbill.setDeliverCompany(j_companyValue);
        		//j_contact
        		Attribute j_contact = recordOrder.attribute("j_contact");
        		String j_contactKey = j_contact.getName();
        		String j_contactValue = j_contact.getValue();
        		System.out.println(j_contactKey+": "+j_contactValue);
        		orderbill.setDeliverName(j_contactValue);
        		//j_tel
        		Attribute j_tel = recordOrder.attribute("j_tel");
        		String j_telKey = j_tel.getName();
        		String j_telValue = j_tel.getValue();
        		System.out.println(j_telKey+": "+j_telValue);
        		orderbill.setDeliverTel(j_telValue);
        		//j_province
        		Attribute j_province = recordOrder.attribute("j_province");
        		String j_provinceKey = j_province.getName();
        		String j_provinceValue = j_province.getValue();
        		System.out.println(j_provinceKey+": "+j_provinceValue);
        		orderbill.setDeliverProvince(j_provinceValue);
        		//j_city
        		Attribute j_city = recordOrder.attribute("j_city");
        		String j_cityKey = j_city.getName();
        		String j_cityValue = j_city.getValue();
        		System.out.println(j_cityKey+": "+j_cityValue);
        		orderbill.setDeliverCity(j_cityValue);
        		//j_address
        		Attribute j_address = recordOrder.attribute("j_address");
        		String j_addressKey = j_address.getName();
        		String j_addressValue = j_address.getValue();
        		System.out.println(j_addressKey+": "+j_addressValue);
        		orderbill.setDeliverAddress(j_addressValue);
        		//d_company
        		Attribute d_company = recordOrder.attribute("d_company");
        		String d_companyKey = d_company.getName();
        		String d_companyValue = d_company.getValue();
        		System.out.println(d_companyKey+": "+d_companyValue);
        		orderbill.setConsignerCompany(d_companyValue);
        		//d_contact
        		Attribute d_contact = recordOrder.attribute("d_contact");
        		String d_contactKey = d_contact.getName();
        		String d_contactValue = d_contact.getValue();
        		System.out.println(d_contactKey+": "+d_contactValue);
        		orderbill.setConsignerName(d_contactValue);
        		//d_tel
        		Attribute d_tel = recordOrder.attribute("d_tel");
        		String d_telKey = d_tel.getName();
        		String d_telValue = d_tel.getValue();
        		System.out.println(d_telKey+": "+d_telValue);
        		orderbill.setConsignerTel(d_telValue);
        		//express_type
        		Attribute express_type = recordOrder.attribute("express_type");
        		String express_typeKey = express_type.getName();
        		String express_typeValue = express_type.getValue();
        		System.out.println(express_typeKey+": "+express_typeValue);
        		orderbill.setExpressType(express_typeValue);
        		//pay_method
        		Attribute pay_method = recordOrder.attribute("pay_method");
        		String pay_methodKey = pay_method.getName();
        		String pay_methodValue = pay_method.getValue();
        		System.out.println(pay_methodKey+": "+pay_methodValue);
        		orderbill.setPayMethod(pay_methodValue);
        		//parcel_quantity
        		Attribute parcel_quantity = recordOrder.attribute("parcel_quantity");
        		String parcel_quantityKey = parcel_quantity.getName();
        		String parcel_quantityValue = parcel_quantity.getValue();
        		System.out.println(parcel_quantityKey+": "+parcel_quantityValue);
        		
        		//custid
        		Attribute custid = recordOrder.attribute("custid");
        		String custidKey = custid.getName();
        		String custidValue = custid.getValue();
        		System.out.println(custidKey+": "+custidValue);
        		orderbill.setCustid(custidValue);
        		//cargo
        		Attribute cargo = recordOrder.attribute("cargo");
        		String cargoKey = cargo.getName();
        		String cargoValue = cargo.getValue();
        		System.out.println(cargoKey+": "+cargoValue);
        		orderbill.setCargo(cargoValue);
        		//获取Cargo标签
//        		Iterator iterCargo = recordOrder.elementIterator("Cargo");
//        		while(iterCargo.hasNext()){
//        			Element recordCargo = (Element)iterCargo.next();
//        		}
            	//获取AddedService标签
        		Iterator iterAddedService = recordOrder.elementIterator("AddedService");
            	while(iterAddedService.hasNext()){
            		Element recordAddedService = (Element)iterAddedService.next();
            		//name
            		Attribute name = recordAddedService.attribute("name");
            		String nameKey = name.getName();
            		String nameValue = name.getValue();
            		System.out.println(nameKey+": "+nameValue);
            		orderbill.setCargoName(nameValue);
            		//value
            		Attribute value = recordAddedService.attribute("value");
            		if(value != null){
            			String valueKey = value.getName();
                		String valueValue = value.getValue();
                		System.out.println(valueKey+": "+valueValue);
                		orderbill.setCargoValue(valueValue);
            		}	
            		//value1
            		Attribute value1 = recordAddedService.attribute("value1");
            		if(value1 != null){
            			String value1Key = value1.getName();
                		String value1Value = value1.getValue();
                		System.out.println(value1Key+": "+value1Value);
            		}
            	}
        		
        	}
        }
        
		return orderbill;
	}
	
	//电子运单解析
	public synchronized List<Waybill> WaybillXML(String xmlDoc) throws DocumentException{
		List<Waybill> waybillList = new ArrayList<Waybill>();
		Document doc = null;          
        doc = DocumentHelper.parseText(xmlDoc);
		return waybillList;
	}
}
