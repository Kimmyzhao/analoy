package cn.epalmpay.analoy.qiandaibao.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.http.client.ResponseHandler;
import org.apache.http.impl.client.BasicResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import cn.epalmpay.analoy.qiandaibao.entity.PosQuery;
import cn.epalmpay.analoy.qiandaibao.entity.TransactionRecordQuery;
import cn.epalmpay.analoy.utils.Constant;
import cn.epalmpay.analoy.utils.FileUtils;
import cn.epalmpay.analoy.utils.HttpUtils;
import cn.epalmpay.analoy.utils.StringUtils;

@Service
public class QiandaibaoService {
	private static final Logger logger = LoggerFactory.getLogger(QiandaibaoService.class);
	@Value("${MD5key}")
	private String MD5key;
	@Value("${qiandaibao.url.pullTradesRecord}")
	private String pullTradesRecord;
	@Value("${zftiming.url}")
	private String baseurl;

	public String getTradeRecord1() {
		File file = null;
		String result = "";
		try {
			file = ResourceUtils.getFile(Constant.FILE_QIANDAIBAO);
			result = FileUtils.txt2String(file);
			TransactionRecordQuery query = new TransactionRecordQuery();
			query = (TransactionRecordQuery) StringUtils.parseJSONStringToObject(result, query);
			logger.debug(query.toString());

		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	public String queryPosState() {
		File file = null;
		String result = "";

		try {
			file = ResourceUtils.getFile(Constant.FILE_QIANDAIBAO_QUERYPOS);
			result = FileUtils.txt2String(file);
			PosQuery pos = new PosQuery();
			pos = (PosQuery) StringUtils.parseJSONStringToObject(result, pos);
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
			StringBuilder sb = new StringBuilder();
			sb.append("eqno=" + pos.getEqno());
			sb.append("now=" + formatter.format(date).toString());
			sb.append(MD5key);
			String sign = StringUtils.encryption(sb.toString(), "MD5");
			System.out.println(pos.toString());
			pos.setSign(sign);
			System.out.println(pos.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 推送交易记录
	 * 
	 * @return
	 */
	public String getTradeRecord() {
		final String QD = "QD";
		String[] bankName = new String[] { "中国工商银行", "交通银行", "广发银行", "中国农业银行", "招商银行", "平安银行", "中国邮政储蓄银行", "中国建设银行" };
		String[] cardtype = new String[] { "1", "2" };// 1借记卡2贷记卡
		String[] agentno = new String[] { "986856192260", "986825803310", "981818190230", "981818216288", "986826060820", "981818680111" };
		String[] eqno = new String[] { "61021174690", "82316280", "32011085657", "32032038730", "501000082320", "501000013563" };
		String[] cardno = new String[] { "621559******0617", "622252******9067", "622556******5151", "622848******0679", "621483******1725", "526855******3116", "621799******5532", "621700******1117" };

		Date date = new Date();
		String format = new SimpleDateFormat("yyyyMMddHH").format(date);
		String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(date);// 交易时间
		String orderid = QD + format + "-" + new SimpleDateFormat("HHmmss").format(date);// 订单号
		int agentno_index = generateInt(6);
		String money = generateDouble(1000);// 订单支付金额
		String fee = format(Double.parseDouble(money) * 0.01);// 手续费
		String settlemoney = format(Double.parseDouble(money) - Double.parseDouble(fee));// 应结算金额
		int cardno_index = generateInt(8);
		int cardtype_index = generateInt(2);

		// 计算签名
		StringBuffer sb = new StringBuffer();
		sb.append("orderid=" + orderid);
		sb.append("agentno=" + agentno[agentno_index]);
		sb.append("money=" + money);
		sb.append("eqno=" + eqno[agentno_index]);
		sb.append("cardno=" + cardno[cardno_index]);
		sb.append("cardtype=" + cardtype[cardtype_index]);
		sb.append(MD5key);
		String md5_str = StringUtils.encryption(sb.toString(), "MD5");
		logger.info("签名为...." + md5_str);

		// 发送POST请求
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/x-www-form-urlencoded");
		Map<String, String> params = new HashMap<String, String>();
		params.put("orderid", orderid);
		params.put("agentno", agentno[agentno_index]);
		params.put("money", money);
		params.put("fee", fee);
		params.put("eqno", eqno[agentno_index]);
		params.put("cardno", cardno[cardno_index]);
		params.put("cardtype", cardtype[cardtype_index]);
		params.put("bankName", bankName[cardno_index]);
		params.put("settlemoney", settlemoney);
		params.put("sign", md5_str);
		params.put("time", time);
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		try {
			logger.info(baseurl + pullTradesRecord);
			HttpUtils.post(baseurl + pullTradesRecord, headers, params, responseHandler);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return "ok";
	}

	public static void main(String[] args) throws ParseException {
		new QiandaibaoService().getTradeRecord();
	}

	/**
	 * 产生一个随机数
	 * 
	 * @param n
	 * @return
	 */
	private static int generateInt(int n) {
		return new Random().nextInt(n);
	}

	/**
	 * 产生一个随机数
	 * 
	 * @param money
	 * @return
	 */
	private static String generateDouble(double money) {
		DecimalFormat format = new DecimalFormat("######0.0000");
		return format.format(new Random().nextDouble() * money);

	}

	/**
	 * 格式化,保留四位小数
	 * 
	 * @param d
	 * @return
	 */
	private static String format(double d) {
		return new DecimalFormat("######0.0000").format(d);
	}
}
