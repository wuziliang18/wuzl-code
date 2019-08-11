package org.wuzl.test.yoloho;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.wuzl.test.io.FileUtil;

public class UpdateImportService {
	private final static String FILE_SEPARATOR =System.getProperty("line.separator");  
	public static void main(String[] args) {
//		String workSpacePath="/home/wuzl/dayimaworkspace/forumapi/forum-service-api";
		String javaPackagePath="/home/wuzl/dayimaworkspace/forumapi/forum-provider/src/main/java/com/yoloho/forum/provider/service/impl";
		File[] listFiles= new File(javaPackagePath).listFiles();
		for(File file:listFiles){
			StringBuilder content=new StringBuilder();
			FileReader  fr= null;
			BufferedReader br =null;
			try{
				fr=new FileReader(file);
				br=new BufferedReader(fr);
				String data = br.readLine();
				while(data!=null){
					if(!data.contains("com.yoloho.forum.provider.service.base.GenericServiceImpl")){
						content.append(data+FILE_SEPARATOR);
					}else{
						content.append("import com.yoloho.service.GenericServiceImpl;"+FILE_SEPARATOR);
					}
					data = br.readLine(); //接着读下一行  
				}
			}catch (Exception e) {   
	        	    
	        }finally {   
	        	try {    
	                if(br!=null){
	                	br.close();
	                }
	            } catch (Exception e) {    
	            	throw new RuntimeException(e.getMessage());     
	            } 
	        	try{
	            	if(fr!=null){
	            		fr.close();
	            	}
	            }catch (Exception e) {    
	            	throw new RuntimeException(e.getMessage());    
	            }
	        } 
			FileUtil.writeFile(content.toString(), file,false);
		}
	}
}
