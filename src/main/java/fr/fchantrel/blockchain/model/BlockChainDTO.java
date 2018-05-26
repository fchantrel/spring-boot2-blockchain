/**
 * 
 */
package fr.fchantrel.blockchain.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author fchantrel
 *
 */
public class BlockChainDTO {

	private int difficulty;

	private List<BlockDTO> blocks;

	private List<TransactionDTO> pendingTransactions = new ArrayList<TransactionDTO>();

	public BlockChainDTO(int difficulty) {
		this.difficulty = difficulty;
		blocks = new ArrayList<BlockDTO>();
	}

	public int getDifficulty() {
		return difficulty;
	}

	/**
	 * @return the blocks
	 */
	public List<BlockDTO> getBlocks() {
		return blocks;
	}

	@JsonIgnore
	public BlockDTO latestBlock() {
		return blocks.get(blocks.size() - 1);
	}

	public BlockDTO newBlock(List<TransactionDTO> lstTransaction) {
		BlockDTO latestBlock = latestBlock();
		return new BlockDTO(latestBlock.getIndex() + 1, System.currentTimeMillis(), latestBlock.getHash(),
				lstTransaction);
	}

	public void addBlock(BlockDTO b) {
		if (b != null) {
			b.mineBlock(difficulty);
			blocks.add(b);
		}
	}

	@JsonIgnore
	public boolean isFirstBlockValid() {
		BlockDTO firstBlock = blocks.get(0);

		if (firstBlock.getIndex() != 0) {
			return false;
		}

		if (firstBlock.getPreviousHash() != null) {
			return false;
		}

		return true;
	}

	public boolean isValidNewBlock(BlockDTO newBlock, BlockDTO previousBlock) {
		if (newBlock != null && previousBlock != null) {
			if (previousBlock.getIndex() + 1 != newBlock.getIndex()) {
				return false;
			}

			if (newBlock.getPreviousHash() == null || !newBlock.getPreviousHash().equals(previousBlock.getHash())) {
				return false;
			}

			if (newBlock.getHash() == null || !BlockDTO.calculateHash(newBlock).equals(newBlock.getHash())) {
				return false;
			}

			return true;
		}

		return false;
	}

	@JsonIgnore
	public boolean isBlockChainValid() {
		if (!isFirstBlockValid()) {
			return false;
		}

		for (int i = 1; i < blocks.size(); i++) {
			BlockDTO currentBlock = blocks.get(i);
			BlockDTO previousBlock = blocks.get(i - 1);

			if (!isValidNewBlock(currentBlock, previousBlock)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * @return the pendingTransactions
	 */
	@JsonIgnore
	public List<TransactionDTO> getPendingTransactions() {
		return pendingTransactions;
	}

	/**
	 * Supprime les transactions en attente
	 */
	public void resetPendingTransactions() {
		pendingTransactions = new ArrayList<TransactionDTO>();
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();

		for (BlockDTO block : blocks) {
			builder.append(block).append("\n");
		}

		return builder.toString();
	}

	public TransactionDTO addTransaction(TransactionDTO pTransaction) {
		pendingTransactions.add(pTransaction);
		return pTransaction;
	}

}
