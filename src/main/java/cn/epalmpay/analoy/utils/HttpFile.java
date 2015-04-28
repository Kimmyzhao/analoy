package cn.epalmpay.analoy.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.springframework.web.multipart.MultipartFile;

public class HttpFile {

	private static String localpath = "/opt/data/";
	private static String urlpath = "http://121.40.84.2:8888/File/index/upload";
	private static final String BOUNDARY = "*****";
	private static final String PREFIX = "--", LINEND = "\r\n";
	private static final String MULTIPART_FROM_DATA = "multipart/form-data";
	private static final String CHARSET = "UTF-8";

	public static String upload(MultipartFile file, String path) {
		String upload_path = localpath + path;
		String name = file.getOriginalFilename();
		int a = -1;
		try {
			String extName = "";
			if (name.lastIndexOf(".") >= 0) {
				extName = name.substring(name.lastIndexOf("."));
			}
			name = new Date().getTime() + extName;
			File f = new File(upload_path, name);
			FileUtils.copyInputStreamToFile(file.getInputStream(), f);
			a = postHttp(urlpath, path, f);
		} catch (Exception e) {
			e.printStackTrace();
			return "上传失败";
		}
		if (a == -1) {
			return "同步上传失败";
		} else {
			return path + name;
		}

	}

	public static int postHttp(String url, String path, File file) throws HttpException, IOException {

		HttpClient httpClient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		MultipartEntityBuilder mEntityBuilder = MultipartEntityBuilder.create();
		mEntityBuilder.addBinaryBody("file", file);
		mEntityBuilder.addTextBody("path", path);
		mEntityBuilder.addTextBody("flag", "1");
		httppost.setEntity(mEntityBuilder.build());
		HttpResponse resp = httpClient.execute(httppost);
		int code = resp.getStatusLine().getStatusCode();
		if (200 == code) {
			return 0;
		} else {
			return -1;
		}
	}

	public static void uploadFile(String uploadUrl, String filePath, String paramName) {
		String fileName = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length());
		String end = "\r\n";
		String twoHyphens = "--";
		String boundary = "*****";
		try {
			URL url = new URL(uploadUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false);
			con.setRequestMethod("POST");
			con.setRequestProperty("Connection", "Keep-Alive");
			con.setRequestProperty("Charset", "UTF-8");
			con.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
			DataOutputStream ds = new DataOutputStream(con.getOutputStream());
			ds.writeBytes(twoHyphens + boundary + end);
			ds.writeBytes("Content-Disposition: form-data; " + "name=\"" + paramName + "\";filename=\"" + fileName + "\"" + end);
			ds.writeBytes(end);
			read(filePath, ds);
			InputStream is = con.getInputStream();
			int ch;
			StringBuffer b = new StringBuffer();
			while ((ch = is.read()) != -1) {
				b.append((char) ch);
			}
			ds.close();
		} catch (Exception e) {
		}

	}

	private static void read(String filePath, DataOutputStream ds) throws FileNotFoundException, IOException {
		FileInputStream fStream = new FileInputStream(filePath);
		int bufferSize = 1024;
		byte[] buffer = new byte[bufferSize];
		int length = -1;
		while ((length = fStream.read(buffer)) != -1) {
			ds.write(buffer, 0, length);
		}
		ds.writeBytes(LINEND);
		// 请求结束标志
		ds.writeBytes(PREFIX + BOUNDARY + PREFIX + LINEND);
		fStream.close();
		ds.flush();
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
	public static boolean post(String url, Map<String, String> params, String files) throws IOException {

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
		read(files, outStream);

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
		System.out.println(data);
		outStream.close();
		conn.disconnect();
		return success;
	}
}
