package cn.epalmpay.analoy.mapper;

import java.util.List;
import java.util.Map;

import cn.epalmpay.analoy.entity.base.Tbank;

public interface TbankMapper {
	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table tbank
	 *
	 * @mbggenerated
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table tbank
	 *
	 * @mbggenerated
	 */
	int insert(Tbank record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table tbank
	 *
	 * @mbggenerated
	 */
	Tbank selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table tbank
	 *
	 * @mbggenerated
	 */
	List<Tbank> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table tbank
	 *
	 * @mbggenerated
	 */
	int updateByPrimaryKey(Tbank record);

	/**
	 * 模糊查询银行
	 * 
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getBankList(Map<String, Object> params);

	int getBankListCount(Map<String, Object> params);
}