package cn.epalmpay.analoy.scheduling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestTask {
	private static final Logger logger = LoggerFactory.getLogger(TestTask.class);

	@Scheduled(cron = "0/5 * * * * ?")
	public void task() {
		logger.info("开始记录日志........");
		logger.info("Hello World");
		logger.info("日志记录结束........");
	}
}
