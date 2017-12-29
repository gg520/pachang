package pachang.gsz.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * 模拟请求参数
 * @title GetDocument
 * @author guosuzhou
 *
 * @date 2017年12月29日
 */
public class GetDocumentImpl implements GetDocument{
	
	@Override
	public Document httpGet(String url, Map<String, String> heads, ProxyHost proxyHost, String encoading)
			throws Exception {
		return httpGetConntion(url,heads,proxyHost,encoading);
	}

	@Override
	public Document httpGet(String url, Map<String, String> heads, String encoading) throws Exception {
		return httpGetConntion(url,heads,null,encoading);
	}

	@Override
	public Document httpGet(String url, String encoading) throws Exception {
		return httpGetConntion(url,null,null,encoading);
	}
	

	@Override
	public Document httpGet(String url) throws Exception {
		return httpGetConntion(url,null,null,null);
	}

	@Override
	public Document httpPost(String url, Map<String, String> map) throws Exception {
		return httpPostConntion(url,map,null,null, null);
	}

	@Override
	public Document httpPost(String url, Map<String, String> map, String encoading) throws Exception {
		return httpPostConntion(url,map,null,null, encoading);
	}
	
	@Override
	public Document httpPost(String url, Map<String,String> map, Map<String, String> heads, String encoading) throws Exception {
		return httpPostConntion(url,map,heads,null, encoading);
	}

	@Override
	public Document httpPost(String url, Map<String,String> map, Map<String, String> heads, ProxyHost proxyHost, String encoading)
			throws Exception {
		return httpPostConntion(url,map,heads,proxyHost, encoading);
	}
	

	@Override
	public Document httpsGet(String url,String encoading) throws Exception {
		return httpsRequest(url, "GET", null, encoading);
	}

	@Override
	public Document httpsPost(String url, Map<String, String> map, String encoading) throws Exception {
		String str="";
		Iterator<Entry<String, String>> entries = map.entrySet().iterator();  
		while (entries.hasNext()) {  
		    Entry<String, String> entry = entries.next();
		    str+=entry.getKey()+":"+entry.getValue()+";";
		}  
		return httpsRequest(url, "POST", str, encoading);
	}

	/**
	 * http： post 
	 *
	 * @author guosuzhou
	 *
	 * @param url
	 * @param map 模拟登陆的参数
	 * @param heads
	 * @param proxyHost
	 * @param encoading
	 * @return
	 * @throws Exception
	 *
	 *date 2017年12月29日 下午1:58:29
	 */
	private static Document httpPostConntion(String url,Map<String,String> map,Map<String, String> heads,ProxyHost proxyHost,String encoading)throws Exception{
        String logMassage="url ： "+url+"  使用post请求    ";
		HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try{
            httpClient = new DefaultHttpClient();
            httpPost = new HttpPost(url);
            if (proxyHost!=null) {
				HttpHost proxy = null;
				proxy = new HttpHost(proxyHost.getIp(),
							proxyHost.getPort());
				httpClient.getParams().setParameter(
						ConnRoutePNames.DEFAULT_PROXY, proxy);
				//使用代理IP，日志信息
				logMassage+=proxyHost.toString()+"  ";
			}else{
				logMassage+="未使用代理IP  ";
			}
			httpClient.getParams().setParameter(
						CoreConnectionPNames.CONNECTION_TIMEOUT, 60 * 1000);
			httpClient.getParams().setParameter(
						CoreConnectionPNames.SO_TIMEOUT, 60 * 1000);
			httpClient.getParams().setParameter(
						ClientPNames.ALLOW_CIRCULAR_REDIRECTS, false);
			//设置请求头
			if(heads!=null){
				Iterator<Entry<String, String>> entries = heads.entrySet().iterator();
				while (entries.hasNext()) {
				    Entry<String, String> entry = entries.next();
				    httpPost.setHeader(entry.getKey(), entry.getValue());
				}
			}else{
				httpPost.setHeader("User-Agent", UserAgent.getUserAgent());
			}
            //设置参数
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            if(map!=null){
            	 Iterator iterator = map.entrySet().iterator();
                 while(iterator.hasNext()){  
                     Entry<String,String> elem = (Entry<String, String>) iterator.next();  
                     list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));
                 }
                 if(encoading==null){
             		encoading="utf-8";
             	}
            }
            if(list.size() > 0){
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,encoading);
                httpPost.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(httpPost);
            if(response != null){
            	 if (response.getStatusLine().getStatusCode() != 200) {
 		        	
 		        	System.err.println(response.getStatusLine().getStatusCode());
 		        	logMassage+="返回结果： "+response.getStatusLine().getStatusCode()+"   ";
 		        	throw new Exception();
 		        }
            	System.err.println(response.getStatusLine().getStatusCode());
            	logMassage+="返回结果： "+response.getStatusLine().getStatusCode()+"   ";
                HttpEntity resEntity = response.getEntity();
                if(resEntity != null){
                    result = EntityUtils.toString(resEntity,encoading);
                }
            }
        }catch(Exception ex){
            logMassage+="出现异常； 异常信息：   "+ex.getMessage();
        }
