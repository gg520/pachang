package pachang.gsz.utils;


import java.util.Map;

import org.jsoup.nodes.Document;

/**
 * 进行请求服务器操作，获取document
 * 
 * @title GetDocument
 * @author guosuzhou
 *
 * @data2017年12月29日下午8:28:56
 *
 */
public interface GetDocument {

	/**
	 * http：get请求
	 * 
	 * @param url 链接
	 * @param heads 请求头
	 * @param proxyHost 代理IP
	 * @param encoading 编码类型 ，默认utf-8
	 * @return
	 * @throws Exception
	 */
	Document httpGet(String url,Map<String, String> heads,ProxyHost proxyHost,String encoading)throws Exception;
	
	/**
	 * http： get请求
	 * 
	 * @param url 链接
	 * @param heads 请求头，key,value的形式存在
	 * @param encoading
	 * @return
	 * @throws Exception
	 */
	Document httpGet(String url,Map<String,String> heads,String encoading) throws Exception;
	
	/**
	 * http:get请求
	 * 
	 * @param url 链接
	 * @param encoading 编码
	 * @return
	 * @throws Exception
	 */
	Document httpGet(String url,String encoading)throws Exception;
	
	/**
	 *  http: get请求
	 *  默认编码： utf-8
	 * @author guosuzhou
	 *
	 * @param url
	 * @return
	 *
	 *date 2017年12月29日 下午1:12:16
	 */
	Document httpGet(String url)throws Exception;
	
	/**
	 * http post 请求
	 *
	 * @author guosuzhou
	 *
	 * @param url 链接
	 * @param map 参数信息， 例如：模拟登陆的账号密码
	 * @return
	 * @throws Exception
	 *
	 *date 2017年12月29日 下午1:20:42
	 */
	Document httpPost(String url, Map<String,String> map)throws Exception;
	
	/**
	 * http post 请求
	 *
	 * @author guosuzhou
	 *
	 * @param url 链接
	 * @param map 参数信息， 例如：模拟登陆的账号密码
	 * @param heads 请求头
	 * @return
	 * @throws Exception
	 *
	 *date 2017年12月29日 下午1:20:42
	 */
	Document httpPost(String url, Map<String,String> map,String encoading)throws Exception;
	
	/**
	 * http post 请求
	 *
	 * @author guosuzhou
	 *
	 * @param url 链接
	 * @param map 参数信息， 例如：模拟登陆的账号密码
	 * @param heads 请求头
	 * @param encoading 编码类型 utf-8、gbk等
	 * @return
	 * @throws Exception
	 *
	 *date 2017年12月29日 下午1:20:42
	 */
	Document httpPost(String url, Map<String,String> map,Map<String,String> heads,String encoading)throws Exception;
	
	/**
	 * http: post 请求
	 *
	 * @author guosuzhou
	 *
	 * @param url 链接
	 * @param map 参数信息， 例如：模拟登陆的账号密码
	 * @param heads 请求头
	 * @param proxyHost 代理IP
	 * @param encoading 编码类型 utf-8、gbk等
	 * @return
	 * @throws Exception
	 *
	 *date 2017年12月29日 下午1:21:29
	 */
	Document httpPost(String url, Map<String,String> map, Map<String,String> heads,ProxyHost proxyHost,String encoading)throws Exception;
	
	/**
	 * https: get请求，
	 *
	 * @author guosuzhou
	 *
	 * @param url 链接
	 * @param encoading 编码类型 utf-8、gbk等
	 * @return
	 *
	 *date 2017年12月29日 下午1:10:55
	 */
	Document httpsGet(String url,String encoading)throws Exception;
	
	/**
	 * https: post请求
	 *
	 * @author guosuzhou
	 *
	 * @param url
	 * @param map 参数信息， 例如：模拟登陆的账号密码
	 * @param encoading 编码类型 utf-8、gbk等
	 * @return
	 *
	 *date 2017年12月29日 下午1:11:50
	 */
	Document httpsPost(String url, Map<String, String> map, String encoading) throws Exception;
}
