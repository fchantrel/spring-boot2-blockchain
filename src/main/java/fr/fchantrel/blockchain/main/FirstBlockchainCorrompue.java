/**
 * 
 */
package fr.fchantrel.blockchain.main;

import fr.fchantrel.blockchain.model.BlockDTO;
import fr.fchantrel.blockchain.model.TransactionDTO;

import java.util.ArrayList;
import java.util.Collections;

import fr.fchantrel.blockchain.model.BlockChainDTO;

/**
 * @author fchantrel
 *
 */
public class FirstBlockchainCorrompue {

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

	    System.out.println("Blockchain valid ? " + blockchain.isBlockChainValid());
	    System.out.println(blockchain);

	    // add an invalid block to corrupt Blockchain
	    blockchain.addBlock(new BlockDTO(15, System.currentTimeMillis(), "aaaabbb", new ArrayList<TransactionDTO>()));

	    System.out.println("Blockchain valid ? " + blockchain.isBlockChainValid());
	}

}
