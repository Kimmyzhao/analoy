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

import cn.epalmpay.analoy.model.Response;
import cn.epalmpay.analoy.service.EquimentService;
import cn.epalmpay.analoy.service.TradeOrderService;

@RestController
@RequestMapping("api/tradeOrder")
public class TradeOrderController {
	private static final Logger logger = LoggerFactory.getLogger(TradeOrderController.class);
	@Autowired
	private TradeOrderService tradeOrderService;

	@Autowired
	private EquimentService equimentService;

	@RequestMapping(value = "addTradeOrder", method = RequestMethod.POST)
	public Response addTradeOrder(@RequestBody Map<String, Object> params) {
		Response res = new Response();
		int paytype = Integer.parseInt(params.get("paytype").toString());
		String eqno = params.get("eqno").toString();
		List<Map<String, Object>> list = equimentService.getEquipmentByEqnoAndType(eqno, paytype);
		if (list == null) {
			res.setCode(Response.ERROR_CODE);
			res.setMessage("该终端不存在,无法添加消费记录,请先添加终端");
			return res;
		}

		try {
			if (tradeOrderService.save(params) > 0) {
				res.setCode(Response.SUCCESS_CODE);
				return res;
			} else {
				res.setCode(Response.ERROR_CODE);
				res.setMessage("该终端还未开通,无法添加消费记录");
				return res;
			}
		} catch (Exception e) {

		}

		return res;
	}

	// @RequestMapping(value = "getTradeOrder", method = RequestMethod.GET)
	public List<Map<String, Object>> getTradeOrder() {
		List<Map<String, Object>> list = tradeOrderService.getTradeOrder();
		logger.debug(list.toString());
		return list;
	}
}
