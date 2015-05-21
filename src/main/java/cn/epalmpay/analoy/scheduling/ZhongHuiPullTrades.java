package cn.epalmpay.analoy.scheduling;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.epalmpay.analoy.service.zhonghui.ZhongHuiTaskService;

@Component
public class ZhongHuiPullTrades {
	private static final Logger logger = LoggerFactory.getLogger(ZhongHuiPullTrades.class);

	@Resource
	private ZhongHuiTaskService zhonghuiTaskService;

	@Scheduled(cron = "0 0 1 * * ?")
	public void job() {
		try {
			logger.info("中汇交易流水开始推送.....");
			zhonghuiTaskService.pushTradeRecords();
			logger.info("中汇交易流水推送结束.....");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
