package cn.epalmpay.analoy.zhonghui.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.epalmpay.analoy.zhonghui.service.ZhongHuiTaskService;

@RestController
@RequestMapping("api/zhonghui")
public class ZhongHuiController {

	@Autowired
	private ZhongHuiTaskService zhongHuiService1;

	@RequestMapping(value = "showorder")
	public String showorder() throws IOException {
		return zhongHuiService1.getTradeRecord();
	}
}
