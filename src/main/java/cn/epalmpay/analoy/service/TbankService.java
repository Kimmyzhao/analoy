package cn.epalmpay.analoy.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.epalmpay.analoy.mapper.TbankMapper;

@Service
public class TbankService {

	@Autowired
	private TbankMapper tbankMapper;

	/**
	 * 获取银行列表
	 * 
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> getBankList(Map<String, Object> params) {
		return tbankMapper.getBankList(params);
	}
	
	public int getBankListCount(Map<String, Object> params){
		return tbankMapper.getBankListCount(params);
	}
}
