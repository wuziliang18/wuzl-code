package com.family.tools.conveniently_tools.create_code;

import com.family.tools.conveniently_tools.create_code.support.PrintBean;
import com.family.util.ConfigUtil;
import com.family.util.FileUtil;
import com.family.util.JavaFileUtil;

/**
 * 生成工程代码
 * @author wuzl
 *
 */
public abstract class BaseCodeCreate {
	private String dao_interface_package_file_path;//dao层接口绝对路径
	private String dao_package_file_path;//dao层绝对路径
	private String service_interface_package_file_path;//service层接口绝对路径
	private String service_package_file_path;//service层绝对路径
	private String action_package_file_path;//action层绝对路径
	private String base_function_name;//功能名称
	private String function_text;//功能具体名称汉字
	private PrintBean printBean;
	public BaseCodeCreate() {
		printBean=new PrintBean();// 初始化一个输入输出字符串的bean
	}
	public String getDao_interface_package_file_path() {
		return dao_interface_package_file_path;
	}
	public void setDao_interface_package_file_path(
			String dao_interface_package_file_path) {
		this.dao_interface_package_file_path = dao_interface_package_file_path;
	}
	public String getDao_package_file_path() {
		return dao_package_file_path;
	}
	public void setDao_package_file_path(String dao_package_file_path) {
		this.dao_package_file_path = dao_package_file_path;
	}
	public String getService_interface_package_file_path() {
		return service_interface_package_file_path;
	}
	public void setService_interface_package_file_path(
			String service_interface_package_file_path) {
		this.service_interface_package_file_path = service_interface_package_file_path;
	}
	public String getService_package_file_path() {
		return service_package_file_path;
	}
	public void setService_package_file_path(String service_package_file_path) {
		this.service_package_file_path = service_package_file_path;
	}
	public String getAction_package_file_path() {
		return action_package_file_path;
	}
	public void setAction_package_file_path(String action_package_file_path) {
		this.action_package_file_path = action_package_file_path;
	}
	public String getBase_function_name() {
		return base_function_name;
	}
	public void setBase_function_name(String base_function_name) {
		this.base_function_name = base_function_name;
	}
	public String getFunction_text() {
		return function_text;
	}
	public void setFunction_text(String function_text) {
		this.function_text = function_text;
	}
	/**
	 * 生成dao接口代码
	 * @return
	 */
	public abstract String createDaoInterfaceCode();
	/**
	 * 生成dao代码
	 * @return
	 */
	public abstract String createDaoCode();
	/**
	 * 生成sercie接口代码
	 * @return
	 */
	public abstract String createServiceInterfaceCode();
	/**
	 * 生成sercie代码
	 * @return
	 */
	public abstract String createServiceCode();
	/**
	 * 生成action代码
	 * @returnDayimaCodeCreate
	 */
	public abstract String createActionCode();
	/**
	 * 保存dao代码
	 */
	public final void saveDaoCodeFile(){
		/*1.获取dao接口代码*/
		String daoInterfaceCode=this.createDaoInterfaceCode();
		/*2.保存dao接口代码*/
		this.saveJavaFile(daoInterfaceCode, getDao_interface_package_file_path()+getDaoInterfaceFileRelativePath());
		/*3.获取dao代码*/
		String daoCode=this.createDaoCode();
		/*4.保存dao代码*/
		this.saveJavaFile(daoCode, getDao_package_file_path()+getDaoFileRelativePath());
	}
	/**
	 * 保存dao代码
	 */
	public final void saveServiceCodeFile(){
		/*1.获取Service接口代码*/
		String serviceInterfaceCode=this.createServiceInterfaceCode();
		/*2.保存Service接口代码*/
		this.saveJavaFile(serviceInterfaceCode, getService_interface_package_file_path()+getServiceInterfaceFileRelativePath());
		/*3.获取Service代码*/
		String serviceCode=this.createServiceCode();
		/*4.保存Service代码*/
		this.saveJavaFile(serviceCode, getService_package_file_path()+getServiceFileRelativePath());
	}
	/**
	 * 保存action代码
	 */
	public final void saveActionCodeFile(){
		/*1.获取action代码*/
		String actionCode=this.createActionCode();
		/*2.保存Service代码*/
		this.saveJavaFile(actionCode, getAction_package_file_path()+getActionFileRelativePath());
	}
	/**
	 * 获取dao文件的相对路径
	 * @return
	 */
	public String getDaoFileRelativePath(){
		return "/"+changeNameStyle()+"DaoImpl";
	}
	/**
	 * 获取dao接口文件的相对路径
	 * @return
	 */
	public String getDaoInterfaceFileRelativePath(){
		return "/"+changeNameStyle()+"Dao";
	}
	/**
	 * 获取service文件的相对路径
	 * @return
	 */
	public String getServiceFileRelativePath(){
		return "/"+changeNameStyle()+"ServiceImpl";
	}
	/**
	 * 获取service接口文件的相对路径
	 * @return
	 */
	public String getServiceInterfaceFileRelativePath(){
		return "/"+changeNameStyle()+"Service";
	}
	/**
	 * 获取action文件的相对路径
	 * @return
	 */
	public String getActionFileRelativePath(){
		return "/"+changeNameStyle()+"Action";
	}
	/**
	 * 改变name为合适的格式
	 * @return
	 */
	public String changeNameStyle(){
		return ConfigUtil.changeUnderLineToHumpAndFontUpOtherLower(getBase_function_name());
	}
	/**
	 * 文件路径转成package 但要求是src/main/java
	 * @param filePath
	 * @return
	 */
	public String getPackageNameFromPath(String filePath){
		return JavaFileUtil.getPackageFormFilePath(filePath);
	}
	/**
	 * 保存信息到输入输出中
	 */
	public void saveMsg(String msg){
		this.printBean.in(msg);
	}
	/**
	 * 保存信息到输入输出中,并且换行
	 * @param msg
	 */
	public void saveMsgAndNewLine(String msg){
		this.printBean.inAndNewLine(msg);
	}
	/**
	 * 输出信息根据选择 选择合适的方式
	 * @return
	 */
	public void printMsg(){
		String msg=this.printBean.outAndClear();
		if(msg.length()>0){
			//打印 可以用log之类
			System.out.println(msg);
		}
		
	}
	/**
	 * 保存java代码文件
	 * @param code
	 * @param filePath
	 */
	public final void saveJavaFile(String code,String filePath){
		this.saveFile(code, filePath+".java");
	}
	/**
	 * 保存代码文件
	 * @param code
	 * @param filePath
	 */
	public final void saveFile(String code,String filePath){
		if(!FileUtil.validateDirectoryHave(filePath)){
			throw new RuntimeException("指定的目录"+filePath+"不存在");
		}
		FileUtil.writeAndValidateFile(code, filePath);
		System.out.println("生成文件:"+filePath);
		this.printMsg();
	}
}
