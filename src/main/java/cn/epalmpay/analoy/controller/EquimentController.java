package cn.epalmpay.analoy.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.epalmpay.analoy.entity.base.EquipMent;
import cn.epalmpay.analoy.service.base.EquimentService;
import cn.epalmpay.analoy.utils.page.Response;

@RestController
@RequestMapping("api/equiment")
public class EquimentController {
	private static final Logger logger = LoggerFactory.getLogger(EquimentController.class);
	@Autowired
	private EquimentService equimentService;

	/**
	 * 添加终端信息
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "addEqno", method = RequestMethod.POST)
	public Response addEqno(@RequestBody Map<String, Object> params) {
		Response res = new Response();
		try {
			logger.debug(params.get("type") + ":" + params.get("eqno").toString());
			String eqno = params.get("eqno").toString();
			int eqtype = Integer.parseInt(params.get("type").toString());
			EquipMent eq = equimentService.getEquipmentByEqnoAndType(eqno, eqtype);
			if (eq != null) {
				res.setCode(Response.ERROR_CODE);
				res.setMessage("添加失败:该终端已经存在");

			} else {

				int i = equimentService.insert(params);
				if (i == -1) {
					res.setCode(Response.ERROR_CODE);
					res.setMessage("添加失败:中汇设备手机号码必须唯一");
				} else {
					res.setResult(equimentService.insert(params));
					res.setCode(Response.SUCCESS_CODE);
					res.setMessage("添加成功");
				}

			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			res.setCode(Response.ERROR_CODE);
			res.setMessage("系统错误--->添加失败");
		}
		return res;
	}

	/**
	 * 获得终端列表
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public Response getList(@RequestBody Map<String, Object> params) {
		Response response = new Response();
		Map<String, Object> map = equimentService.getList();
		response.setCode(Response.SUCCESS_CODE);
		response.setResult(map);
		return response;
	}
}
