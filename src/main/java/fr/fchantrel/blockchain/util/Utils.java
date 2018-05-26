/**
 * 
 */
package fr.fchantrel.blockchain.util;

/**
 * @author fchantrel
 *
 */
public class Utils {

	
	public static String zeros(int length) {
		StringBuilder strBuilder = new StringBuilder();
		
		for (int i = 0; i < length; i++) {
			strBuilder.append('0');
		} // fin du for
		
		return strBuilder.toString();
	}
}
