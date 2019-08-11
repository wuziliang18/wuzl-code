package com.family.util;

import java.io.File;

import com.family.util.constaints.Constants;

/**
 * sftp操作工具类
 * 
 * @date Nov 3, 2013 4:15:04 PM
 * @author wuzl
 * @comment
 */
public class SFTPOperateUtil {
	/**
	 * 用sftp上传文件到服务器(会覆盖)
	 * 
	 * @param host
	 * @param port
	 * @param username
	 * @param password
	 * @param localPath
	 * @param remotePath
	 * @date Nov 3, 2013 4:18:10 PM
	 * @author wuzl
	 * @comment
	 */
	public static void upFileAndDirectoryBySFTP(String host, int port,
			String username, String password, String localPath,
			String remotePath) {
		/* 1.验证数据完整性 */
		validate(host, port, username, password, localPath);
		remotePath = StringUtil.isBlank(remotePath) ? Constants.LINUX_HONE_PATH
				+ username : remotePath;
		SFTPOperate operete = new SFTPOperate(host, port, username, password);
		/* 2.设置一些属性 */
		operete.setLocalPath(localPath);
		operete.setRemotePath(remotePath);
		operete.setFile(false);
		operete.setCover(true);
		/* 3.上传文件 */
		upFile(operete);
	}

	/**
	 * 下载文件
	 * 
	 * @param host
	 * @param port
	 * @param username
	 * @param password
	 * @param localPath
	 * @param remotePath
	 * @param fileName
	 * @date Nov 3, 2013 5:08:12 PM
	 * @author wuzl
	 * @comment
	 */
	public static void downFileBySFTP(String host, int port, String username,
			String password, String localPath, String remotePath,
			String fileName) {
		/* 1.验证数据完整性 */
		validate(host, port, username, password, localPath);
		if (StringUtil.isBlank(fileName)) {
			throw new RuntimeException("没有输入要下载的文件名称");
		}
		SFTPOperate operete = new SFTPOperate(host, port, username, password);
		/* 2.下载文件 */
		try {
			operete.connect();
			operete.downFile(remotePath, fileName, localPath);
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			operete.disconnect();
		}
	}

	/**
	 * 用sftp上传文件到服务器(不覆盖，只上传单个文件)
	 * 
	 * @param host
	 * @param port
	 * @param username
	 * @param password
	 * @param localPath
	 * @param remotePath
	 * @date Nov 3, 2013 4:18:10 PM
	 * @author wuzl
	 * @comment
	 */
	public static void upOnlyFileBySFTP(String host, int port, String username,
			String password, String localPath, String remotePath) {
		/* 1.验证数据完整性 */
		validate(host, port, username, password, localPath);
		remotePath = StringUtil.isBlank(remotePath) ? Constants.LINUX_HONE_PATH
				+ username : remotePath;
		SFTPOperate operete = new SFTPOperate(host, port, username, password);
		/* 2.设置一些属性 */
		operete.setLocalPath(localPath);
		operete.setRemotePath(remotePath);
		/* 3.上传文件 */
		upFile(operete);
	}

	/**
	 * 用sftp上传文件到服务器(自定义属性)
	 * 
	 * @param host
	 * @param port
	 * @param username
	 * @param password
	 * @param localPath
	 * @param remotePath
	 * @param isFile
	 * @param cover
	 * @date Nov 3, 2013 4:34:19 PM
	 * @author wuzl
	 * @comment
	 */
	public static void upFileFromConBySFTP(String host, int port,
			String username, String password, String localPath,
			String remotePath, boolean isFile, boolean cover) {
		/* 1.验证数据完整性 */
		validate(host, port, username, password, localPath);
		remotePath = StringUtil.isBlank(remotePath) ? Constants.LINUX_HONE_PATH
				+ username : remotePath;
		SFTPOperate operete = new SFTPOperate(host, port, username, password);
		/* 2.设置一些属性 */
		operete.setLocalPath(localPath);
		operete.setRemotePath(remotePath);
		operete.setFile(isFile);
		operete.setCover(cover);
		/* 3.上传文件 */
		upFile(operete);
	}

	/**
	 * 验证属性
	 * 
	 * @param host
	 * @param port
	 * @param username
	 * @param password
	 * @param localPath
	 * @date Nov 3, 2013 4:32:07 PM
	 * @author wuzl
	 * @comment
	 */
	private static void validate(String host, int port, String username,
			String password, String localPath) {
		if (StringUtil.isBlank(host)) {
			throw new RuntimeException("没有输入要连接的host");
		}
		if (StringUtil.isBlank(username)) {
			throw new RuntimeException("没有输入要连接的服务器的登录用户名");
		}
		if (StringUtil.isBlank(localPath)) {
			throw new RuntimeException("没有输入要连接的文件路径");
		}
		if (StringUtil.isBlank(password)) {
			throw new RuntimeException("没有输入要连接的服务器的登录用户名密码");
		}
	}

	/**
	 * 上传文件操作
	 * 
	 * @param operete
	 * @date Nov 3, 2013 4:30:21 PM
	 * @author wuzl
	 * @comment
	 */
	private static void upFile(SFTPOperate operete) {
		try {
			operete.connect();
			operete.upload();
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			operete.disconnect();
		}
	}

	/**
	 * 直接上传file对象
	 * 
	 * @param host
	 * @param port
	 * @param username
	 * @param password
	 * @param localPath
	 * @param remotePath
	 * @param isFile
	 * @param cover
	 */
	public static void upFileFromConBySFTP(String host, int port,
			String username, String password, File localPath,
			String remotePath, boolean isFile, boolean cover) {
		remotePath = StringUtil.isBlank(remotePath) ? Constants.LINUX_HONE_PATH
				+ username : remotePath;
		SFTPOperate operete = new SFTPOperate(host, port, username, password);
		operete.setRemotePath(remotePath);
		operete.setFile(isFile);
		operete.setCover(cover);
		try {
			operete.connect();
			operete.upload(localPath);
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			operete.disconnect();
		}
	}

	public static void main(String[] args) {
		SFTPOperateUtil.upFileAndDirectoryBySFTP("101.251.238.162", 22, "root",
				"1qazxdr5!@#$%", "/home/wuzl/dayimawork/yim/yim-server/target/yim-server-0.0.1-SNAPSHOT-assembly.tar.gz",
				"/dayima/yim/yim-server");

	}
}
