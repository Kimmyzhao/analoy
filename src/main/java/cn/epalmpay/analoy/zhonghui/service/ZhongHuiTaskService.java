package cn.epalmpay.analoy.zhonghui.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.epalmpay.analoy.utils.HttpFile;

@Service
public class ZhongHuiTaskService {
	private static final Logger logger = LoggerFactory.getLogger(ZhongHuiTaskService.class);
	@Value("${zhonghui.url}")
	private String url;
	@Value("${zhonghui.url.import}")
	private String importUrl;
	@Value("${filePath}")
	private String filepath;
	@Value("${zhonghui.import.file}")
	private String importfilepath;
	Map<String, MultipartFile> upfiles = new HashMap<String, MultipartFile>();

	public String queryPosState() {
		return null;
	}

	/**
	 * 获取交易流水
	 * 
	 * @return
	 * @throws IOException
	 * @throws HttpException
	 */
	public String getTradeRecord() throws IOException, HttpException {
		logger.debug(url + " == " + importUrl + " == " + filepath);
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("flag", "1");
		File file = new File(filepath + "2015-04-22.txt");
		HttpFile.postHttp(this.url + this.importUrl, importfilepath, file);
		// post(this.url + this.importUrl, headers, upfiles);
		HttpFile.upload(upfiles.get(0), this.url + this.importUrl);
		return "ok";
	}

	/**
	 * 第一份参数导入的URL,第二个参数为头部信息,第三个参数为上传的文件
	 * 
	 * @param url
	 * @param params
	 * @param files
	 * @return
	 * @throws MalformedURLException
	 */
	public static boolean post(String url, Map<String, String> params, Map<String, File> files) throws IOException {
		String BOUNDARY = java.util.UUID.randomUUID().toString();
		String PREFIX = "--", LINEND = "\r\n";
		String MULTIPART_FROM_DATA = "multipart/form-data";
		String CHARSET = "UTF-8";
		URL uri = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
		conn.setReadTimeout(5 * 1000);
		conn.setDoInput(true);// 允许输入
		conn.setDoOutput(true);// 允许输出
		conn.setUseCaches(false);
		conn.setRequestMethod("POST"); // Post方式
		conn.setRequestProperty("connection", "keep-alive");
		conn.setRequestProperty("Charsert", "UTF-8");
		conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA + ";boundary=" + BOUNDARY);
		// 首先组拼文本类型的参数
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			sb.append(PREFIX);
			sb.append(BOUNDARY);
			sb.append(LINEND);
			sb.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"" + LINEND);
			sb.append("Content-Type: text/plain; charset=" + CHARSET + LINEND);
			sb.append("Content-Transfer-Encoding: 8bit" + LINEND);
			sb.append(LINEND);
			sb.append(entry.getValue());
			sb.append(LINEND);
		}
		DataOutputStream outStream = new DataOutputStream(conn.getOutputStream());
		outStream.write(sb.toString().getBytes());
		// 发送文件数据
		if (files != null) {
			for (Map.Entry<String, File> file : files.entrySet()) {
				StringBuilder sb1 = new StringBuilder();
				sb1.append(PREFIX);
				sb1.append(BOUNDARY);
				sb1.append(LINEND);
				sb1.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getKey() + "\"" + LINEND);
				sb1.append("Content-Type: multipart/form-data; charset=" + CHARSET + LINEND);
				sb1.append(LINEND);
				outStream.write(sb1.toString().getBytes());
				InputStream is = new FileInputStream(file.getValue());
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = is.read(buffer)) != -1) {
					outStream.write(buffer, 0, len);
				}
				is.close();
				outStream.write(LINEND.getBytes());
			}
		}
		// 请求结束标志
		byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINEND).getBytes();
		outStream.write(end_data);
		outStream.flush();
		// 得到响应码
		boolean success = conn.getResponseCode() == 200;
		InputStream in = conn.getInputStream();
		InputStreamReader isReader = new InputStreamReader(in);
		BufferedReader bufReader = new BufferedReader(isReader);
		String line = null;
		String data = "getResult = ";
		while ((line = bufReader.readLine()) != null) {
			data += line;
		}
		logger.debug(data);
		outStream.close();
		conn.disconnect();
		return success;
	}

}
