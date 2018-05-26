/**
 * 
 */
package fr.fchantrel.blockchain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author fchantrel
 *
 */
public class NodeDTO {

	// Node address. Eg. 'http://192.168.0.5:5000'
	@JsonProperty("node_address")
	private String nodeAddress;

	/**
	 * @return the nodeAddress
	 */
	public String getNodeAddress() {
		return nodeAddress;
	}
	
	/**
	 * 
	 */
	public NodeDTO() {
		super();
	}
	
	/**
	 * @param nodeAddress
	 */
	public NodeDTO(String nodeAddress) {
		super();
		this.nodeAddress = nodeAddress;
	}
}
