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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@Autowired
	private QiandaibaoService qiandaibaoService;

	@RequestMapping(value = "getAgentInfoByEqno")
	public String getAgentInfoByEqno() {
		String eqno = "82316280";
		String now = "2015-04-27 09:30";
		String remark = "根据POS终端号查询开通状态";
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
			result = HttpUtils.post(posQueryUrl, headers, params, fileParams, responseHandler);
		} catch (IOException e) {
			logger.error("error..." + e);
			return "请求失败";
		}
		logger.debug(result);
		return "ok";
	}
}
