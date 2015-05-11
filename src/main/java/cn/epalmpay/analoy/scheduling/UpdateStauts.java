package cn.epalmpay.analoy.scheduling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.epalmpay.analoy.service.EquimentService;

@Component
public class UpdateStauts {

	@Autowired
	private EquimentService equimentService;

	@Scheduled(cron = "0 0/30 * * * ?")
	public void task() {
		equimentService.updateStatus();
	}
}
