package com.fengqiao.mapper;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fengqiao.entity.Waybill;

/**
 * 
 * Author zouyang
 * 2018.7.3
 *
 */
@Service
@Transactional
public interface WaybillDao {
	
	//根据id查询Waybill对象
	Waybill getWaybill(int id);
	
	//查询所有
	List<Waybill> getWaybillList();
	
	//批量插入
	@Transactional
	void insertBatch(List<Waybill> list);
	
	//分页
	List<Waybill> getWaybillPager(int rowStart,int pageSize);
	
	//多条件匹配查询
	List<Waybill> getWaybillSqlWhere(Waybill waybill);
	
	//分页+多条件匹配查询
	List<Waybill> getWaybillPagerContent(Waybill waybill);
	
	//根据条件查询获取记录数
	int getCountSqlwhere(Waybill waybill);
	
	//调用存储过程
	//List<Waybill> getWaybillProcedure(Waybill waybill);
}
