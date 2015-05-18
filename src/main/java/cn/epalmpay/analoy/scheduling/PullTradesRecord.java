package cn.epalmpay.analoy.scheduling;

import org.springframework.beans.factory.annotation.Autowired;

import cn.epalmpay.analoy.service.qiandaibao.QiandaibaoService;

//@Component
public class PullTradesRecord {

	@Autowired
	private QiandaibaoService qiandaibaoService;

	// @Scheduled(cron = "0 0/10 * * * ?")
	public void task() {
		qiandaibaoService.getTradeRecord();
	}
}
