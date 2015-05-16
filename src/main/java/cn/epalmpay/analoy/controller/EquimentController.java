package cn.epalmpay.analoy.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.epalmpay.analoy.service.EquimentService;

@RestController
@RequestMapping("api/equiment")
public class EquimentController {
	private static final Logger logger = LoggerFactory.getLogger(EquimentController.class);
	@Autowired
	private EquimentService equimentService;

	@RequestMapping(value = "addEqno", method = RequestMethod.POST)
	public String addEqno(@RequestBody Map<String, Object> params) {
		logger.debug(params.get("type") + ":" + params.get("eqno").toString());
		//equimentService.insert(params);
		return "ok";
	}
}
