package cn.epalmpay.analoy.service.base;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.epalmpay.analoy.entity.base.EquipMent;
import cn.epalmpay.analoy.entity.base.TradeOrder;
import cn.epalmpay.analoy.mapper.EquipMentMapper;
import cn.epalmpay.analoy.mapper.TradeOrderFileMapper;
import cn.epalmpay.analoy.mapper.TradeOrderMapper;

/**
 * 交易Service
 * 
 * @author DELL
 *
 */
@Service
public class TradeOrderService {

	@Autowired
	private TradeOrderMapper tradeOrderMapper;
	@Autowired
	private EquipMentMapper equipMentMapper;
	@Autowired
	private TradeOrderFileMapper orderFileMapper;

	/**
	 * 查询交易流水
	 * 
	 * @return
	 */
	public List<Map<String, Object>> getTradeOrder() {
		return tradeOrderMapper.getTradeOrder();
	}

	/**
	 * 保存消费记录
	 * 
	 * @param order
	 */
	public void saveRecords(TradeOrder order) {
		tradeOrderMapper.insert(order);
	}

	/**
	 * 根据支付通道以及终端号查询终端信息
	 * 
	 * @param paytype
	 * @param eqno
	 * @return
	 */
	public EquipMent getEuipMent(String paytype, String eqno) {
		return equipMentMapper.getEuipMent(Integer.parseInt(paytype), eqno);
	}
}
