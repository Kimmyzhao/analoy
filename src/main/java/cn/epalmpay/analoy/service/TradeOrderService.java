package cn.epalmpay.analoy.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.epalmpay.analoy.entity.EquipMent;
import cn.epalmpay.analoy.entity.TradeOrder;
import cn.epalmpay.analoy.mapper.EquipMentMapper;
import cn.epalmpay.analoy.mapper.TradeOrderMapper;

@Service
public class TradeOrderService {

	@Autowired
	private TradeOrderMapper tradeOrderMapper;
	@Autowired
	private EquipMentMapper equipMentMapper;

	public int save(String paytype, String eqno, String bankName, String cardno, String cardtype, double money) {
		EquipMent e = equipMentMapper.getEuipMent(Integer.parseInt(paytype), eqno);
		if (e != null) {
			String orderid = "";
			Date date = new Date();
			TradeOrder order = new TradeOrder();
			if (Integer.parseInt(paytype) == 1) {// 钱袋宝
				orderid = "QD" + new SimpleDateFormat("yyyyMMddHH").format(date) + "-" + new SimpleDateFormat("HHmmss").format(date);
				order.setTransactionalNumber(orderid);
			} else if (Integer.parseInt(paytype) == 2) {// 中汇

			}
			order.setSettlebankname(getBankName(Integer.parseInt(bankName)));
			order.setSettlecardno(cardno);
			order.setCardtype(Integer.parseInt(cardtype));
			order.setMoney(money);
			order.setFee(money * 0.001);
			order.setSettlemoney(money - money * 0.001);
			order.setEqId(e.getId());
			order.setCreatedat(date);
			tradeOrderMapper.insert(order);
		}
		return 0;
	}

	private String getBankName(int index) {
		String bankName = "";
		switch (index) {
		case 1:
			bankName = "中国工商银行";
			break;
		case 2:
			bankName = "交通银行";
			break;
		case 3:
			bankName = "广发银行";
			break;
		case 4:
			bankName = "中国农业银行";
			break;
		case 5:
			bankName = "招商银行";
			break;
		case 6:
			bankName = "平安银行";
			break;
		case 7:
			bankName = "中国邮政储蓄银行";
			break;
		case 8:
			bankName = "中国建设银行";
			break;
		default:
			bankName = "中国银行";
			break;
		}
		return bankName;
	}

}
