package cn.epalmpay.analoy.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

	public int updateStatus() {
		List<Map<String, Object>> list = equipMentMapper.getEqnoByStatus();
		if (list != null && !list.isEmpty()) {
			String[] ids = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				ids[i] = list.get(i).get("id").toString();
			}

			return equipMentMapper.updateStatus(ids);
		}
		return 0;
	}
}
