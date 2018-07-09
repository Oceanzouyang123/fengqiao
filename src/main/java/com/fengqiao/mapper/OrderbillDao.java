package com.fengqiao.mapper;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.fengqiao.entity.Orderbill;


@Transactional
public interface OrderbillDao {

	//根据id查询Waybill对象
	Orderbill getOrderbill(int id);
		
	//查询所有
	List<Orderbill> getOrderbillList();
		
	//单个插入
	void addOrderService(Orderbill orderbill);
	
	//批量插入
	@Transactional
	void insertBatch(List<Orderbill> list);
		
	//分页
	List<Orderbill> getOrderbillPager(int rowStart,int pageSize);
		
	//多条件匹配查询
	List<Orderbill> getOrderbillSqlWhere(Orderbill orderbill);
		
	//分页+多条件匹配查询
	List<Orderbill> getOrderbillPagerContent(Orderbill orderbill);
		
	//根据条件查询获取记录数
	int getOrderbillCountSqlwhere(Orderbill orderbill);
		
	//调用存储过程
	//List<Orderbill> getOrderbillProcedure(Orderbill orderbill);
	
}
