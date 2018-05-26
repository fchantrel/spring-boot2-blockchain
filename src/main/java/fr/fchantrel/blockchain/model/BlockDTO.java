/**
 * 
 */
package fr.fchantrel.blockchain.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.fchantrel.blockchain.util.Utils;

/**
 * @author fchantrel
 *
 */
public class BlockDTO {
	
	private int index;
	// date de creation
	private long timestamp;
	// assure integrite du bloc en 
	private String hash;
	// lie le bloc courant au bloc precedent
	@JsonProperty("previous_hash")
	private String previousHash;
	// les transactions du bloc
	private List<TransactionDTO> lstTransaction;
	// utile pour la phase de minage
	private int nonce;
	
	/**
	 * @param index
	 * @param timestamp
	 * @param previousHash
	 * @param data
	 */
	public BlockDTO(int index, long timestamp, String previousHash, List<TransactionDTO> lstTransaction) {
		super();
		this.index = index;
		this.timestamp = timestamp;
		this.lstTransaction = lstTransaction;
		this.previousHash = previousHash;
		nonce = 0;
		hash = BlockDTO.calculateHash(this);
	}

	/**
	 * @return the lstTransaction
	 */
	public List<TransactionDTO> getLstTransaction() {
		return lstTransaction;
	}
	
	public int getIndex() {
		return index;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public String getHash() {
		return hash;
	}

	public String getPreviousHash() {
		return previousHash;
	}
	
	public String str() {
		
		return index + timestamp + previousHash + lstTransaction + nonce;
	}
	
	public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		
		strBuilder.append("Block #").append(index).append(", previousHash : ").append(previousHash).append(", timestamp : ")
					.append(new Date(timestamp)).append(", transactions : ").append(lstTransaction).append(", hash : ").append(hash);
		
		return strBuilder.toString();
	}
	
	public static String calculateHash(BlockDTO pBlock) {
		if(pBlock != null) {
			MessageDigest digest = null;
			try {
				digest = MessageDigest.getInstance("SHA-256");
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
				return null;
			}
			
			String txt = pBlock.str();
			final byte bytes[] = digest.digest(txt.getBytes());
			final StringBuilder strBuilder = new StringBuilder();
			
			for(final byte b : bytes) {
				String hex = Integer.toHexString(0xff & b);
				
				if(hex.length() == 1) {
					strBuilder.append('0');
				}
				
				strBuilder.append(hex);
			}
			
			return strBuilder.toString();
			
		}
		
		return null;
	}
	
	/*
	 * mine un block en resolvant la preuve de travail
	 */
	public void mineBlock(int difficulty) {
		// nombre d'essais réalisés avant de résoudre la preuve de travail durant le processus de minage pour le bloc courant
		// difficulté = nombre de 0 que nous aurons au début du hash que l'on veut obtenir pour le bloc courant
		
		nonce = 0;
		// tant que le hash calculé est différent du hash attendu nous continuons le processus de minage
		// quand la preuve de travail est résolue le bloc est miné 
		while(! getHash().substring(0, difficulty).equals(Utils.zeros(difficulty))) {
			nonce++;
			hash = BlockDTO.calculateHash(this);
		}
	}
	
}
