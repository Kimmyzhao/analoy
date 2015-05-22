package cn.epalmpay.analoy.scheduling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.epalmpay.analoy.entity.base.EquipMent;
import cn.epalmpay.analoy.service.base.EquimentService;

@Component
public class UpdateStatus {
	private static final Logger logger = LoggerFactory.getLogger(UpdateStatus.class);
	@Autowired
	private EquimentService equimentService;

	@Scheduled(cron = "0 0 0/2 * * ?")
	public void task() {
		logger.info("修改状态开始........");
		equimentService.updateStatus(EquipMent.NOT_OPEN_STATUS);
		logger.info("修改状态结束........");
	}
}
