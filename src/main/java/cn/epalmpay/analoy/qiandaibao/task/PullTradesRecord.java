package cn.epalmpay.analoy.qiandaibao.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.epalmpay.analoy.qiandaibao.service.QiandaibaoService;

@Component
public class PullTradesRecord {

	@Autowired
	private QiandaibaoService qiandaibaoService;

	@Scheduled(cron = "0 0/1 * * * ?")
	public void task() {
		qiandaibaoService.getTradeRecord();
	}
}
