package cn.epalmpay.analoy.qiandaibao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.epalmpay.analoy.qiandaibao.service.QiandaibaoService;

@RestController
@RequestMapping("api/hello")
public class HelloController {

	@Autowired
	private QiandaibaoService qiandaibaoService;

	@RequestMapping(value = "sayHello")
	public String sayHello() {
		qiandaibaoService.sayHello();
		return "ok";
	}
}
