/**
 * 
 */
package fr.fchantrel.blockchain.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.fchantrel.blockchain.model.BlockChainDTO;
import fr.fchantrel.blockchain.model.BlockDTO;
import fr.fchantrel.blockchain.model.NodeDTO;
import fr.fchantrel.blockchain.model.TransactionDTO;

/**
 * @author fchantrel
 *
 */
@Service
public class BlockChainService {

	private static final int DIFFICULTE = 4;

	private static BlockChainDTO blockchain = new BlockChainDTO(DIFFICULTE);

	static {
		// create the first block
		BlockDTO b = new BlockDTO(0, System.currentTimeMillis(), null, Collections.<TransactionDTO>emptyList());
		b.mineBlock(DIFFICULTE);
		blockchain.addBlock(b);
	}
	
	private List<NodeDTO> lstNode = new ArrayList<NodeDTO>();

	/**
	 * @return the blockchain
	 */
	public BlockChainDTO getBlockchain() {
		return blockchain;
	}

	/**
	 * @return the lstNode
	 */
	public List<NodeDTO> getLstNode() {
		return lstNode;
	}
	
	public TransactionDTO addTransaction(TransactionDTO pTransaction) {
		blockchain.addTransaction(pTransaction);
		return pTransaction;
	}

	public BlockDTO addBlock() {
		// On crée le nouveau block à ajouter à la chaine
		BlockDTO lastBlock = blockchain.getBlocks().get(blockchain.getBlocks().size() - 1);
		BlockDTO newBlock = new BlockDTO(blockchain.getBlocks().size(), System.currentTimeMillis(), lastBlock.getHash(),
				blockchain.getPendingTransactions());
		newBlock.mineBlock(DIFFICULTE);
		blockchain.addBlock(newBlock);
		blockchain.resetPendingTransactions();

		return newBlock;
	}
	
	public NodeDTO registerNode(NodeDTO pNode) {
		lstNode.add(pNode);
		return pNode;
	}
	
	public boolean resolveConflicts() {
        // Algo de consensus : remplace la chaine par la plus longue chaine valide du réseau
        // return True si la chaine est remplacée, False sinon
        List<NodeDTO> neighbours = lstNode;
        BlockChainDTO new_chain = null;
        boolean isReplaced = false;

        // On valide uniquement les chaines plus longues que celle du noeud
        int max_length = blockchain.getBlocks().size();

        // Récupère les chaines des noeuds connus
        int maxLength = blockchain.getBlocks().size();
        for (Iterator<NodeDTO> iterator = neighbours.iterator(); iterator.hasNext();) {
			NodeDTO nodeDTO = (NodeDTO) iterator.next();
			
			// on récupère la blockchain sur chaque noeud
			String currentURL = nodeDTO.getNodeAddress() + "/blockchain";
			
			// TODO a deporter dans un service qui utilise HTTPClient
			/*
			HttpRequest request = HttpRequest.newBuilder()
					  .uri(new URI(currentURL))
					  .GET()
					  .build();
			
			// on parse la reponse et on obtient currentBlockChain
			// on vérifie la longueur de la chaine et sa validité
			if((currentBlockChain.getBlocks().size() > maxLength) && (blockchain.isBlockChainValid())) {
				maxLength = currentBlockChain.getBlocks().size();
				blockchain = currentBlockChain;
				isReplaced = true;
			}
			*/
		}
		return isReplaced;
	}

}
