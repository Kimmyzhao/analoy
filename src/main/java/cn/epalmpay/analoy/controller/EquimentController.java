package cn.epalmpay.analoy.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.epalmpay.analoy.model.Response;
import cn.epalmpay.analoy.service.EquimentService;

@RestController
@RequestMapping("api/equiment")
public class EquimentController {
	private static final Logger logger = LoggerFactory.getLogger(EquimentController.class);
	@Autowired
	private EquimentService equimentService;

	@RequestMapping(value = "addEqno", method = RequestMethod.POST)
	public Response addEqno(@RequestBody Map<String, Object> params) {
		Response res = new Response();
		try {
			logger.debug(params.get("type") + ":" + params.get("eqno").toString());
			String eqno = params.get("eqno").toString();
			int eqtype = Integer.parseInt(params.get("type").toString());
			List<Map<String, Object>> list = equimentService.getEquipmentByEqnoAndType(eqno,eqtype);
			if (list != null && list.size() > 0) {
				res.setCode(Response.ERROR_CODE);
				res.setMessage("添加失败:该终端已经存在");

			} else {
				res.setResult(equimentService.insert(params));
				res.setCode(Response.SUCCESS_CODE);
				res.setMessage("添加成功");
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			res.setCode(Response.ERROR_CODE);
			res.setMessage("系统错误--->添加失败");
		}
		return res;
	}
}
