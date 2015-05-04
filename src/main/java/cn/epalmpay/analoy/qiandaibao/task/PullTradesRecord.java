package cn.epalmpay.analoy.qiandaibao.task;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PullTradesRecord {
	@Value("${qiandaibao.url.pullTradesRecord}")
	private String pullTradesRecord;
}
