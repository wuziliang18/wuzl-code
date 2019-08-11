package com.yoloho.server.queue.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yoloho.server.queue.DaemonConfiguration;
import com.yoloho.server.queue.exception.CanNotWritePidFileException;

public class Utils {
	private static Log _logger = LogFactory.getLog("apns");
	private static SimpleDateFormat _formatter = new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]\t");
	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

	public static enum ENV {
		PRODUCTION, STAGING, LOCAL
	};

	public static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String cutString(String msg, int size) {
		if (msg.getBytes().length <= size) {
			return msg;
		} else {
			StringBuilder str = new StringBuilder();
			int len = 0;
			for (int i = 0; i < msg.length(); i++) {
				String item = msg.substring(i, i + 1);
				str.append(item);
				len += item.getBytes().length;
				if (len > size - 3) {
					break;
				}
			}
			return str.toString();
		}
	}

	public static long getPID() {
		String processName = java.lang.management.ManagementFactory.getRuntimeMXBean().getName();
		return Long.parseLong(processName.split("@")[0]);
	}

	public static void writeLog(String msg) {
		writeLog(msg, "main");
	}

	public static void writeLog(String msg, String module) {
		FileOutputStream file = null;
		try {
			file = new FileOutputStream("/var/log/apns_pusher/" + module + ".log", true);
			file.write(_formatter.format(new Date()).getBytes());
			file.write(msg.getBytes());
			file.write("\n".getBytes());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (file != null)
					file.close();
			} catch (Exception e) {
			}
		}
	}

	public static ENV getEnv() {
		try {
			Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
			InetAddress ip = null;
			while (allNetInterfaces.hasMoreElements()) {
				NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
				Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
				while (addresses.hasMoreElements()) {
					ip = (InetAddress) addresses.nextElement();
					if (ip != null && ip instanceof Inet4Address) {
						if (ip.getHostAddress().indexOf("10.10.99") >= 0) {
							return ENV.PRODUCTION;
						} else if (ip.getHostAddress().equals("192.168.123.3")) {
							return ENV.STAGING;
						}
					}
				}
			}
		} catch (SocketException e1) {
			e1.printStackTrace();
		}
		return ENV.LOCAL;
	}

	public static void writeStatus(String msg, String module) {
		FileOutputStream file = null;
		try {
			file = new FileOutputStream("/var/log/apns_pusher/" + module + ".status");
			file.write(msg.getBytes());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (file != null)
					file.close();
			} catch (Exception e) {
			}
		}
	}

	public static void writePidFile() throws CanNotWritePidFileException {
		FileOutputStream file = null;
		String filepath = DaemonConfiguration.getPidPath();
		try {
			// 首先检查是奋有已启动的实例
			BufferedReader in = null;
			try {
				in = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));
				String line = null;
				while ((line = in.readLine()) != null) {
					int pid = Integer.parseInt(line.trim());
					if (pid > 0) {
						Utils.log_info("检测到旧的pid文件，检查进程是否存在(" + pid + ")");
						Process process = Runtime.getRuntime().exec("/bin/ps -p " + pid + " h");
						BufferedReader result = null;
						try {
							result = new BufferedReader(new InputStreamReader(process.getInputStream()));
							String ret = null;
							while ((ret = result.readLine()) != null) {
								if (ret.length() > 0) {
									Utils.log_error("已经有实例正在运行");
									throw new CanNotWritePidFileException();
								}
							}
						} catch (IOException e) {
						}
						break;
					}
				}
			} catch (IOException e) {
				// 说明无有效已存在实例
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (Exception e) {
					}
				}
			}
			// 如果正常，则写入本次实例
			file = new FileOutputStream(filepath);
			file.write((getPID() + "").getBytes());
		} catch (IOException e) {
			log_error("写入pid错误");
			throw new CanNotWritePidFileException();
		} finally {
			try {
				if (file != null) {
					file.close();
					File f = new File(filepath);
					if (f.exists()) {
						f.deleteOnExit();
					}
				}
			} catch (Exception e) {
			}
		}
	}

	public static double getDouble(String str) {
		try {
			return Double.parseDouble(str);
		} catch (Exception e) {
			return 0;
		}
	}

	public static int getInteger(String str) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			return 0;
		}
	}

	public static long getLong(String str) {
		try {
			return Long.parseLong(str);
		} catch (Exception e) {
			return 0;
		}
	}

	public static long ip2long(String ip) {
		String[] arr = ip.split("\\.");
		if (arr.length == 4) {
			long ret = 0;
			ret += Integer.parseInt(arr[3]);
			ret += (Long.parseLong(arr[2]) << 8);
			ret += (Long.parseLong(arr[1]) << 16);
			ret += (Long.parseLong(arr[0]) << 24);
			return ret;
		}
		return 0;
	}

	public static String long2ip(long ip) {
		StringBuilder result = new StringBuilder();
		result.append("" + ((ip >> 24) & 0xff));
		result.append(".");
		result.append("" + ((ip >> 16) & 0xff));
		result.append(".");
		result.append("" + ((ip >> 8) & 0xff));
		result.append(".");
		result.append("" + (ip & 0xff));
		return result.toString();
	}

	public static void log_debug(Object msg) {
		_logger.debug(msg);
	}

	public static void log_info(Object msg) {
		_logger.info(msg);
	}

	public static void log_warn(Object msg) {
		_logger.warn(msg);
	}

	public static void log_error(Object msg) {
		_logger.error(msg);
	}

	public static void log_fatal(Object msg) {
		_logger.fatal(msg);
	}

	public static String bytesToHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}
	
	public static Date longToDate(long longdate){
		return new Date(longdate);
	}
}
