/**  
 * @author guosuzhou
 */
package pachang.gsz.utils;

/**
 * ip保存形式
 * @title ProxyHost
 * @author guosuzhou
 *
 * @date 2017年12月29日
 */
public class ProxyHost {

	public String ip;
	
	public Integer port;
	
	public ProxyHost() {
		
	}
	
	ProxyHost(String ip,Integer port){
		this.ip=ip;
		this.port=port;
	}
	
	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public Integer getPort() {
		return port;
	}
	
	public void setPort(Integer port) {
		this.port = port;
	}
	
	@Override
	public String toString() {
		return "代理IP： [ip=" + ip + ", port=" + port + "]";
	}
	
	
}
