package cn.epalmpay.analoy.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.epalmpay.analoy.entity.EquipMent;
import cn.epalmpay.analoy.mapper.EquipMentMapper;
import cn.epalmpay.analoy.utils.DataUtils;
import cn.epalmpay.analoy.utils.StringUtils;

@Service
public class EquimentService {

	@Autowired
	private EquipMentMapper equipMentMapper;

	public int insert(String eqno, String type) {
		String[] agentno = new String[] { "986856192260", "986825803310", "981818190230", "981818216288", "986826060820", "981818680111" };
		String[] agentname = new String[] { "上海掌富网络科技有限公司", "香飘飘奶茶股份公司", "海底捞", "快的打车", "2345网络科技有限公司", "PPTV" };
		EquipMent record = new EquipMent();
		record.setEqno(eqno);
		record.setEqtype(Integer.parseInt(type));
		record.setCreatedat(new Date());
		record.setStatus(2);
		int agentno_index = DataUtils.generateInt(6);
		record.setAgentno(agentno[agentno_index]);
		record.setAgentname(agentname[agentno_index]);

		if ("2".equals(type)) {// 中汇
			record.setPassword(StringUtils.encryption("123456", "MD5"));
		}
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

	public Map<String, Object> getEqByEqno(String eqno) {
		return equipMentMapper.getEqByEqno(eqno);
	}

	public Map<String, Object> login(String loginName, String password) {
		return equipMentMapper.login(loginName, password);
	}
}
