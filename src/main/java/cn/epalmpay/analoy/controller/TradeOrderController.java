package cn.epalmpay.analoy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.epalmpay.analoy.service.TradeOrderService;

@RestController
@RequestMapping("api/tradeOrder")
public class TradeOrderController {
	private static final Logger logger = LoggerFactory.getLogger(TradeOrderController.class);
	@Autowired
	private TradeOrderService tradeOrderService;

	@RequestMapping(value = "addTradeOrder", method = RequestMethod.POST)
	public String addTradeOrder(String paytype, String eqno, String bankName, String cardno, String cardtype, double money) {
		logger.info(paytype + ":" + eqno + ":" + bankName + ":" + cardno + ":" + cardtype + ":" + money);
		if (tradeOrderService.save(paytype, eqno, bankName, cardno, cardtype, money) == 0) {
			return "该终端还未开通,不能消费";
		}
		return "ok";
	}
}
