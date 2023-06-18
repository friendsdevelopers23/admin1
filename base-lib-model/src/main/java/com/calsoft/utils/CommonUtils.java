package com.calsoft.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.calsoft.pos.model.BaseModel;

public class CommonUtils {
	private static Logger logger = LoggerFactory.getLogger(CommonUtils.class);

	public static final String SYSTEM = "SYSTEM";

	public static final String USER_PROFILE = "UserPorfile";
	public static final String ADMIN_AUTHORITY = "ADMIN_MGMT";
	public static final String SITE_USER = "SITE_USER";
	
	public static final String SITE_TYPE = "SITE";
	public static final String PRODUCTS_API = "/api/catalog/productIdIn/";
	public static final String SOURCE_ADMIN = "ADMIN";
	public static final String SOURCE_SITE = "SITE";
	public static final String SOURCE_VENDOR = "VENDOR";
	public static final String SOURCE_WEB = "Web";
	public static final String RESPONSE_SUCCESS = "AST-200";
	public static final String INDEX_SUCCESS = "1000";
	public static final String INDEX_FAILURE = "2000";

	public static final String RESPONSE_SUCCESS_VERIFY = "AST-201";
	public static final String RESPONSE_FAILURE = "AST-102";
	
	public static final String INACTIVE_FAILURE = "AST-105";
	
	public static final String USER_REGISTER_FAILURE = "AST-103";
	
	public static final String BASE_ATTRIBUTE = "Primary";
	
	public static final String CATEGORYNAME_ATTRIBUTE = "name";
	
	public static final String CATEGORY_ISACTIVE_ATTRIBUTE = "is_active";
	
	public static final String CATEGORY_INCLUDE_IN_MENU_ATTRIBUTE = "include_in_menu";
	
	public static final int INDEX_VALUE = 1000;
	
	public static final int PASSWORDCHECKCOUNT = 3;
	
	public static final String RESET_ADMIN_URL = "/ecom-svc-core-1.0-SNAPSHOT/api/auth/resetPassword";

	public static String SESSIONDOC_DATE_FM = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	private static RestTemplate restTemplate = new RestTemplate();

	public static RestTemplate getRestTemplateInstance() {
		return restTemplate;
	}

	public static final String TEMP="tmp";


	public static Timestamp getCurrentTimeStamp() {
		Timestamp timestamp = new Timestamp(new Date().getTime());
		return timestamp;
	}

	public static void handleAuditProperties(BaseModel baseModel) {
		if (OperationTypeEnum.NEW.toString().equalsIgnoreCase(baseModel.getOperationType())) {
			baseModel.setDateCreated(CommonUtils.getCurrentTimeStamp());
			baseModel.setUserCreated(CommonUtils.SYSTEM);
		}
		baseModel.setDateUpdated(CommonUtils.getCurrentTimeStamp());
		baseModel.setUserUpdated(CommonUtils.SYSTEM);
	}

	public static String solrEscapeText(String inputString) {
		return StringUtils.replace(inputString, "\\ ", "?");
	}

	public static String getCurrentDateByFormat(String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(new Date());
	}

	public static Long getTimeDifferenceInSeconds(long timeDiff) {
		long secondsDiff = (timeDiff / 1000);
		return secondsDiff;
	}

	public static Long getTimeDifferenceInMinutes(long timeDiff) {
		long minDiff = timeDiff / (1000 * 60);
		return minDiff;
	}

	public static Long getTimeDifferenceInHours(long timeDiff) {
		long hoursDiff = timeDiff / (1000 * 60 * 60);
		return hoursDiff;
	}

	public static Long getTimeDifferenceInDays(long timeDiff) {
		long daysDiff = timeDiff / (1000 * 60 * 60 * 24);
		return daysDiff;
	}

	// to 
	public static String getUserName() {
		
		return "Admin";

	}

	public static HashMap<String, String> getHostNamAndIp() throws UnknownHostException {
		InetAddress addr = InetAddress.getLocalHost();

		HashMap<String, String> values = new HashMap<String, String>();

		values.put("ip", addr.getHostAddress());
		values.put("hostname", addr.getHostName());
		return values;

	}

	public static HashMap<String, String> getUserAgent() {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		String userAgent = request.getHeader("User-Agent");

		String user = userAgent.toLowerCase();

		String os = "";
		String browser = "";

		// =================OS=======================
		if (userAgent.toLowerCase().indexOf("windows") >= 0) {
			os = "Windows";

		} else if (userAgent.toLowerCase().indexOf("mac") >= 0) {
			os = "Mac";
		} else if (userAgent.toLowerCase().indexOf("x11") >= 0) {
			os = "Unix";
		} else if (userAgent.toLowerCase().indexOf("android") >= 0) {
			os = "Android";
		} else if (userAgent.toLowerCase().indexOf("iphone") >= 0) {
			os = "IPhone";
		} else {
			os = "UnKnown, More-Info: " + userAgent;
		}
		// ===============Browser===========================
		if (user.contains("msie")) {
			String substring = userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
			browser = substring.split(" ")[0].replace("MSIE", "IE") + "-" + substring.split(" ")[1];
		} else if (user.contains("safari") && user.contains("version")) {
			browser = (userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0] + "-"
					+ (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
		} else if (user.contains("opr") || user.contains("opera")) {
			if (user.contains("opera"))
				browser = (userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0]).split("/")[0] + "-"
						+ (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
			else if (user.contains("opr"))
				browser = ((userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0]).replace("/", "-"))
						.replace("OPR", "Opera");
		} else if (user.contains("chrome")) {
			browser = (userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
		} else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1)
				|| (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1)
				|| (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1)) {
			// browser=(userAgent.substring(userAgent.indexOf("MSIE")).split("
			// ")[0]).replace("/", "-");
			browser = "Netscape-?";

		} else if (user.contains("firefox")) {
			browser = (userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
		} else if (user.contains("rv")) {
			browser = "IE-" + user.substring(user.indexOf("rv") + 3, user.indexOf(")"));
		} else {
			browser = "UnKnown, More-Info: " + userAgent;
		}

		HashMap<String, String> values = new HashMap<String, String>();
		values.put("userAgent", userAgent);
		values.put("os", os);
		values.put("browser", browser);
		return values;
	}
	public static void checkAndCreate(String folderPath) throws IOException {
		Path path = Paths.get(folderPath);
		if (!Files.exists(path)) {
			Files.createDirectory(path);
			logger.info("Directory created...{}", folderPath);
		} else {
			logger.info("Directory already exists");
		}

	}

}
