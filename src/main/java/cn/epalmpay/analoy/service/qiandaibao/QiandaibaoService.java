package cn.epalmpay.analoy.service.qiandaibao;

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

import cn.epalmpay.analoy.entity.base.EquipMent;
import cn.epalmpay.analoy.mapper.EquipMentMapper;
import cn.epalmpay.analoy.utils.DataUtils;
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

	@Autowired
	private EquipMentMapper equipMentMapper;

	/**
	 * 推送交易记录
	 * 
	 * @return
	 */
	public String getTradeRecord() {
		final String QD = "QD";
		String[] bankName = new String[] { "中国工商银行", "交通银行", "广发银行", "中国农业银行", "招商银行", "平安银行", "中国邮政储蓄银行", "中国建设银行" };
		String[] cardtype = new String[] { "1", "2" };// 1借记卡2贷记卡
		String[] agentno = new String[] { "986856192260", "981818190230", "981818216288", "986826060820", "981818680111" };
		String[] eqno = new String[] { "61021174690", "32011085657", "32032038730", "501000082320", "501000013563" };
		String[] cardno = new String[] { "621559******0617", "622252******9067", "622556******5151", "622848******0679", "621483******1725", "526855******3116", "621799******5532", "621700******1117" };
		// "823162801",
		Date date = new Date();
		String format = new SimpleDateFormat("yyyyMMddHH").format(date);
		String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(date);// 交易时间
		String orderid = QD + format + "-" + new SimpleDateFormat("HHmmss").format(date);// 订单号
		int agentno_index = DataUtils.generateInt(5);
		String money = DataUtils.generateDouble(1000);// 订单支付金额
		String fee = DataUtils.format(Double.parseDouble(money) * 0.01);// 手续费
		String settlemoney = DataUtils.format(Double.parseDouble(money) - Double.parseDouble(fee));// 应结算金额
		int cardno_index = DataUtils.generateInt(8);
		int cardtype_index = DataUtils.generateInt(2);

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

	/**
	 * 添加终端
	 * 
	 * @param eqno
	 * @param type
	 * @return
	 */
	public int insert(String eqno, String type) {
		EquipMent record = new EquipMent();
		record.setEqno(eqno);
		record.setEqtype(Integer.parseInt(type));
		record.setCreatedat(new Date());
		record.setStatus(2);
		return equipMentMapper.insert(record);

	}
}
