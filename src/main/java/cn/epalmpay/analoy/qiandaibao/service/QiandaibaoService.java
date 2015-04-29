package cn.epalmpay.analoy.qiandaibao.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import cn.epalmpay.analoy.qiandaibao.constant.Constant;
import cn.epalmpay.analoy.qiandaibao.entity.PosQuery;
import cn.epalmpay.analoy.qiandaibao.entity.TransactionRecordQuery;
import cn.epalmpay.analoy.utils.FileUtils;
import cn.epalmpay.analoy.utils.StringUtils;

@Service
public class QiandaibaoService {
	private static final Logger logger = LoggerFactory.getLogger(QiandaibaoService.class);
	@Value("${MD5key}")
	private String MD5key;

	public void sayHello() {
		System.out.println("Hello World!");
	}

	public String getTradeRecord() {
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

}
