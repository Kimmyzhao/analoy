package cn.epalmpay.analoy.utils;

public class Constant {

	/**
	 * 查询成功
	 */
	public static final String POS_SUCCESS_QUERY_CODE = "00";
	/**
	 * 设备不存在
	 */
	public static final String POS_NOT_EXIST_CODE = "101";
	/**
	 * 未开通
	 */
	public static final String POS_NOT_OPENED_CODE = "102";
	/**
	 * 非掌富设备
	 */
	public static final String NOT_BELONGS_TO_ZFDEVICE_CODE = "103";

	/**
	 * 验证签名失败
	 */
	public static final String SIGN_CHECKED_ERROR_CODE = "104";

	/**
	 * 设备编号不能为空
	 */
	public static final String EQNO_CANNOT_BE_EMPTY_CODE = "105";

	/**
	 * 查询成功
	 */
	public static final String SUCCESS_POS_MESSAGE = "查询成功";
	/**
	 * 失败,设备不存在
	 */
	public static final String POS_NOT_EXIST_MESSAGE = "失败,设备不存在";
	/**
	 * 已经开通
	 */
	public static final String HAS_OPENED_POS_MESSAGE = "已经开通";
	/**
	 * 未开通
	 */
	public static final String POS_NOT_OPENED_MESSAGE = "未开通";

	public static final String NOT_BELONGS_TO_ZFDEVICE_MESSAGE = "非掌富设备";

	public static final String SIGN_CHECKED_ERROR_MESSAGE = "验签错误";

	public static final String EQNO_CANNOT_BE_EMPTY_MESSAGE = "设备编号不能为空";

	/**
	 * 成功并且有交易记录
	 */
	public static final String SUCCESS_TRADE_CODE = "00";
	/**
	 * 交易不存在
	 */
	public static final String NO_TRADE_CODE = "01";
	/**
	 * 其他未知错误
	 */
	public static final String ERROE_TRADE_CODE = "02";

	/**
	 * 根据设备编号查询
	 */
	public static final String SEARCH_TRADE_TYPE_1 = "1";
	/**
	 * 根据时间查询
	 */
	public static final String SEARCH_TRADE_TYPE_2 = "2";
	/**
	 * 根据时间和设备编号查询
	 */
	public static final String SEARCH_TRADE_TYPE_3 = "3";

	/**
	 * 成功并且有交易记录
	 */
	public static final String SUCCESS_TRADE_MESSAGE = "成功,并且有交易记录";
	/**
	 * 交易不存在
	 */
	public static final String NO_TRADE_MESSAGE = "交易不存在";
	/**
	 * 其他未知错误
	 */
	public static final String ERROE_TRADE_MESSAGE = "其他未知错误";


	public static final String THROUGH_THE_AUDIT_STATUS = "2222";// 开通成功
	public static final String NOT_THROUGH_THE_AUDIT_STATUS = "1111";// 开通成功

	public static final String ZHONGHUI_FLAG = "1";// 中汇
	public static final String HANXIN_FLAG = "2";// 韩鑫,现在他们推送订单服务器停了
	public static final String YIPIAOLIAN_FLAG = "3";// 易票联,不需要导入
	public static final String XISHUASHUA_FLAG = "4";// 嘻唰唰

	public static final String ILLEGAL_ARGUMENT = "ILLEGAL_ARGUMENT";
	public static final String ILLEGAL_LOGIN_OR_PASSWD = "ILLEGAL_LOGIN_OR_PASSWD";
	public static final String SUCCESS = "SUCCESS";

	public static final String ACTIVATED_ERROR_CODE = "ACTIVATED_FAILED";
	public static final String FILE_TYPE = ".txt";
	public static final String FILE_SPLIT = "||";

	public static final String ACTIVATED_SUCCESS_CODE = "ACTIVATED_SUCCESS";

	public static final String REGISTERED_SUCCESS = "REGISTERED_SUCCESS";
}
