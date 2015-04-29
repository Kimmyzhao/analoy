package cn.epalmpay.analoy.qiandaibao.controller;

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

import cn.epalmpay.analoy.model.Response;
import cn.epalmpay.analoy.qiandaibao.constant.Constant;
import cn.epalmpay.analoy.qiandaibao.entity.PosQuery;
import cn.epalmpay.analoy.qiandaibao.service.QiandaibaoService;
import cn.epalmpay.analoy.utils.HttpUtils;
import cn.epalmpay.analoy.utils.StringUtils;

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

	private String begintime;// 开始时间
	private String endtime;// 结束时间

	public String getBegintime() {
		return begintime;
	}

	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	@Autowired
	private QiandaibaoService qiandaibaoService;

	@RequestMapping(value = "getAgentInfoByEqno", method = RequestMethod.POST)
	public Response getAgentInfoByEqno(@RequestBody Map<String, Object> param) {
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
			System.out.println(url);
			logger.debug(url);
			result = HttpUtils.post(url, headers, params, fileParams, responseHandler);
		} catch (IOException e) {
			logger.error("error..." + e);
			return Response.getError("请求失败");
		}
		System.out.println(result);
		return Response.getSuccess(StringUtils.parseJSONStringToObject(result, new PosQuery()));
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

	@RequestMapping(value = "showorder1")
	public String showorder1() {
		logger.debug(begintime + " ==== " + endtime);
		return qiandaibaoService.getTradeRecord();
	}

	@RequestMapping(value = "getAgentInfoByEqno1")
	public String getAgentInfoByEqno1() {
		return qiandaibaoService.queryPosState();
	}
}
