package cn.epalmpay.analoy.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import cn.epalmpay.analoy.mapper.TradeOrderFileMapper;
import cn.epalmpay.analoy.mapper.TradeOrderMapper;
import cn.epalmpay.analoy.utils.Constant;
import cn.epalmpay.analoy.utils.DataUtils;
import cn.epalmpay.analoy.utils.HttpUtils;
import cn.epalmpay.analoy.utils.StringUtils;
import cn.epalmpay.analoy.zhonghui.entity.Trades;

@Service
public class TradeOrderService {

	private static final Logger logger = LoggerFactory.getLogger(TradeOrderService.class);
	@Value("${MD5key}")
	private String MD5key;
	@Value("${qiandaibao.url.pullTradesRecord}")
	private String pullTradesRecord;
	@Value("${zftiming.url}")
	private String baseurl;
	@Value("${savepath}")
	private String savepath;
	@Value("${path.root}")
	private String root;

	@Autowired
	private TradeOrderMapper tradeOrderMapper;
	@Autowired
	private EquipMentMapper equipMentMapper;
	@Autowired
	private TradeOrderFileMapper orderFileMapper;

	public int save(Map<String, Object> map) {
		String paytype = map.get("paytype").toString();
		String eqno = map.get("eqno").toString();
		String bankName = map.get("bankName").toString();
		String cardno = map.get("cardno").toString();
		String cardtype = map.get("cardtype").toString();
		String tradetype = map.get("tradetype").toString();
		double money = Double.parseDouble(map.get("money").toString());
		String[] agentno = new String[] { "986856192260", "986825803310", "981818190230", "981818216288", "986826060820", "981818680111" };
		EquipMent eq = equipMentMapper.getEuipMent(Integer.parseInt(paytype), eqno);
		if (eq != null) {
			String orderid = "";
			double fee = 0.00;
			Date date = new Date();
			String time = StringUtils.dateToString(date, "yyyy-MM-dd HH:mm:ss.SSS");// 交易时间
			TradeOrder order = new TradeOrder();
			if (Integer.parseInt(paytype) == 1) {// 钱袋宝
				orderid = "QD" + StringUtils.dateToString(date, "yyyyMMddHH") + "-" + StringUtils.dateToString(date, "HHmmss");
				order.setTransactionalNumber(orderid);
			} else if (Integer.parseInt(paytype) == 2) {// 中汇
				orderid = StringUtils.dateToString(date, "yyyyMMddHHmmssSSS");
			}
			order.setTransactionalNumber(orderid);
			order.setPaytype(Integer.parseInt(paytype));// 支付通道
			order.setSettlebankname(getBankName(Integer.parseInt(bankName)));// 结算银行
			order.setSettlecardno(cardno);// 银行账号
			order.setCardtype(Integer.parseInt(cardtype));// 银行卡类型
			order.setMoney(money);// 结算金额
			fee = money * 0.001;
			order.setFee(fee);// 手续费
			order.setSettlemoney(money - fee);// 实际结算金额
			order.setEquipmentid(eq.getId());// 设备ID
			order.setTradetype(Integer.parseInt(tradetype));// 交易类型
			order.setCreatedat(date);// 创建时间
			tradeOrderMapper.insert(order);

			int agentno_index = DataUtils.generateInt(6);
			if (Integer.parseInt(paytype) == 1) {
				// 计算签名
				String md5_str = StringUtils.encryption(getSign(eq, agentno[agentno_index], order), "MD5");
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
				params.put("cardno", StringUtils.toProSub(cardno));
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
			} else if (2 == Integer.parseInt(paytype)) {// 中汇
				File file = new File(root + savepath);
				if (!file.exists()) {
					file.mkdir();
				}

				if (!file.getParentFile().exists()) {
					file.mkdir();
				}

				String fileName = StringUtils.dateToString(date, "yyyy-MM-dd") + Constant.FILE_TYPE;
				String saveFilePath = savepath + fileName;

				orderFileMapper.savepath(saveFilePath);
				logger.info(root + saveFilePath);
				String path = root + savepath + fileName;
				File recordfile = new File(path);
				if (!recordfile.exists()) {
					try {
						recordfile.createNewFile();
					} catch (IOException e) {
						logger.error(e.getMessage());

					}
				}

				Trades trade = setTrades(eq, date, order);
				appendtradeorder(path, trade);
			}
			return 1;
		}
		return 0;
	}

	/**
	 * 向文件中追加交易记录
	 * 
	 * @param recordfile
	 * @param trade
	 */
	private void appendtradeorder(String fileName, Trades trade) {
		try {
			FileWriter writer = new FileWriter(fileName, true);
			StringBuffer sb = new StringBuffer();
			sb.append(trade.getDevKSN() + Constant.FILE_SPLIT);
			sb.append(trade.getTransflowno() + Constant.FILE_SPLIT);
			sb.append(trade.getTranstime() + Constant.FILE_SPLIT);
			sb.append(trade.getTransmoney() + Constant.FILE_SPLIT);
			sb.append(trade.getPayCardNo() + Constant.FILE_SPLIT);
			sb.append(trade.getTranstype() + Constant.FILE_SPLIT);
			sb.append(trade.getTransState() + Constant.FILE_SPLIT);
			sb.append(trade.getMerchantno() + Constant.FILE_SPLIT);
			sb.append(trade.getMerchantname() + Constant.FILE_SPLIT);
			sb.append(trade.getReferenceno() + Constant.FILE_SPLIT);
			sb.append(trade.getTransno());
			sb.append("\r\t");
			writer.write(sb.toString());
			writer.close();
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * 设置交易流水
	 * 
	 * @param eq
	 * @param date
	 * @param order
	 * @return
	 */
	private Trades setTrades(EquipMent eq, Date date, TradeOrder order) {
		Trades trade = new Trades();
		trade.setDevKSN(eq.getEqno());// 设备号
		trade.setTransflowno(order.getTransactionalNumber());// 交易流水号
		trade.setTranstime(StringUtils.dateToString(date, "yyyy-M-d H.m.s.S"));// 交易时间
		trade.setTransmoney(order.getMoney() + "");
		trade.setPayCardNo(StringUtils.toProSub(order.getSettlecardno()));
		trade.setTranstype(order.getTradetype() + "");
		trade.setTransState("1");
		trade.setMerchantno(eq.getAgentno());
		trade.setMerchantname(eq.getAgentname());
		trade.setReferenceno(StringUtils.dateToString(date, "yyyyMMddHHmm"));
		trade.setTransno(StringUtils.dateToString(date, "yyyyMMddHHmm"));
		return trade;
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

	public List<Map<String, Object>> getTradeOrder() {
		return tradeOrderMapper.getTradeOrder();
	}

}
