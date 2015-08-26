package cn.epalmpay.analoy.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.epalmpay.analoy.entity.zhonghui.LoginResq;
import cn.epalmpay.analoy.utils.StringUtils;

@RestController
@RequestMapping("api/test")
public class TestController {
  private static final Logger logger = LoggerFactory.getLogger(TestController.class);

  public static String loginResult = null;

  /**
   * 添加终端信息
   * 
   * @param params
   * @return
   */
  @RequestMapping(value = "addFee", method = RequestMethod.POST)
  public String addEqno(@RequestBody Map<String, Object> params) {
    logger.info(params.toString());
    String serialType = params.get("fee").toString();
    if (params.get("topCharge") != null && !"".equals(params.get("topCharge"))) {
      serialType = serialType + "--" + params.get("topCharge").toString();
    }
    String respTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

    LoginResq resq = new LoginResq();
    resq.setRespTime(respTime);
    resq.setIsSuccess(true);
    resq.setRespCode("SUCCESS");
    resq.setRespMsg("测试测试测试测试测试测试测试测试测试测试");
    resq.setStatus(params.get("status").toString());
    resq.setName("与椰蓉");
    resq.setCardTail("8907");
    resq.setKsnNo(params.get("eqno").toString());
    resq.setSerialType(serialType);
    resq.setNextReqNo(8);
    resq.setArgument(new HashMap<String, Object>());
    resq.setSessionKey("7CFF5CA0030BD545227A07027FE9EADF");
    resq.setKeyCheck("12E0926D");
    resq.setIsBluetooth(false);
    resq.setModel("zfmini");
    resq.setBluetoothName("");
    String result = StringUtils.parseObjectToJSONString(resq);
    loginResult = result;
    logger.info(result);
    return result;
  }
}
