package cn.epalmpay.analoy.service.zhonghui;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.epalmpay.analoy.mapper.TradeOrderFileMapper;
import cn.epalmpay.analoy.utils.HttpFile;

@Service
public class ZhongHuiTaskService {
	private static final Logger logger = LoggerFactory.getLogger(ZhongHuiTaskService.class);
	@Value("${zftiming.url}")
	private String url;
	@Value("${zhonghui.url.import}")
	private String importUrl;
	@Value("${filePath}")
	private String filepath;

	@Value("${path.root}")
	private String root;// 根目录

	@Autowired
	private TradeOrderFileMapper orderFileMapper;

	public String queryPosState() {
		return null;
	}

	/**
	 * 获取交易流水
	 * 
	 * @return
	 * @throws IOException
	 * @throws HttpException
	 */
	public String pushTradeRecords() throws IOException, HttpException {
		logger.debug(url + " == " + importUrl + " == " + root);

		List<Map<String, Object>> list = orderFileMapper.findFileUrlByIsPushed(1);
		File file = null;
		if (list != null && !list.isEmpty()) {
			String[] ids = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				file = new File(root + list.get(i).get("filepath").toString());
				logger.debug("上传文件的绝对路径为...." + file.getAbsolutePath() + "上传文件父级路径为..." + file.getParent());

				HttpFile.postHttp(this.url + this.importUrl, file.getParent(), file);
				ids[i] = list.get(i).get("id").toString();
			}
			logger.info(ids + "");
			orderFileMapper.updateRecordFile(ids);
		}

		return "ok";
	}

}
