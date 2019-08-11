package com.yoloho.server.queue.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class StringUtil {

	public StringUtil() {
	}

	public static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;)
			if (!Character.isDigit(str.charAt(i)))
				return false;

		return true;
	}

	public static StringBuilder formatMsg(CharSequence msgWithFormat, boolean autoQuote, Object args[]) {
		int argsLen = args.length;
		boolean markFound = false;
		StringBuilder sb = new StringBuilder(msgWithFormat);
		if (argsLen > 0) {
			for (int i = 0; i < argsLen; i++) {
				String flag = (new StringBuilder("%")).append(i + 1).toString();
				for (int idx = sb.indexOf(flag); idx >= 0; idx = sb.indexOf(flag)) {
					markFound = true;
					sb.replace(idx, idx + 2, toString(args[i], autoQuote));
				}

			}

			if (args[argsLen - 1] instanceof Throwable) {
				StringWriter sw = new StringWriter();
				((Throwable) args[argsLen - 1]).printStackTrace(new PrintWriter(sw));
				sb.append("\n").append(sw.toString());
			} else if (argsLen == 1 && !markFound)
				sb.append(args[argsLen - 1].toString());
		}
		return sb;
	}

	public static StringBuilder formatMsg(String msgWithFormat, Object args[]) {
		return formatMsg(((CharSequence) (new StringBuilder(msgWithFormat))), true, args);
	}

	public static String toString(Object obj, boolean autoQuote) {
		StringBuilder sb = new StringBuilder();
		if (obj == null)
			sb.append("NULL");
		else if (obj instanceof Object[]) {
			for (int i = 0; i < ((Object[]) obj).length; i++)
				sb.append(((Object[]) obj)[i]).append(", ");

			if (sb.length() > 0)
				sb.delete(sb.length() - 2, sb.length());
		} else {
			sb.append(obj.toString());
		}
		if (autoQuote && sb.length() > 0 && (sb.charAt(0) != '[' || sb.charAt(sb.length() - 1) != ']')
				&& (sb.charAt(0) != '{' || sb.charAt(sb.length() - 1) != '}'))
			sb.insert(0, "[").append("]");
		return sb.toString();
	}

	public static String convertQuot(String orgStr) {
		return orgStr.replace("'", "\\'").replace("\"", "\\\"");
	}

	public static String trimSufffix(String toTrim, String trimStr) {
		for (; toTrim.endsWith(trimStr); toTrim = toTrim.substring(0, toTrim.length() - trimStr.length()))
			;
		return toTrim;
	}

	public static String htmlEntityToString(String dataStr) {
		int start = 0;
		int end = 0;
		StringBuffer buffer = new StringBuffer();
		while (start > -1) {
			int system = 10;
			if (start == 0) {
				int t = dataStr.indexOf("&#");
				if (start != t)
					start = t;
			}
			end = dataStr.indexOf(";", start + 2);
			String charStr = "";
			if (end != -1) {
				charStr = dataStr.substring(start + 2, end);
				char s = charStr.charAt(0);
				if (s == 'x' || s == 'X') {
					system = 16;
					charStr = charStr.substring(1);
				}
			}
			try {
				char letter = (char) Integer.parseInt(charStr, system);
				buffer.append((new Character(letter)).toString());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			start = dataStr.indexOf("&#", end);
			if (start - end > 1)
				buffer.append(dataStr.substring(end + 1, start));
			if (start == -1) {
				int length = dataStr.length();
				if (end + 1 != length)
					buffer.append(dataStr.substring(end + 1, length));
			}
		}
		return buffer.toString();
	}

	public static String stringToHtmlEntity(String str) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			switch (c) {
			case 10: // '\n'
				sb.append(c);
				break;

			case 60: // '<'
				sb.append("&lt;");
				break;

			case 62: // '>'
				sb.append("&gt;");
				break;

			case 38: // '&'
				sb.append("&amp;");
				break;

			case 39: // '\''
				sb.append("&apos;");
				break;

			case 34: // '"'
				sb.append("&quot;");
				break;

			default:
				if (c < ' ' || c > '~') {
					sb.append("&#x");
					sb.append(Integer.toString(c, 16));
					sb.append(';');
				} else {
					sb.append(c);
				}
				break;
			}
		}

		return sb.toString();
	}

	public static String stringToUnicode(String s) {
		String unicode = "";
		char charAry[] = new char[s.length()];
		for (int i = 0; i < charAry.length; i++) {
			charAry[i] = s.charAt(i);
			unicode = (new StringBuilder(String.valueOf(unicode))).append("\\u").append(Integer.toString(charAry[i], 16)).toString();
		}

		return unicode;
	}

	public static String unicodeToString(String unicodeStr) {
		StringBuffer sb = new StringBuffer();
		String str[] = unicodeStr.toUpperCase().split("\\\\U");
		for (int i = 0; i < str.length; i++)
			if (!str[i].equals("")) {
				char c = (char) Integer.parseInt(str[i].trim(), 16);
				sb.append(c);
			}

		return sb.toString();
	}

	public static String html2Text(String inputString) {
		String htmlStr = inputString;
		String textStr = "";
		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
			String regEx_html = "<[^>]+>";
			Pattern p_script = Pattern.compile(regEx_script, 2);
			Matcher m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll("");
			Pattern p_style = Pattern.compile(regEx_style, 2);
			Matcher m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll("");
			Pattern p_html = Pattern.compile(regEx_html, 2);
			Matcher m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll("");
			textStr = htmlStr;
		} catch (Exception e) {
			System.err.println((new StringBuilder("Html2Text: ")).append(e.getMessage()).toString());
		}
		return textStr;
	}

	/**
	 * 判断字符串是否非空
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0 || str.trim().equals("");
	}

	public static String toLowerCase(String str) {
		return str != null ? str.toLowerCase() : null;
	}

	public static String toUpperCase(String str) {
		return str != null ? str.toUpperCase() : null;
	}

	public static String encodingString(String str, String from, String to) {
		String result = str;
		try {
			result = new String(str.getBytes(from), to);
		} catch (Exception e) {
			result = str;
		}
		return result;
	}

	
	/**
	 * 计算从字符串的start位置开始，字符c出现的次数
	 * @param str
	 * @param c
	 * @param start
	 * @return
	 */
	public static int strspn(String str, char c, int start) {
		int count = 0;
		
		char[] target = str.substring(start).toCharArray();
		for (char chr : target) {
			if (chr == c) {
				count++;
			}
		}
		
		return count;
	}

	/**
	 * 格式化MAC地址，以":"分割；替换调非标准字符
	 * @param mac
	 * @return
	 */
	public static String formatMac(String mac) {
		// 替换空白符 斜杠
		return mac.trim().replaceAll("[\\s/]", ":");
	}
	
}
