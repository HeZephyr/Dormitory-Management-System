package team.family.dbs.util;

public class StringUtil {
	//判断一个字符串是否为空。非空包括空字符串和空值。
	public static boolean isEmpty(String str){
		if("".equals(str)|| str==null){
			return true;
		}else{
			return false;
		}
	}
	//判断一个字符串是否为非空。
	public static boolean isNotEmpty(String str){
		if(!"".equals(str)&&str!=null){
			return true;
		}else{
			return false;
		}
	}
	
	
}
