package cn.epalmpay.analoy.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import cn.epalmpay.analoy.service.TradeOrderService;

@RestController
@RequestMapping("api/tradeOrder")
public class TradeOrderController {
	private static final Logger logger = LoggerFactory.getLogger(TradeOrderController.class);
	@Autowired
	private TradeOrderService tradeOrderService;

	@RequestMapping(value = "addTradeOrder", method = RequestMethod.POST)
	public ModelAndView addTradeOrder(String paytype, String eqno, String bankName, String cardno, String cardtype, String tradetype, double money) {
		logger.info(paytype + ":" + eqno + ":" + bankName + ":" + cardno + ":" + cardtype + ":" + money);
		ModelAndView view = new ModelAndView();
		if (tradeOrderService.save(paytype, eqno, bankName, cardno, cardtype, tradetype, money) == 0) {
			return view;
		}
		List<Map<String, Object>> tradeOrder = getTradeOrder();
		view.addObject("tradeorder", tradeOrder);
		view.setViewName("record");
		return view;
	}

//	@RequestMapping(value = "getTradeOrder", method = RequestMethod.GET)
	public List<Map<String, Object>> getTradeOrder() {
		List<Map<String, Object>> list = tradeOrderService.getTradeOrder();
		logger.debug(list.toString());
		return list;
	}
}
