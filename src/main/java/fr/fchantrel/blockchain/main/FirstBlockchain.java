/**
 * 
 */
package fr.fchantrel.blockchain.main;

import java.util.ArrayList;
import java.util.Collections;

import fr.fchantrel.blockchain.model.BlockChainDTO;
import fr.fchantrel.blockchain.model.BlockDTO;
import fr.fchantrel.blockchain.model.TransactionDTO;

/**
 * @author fchantrel
 *
 */
public class FirstBlockchain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	    BlockChainDTO blockchain = new BlockChainDTO(4);
	    // on ajoute le bloc initial
		BlockDTO b = new BlockDTO(0, System.currentTimeMillis(), null, Collections.<TransactionDTO>emptyList());
		b.mineBlock(4);
		blockchain.addBlock(b);
	    
	    blockchain.addBlock(blockchain.newBlock(new ArrayList<TransactionDTO>()));
	    blockchain.addBlock(blockchain.newBlock(new ArrayList<TransactionDTO>()));
	    blockchain.addBlock(blockchain.newBlock(new ArrayList<TransactionDTO>()));
	  
	    System.out.println(blockchain);
	    System.out.println("Blockchain valid ? " + blockchain.isBlockChainValid());
		
	}

}
