package pachang.gsz.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
	/**
	 * 获取当前时间，输出格式：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getTime(){
		
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
		
	}
	public static void main(String[] args) {
		System.out.println(getTime());
	}

}
