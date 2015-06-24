package cn.epalmpay.analoy.controller.zhonghui;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.epalmpay.analoy.entity.base.EquipMent;
import cn.epalmpay.analoy.entity.zhonghui.ActivateResult;
import cn.epalmpay.analoy.entity.zhonghui.Bank;
import cn.epalmpay.analoy.entity.zhonghui.BankResult;
import cn.epalmpay.analoy.entity.zhonghui.LoginResq;
import cn.epalmpay.analoy.entity.zhonghui.Resp;
import cn.epalmpay.analoy.entity.zhonghui.ResponseResult;
import cn.epalmpay.analoy.service.base.EquimentService;
import cn.epalmpay.analoy.service.base.TbankService;
import cn.epalmpay.analoy.service.zhonghui.ZhongHuiTaskService;
import cn.epalmpay.analoy.utils.Constant;
import cn.epalmpay.analoy.utils.DataUtils;
import cn.epalmpay.analoy.utils.StringUtils;

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

	@Autowired
	private TbankService tbankService;

	@RequestMapping(value = "showorder")
	public String showorder() throws IOException {
		try {
			return zhongHuiTaskService.pushTradeRecords();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	/**
	 * 
	 * { "respTime": "20150516163252", "isSuccess": true, "respCode": "SUCCESS",
	 * "respMsg": "登录成功", "status": "2222", "name": "刘引惟", "cardTail": "9477",
	 * "ksnNo": "501010033373", "bluetoothName": "", "serialType": "0.78",
	 * "model": "zfmini", "nextReqNo": 67, "argument": {}, "sessionKey":
	 * "01B6A60F77F58D5AA74182DB826198B4", "keyCheck": "5C868F01",
	 * "isBluetooth": false }
	 * 
	 * @param reqTime
	 * @param loginName
	 * @param password
	 * @param position
	 * @param appVersion
	 * @param product
	 * @return
	 */
	@RequestMapping(value = "user/login", method = RequestMethod.POST)
	public String login(String reqTime, String loginName, String password, String position, String appVersion, String product) {
		String date = StringUtils.dateToString(new Date(), "yyyyMMddHHmmss");
		if (reqTime == null || "".equals(reqTime)) {
			return StringUtils.parseObjectToJSONString(new Resp(date, false, Constant.ILLEGAL_ARGUMENT, "缺少参数reqTime"));
		}

		if (loginName == null || "".equals(loginName)) {
			return StringUtils.parseObjectToJSONString(new Resp(date, false, Constant.ILLEGAL_ARGUMENT, "缺少参数loginName"));
		}
		if (password == null || "".equals(password)) {
			return StringUtils.parseObjectToJSONString(new Resp(date, false, Constant.ILLEGAL_ARGUMENT, "缺少参数password"));
		}
		if (position == null || "".equals(position)) {
			return StringUtils.parseObjectToJSONString(new Resp(date, false, Constant.ILLEGAL_ARGUMENT, "缺少参数position"));
		}
		if (appVersion == null || "".equals(appVersion)) {
			return StringUtils.parseObjectToJSONString(new Resp(date, false, Constant.ILLEGAL_ARGUMENT, "缺少参数appVersion"));
		}
		if (product == null || "".equals(product)) {
			return StringUtils.parseObjectToJSONString(new Resp(date, false, Constant.ILLEGAL_ARGUMENT, "缺少参数product"));
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
				result.setIsSuccess(true);
				result.setStatus("2222");
			} else {
				result.setIsSuccess(false);
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
		resq.setIsSuccess(true);
		resq.setRespMsg("成功");
		resq.setCardTail(cardno[index].substring(cardno[index].length() - 4, cardno[index].length()));
		resq.setModel(model[DataUtils.generateInt(6)]);
		logger.debug(StringUtils.parseObjectToJSONString(resq));
		return StringUtils.parseObjectToJSONString(resq);
	}

	/**
	 * 激活
	 * 
	 * @param licenseCode
	 * @param ksnNo
	 * @param appVersion
	 * @param product
	 * @return
	 */
	@RequestMapping(value = "/swiper/register", method = RequestMethod.POST)
	public String activate(String reqTime, String licenseCode, String ksnNo, String appVersion, String product) {
		String response = "";
		String date = StringUtils.dateToString(new Date(), "yyyyMMddHHmmss");
		if (licenseCode == null || "".equals(licenseCode)) {
			response = StringUtils.parseObjectToJSONString(new Resp(date, false, Constant.ILLEGAL_ARGUMENT, "缺少参数licenseCode"));
			logger.info(response);
			return response;
		}
		EquipMent eq = equimentService.getEquipmentByEqnoAndCode(ksnNo, licenseCode);
		ActivateResult result = new ActivateResult();
		if (eq == null) {
			response = StringUtils.parseObjectToJSONString(new Resp(date, false, Constant.ACTIVATED_ERROR_CODE, "设备不存在或者激活码错误"));
			logger.info(response);
			return response;
		} else {
			if (eq.getActivated() == EquipMent.ACTIVATE_STATUS_NO_ACTIVED) {// 未激活
				equimentService.activatedByKsnNo(ksnNo, EquipMent.EQTYPE_ZHONGHUI, EquipMent.ACTIVATE_STATUS_NO_REGISTED);// 更改为激活未注册
				result.setIsSuccess(true);
				result.setRespMsg("设备已经激活成功但还未注册");
				result.setSerialType("0.78");
			} else if (EquipMent.ACTIVATE_STATUS_NO_REGISTED == eq.getActivated()) {// 激活未注册
				equimentService.activatedByKsnNo(ksnNo, EquipMent.EQTYPE_ZHONGHUI, EquipMent.ACTIVATE_STATUS_REGISTED);// 更改为激活注册
				result.setIsSuccess(false);
				result.setRespMsg("设备已经激活成功并已注册");
			} else if (EquipMent.ACTIVATE_STATUS_REGISTED == eq.getActivated()) {// 激活注册
				response = StringUtils.parseObjectToJSONString(new Resp(date, false, Constant.ACTIVATED_ERROR_CODE, "设备已经激活并注册"));
				logger.info(response);
				return response;
			}
		}
		result.setRespCode(Constant.ACTIVATED_SUCCESS_CODE);
		result.setRespTime(date);

		response = StringUtils.parseObjectToJSONString(result);
		logger.info(response);
		return response;
	}

	/**
	 * 
	 * @param reqTime
	 * @param ksnNo
	 * @param name
	 * @param mobile
	 * @param password
	 * @param registPosition
	 * @param appVersion
	 * @param product
	 * @return
	 */
	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	public String register(String reqTime, String ksnNo, String name, String mobile, String password, String registPosition, String appVersion, String product) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("eqno", ksnNo);
		params.put("loginName", mobile);
		params.put("password", StringUtils.encryption(password, "MD5"));
		params.put("appVersion", appVersion);
		params.put("product", product);
		params.put("username", name);
		params.put("activated", EquipMent.ACTIVATE_STATUS_REGISTED);
		params.put("eqtype", EquipMent.EQTYPE_ZHONGHUI);
		equimentService.updateEquipment(params);

		Resp result = new Resp();
		result.setRespCode(Constant.REGISTERED_SUCCESS);
		result.setRespMsg("注册成功");
		result.setRespTime(StringUtils.dateToString(new Date(), "yyyyMMddHHmmss"));
		result.setIsSuccess(true);
		String response = StringUtils.parseObjectToJSONString(result);
		logger.debug(response);
		return response;
	}

	/**
	 * 模糊查询银行
	 * 
	 * { "respTime": "20150520130443", "isSuccess": true, "respCode": "SUCCESS",
	 * "respMsg": "成功", "total": 647, "tip": "结果较多, 建议使用精确关键字, 例如添加地名等",
	 * "banks": [ { "bankDeposit": "中国工商银行股份有限公司无锡吴桥支行", "unionBankNo":
	 * "102302002105" }, { "bankDeposit": "中国工商银行股份有限公司无锡河埒支行", "unionBankNo":
	 * "102302002113" }, { "bankDeposit": "中国工商银行股份有限公司无锡太湖国家旅游度假区支行",
	 * "unionBankNo": "102302002121" }, { "bankDeposit": "中国工商银行股份有限公司无锡湖滨苑分理处",
	 * "unionBankNo": "102302002130" }, { "bankDeposit": "中国工商银行股份有限公司无锡长街支行",
	 * "unionBankNo": "102302002148" }, { "bankDeposit": "中国工商银行股份有限公司无锡会龙桥支行",
	 * "unionBankNo": "102302002156" }, { "bankDeposit": "中国工商银行股份有限公司无锡胡埭支行",
	 * "unionBankNo": "102302002172" }, { "bankDeposit": "中国工商银行股份有限公司无锡梁青支行",
	 * "unionBankNo": "102302002201" }, { "bankDeposit": "中国工商银行股份有限公司无锡锡沪路支行",
	 * "unionBankNo": "102302002228" }, { "bankDeposit": "中国工商银行股份有限公司无锡清扬支行",
	 * "unionBankNo": "102302002252" }, { "bankDeposit": "中国工商银行股份有限公司无锡迎溪支行",
	 * "unionBankNo": "102302002316" }, { "bankDeposit": "中国工商银行股份有限公司无锡惠钱路分理处",
	 * "unionBankNo": "102302002324" }, { "bankDeposit":
	 * "中国工商银行股份有限公司无锡蠡园开发区支行", "unionBankNo": "102302002332" }, {
	 * "bankDeposit": "中国工商银行股份有限公司无锡硕放支行", "unionBankNo": "102302002349" }, {
	 * "bankDeposit": "中国工商银行盐城通榆中路支行", "unionBankNo": "102311066155" }, {
	 * "bankDeposit": "中国工商银行盐城盐马路支行", "unionBankNo": "102311066171" }, {
	 * "bankDeposit": "中国工商银行盐城盐都支行", "unionBankNo": "102311066180" }, {
	 * "bankDeposit": "中国工商银行盐城亭湖支行", "unionBankNo": "102311066219" }, {
	 * "bankDeposit": "中国工商银行盐城青年路支行", "unionBankNo": "102311066227" }, {
	 * "bankDeposit": "中国工商银行盐城南元支行", "unionBankNo": "102311066235" } ] }
	 * 
	 * @param keyword
	 * @param max
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/bank/query", method = RequestMethod.POST)
	public String queryBanks(String keyword, int max, int p) {
		String response = "";
		logger.info("接受的参数...keyword={},p={},max={}", keyword, p, max);
		String date = StringUtils.dateToString(new Date(), "yyyyMMddHHmmss");

		if (keyword == null || "".equals(keyword)) {
			response = StringUtils.parseObjectToJSONString(new Resp(date, false, Constant.ILLEGAL_ARGUMENT, "请输入关键字"));
			logger.info(response);
			return response;
		}

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bankName", "%" + keyword + "%");
		params.put("page", p);
		params.put("pageSize", max);

		List<Map<String, Object>> list = tbankService.getBankList(params);
		int total = tbankService.getBankListCount(params);
		BankResult result = new BankResult();
		Bank bank = new Bank();
		List<Bank> banks = new ArrayList<Bank>();
		if (list.size() > 100) {
			result.setTip("结果较多, 建议使用精确关键字, 例如添加地名等");
		} else {
			result.setTip("查询成功");
		}

		result.setRespTime(date);
		result.setIsSuccess(true);
		result.setRespMsg("成功");
		result.setRespCode(Constant.SUCCESS);
		result.setTotal(total);
		for (int i = 0, j = list.size(); i < j; i++) {
			Map<String, Object> map = list.get(i);
			bank.setBankDeposit(map.get("bankDeposit") == null ? "" : map.get("bankDeposit").toString());
			bank.setUnionBankNo(map.get("unionBankNo") == null ? "" : map.get("unionBankNo").toString());
			banks.add(bank);
		}
		result.setBanks(banks);
		response = StringUtils.parseObjectToJSONString(result);

		logger.info("返回的结果是..." + response);
		return response;
	}
}
