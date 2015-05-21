package cn.epalmpay.analoy.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.epalmpay.analoy.entity.base.EquipMent;
import cn.epalmpay.analoy.entity.po.PageTrade;
import cn.epalmpay.analoy.joint.JointManager;
import cn.epalmpay.analoy.service.base.TradeOrderService;

@Service
public class BaseService {

	@Resource
	private Map<String, JointManager> managers;
	@Autowired
	private TradeOrderService tradeOrderService;

	public int pushRecords(PageTrade order, EquipMent eq) {
		String eqtype = order.getPaytype();// 获取支付通道
		JointManager manager = switchManager(eqtype);
		return manager.pushRecords(order, tradeOrderService, eq);
	}

	private JointManager switchManager(String eqtype) {
		return managers.get(eqtype.toString());
	}
}
