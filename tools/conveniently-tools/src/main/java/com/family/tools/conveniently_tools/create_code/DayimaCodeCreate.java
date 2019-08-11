package com.family.tools.conveniently_tools.create_code;

import com.family.util.ConfigUtil;

/**
 * 生成工程代码
 * @author wuzl
 *
 */
public class DayimaCodeCreate extends BaseCodeCreate{
	//换行符号
	private final static String separator =System.getProperty("line.separator");  
	@Override
	public String createDaoInterfaceCode() {
		StringBuilder sb=new StringBuilder();
		sb.append("package "+getPackageNameFromPath(getDao_interface_package_file_path())+";"+separator+separator);
		sb.append("import com.dayima.core.dao.BaseDao;"+separator);
		sb.append("import com.dayima.uic.api.model."+changeNameStyle()+";"+separator+separator);
		sb.append("public interface "+changeNameStyle()+"Dao extends BaseDao<"+changeNameStyle()+">{"+separator);
		sb.append(separator);
		sb.append("}");
		return sb.toString();
	}

	@Override
	public String createDaoCode() {
		StringBuilder sb=new StringBuilder();
		sb.append("package "+getPackageNameFromPath(getDao_package_file_path())+";"+separator+separator);
		sb.append("import com.dayima.core.dao.impl.BaseDaoImpl;"+separator);
		sb.append("import com.dayima.uic.api.model."+changeNameStyle()+";"+separator);
		sb.append("import "+getPackageNameFromPath(getDao_interface_package_file_path())+"."+changeNameStyle()+"Dao;"+separator+separator);
		sb.append("public class "+changeNameStyle()+"DaoImpl extends BaseDaoImpl<"+changeNameStyle()+"> implements"+separator);
		sb.append("\t\t"+changeNameStyle()+"Dao  {"+separator);
		sb.append("\tpublic "+changeNameStyle()+"DaoImpl() {"+separator);
		sb.append("\t\tsuper("+changeNameStyle()+".class);"+separator);
		sb.append("\t}"+separator);
		sb.append(separator);
		sb.append("}"+separator);
		saveMsgAndNewLine("#########");
		saveMsgAndNewLine("app-dao.xml中加入");
		saveMsgAndNewLine("<bean id=\""+ConfigUtil.fontCharToLower(changeNameStyle())+"Dao\" class=\""+getPackageNameFromPath(getDao_package_file_path())+"."+changeNameStyle()+"DaoImpl\" parent=\"baseDao\"/>");
		saveMsgAndNewLine("#########");
		return sb.toString();
	}

	@Override
	public String createServiceInterfaceCode() {
		StringBuilder sb=new StringBuilder();
		sb.append("package "+getPackageNameFromPath(getService_interface_package_file_path())+";"+separator+separator);
		sb.append("import com.dayima.core.service.GenericService;"+separator);
		sb.append("import com.dayima.uic.api.model."+changeNameStyle()+";"+separator+separator);
		sb.append("public interface "+changeNameStyle()+"Service extends GenericService<"+changeNameStyle()+", Long>{"+separator);
		sb.append(separator);
		sb.append("}");
		return sb.toString();
	}

