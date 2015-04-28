package cn.epalmpay.analoy.scheduling;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.epalmpay.analoy.zhonghui.service.ZhongHuiTaskService;

public class ZhongHuiPullTrades {
	private static final Logger logger = LoggerFactory.getLogger(ZhongHuiPullTrades.class);

	@Resource
	private ZhongHuiTaskService zhonghuiTaskService;

	public void job() {
		try {
			zhonghuiTaskService.getTradeRecord();
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
	}
}
