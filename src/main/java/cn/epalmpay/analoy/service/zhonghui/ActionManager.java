package cn.epalmpay.analoy.service.zhonghui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import cn.epalmpay.analoy.entity.base.EquipMent;
import cn.epalmpay.analoy.entity.base.TradeOrder;
import cn.epalmpay.analoy.entity.po.PageTrade;
import cn.epalmpay.analoy.entity.zhonghui.Trades;
import cn.epalmpay.analoy.joint.JointManager;
import cn.epalmpay.analoy.mapper.TradeOrderFileMapper;
import cn.epalmpay.analoy.service.base.TradeOrderService;
import cn.epalmpay.analoy.utils.Constant;
import cn.epalmpay.analoy.utils.DataUtils;
import cn.epalmpay.analoy.utils.StringUtils;

public class ActionManager implements JointManager {

	private static final Logger logger = LoggerFactory.getLogger(ActionManager.class);

	@Value("${filePath}")
	private String savepath;
	@Value("${path.root}")
	private String root;

	@Autowired
	private TradeOrderFileMapper orderFileMapper;

	@Override
	public int pushRecords(PageTrade order, TradeOrderService tradeOrderService, EquipMent eq) {
		Date date = new Date();
		String orderid = StringUtils.dateToString(date, "yyyyMMddHHmmssSSS");
		double fee = 0;
		if (eq.getStatus() == EquipMent.OPEN_STATUS && eq.getActivated() == EquipMent.ACTIVATE_STATUS_REGISTED) {
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

			File file = new File(root + savepath);
			if (!file.getParentFile().exists()) {
				file.mkdir();
			}
			String fileName = StringUtils.dateToString(date, "yyyy-MM-dd") + Constant.FILE_TYPE;
			String saveFilePath = savepath + fileName;

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
			Trades t = setTrades(eq, date, trade);
			List<Map<String, Object>> list = orderFileMapper.getFiles(saveFilePath);
			if (list != null && list.size() > 0) {// 有文件未被推送
				appendtradeorder(path, t, true);
			} else {// 所有文件都已推送
				orderFileMapper.savepath(saveFilePath);
				appendtradeorder(path, t);
			}

		} else {
			return -1;
		}
		return 1;
	}

	/**
	 * 向文件中追加交易记录
	 * 
	 * @param recordfile
	 * @param trade
	 */
	private void appendtradeorder(String fileName, Trades trade, boolean flag) {
		try {
			FileWriter writer = new FileWriter(fileName, flag);
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
	 * 文件覆盖
	 * 
	 * @param recordfile
	 * @param trade
	 */
	private void appendtradeorder(String fileName, Trades trade) {
		try {
			FileWriter writer = new FileWriter(fileName);
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

}
