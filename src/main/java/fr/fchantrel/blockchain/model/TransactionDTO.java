/**
 * 
 */
package fr.fchantrel.blockchain.model;

/**
 * @author fchantrel
 *
 */
public class TransactionDTO {

	public String sender;
	
	public String recipient;
	
	public int amount;

	/**
	 * @return the sender
	 */
	public String getSender() {
		return sender;
	}

	/**
	 * @return the recipient
	 */
	public String getRecipient() {
		return recipient;
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}
	
	/**
	 * @param sender : adresse de l 'expéditeur
	 * @param recipient : adresse du bénéficiaire
	 * @param amount : montant
	 */
	public TransactionDTO(String sender, String recipient, int amount) {
		super();
		this.sender = sender;
		this.recipient = recipient;
		this.amount = amount;
	}
	
	/**
	 * 
	 */
	public TransactionDTO() {
		super();
	}

}
