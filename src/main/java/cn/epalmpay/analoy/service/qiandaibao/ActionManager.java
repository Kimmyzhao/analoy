package cn.epalmpay.analoy.service.qiandaibao;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ResponseHandler;
import org.apache.http.impl.client.BasicResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import cn.epalmpay.analoy.entity.base.EquipMent;
import cn.epalmpay.analoy.entity.base.TradeOrder;
import cn.epalmpay.analoy.entity.po.PageTrade;
import cn.epalmpay.analoy.joint.JointManager;
import cn.epalmpay.analoy.service.base.TradeOrderService;
import cn.epalmpay.analoy.utils.DataUtils;
import cn.epalmpay.analoy.utils.HttpUtils;
import cn.epalmpay.analoy.utils.StringUtils;

public class ActionManager implements JointManager {

	private static final Logger logger = LoggerFactory.getLogger(ActionManager.class);

	@Value("${MD5key}")
	private String MD5key;
	@Value("${qiandaibao.url.pullTradesRecord}")
	private String pullTradesRecord;
	@Value("${zftiming.url}")
	private String baseurl;

	/**
	 * 保存交易记录并推送
	 */
	@Override
	public int pushRecords(PageTrade order, TradeOrderService tradeOrderService, EquipMent eq) {
		Date date = new Date();
		String time = StringUtils.dateToString(date, "yyyy-MM-dd HH:mm:ss.SSS");// 交易时间
		String orderid = "QD" + StringUtils.dateToString(date, "yyyyMMddHH") + "-" + StringUtils.dateToString(date, "HHmmss");

		String[] agentno = new String[] { "986856192260", "986825803310", "981818190230", "981818216288", "986826060820", "981818680111" };
		double fee = 0;

		if (eq.getStatus() == EquipMent.OPEN_STATUS) {
			TradeOrder trade = new TradeOrder();
			trade.setTransactionalNumber(orderid);// 订单号
			trade.setPaytype(Integer.parseInt(order.getPaytype()));// 支付通道
			trade.setCardtype(Integer.parseInt(order.getCardtype()));// 银行卡类型
			double money = order.getMoney();
			String cardno = order.getCardno();
			trade.setSettlecardno(cardno);
			trade.setMoney(money);// 结算金额
			fee = money * 0.001;
			trade.setFee(fee);// 手续费
			trade.setSettlemoney(money - fee);// 实际结算金额
			trade.setEquipmentid(eq.getId());// 设备ID
			trade.setTradetype(Integer.parseInt(order.getTradetype()));// 交易类型
			trade.setCreatedat(date);// 创建时间
			String bankName = DataUtils.getBankName(Integer.parseInt(order.getBankName()));
			trade.setSettlebankname(bankName);

			tradeOrderService.saveRecords(trade);

			int agentno_index = DataUtils.generateInt(6);
			// 计算签名
			String md5_str = StringUtils.encryption(getSign(eq, agentno[agentno_index], trade), "MD5");
			logger.info("签名为...." + md5_str);

			// 发送POST请求
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/x-www-form-urlencoded");
			Map<String, String> params = new HashMap<String, String>();
			params.put("orderid", orderid);
			params.put("agentno", agentno[agentno_index]);
			params.put("money", money + "");
			params.put("fee", fee + "");
			params.put("eqno", order.getEqno());
			params.put("cardno", StringUtils.toProSub(cardno));
			params.put("cardtype", order.getCardtype());
			params.put("bankName", bankName);
			params.put("settlemoney", money - fee + "");
			params.put("sign", md5_str);
			params.put("time", time);
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			try {
				logger.info(baseurl + pullTradesRecord);
				HttpUtils.post(baseurl + pullTradesRecord, headers, params, responseHandler);
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
			return 1;

		}

		return 0;
	}

	/**
	 * 得到签名
	 * 
	 * @param eq
	 * @param order
	 * @return
	 */
	private String getSign(EquipMent eq, String agentno, TradeOrder order) {
		StringBuffer sb = new StringBuffer();
		sb.append("orderid=" + order.getTransactionalNumber());
		sb.append("agentno=" + agentno);
		sb.append("money=" + order.getMoney());
		sb.append("eqno=" + eq.getEqno());
		sb.append("cardno=" + StringUtils.toProSub(order.getSettlecardno()));
		sb.append("cardtype=" + order.getCardtype());
		sb.append(MD5key);

		logger.debug("接受的参数为..." + sb.toString());
		return sb.toString();
	}
}
