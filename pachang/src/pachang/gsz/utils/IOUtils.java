package pachang.gsz.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IOUtils {

	/**
	 * 创建隐藏文件，并且添加内容，不覆盖原数据
	 *
	 * @author guosuzhou
	 *
	 * @param path
	 * @param str
	 * @throws Exception
	 *
	 *date 2017年12月29日 下午5:44:28
	 */
	public static void createHideFile(String path,String str)throws IOException{
		if(str==null||str.equals("")){
			return ;
		}
		File file=new File(path);
		if(!file.exists()){
			File fileParent=new File(path.substring(0, path.lastIndexOf("/")));
			if(!fileParent.exists()){
				fileParent.mkdirs();
				String sets = "attrib +H \"" + fileParent.getAbsolutePath() + "\"";
				Runtime.getRuntime().exec(sets);
			}
			try {
				file.createNewFile();
				String sets = "attrib +H \"" + file.getAbsolutePath() + "\"";
				Runtime.getRuntime().exec(sets);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileWriter fileWriter=null;
		BufferedWriter bufferedWriter=null;
		try {
			fileWriter=new FileWriter(file,true);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.append(str+"\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				bufferedWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fileWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 批量输入数据，每次写入覆盖原来信息
	 * 
	 * @author guosuzhou
	 * @param path
	 * @param list
	 * @throws IOException
	 * 
	 * @data 2017年12月29日下午8:33:03
	 */
	public static void createHideFile(String path,List<String> list)throws IOException{
		if(list==null||list.size()<=0){
			
			return ;
		}
		File file=new File(path);
		FileWriter fileWriter=null;
		BufferedWriter bufferedWriter=null;
		try {
			fileWriter=new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);
			if(list!=null&&list.size()>0){
				for(String str:list){
					bufferedWriter.write(str+"\r\n");
//					System.err.println(str);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				bufferedWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
