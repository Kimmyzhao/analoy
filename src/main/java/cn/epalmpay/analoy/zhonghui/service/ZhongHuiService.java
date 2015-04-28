package cn.epalmpay.analoy.zhonghui.service;

import cn.epalmpay.analoy.joint.JointManager;
import cn.epalmpay.analoy.model.trades.TradeRecord;
import cn.epalmpay.analoy.model.zhangfu.Terminal;
import cn.epalmpay.analoy.utils.page.Page;
import cn.epalmpay.analoy.utils.page.PageRequest;

public class ZhongHuiService implements JointManager {

	@Override
	public String syncStatus(Terminal terminal) {
		
		return null;
	}

	@Override
	public Page<TradeRecord> pullTrades(Terminal terminal, Integer tradeTypeId, PageRequest request) {
		return null;
	}

}
