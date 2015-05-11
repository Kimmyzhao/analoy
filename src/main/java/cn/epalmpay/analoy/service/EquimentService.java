package cn.epalmpay.analoy.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.epalmpay.analoy.entity.EquipMent;
import cn.epalmpay.analoy.mapper.EquipMentMapper;

@Service
public class EquimentService {

	@Autowired
	private EquipMentMapper equipMentMapper;

	public int insert(String eqno, String type) {
		EquipMent record = new EquipMent();
		record.setEqno(eqno);
		record.setEqtype(Integer.parseInt(type));
		record.setCreatedat(new Date());
		record.setStatus(2);
		return equipMentMapper.insert(record);

	}
}
