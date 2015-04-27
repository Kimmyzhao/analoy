package cn.epalmpay.analoy.joint;

import cn.epalmpay.analoy.model.trades.TradeRecord;
import cn.epalmpay.analoy.model.zhangfu.Terminal;
import cn.epalmpay.analoy.service.TerminalService;
import cn.epalmpay.analoy.utils.page.Page;
import cn.epalmpay.analoy.utils.page.PageRequest;

public interface JointManager {
	/**
	 * 同步状态
	 * 
	 * @return
	 */
	String syncStatus(Terminal terminal, TerminalService terminalService);

	/**
	 * 从第三方拉去交易流水
	 * 
	 * @param terminalId
	 *            终端号
	 * @param tradeTypeId
	 *            交易类型
	 */
	Page<TradeRecord> pullTrades(Terminal terminal, Integer tradeTypeId, PageRequest request);
}
