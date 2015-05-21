package cn.epalmpay.analoy.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.epalmpay.analoy.entity.po.PageTrade;
import cn.epalmpay.analoy.service.BaseService;
import cn.epalmpay.analoy.service.base.EquimentService;
import cn.epalmpay.analoy.service.base.TradeOrderService;
import cn.epalmpay.analoy.utils.page.Response;

@RestController
@RequestMapping("api/tradeOrder")
public class TradeOrderController {
	private static final Logger logger = LoggerFactory.getLogger(TradeOrderController.class);
	@Autowired
	private TradeOrderService tradeOrderService;

	@Autowired
	private EquimentService equimentService;

	@Autowired
	private BaseService baseService;

	/**
	 * 添加消费记录
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "addTradeOrder", method = RequestMethod.POST)
	public Response addTradeOrder(@RequestBody Map<String, Object> params) {

		String paytype = params.get("paytype").toString();
		String eqno = params.get("eqno").toString();
		String bankName = params.get("bankName").toString();
		String cardno = params.get("cardno").toString();
		String cardtype = params.get("cardtype").toString();
		String tradetype = params.get("tradetype").toString();
		double money = Double.parseDouble(params.get("money").toString());

		Response res = new Response();
		List<Map<String, Object>> list = equimentService.getEquipmentByEqnoAndType(eqno, Integer.parseInt(paytype));
		if (list == null) {
			res.setCode(Response.ERROR_CODE);
			res.setMessage("该终端不存在,无法添加消费记录,请先添加终端");
			return res;
		}

		PageTrade order = new PageTrade(paytype, eqno, bankName, cardno, cardtype, tradetype, money);
		int code = baseService.pushRecords(order, eqno);
		if (code > 0) {
			res.setCode(Response.SUCCESS_CODE);
			return res;
		} else {
			res.setCode(Response.ERROR_CODE);
			res.setMessage("该终端还未开通,无法添加消费记录");
			return res;
		}

	}

	@RequestMapping(value = "getTradeOrder", method = RequestMethod.GET)
	public List<Map<String, Object>> getTradeOrder() {
		List<Map<String, Object>> list = tradeOrderService.getTradeOrder();
		logger.debug(list.toString());
		return list;
	}
}
