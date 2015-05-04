package cn.epalmpay.analoy.zhonghui.controller;

import java.io.IOException;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.epalmpay.analoy.zhonghui.service.ZhongHuiTaskService;

@RestController
@RequestMapping("api/zhonghui")
public class ZhongHuiController {
	private static final Logger logger = LoggerFactory.getLogger(ZhongHuiController.class);
	@Autowired
	private ZhongHuiTaskService zhongHuiTaskService;

	@RequestMapping(value = "showorder")
	public String showorder() throws IOException {
		try {
			return zhongHuiTaskService.getTradeRecord();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@RequestMapping(value = "getAgentInfoByEqno")
	public String getAgentInfoByEqno(String account, String password, String position, String appVersion, String product) {
		return null;
	}
}
