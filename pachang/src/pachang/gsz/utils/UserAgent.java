
package pachang.gsz.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @Title: UserAgent.java
 * @Package com.yokoboy.fanPingBi
 * @Description: TODO
 * @author yokoboy
 * @date 2013-5-13
 */
public class UserAgent {

	public static List<String> userAgentList;

	public static int f = 0;

	private static void getU1() {
		userAgentList.clear();
		userAgentList.add("Mozilla/5.0 (Windows NT 5.2) AppleWebKit/534.30 (KHTML, like Gecko) Chrome/12.0.742.122 Safari/534.30");
		userAgentList.add("Mozilla/5.0 (Windows NT 5.1; rv:5.0) Gecko/20100101 Firefox/5.0");
		userAgentList.add("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET4.0E; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; .NET4.0C)");
		userAgentList.add("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.2; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET4.0E; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; .NET4.0C)");
		userAgentList.add("Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 2.0.50727)");
		userAgentList.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E)");
		userAgentList.add("Opera/9.80 (Windows NT 5.1; U; zh-cn) Presto/2.9.168 Version/11.50");
		userAgentList.add("Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
		userAgentList.add("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; .NET CLR 2.0.50727; .NET CLR 3.0.04506.648; .NET CLR 3.5.21022; .NET4.0E; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; .NET4.0C)");
		userAgentList.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN) AppleWebKit/533.21.1 (KHTML, like Gecko) Version/5.0.5 Safari/533.21.1");
		userAgentList.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; ) AppleWebKit/534.12 (KHTML, like Gecko) Maxthon/3.0 Safari/534.12");
		userAgentList.add("Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 2.0.50727; TheWorld)");
		userAgentList.add("(Windows Vista) Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
		userAgentList.add("Mozilla/4.8 [en] (Windows NT 6.0; U)");
		userAgentList.add("Opera/9.20 (Windows NT 6.0; U; en)");
	}

	private static void getU2() {
		userAgentList.clear();
		userAgentList.add("Opera/7.51 (Windows NT 5.1; U) [en]");
		userAgentList.add("Opera/7.50 (Windows XP; U)");
		userAgentList.add("Opera/7.50 (Windows ME; U) [en]");
		userAgentList.add("Mozilla/5.0 (Windows; U; Win98; en-US; rv:1.4) Gecko Netscape/7.1 (ax)");
		userAgentList.add("Mozilla/4.8 [en] (Windows NT 5.1; U)");
		userAgentList.add("Mozilla/3.01Gold (Win95; I)");
		userAgentList.add("Mozilla/2.02E (Win95; U)");
		userAgentList.add("Mozilla/5.0 (Macintosh; U; PPC Mac OS X; en) AppleWebKit/125.2 (KHTML, like Gecko) Safari/125.8");
		userAgentList.add("Mozilla/5.0 (Macintosh; U; PPC Mac OS X; en) AppleWebKit/125.2 (KHTML, like Gecko) Safari/85.8");
		userAgentList.add("Mozilla/5.0 (Macintosh; U; PPC Mac OS X Mach-O; en-US; rv:1.7a) Gecko/20040614 Firefox/0.9.0+");
		userAgentList.add("Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.6) Gecko/20040614 Firefox/0.8");
		userAgentList.add("Mozilla/5.0 (compatible; Konqueror/3.3; Linux 2.6.8-gentoo-r3; X11;");
		userAgentList.add("Mozilla/5.0 (X11; U; Linux; i686; en-US; rv:1.6) Gecko Debian/1.6-7");
		userAgentList.add("MSIE (MSIE 6.0; X11; Linux; i686) Opera 7.23");
		userAgentList.add("Links (2.1pre15; Linux 2.4.26 i686; 158x61)");
		userAgentList.add("Links/0.9.1 (Linux 2.4.24; i386;)");
		userAgentList.add("Lynx/2.8.5rel.1 libwww-FM/2.14 SSL-MM/1.4.1 GNUTLS/0.8.12");
		userAgentList.add("w3m/0.5.1");
		userAgentList.add("Links (2.1pre15; FreeBSD 5.3-RELEASE i386; 196x84)");
		userAgentList.add("Mozilla/5.0 (X11; U; FreeBSD; i386; en-US; rv:1.7) Gecko");
		userAgentList.add("Mozilla/4.77 [en] (X11; I; IRIX;64 6.5 IP30)");
		userAgentList.add("Mozilla/4.8 [en] (X11; U; SunOS; 5.7 sun4u)");
	}

	/**
	 * 随机获得一个UserAgent
	 * 
	 * @Description: UserAgent
	 * @author yokoboy
	 * @date 2013-5-13
	 */
	public synchronized static String getUserAgent() {
		if (userAgentList == null) {
			userAgentList = new ArrayList<String>();
			if (f == 0) {
				getU1();
			} else {
				getU2();
			}
		}
		return userAgentList.get((int) (userAgentList.size() * Math.random()));
	}

	public static void main(String[] args) {
		System.out.println(getUserAgent());
	}
}
