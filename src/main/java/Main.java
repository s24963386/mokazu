import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.alibaba.fastjson.JSONObject;

public class Main {

	public Main() {
	}

	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));

		System.out.println(Main.class.getClassLoader().getResource(""));

		System.out.println(ClassLoader.getSystemResource(""));
		System.out.println(Main.class.getResource(""));
		System.out.println(Main.class.getResource("/"));
		// Class文件所在路径
		System.out.println(new File("/").getAbsolutePath());
		System.out.println(System.getProperty("user.dir"));
		try {
			String json = FileUtils.readFileToString(new File(ClassLoader.getSystemResource("coupon.json").getFile()));
			JSONObject jo = JSONObject.parseObject(json);
			System.out.println(jo.getJSONObject("card").get("card_type"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