	@Override
	public String createServiceCode() {
		StringBuilder sb=new StringBuilder();
		sb.append("package "+getPackageNameFromPath(getService_package_file_path())+";"+separator+separator);
		sb.append("import com.dayima.core.service.impl.GenericServiceImpl;"+separator);
		sb.append("import "+getPackageNameFromPath(getService_interface_package_file_path())+"."+changeNameStyle()+"Service;"+separator);
		sb.append("import com.dayima.uic.api.model."+changeNameStyle()+";"+separator);
		sb.append("import "+getPackageNameFromPath(getDao_interface_package_file_path())+"."+changeNameStyle()+"Dao;"+separator+separator);
		sb.append("public class "+changeNameStyle()+"ServiceImpl extends GenericServiceImpl<"+changeNameStyle()+", Long> "+separator);
		sb.append("\t\timplements "+changeNameStyle()+"Service {"+separator);
		sb.append("\tprivate "+changeNameStyle()+"Dao dao;"+separator+separator);
		sb.append("\tpublic "+changeNameStyle()+"ServiceImpl("+changeNameStyle()+"Dao dao) {"+separator);
		sb.append("\t\tsuper(dao);"+separator);
		sb.append("\t\tthis.dao = dao;"+separator);
		sb.append("\t}"+separator);
		sb.append("}"+separator);
		saveMsgAndNewLine("#########");
		saveMsgAndNewLine("app-service.xml中加入");
		saveMsgAndNewLine("\t<bean id=\""+ConfigUtil.fontCharToLower(changeNameStyle())+"Service\" class=\""+getPackageNameFromPath(getService_package_file_path())+"."+changeNameStyle()+"ServiceImpl\" >");
		saveMsgAndNewLine("\t\t<constructor-arg index=\"0\" ref=\""+ConfigUtil.fontCharToLower(changeNameStyle())+"Dao\"/>");
		saveMsgAndNewLine("\t</bean>");
		saveMsgAndNewLine("#########");
		saveMsgAndNewLine("app-dubbo-provider.xml中加入");
		saveMsgAndNewLine("\t<dubbo:service interface=\""+getPackageNameFromPath(getService_interface_package_file_path())+"."+changeNameStyle()+"Service\"");
		saveMsgAndNewLine("\t\tref=\""+ConfigUtil.fontCharToLower(changeNameStyle())+"Service\" />");
		saveMsgAndNewLine("dubbo引用 app-dubbo-consumer.xml中加入");
		saveMsgAndNewLine("\t<dubbo:reference interface=\""+getPackageNameFromPath(getService_interface_package_file_path())+"."+changeNameStyle()+"Service\" id=\""+ConfigUtil.fontCharToLower(changeNameStyle())+"Service\" />");
		saveMsgAndNewLine("#########");
		saveMsgAndNewLine("WuzlStartService测试方法");
		saveMsgAndNewLine(changeNameStyle()+"Service "+ConfigUtil.fontCharToLower(changeNameStyle())+"Service = context.getBean("+changeNameStyle()+"Service.class);");
		saveMsgAndNewLine("#########");
		saveMsgAndNewLine("#########");
		saveMsgAndNewLine("客户端使用方法");
		saveMsgAndNewLine("@Autowired");
		saveMsgAndNewLine("private "+changeNameStyle()+"Service "+ConfigUtil.fontCharToLower(changeNameStyle())+"Service;");
		saveMsgAndNewLine("#########");
		return sb.toString();
	}

	@Override
	public String createActionCode() {
		StringBuilder sb=new StringBuilder();
		
		return sb.toString();
	}
	/**
	 * 生成xml和file
	 * @param beanPath
	 * @param xmlPath
	 */
	public void createHibernateBeanAndXmlFile(String beanPath,String xmlPath,String fields){
		StringBuilder beanCode=new StringBuilder();
		StringBuilder xmlCode=new StringBuilder();
		String[] fieldArray=fields.split(",");
	}
	public static void main(String[] args) {
		DayimaCodeCreate baseCodeCreate=new DayimaCodeCreate();
		baseCodeCreate.setBase_function_name("admin_log");
		baseCodeCreate.setFunction_text("");
		baseCodeCreate.setDao_interface_package_file_path("/home/wuzl/dayimaworkspace/dayimaservice/uic-provider/src/main/java/com/dayima/uic/provider/dao");
		baseCodeCreate.setDao_package_file_path("/home/wuzl/dayimaworkspace/dayimaservice/uic-provider/src/main/java/com/dayima/uic/provider/dao/impl");
		baseCodeCreate.setService_interface_package_file_path("/home/wuzl/dayimaworkspace/dayimaservice/uic-api/src/main/java/com/dayima/uic/api/interfaces");
		baseCodeCreate.setService_package_file_path("/home/wuzl/dayimaworkspace/dayimaservice/uic-provider/src/main/java/com/dayima/uic/provider/service/impl");
		baseCodeCreate.setAction_package_file_path("/home/wuzl/dayimaworkspace/dayimaservice/uic-consumer/src/main/java/com/dayima/uic/consumer/action");
		baseCodeCreate.saveDaoCodeFile();
		baseCodeCreate.saveServiceCodeFile();
		//生成hibernate文件
//		String fields="";
//		String filePath="/home/wuzl/dayimaworkspace/dayimaservice/uic-api/src/main/java/com/dayima/uic/api/model";
//		baseCodeCreate.createHibernateBeanAndXmlFile(filePath,filePath,fields);
//		baseCodeCreate.saveActionCodeFile();
	}
}
