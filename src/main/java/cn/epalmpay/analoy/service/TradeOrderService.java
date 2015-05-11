package cn.epalmpay.analoy.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ResponseHandler;
import org.apache.http.impl.client.BasicResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.epalmpay.analoy.entity.EquipMent;
import cn.epalmpay.analoy.entity.TradeOrder;
import cn.epalmpay.analoy.mapper.EquipMentMapper;
import cn.epalmpay.analoy.mapper.TradeOrderMapper;
import cn.epalmpay.analoy.utils.DataUtils;
import cn.epalmpay.analoy.utils.HttpUtils;
import cn.epalmpay.analoy.utils.StringUtils;

@Service
public class TradeOrderService {

	private static final Logger logger = LoggerFactory.getLogger(TradeOrderService.class);
	@Value("${MD5key}")
	private String MD5key;
	@Value("${qiandaibao.url.pullTradesRecord}")
	private String pullTradesRecord;
	@Value("${zftiming.url}")
	private String baseurl;

	@Autowired
	private TradeOrderMapper tradeOrderMapper;
	@Autowired
	private EquipMentMapper equipMentMapper;

	public int save(String paytype, String eqno, String bankName, String cardno, String cardtype, double money) {
		String[] agentno = new String[] { "986856192260", "986825803310", "981818190230", "981818216288", "986826060820", "981818680111" };
		EquipMent eq = equipMentMapper.getEuipMent(Integer.parseInt(paytype), eqno);
		if (eq != null) {
			String orderid = "";
			double fee = 0.00;
			Date date = new Date();
			String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(date);// 交易时间
			TradeOrder order = new TradeOrder();
			if (Integer.parseInt(paytype) == 1) {// 钱袋宝
				orderid = "QD" + new SimpleDateFormat("yyyyMMddHH").format(date) + "-" + new SimpleDateFormat("HHmmss").format(date);
				order.setTransactionalNumber(orderid);
			} else if (Integer.parseInt(paytype) == 2) {// 中汇

			}
			order.setPaytype(Integer.parseInt(paytype));
			order.setSettlebankname(getBankName(Integer.parseInt(bankName)));
			order.setSettlecardno(cardno);
			order.setCardtype(Integer.parseInt(cardtype));
			order.setMoney(money);
			fee = money * 0.001;
			order.setFee(fee);
			order.setSettlemoney(money - fee);
			order.setEqId(eq.getId());
			order.setCreatedat(date);
			tradeOrderMapper.insert(order);

			int agentno_index = DataUtils.generateInt(6);
			String agentno1 = agentno[agentno_index];
			if (Integer.parseInt(paytype) == 1) {
				// 计算签名
				StringBuffer sb = new StringBuffer();
				sb.append("orderid=" + orderid);
				sb.append("agentno=" + agentno1);
				sb.append("money=" + money);
				sb.append("eqno=" + eqno);
				sb.append("cardno=" + StringUtils.toProSub(cardno));
				sb.append("cardtype=" + cardtype);
				sb.append(MD5key);
				logger.debug(MD5key);
				String md5_str = StringUtils.encryption(sb.toString(), "MD5");
				logger.info("签名为...." + md5_str);

				// 发送POST请求
				Map<String, String> headers = new HashMap<String, String>();
				headers.put("Content-Type", "application/x-www-form-urlencoded");
				Map<String, String> params = new HashMap<String, String>();
				params.put("orderid", orderid);
				params.put("agentno", agentno[agentno_index]);
				params.put("money", money + "");
				params.put("fee", fee + "");
				params.put("eqno", eqno);
				params.put("cardno", cardno);
				params.put("cardtype", cardtype);
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
			}
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
