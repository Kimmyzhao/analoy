package cn.epalmpay.analoy.controller.qiandaibao;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ResponseHandler;
import org.apache.http.impl.client.BasicResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.epalmpay.analoy.entity.base.EquipMent;
import cn.epalmpay.analoy.entity.qiandaibao.PosQuery;
import cn.epalmpay.analoy.service.base.EquimentService;
import cn.epalmpay.analoy.service.qiandaibao.QiandaibaoService;
import cn.epalmpay.analoy.utils.Constant;
import cn.epalmpay.analoy.utils.HttpUtils;
import cn.epalmpay.analoy.utils.StringUtils;
import cn.epalmpay.analoy.utils.page.Response;

@RestController
@RequestMapping("api/qiandai")
public class QiandaibaoController {
	private static final Logger logger = LoggerFactory.getLogger(QiandaibaoController.class);
	@Value("${MD5key}")
	private String MD5key;
	@Value("${transaction.query.url}")
	private String transactionQueryUrl;
	@Value("${pos.query.url}")
	private String posQueryUrl;

	@Value("${qiandaibao.url.pullTradesRecord}")
	private String pullTradesRecord;

	@Value("${savepath}")
	private String savepath;
	@Autowired
	private QiandaibaoService qiandaibaoService;
	@Autowired
	private EquimentService equimentService;

	@RequestMapping(value = "getAgentInfoByEqno2", method = RequestMethod.POST)
	public Response getAgentInfoByEqno2(@RequestBody Map<String, Object> param) {
		String url = posQueryUrl;
		String eqno = param.get("eqno") == null ? "82316280" : param.get("eqno").toString();
		String now = param.get("now") == null ? "2015-04-27 09:30" : param.get("now").toString();
		String remark = param.get("remark") == null ? "根据POS终端号查询开通状态" : param.get("remark").toString();
		StringBuilder sb = new StringBuilder();
		sb.append("eqno=" + eqno);
		sb.append("now=" + now);
		sb.append(MD5key);
		String sign = StringUtils.encryption(sb.toString(), "MD5");
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/x-www-form-urlencoded");

		Map<String, String> params = new HashMap<String, String>();
		params.put("eqno", eqno);
		params.put("now", now);
		params.put("remark", remark);
		params.put("sign", sign);

		Map<String, File> fileParams = new HashMap<String, File>();
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String result = "";
		try {

			logger.debug(url);
			result = HttpUtils.post(url, headers, params, fileParams, responseHandler);
		} catch (IOException e) {
			logger.error("error..." + e);
			return Response.getError("请求失败");
		}
		logger.info("响应是...." + result);
		return Response.getSuccess(StringUtils.parseJSONStringToObject(result, new PosQuery()));
	}

	/**
	 * 钱袋宝POS开通状态查询
	 * 
	 * @param eqno设备号
	 * @param now查询时间
	 * @param remark备注
	 * @param sign签名
	 *            (md5方式)(MD5(eqno=XXXnow=XXXMD5KEY))
	 * @return
	 */
	@RequestMapping(value = "getAgentInfoByEqno")
	public String getAgentInfoByEqno(String eqno, String now, String remark, String sign) {
		logger.debug("接受的参数... eqno={},now={},remark={},sign={}", eqno, now, remark, sign);
		PosQuery pos = new PosQuery();
		Map<String, Object> map = equimentService.getEqByEqno(eqno);
		if (map != null) {
			if (EquipMent.OPEN_STATUS == Integer.parseInt(map.get("status").toString())) {
				pos.setCode(Constant.POS_SUCCESS_QUERY_CODE);
				pos.setMsg(Constant.SUCCESS_POS_MESSAGE);
				pos.setRemark("已开通");
			} else {
				pos.setCode(Constant.POS_NOT_OPENED_CODE);
				pos.setMsg(Constant.POS_NOT_OPENED_MESSAGE);
				pos.setRemark("未开通,请耐心等待.....");
			}

			pos.setEqno(eqno);
			pos.setAgentno(map.get("agentno") == null ? "" : map.get("agentno").toString());
			pos.setName(map.get("username") == null ? "" : map.get("username").toString());
			pos.setUsername(map.get("loginName") == null ? "" : map.get("loginName").toString());
		} else {
			pos.setCode(Constant.POS_NOT_EXIST_CODE);
			pos.setMsg(Constant.POS_NOT_EXIST_MESSAGE);
			pos.setRemark("该设备不存在");
		}

		StringBuilder sb = new StringBuilder();
		sb.append("code=" + pos.getCode());
		sb.append("eqno=" + eqno);
		sb.append(MD5key);
		String md5_str = StringUtils.encryption(sb.toString(), "MD5");
		pos.setSign(md5_str);
		String result = StringUtils.parseObjectToJSONString(pos);
		logger.info("响应是:{},签名为:{}", result, md5_str);
		return result;
	}

	@RequestMapping(value = "showorder")
	public String showorder() {
		String url = transactionQueryUrl;
		String eqno = "82316280";
		String querytype = "1";// 按照时间段查询
		String begintime = "2015-04-29 09:30";
		String endtime = "2015-04-29 18:30";
		String remark = "根据时间查询交易流水";
		StringBuilder sb = new StringBuilder();
		sb.append("eqno=" + eqno);
		if (Constant.SEARCH_TRADE_TYPE_1.equals(querytype)) {
			sb.append("querytype=" + querytype);
			remark = "根据设备编号查询交易流水";
		} else if (Constant.SEARCH_TRADE_TYPE_2.equals(querytype)) {
			sb.append("querytype=" + querytype);
			remark = "根据时间查询交易流水";
		} else if (Constant.SEARCH_TRADE_TYPE_3.equals(querytype)) {
			sb.append("querytype=" + querytype);
			remark = "根据时间和设备编号查询交易流水";
		}

		sb.append("begintime=" + begintime);
		sb.append("endtime=" + endtime);
		sb.append(MD5key);
		String sign = StringUtils.encryption(sb.toString(), "MD5");
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/x-www-form-urlencoded");

		Map<String, String> params = new HashMap<String, String>();
		params.put("eqno", eqno);
		params.put("querytype", querytype);
		params.put("begintime", begintime);
		params.put("endtime", endtime);
		params.put("sign", sign);
		params.put("remark", remark);
		Map<String, File> fileParams = new HashMap<String, File>();
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String result = "";
		try {
			logger.debug(url);
			result = HttpUtils.post(url, headers, params, fileParams, responseHandler);
		} catch (IOException e) {
			logger.error("error..." + e);
			return "请求失败";
		}
		qiandaibaoService.getTradeRecord();
		return result;
	}

}
