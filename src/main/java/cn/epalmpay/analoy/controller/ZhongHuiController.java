package cn.epalmpay.analoy.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.epalmpay.analoy.service.EquimentService;
import cn.epalmpay.analoy.utils.Constant;
import cn.epalmpay.analoy.utils.DataUtils;
import cn.epalmpay.analoy.utils.StringUtils;
import cn.epalmpay.analoy.zhonghui.service.ZhongHuiTaskService;
import cn.epalmpay.analoy.zhonghui.entity.LoginResq;
import cn.epalmpay.analoy.zhonghui.entity.Resp;
import cn.epalmpay.analoy.zhonghui.entity.ResponseResult;

@RestController
@RequestMapping("api/zhonghui")
public class ZhongHuiController {
	private static final Logger logger = LoggerFactory.getLogger(ZhongHuiController.class);
	@Autowired
	private ZhongHuiTaskService zhongHuiTaskService;

	@Value("${joint.zhonghui.product}")
	private String product;

	@Autowired
	private EquimentService equimentService;

	@RequestMapping(value = "showorder")
	public String showorder() throws IOException {
		try {
			return zhongHuiTaskService.getTradeRecord();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@RequestMapping(value = "user/login")
	public String login(String reqTime, String loginName, String password, String position, String appVersion, String product) {
		String date = StringUtils.dateToString(new Date(), "yyyyMMddHHmmss");
		if (reqTime == null || "".equals(reqTime)) {
			return StringUtils.parseObjectToJSONString(new Resp(date, false, Constant.ILLEGAL_ARGUMENT, "缺少参数" + reqTime));
		}

		if (loginName == null || "".equals(loginName)) {
			return StringUtils.parseObjectToJSONString(new Resp(date, false, Constant.ILLEGAL_ARGUMENT, "缺少参数" + loginName));
		}
		if (password == null || "".equals(password)) {
			return StringUtils.parseObjectToJSONString(new Resp(date, false, Constant.ILLEGAL_ARGUMENT, "缺少参数" + password));
		}
		if (position == null || "".equals(position)) {
			return StringUtils.parseObjectToJSONString(new Resp(date, false, Constant.ILLEGAL_ARGUMENT, "缺少参数" + position));
		}
		if (appVersion == null || "".equals(appVersion)) {
			return StringUtils.parseObjectToJSONString(new Resp(date, false, Constant.ILLEGAL_ARGUMENT, "缺少参数" + appVersion));
		}
		if (product == null || "".equals(product)) {
			return StringUtils.parseObjectToJSONString(new Resp(date, false, Constant.ILLEGAL_ARGUMENT, "缺少参数" + product));
		}

		Map<String, Object> map = equimentService.login(loginName, StringUtils.encryption(password, "MD5"));
		if (map == null) {
			return StringUtils.parseObjectToJSONString(new Resp(date, false, Constant.ILLEGAL_LOGIN_OR_PASSWD, "登录名或密码错误"));
		} else {
			ResponseResult result = new ResponseResult();
			result.setRespTime(date);
			result.setRespMsg("登录成功");
			result.setRespCode(Constant.SUCCESS);
			if (map.get("status") == null && Integer.parseInt(map.get("status").toString()) == 1) {
				result.setSuccess(true);
				result.setStatus("2222");
			} else {
				result.setSuccess(false);
			}
			Object receivecardno = map.get("receivecardno");
			result.setCardTail(receivecardno == null ? "" : receivecardno.toString().substring(receivecardno.toString().length() - 4, receivecardno.toString().length()));
			result.setSerialType("0.78");
			Object username = map.get("username");
			result.setName(username == null ? "" : username.toString());
			return StringUtils.parseObjectToJSONString(result);
		}
	}

	@RequestMapping(value = "getAgentInfoByEqno")
	public String getAgentInfoByEqno(String loginName, String password, String position, String appVersion, String product) {
		String[] name = new String[] { "张三", "李四", "王五" };
		String[] ksnNo = new String[] { "501000093906", "501000093915", "501000093919" };
		String[] phone = new String[] { "18667027576", "13262872386", "13636543059" };
		String[] model = new String[] { "woshua", "aishua", "mpos2", "mpos3", "zfshua", "zfmini" };
		String[] cardno = new String[] { "621559******0617", "622252******9067", "622556******5151" };
		List<String> list = Arrays.asList(phone);
		String respTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		if (loginName == null || "".equals(loginName)) {
			return StringUtils.parseObjectToJSONString(new Resp(respTime, false, Constant.ILLEGAL_ARGUMENT, "缺少参数loginName"));
		} else if (password == null || "".equals(password)) {
			return StringUtils.parseObjectToJSONString(new Resp(respTime, false, Constant.ILLEGAL_ARGUMENT, "缺少参数password"));
		} else if (!list.contains(loginName) || !"123456".equals(password)) {
			return StringUtils.parseObjectToJSONString(new Resp(respTime, false, Constant.ILLEGAL_LOGIN_OR_PASSWD, "登录名或密码错误"));
		}

		LoginResq resq = new LoginResq();
		int index = DataUtils.generateInt(3);
		resq.setRespTime(respTime);
		resq.setKsnNo(ksnNo[index]);
		resq.setStatus(Constant.THROUGH_THE_AUDIT_STATUS);
		resq.setRespCode(Constant.SUCCESS);
		resq.setName(name[index]);
		resq.setSuccess(true);
		resq.setRespMsg("成功");
		resq.setCardTail(cardno[index].substring(cardno[index].length() - 4, cardno[index].length()));
		resq.setModel(model[DataUtils.generateInt(6)]);
		logger.debug(StringUtils.parseObjectToJSONString(resq));
		return StringUtils.parseObjectToJSONString(resq);
	}

	public static void main(String[] args) {
		new ZhongHuiController().getAgentInfoByEqno("18667027576", "123456", "460,0,6157,55153", "zfmini.1.3.111", "SHZF");
	}
}
