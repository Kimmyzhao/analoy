package cn.epalmpay.analoy.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

@Service
public class TerminalService {
	
	private static final Logger LOG = LoggerFactory.getLogger(TerminalService.class);

//	@Autowired
//	private TerminalMapper terminalMapper;
//	@Autowired
//	private OpeningApplieMapper openingApplieMapper;
//	@Autowired
//	private TerminalTradeTypeInfoMapper terminalTradeTypeInfoMapper;
//	@Autowired
//	private OperateRecordService operateRecordService;
//	@Autowired
//	private DictionaryOpenPrivateInfoMapper dictionaryOpenPrivateInfoMapper;
	@Value("${file.http.domain}")
	private String fileHttpRoot;
//	
//	public OpeningApplie findOpeningAppylByTerminalId(Integer terminalId){
//		return openingApplieMapper.selectOpeningApplie(terminalId);
//	}
//	
//	public Terminal findById(Integer id) {
//		return terminalMapper.selectByPrimaryKey(id);
//	}
//	
//	public void updateOpeningApply(OpeningApplie oa) {
//		openingApplieMapper.updateByPrimaryKey(oa);
//	}
//	
//	public Terminal findBySerial(String serial) {
//		return terminalMapper.selectBySerial(serial);
//	}
//	
//	public void updateTerminal(Terminal terminal) {
//		terminalMapper.updateByPrimaryKey(terminal);
//	}
//	
//	public void createNewTerminal(Terminal terminal,String newSerialNum) {
//		//创建新的终端
//		Terminal newTerminal = new Terminal();
//		BeanUtils.copyProperties(terminal, newTerminal);
//		newTerminal.setId(null);
//		newTerminal.setSerialNum(newSerialNum);
//		terminalMapper.insert(newTerminal);
//		//把原有终端停用
//		terminal.setStatus(Terminal.STATUS_STOPED);
//		updateTerminal(terminal);
//		//创建新的opening apply
//		OpeningApplie oa = findOpeningAppylByTerminalId(terminal.getId());
//		OpeningApplie newOa = new OpeningApplie();
//		BeanUtils.copyProperties(oa, newOa);
//		newOa.setId(null);
//		newOa.setTerminalId(newTerminal.getId());
//		openingApplieMapper.insert(newOa);
//		//创建新的terminalTradeTypeInfo
//		List<TerminalTradeTypeInfo> infos = findTerminalTradeTypeInfos(terminal.getId());
//		for(TerminalTradeTypeInfo info : infos) {
//			TerminalTradeTypeInfo newInfo = new TerminalTradeTypeInfo();
//			BeanUtils.copyProperties(info, newInfo);
//			newInfo.setId(null);
//			newInfo.setTerminalId(newTerminal.getId());
//			terminalTradeTypeInfoMapper.insert(newInfo);
//		}
//	}
//	
//	//更新TerminalTradeTypeInfo状态
//	public void updateTerminalTradeTypeStatus(Integer status, Integer terminalId, Integer tradeTypeId){
//		terminalTradeTypeInfoMapper.updateStatus(status, terminalId, tradeTypeId);
//	}
//	
//	public void updateTerminalTradeTypeStatus(Integer status, Integer terminalId){
//		terminalTradeTypeInfoMapper.updateStatus(status, terminalId, null);
//	}
//	
//	@Cacheable("allOpenPrivateInfoMap")
//	public Map<Integer, DictionaryOpenPrivateInfo> allOpenPrivateInfos(){
//		List<DictionaryOpenPrivateInfo> openPrivateInfos = dictionaryOpenPrivateInfoMapper.selectAll();
//		return openPrivateInfos.stream().collect(
//				Collectors.toMap(DictionaryOpenPrivateInfo::getId, Function.identity()));
//	}
//	
//	public List<TerminalTradeTypeInfo> findTerminalTradeTypeInfos(Integer terminalId){
//		return terminalTradeTypeInfoMapper.selectTerminalTradeTypeInfos(terminalId);
//	}
	
	public File path2File(String path){
		UrlResource ur = null;
		File file = null;
		try {
			ur = new UrlResource(fileHttpRoot+path);
			file = File.createTempFile("image", ur.getFilename());
		} catch (IOException e1) {
			LOG.error("",e1);
			return null;
		}
		try (InputStream is = ur.getInputStream();
			FileOutputStream fos = new FileOutputStream(file)){
			StreamUtils.copy(is, fos);
			return file;
		} catch (IOException e) {
			LOG.error("",e);
		}
		return null;
	}
	
//	public Integer findNotTradeTypeStatus(Terminal terminal) {
//		List<TerminalTradeTypeInfo> infos = terminalTradeTypeInfoMapper.selectTerminalTradeTypeInfos(terminal.getId());
//		for(TerminalTradeTypeInfo info : infos) {
//			if(info.getTradeTypeId() != DictionaryTradeType.ID_TRADE){
//				return info.getStatus();
//			}
//		}
//		return null;
//	}
//	
//	public void updateNotTradeTypeStatus(Terminal terminal,String idCard,String name){
//		List<TerminalTradeTypeInfo> infos = terminalTradeTypeInfoMapper.selectTerminalTradeTypeInfos(terminal.getId());
//		byte terminalStatus = Terminal.STATUS_OPENED;
//		for(TerminalTradeTypeInfo info : infos){
//			if(info.getTradeTypeId() == DictionaryTradeType.ID_TRADE){
//				if(info.getStatus() == TerminalTradeTypeInfo.STATUS_NO_OPEN){
//					terminalStatus = Terminal.STATUS_PART_OPENED;//如果交易类型未开通，终端设为部分开通
//				}
//			}else{
//				info.setStatus(TerminalTradeTypeInfo.STATUS_OPENED);
//				terminalTradeTypeInfoMapper.updateByPrimaryKey(info);
//			}
//		}
//		
//		terminal.setStatus(terminalStatus);
//		terminalMapper.updateByPrimaryKey(terminal);
//		
//		//将这次的传递过来的参数拼接存入到operate_records中
//		operateRecordService.saveOperateRecord(
//				OperateRecord.TYPE_TRADE_RECORD,
//				terminal.getId(),
//				OperateRecord.TARGET_TYPE_TERMINAL,
//				MessageFormat.format("terminal[{0}] idCard:{1},name:{2}", terminal.getSerialNum(), idCard,name));
//	}
//	
//	public void recordSubmitFail(OpeningApplie oa,String type,String code,String msg){
//		oa.setSubmitStatus(OpeningApplie.SUBMIT_STATUS_FAIL);
//		updateOpeningApply(oa);
//		//失败内容记录到operate_records
//		operateRecordService.saveOperateRecord(
//				OperateRecord.TYPE_OPENING_APPLY,
//				oa.getId(),
//				OperateRecord.TARGET_TYPE_OPENING_APPYL,
//				MessageFormat.format("[{0}] code:{1},msg:{2}", type, code,msg));
//	}
}
