/**  
 * @Title: CompletePrepareStatementI.java
 * @Package com.yokoboy.dblib.util
 * @Description: TODO
 * @author yokoboy
 * @date 2013-10-10
 */
package pachang.gsz.utils;

import java.sql.PreparedStatement;

/**
 * @Title: CompletePrepareStatementI.java
 * @Package com.yokoboy.dblib.util
 * @Description: TODO
 * @author yokoboy
 * @date 2013-10-10
 */
public interface CompletePrepareStatementI {
	
	public PreparedStatement completeIt(PreparedStatement ps);
	
}
