/*
 * @(#)PermissionUtil.java
 * Date : 2014. 12. 31.
 * Copyright: (C) 2014 by devsunset All right reserved.
 */
package kr.pe.devsunset.aps;

import java.util.HashMap;
import java.util.LinkedHashMap;

import android.content.Context;

/**
 * <PRE>
 * AppPermissionScan PermissionUtil
 * </PRE>
 * 
 * @author devsunset
 * @version 1.0
 * @since AppPermissionScan 1.0
 */

public class PermissionUtil {
	
	public static HashMap<String,String> DANGER_PERMISSION = new HashMap<String,String>();
	
	public static LinkedHashMap<String,String> PERMISSION_LIST = new LinkedHashMap<String,String>();
	
	public static String DANGER_TEXT = "";
	public static String USER_DEFINE_PERMISSION = "";
	
	public PermissionUtil(){
		
	}
	
	public PermissionUtil(Context ctx){
		if(DANGER_PERMISSION == null || DANGER_PERMISSION.size() == 0){
			
			DANGER_PERMISSION.put("SEND_SMS","DANGER");
			DANGER_PERMISSION.put("RECEIVE_SMS","DANGER");
			DANGER_PERMISSION.put("SYSTEM_ALERT_WINDOW","DANGER");
			DANGER_PERMISSION.put("READ_HISTORY_BOOKMARKS","DANGER");
			DANGER_PERMISSION.put("WRITE_HISTORY_BOOKMARKS","DANGER");
			DANGER_PERMISSION.put("READ_CONTACTS","DANGER");
			DANGER_PERMISSION.put("WRITE_CONTACTS","DANGER");
			DANGER_PERMISSION.put("READ_CALENDAR","DANGER");
			DANGER_PERMISSION.put("WRITE_CALENDAR","DANGER");
			DANGER_PERMISSION.put("CALL_PHONE","DANGER");
			DANGER_PERMISSION.put("READ_LOGS","DANGER");
			DANGER_PERMISSION.put("ACCESS_FINE_LOCATION","DANGER");
			DANGER_PERMISSION.put("GET_TASKS","DANGER");
			DANGER_PERMISSION.put("RECEIVE_BOOT_COMPLETED","DANGER");
			DANGER_PERMISSION.put("CHANGE_WIFI_STATE","DANGER");
			DANGER_PERMISSION.put("ACCESS_NETWORK_STATE","DANGER");
			DANGER_PERMISSION.put("INTERNET","DANGER");
			DANGER_PERMISSION.put("RECODER_AUDIO","DANGER");
			
			String[] permissionArr = ctx.getResources().getStringArray(R.array.permissions_array);
			if(permissionArr != null){
				for(int i=0; i<permissionArr.length; i++){
					String data[] = permissionArr[i].split("\\|");			
					if(data.length == 2){
						PERMISSION_LIST.put(data[0] , data[1]);
					}else{
						PERMISSION_LIST.put(data[0],"");
					}
					
				}
			}
			
			DANGER_TEXT = ctx.getString(R.string.risk_permission);
			USER_DEFINE_PERMISSION = ctx.getString(R.string.app_define_permission);
		}
	}
	
	public static boolean checkDanger(String str){
		boolean result = false;
		if(DANGER_PERMISSION !=null && DANGER_PERMISSION.get(str) !=null && "DANGER".equals(DANGER_PERMISSION.get(str)) ){
			result = true;
		}
	    return result;
	}
	
	public static String makePermissionStringToHtml(String str){
	    StringBuffer sb = new StringBuffer();
		if( str != null && !"".equals(str)){
			String data[] = str.split("\\n");			
			if(data !=null && data.length > 0){
				for(int i=0 ; i<data.length;i++){
					String dataDetail[] = data[i].split("\\|");
					sb.append("<b>");
					if(checkDanger(dataDetail[0])){
						sb.append("<font color='blue'>"+dataDetail[0]+" "+DANGER_TEXT+"</font>");
						
						if(dataDetail !=null && dataDetail.length > 3 && !"".equals(dataDetail[1])){
							sb.append("<br>"+dataDetail[1]+"<br>"+dataDetail[2]+"<br>"+dataDetail[3]+"<br>"+dataDetail[4]);
						}
					}else{
						sb.append(dataDetail[0]);
						if(dataDetail !=null && dataDetail.length > 3 && !"".equals(dataDetail[1])){
							sb.append("<br>"+dataDetail[1]+"<br>"+dataDetail[2]+"<br>"+dataDetail[3]+"<br>"+dataDetail[4]);
						}
					}
					sb.append("</b><br>");
					sb.append(getKeyString(dataDetail[0]));
					sb.append("<br><br>");
				}
			}
		}
	    return sb.toString();
	}
	
	public static String makePermissionSearchStringToHtml(String str){
		StringBuffer sb = new StringBuffer();
		if( str != null && !"".equals(str)){
			String data[] = str.split("\\n");			
			if(data !=null && data.length > 0){
				for(int i=0 ; i<data.length;i++){
					String dataDetail[] = data[i].split("\\|");
					sb.append("<b>");
					if(dataDetail[0].equals(AppContent.PERMISSION_CODE) || (AppContent.PERMISSION_CODE.equals("APP_PERMISSION") && dataDetail[0].indexOf(".") >-1)){
						if(checkDanger(dataDetail[0])){
							sb.append("<font color='red'>"+dataDetail[0]+" "+DANGER_TEXT+"</font>");
						}else{
							sb.append("<font color='red'>"+dataDetail[0]+"</font>");
						}
						
						if(dataDetail !=null && dataDetail.length > 3 && !"".equals(dataDetail[1])){
							sb.append("<br>"+dataDetail[1]+"<br>"+dataDetail[2]+"<br>"+dataDetail[3]+"<br>"+dataDetail[4]);
						}
					}else{
						if(checkDanger(dataDetail[0])){
							sb.append(dataDetail[0]+" <font color='blue'>"+DANGER_TEXT+"</font>");
						}else{
							sb.append(dataDetail[0]);
						}
						
						if(dataDetail !=null && dataDetail.length > 3 && !"".equals(dataDetail[1])){
							sb.append("<br>"+dataDetail[1]+"<br>"+dataDetail[2]+"<br>"+dataDetail[3]+"<br>"+dataDetail[4]);
						}
					}
					sb.append("</b><br>");
					sb.append(getKeyString(dataDetail[0]));
					sb.append("<br><br>");
				}
			}
		}
	    return sb.toString();
	}
	
	public static String getKeyString(String key){
		String value = "";
		if(PERMISSION_LIST.get(key) !=null && !"".equals(PERMISSION_LIST.get(key))){
			value = PERMISSION_LIST.get(key);
		}else{
			value = USER_DEFINE_PERMISSION;
		}
	    return value;
	}
}
