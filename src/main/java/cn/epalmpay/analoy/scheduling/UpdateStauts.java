package cn.epalmpay.analoy.scheduling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.epalmpay.analoy.service.EquimentService;

@Component
public class UpdateStauts {
	private static final Logger logger = LoggerFactory.getLogger(UpdateStauts.class);
	@Autowired
	private EquimentService equimentService;

	@Scheduled(cron = "0 0/5 * * * ?")
	public void task() {
		logger.info("修改状态开始........");
		equimentService.updateStatus();
		logger.info("修改状态结束........");
	}
}