//        LogUtils.addLog(logMassage);
        return Jsoup.parse(result);
    }  
	
	/**
	 *  http： get请求
	 *
	 * @author guosuzhou
	 *
	 * @param url 网站
	 * @param heads 请求头
	 * @param proxyHost ip
	 * @param encoading 编码
	 * @return
	 * @throws Exception
	 *
	 *date 2017年12月29日 下午1:57:22
	 */
	private static Document httpGetConntion(String url,Map<String, String> heads,ProxyHost proxyHost,String encoading) throws Exception{
		HttpClient httpClient = new DefaultHttpClient();
		url=url.trim();
		HttpGet httpGet = new HttpGet(url);
		if (proxyHost!=null) {
				HttpHost proxy = null;
				proxy = new HttpHost(proxyHost.getIp(),
							proxyHost.getPort());
				httpClient.getParams().setParameter(
						ConnRoutePNames.DEFAULT_PROXY, proxy);
//				使用代理IP，日志信息
				
		}
		httpClient.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, 60 * 1000);
		httpClient.getParams().setParameter(
					CoreConnectionPNames.SO_TIMEOUT, 60 * 1000);
		httpClient.getParams().setParameter(
					ClientPNames.ALLOW_CIRCULAR_REDIRECTS, false);
		if(heads!=null){
			Iterator<Entry<String, String>> entries = heads.entrySet().iterator();  
			while (entries.hasNext()) {  
			    Entry<String, String> entry = entries.next();
			    //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());  
//			    此处应有日志
			    httpGet.setHeader(entry.getKey(), entry.getValue());
			  
			}  
		}else{
			httpGet.setHeader("User-Agent", UserAgent.getUserAgent());
		}
		HttpResponse execute = null;
		    try{
		    	execute = httpClient.execute(httpGet);
		        if (execute.getStatusLine().getStatusCode() != 200) {
		        	
		        	System.err.println(execute.getStatusLine().getStatusCode());
		        	//请求失败，
		        	throw new Exception();
		        }
		    }catch (Exception e) {
//		    	打印日志
		    	
			}
	    String data=null;
	    try{
	    	System.out.println(execute.getStatusLine().getStatusCode());
	        HttpEntity entity = execute.getEntity();
	        if(encoading==null){
	        	encoading="utf-8";
	        }
	        data=EntityUtils.toString(entity,encoading);
	        return Jsoup.parse(data);
	    }catch (Exception e) {
	    	e.printStackTrace();
	    }
	    return null;
	}
	
	  
	
	/**
	 * HTTPS请求方式
	 *
	 * @author guosuzhou
	 *
	 * @param requestUrl 链接
	 * @param requestMethod 请求方法 get或post
	 * @param outputStr 参数 post请求的参数
	 * @return
	 *
	 *date 2017年12月11日 下午6:34:46
	 */
	private static Document httpsRequest(String requestUrl,String requestMethod,String outputStr,String encoading){
	    StringBuffer buffer=null;  
	    try{  
	    //创建SSLContext  
	    SSLContext sslContext=SSLContext.getInstance("SSL");  
	    TrustManager[] tm={new MyX509TrustManager()};
	    //初始化  
	    sslContext.init(null, tm, new java.security.SecureRandom());;  
	    //获取SSLSocketFactory对象  
	    SSLSocketFactory ssf=sslContext.getSocketFactory();  
	    URL url=new URL(requestUrl);  
	    HttpsURLConnection conn=(HttpsURLConnection)url.openConnection();
	    conn.setDoOutput(true);  
	    conn.setDoInput(true);  
	    conn.setUseCaches(false);  
	    conn.setRequestMethod(requestMethod);  
	    //设置当前实例使用的SSLSoctetFactory  
	    conn.setSSLSocketFactory(ssf);  
	    conn.connect();  
	    //往服务器端传送的内容
	    if(null!=outputStr){
	        OutputStream os=conn.getOutputStream();  
	        os.write(outputStr.getBytes("utf-8"));
	        os.close();
	    }  
	      
	    //读取服务器端返回的内容  
	    InputStream is=conn.getInputStream();  
	    if(encoading==null){
	    	encoading="utf-8";
	    }
	    InputStreamReader isr=new InputStreamReader(is,encoading);
	    BufferedReader br=new BufferedReader(isr);
	    buffer=new StringBuffer();
	    String line=null;
	    while((line=br.readLine())!=null){
	        buffer.append(line);
	    }
	    }catch(Exception e){
	        e.printStackTrace();  
	    }
	    return Jsoup.parse(buffer.toString());
	}
	public static void main(String[] args){
//		Document doc=httpsRequest("https://www.liepin.com/job/1910511536.shtml?sfrom=recom-recom_jd-fab0d74fa437e71dd927d5c2fb803520-1&d_pageSize=100&d_headId=fab0d74fa437e71dd927d5c2fb803520&d_ckId=fab0d74fa437e71dd927d5c2fb803520&d_sfrom=recom_jd&d_curPage=0&d_posi=0","GET",null);  
//	    System.out.println(doc);  
		String url="http://swjw.gygov.gov.cn/module/jslib/jquery/jpage/dataproxy.jsp?startrecord=61&endrecord=120&perpage=20";
		Map<String,String> map=new HashMap<>();
		map.put("appid", "1");
		map.put("webid", "37");
		map.put("path", "/");
		map.put("columnid", "25205");
		map.put("sourceContentType", "1");
		map.put("unitid", "29750");
		map.put("webname", "贵阳市卫生和计划生育委员会");
		map.put("permissiontype", "0");
		try {
			Document doc=httpPostConntion(url, map, null, null,null);
			System.out.println(doc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	
}
