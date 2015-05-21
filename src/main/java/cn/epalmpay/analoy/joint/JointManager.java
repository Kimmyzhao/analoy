package cn.epalmpay.analoy.joint;

import cn.epalmpay.analoy.entity.base.EquipMent;
import cn.epalmpay.analoy.entity.po.PageTrade;
import cn.epalmpay.analoy.service.base.TradeOrderService;

public interface JointManager {

	/**
	 * 推送交易流水
	 * 
	 * @param order
	 * @param tradeOrderService
	 * @param eq TODO
	 * @return
	 */
	public int pushRecords(PageTrade order, TradeOrderService tradeOrderService, EquipMent eq);
}
