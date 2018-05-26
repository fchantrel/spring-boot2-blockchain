package fr.fchantrel.blockchain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.fchantrel.blockchain.model.BlockChainDTO;
import fr.fchantrel.blockchain.model.BlockDTO;
import fr.fchantrel.blockchain.model.NodeDTO;
import fr.fchantrel.blockchain.model.TransactionDTO;
import fr.fchantrel.blockchain.service.BlockChainService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author fchantrel
 *
 */
@Api(value = "BlockChain API", description = "the blockchain API", tags = "BlockChain")
@Controller
@RequestMapping(value = "${webservice.root.path}/")
public class BlockChainRestController {

	@Autowired
	BlockChainService blockChainService;

	@ApiOperation(value = "get the blockchain", response = BlockChainDTO.class, tags = "BlockChain")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "BlockChain found", response = BlockChainDTO.class),
			@ApiResponse(code = 400, message = "Request rejected", response = Error.class) })
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE }, path = "/blockchain")
	@ResponseBody
	public BlockChainDTO list() {
		return blockChainService.getBlockchain();
	}

	@ApiOperation(value = "add a transaction", response = TransactionDTO.class, tags = "Transactions")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Transaction saved", response = TransactionDTO.class),
			@ApiResponse(code = 400, message = "Request rejected", response = Error.class) })
	@RequestMapping(method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, path = "/transactions", name = "/transactions")
	@ResponseBody
	public TransactionDTO addTransaction(
			@ApiParam(value = "transactionRequest", required = true) @RequestBody TransactionDTO request) {
		blockChainService.addTransaction(request);
		return request;
	}

	@ApiOperation(value = "add a block to the blockchain", response = TransactionDTO.class, tags = "Blocks")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Block saved", response = TransactionDTO.class),
			@ApiResponse(code = 400, message = "Request rejected", response = Error.class) })
	@RequestMapping(method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, path = "/blocks", name = "/blocks")
	@ResponseBody
	public BlockDTO addBlock() {
		BlockDTO lastBlock = blockChainService.addBlock();
		return lastBlock;
	}

	@ApiOperation(value = "register new node to the blockchain", response = NodeDTO.class, tags = "Node")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Node registered", response = NodeDTO.class),
			@ApiResponse(code = 400, message = "Request rejected", response = Error.class) })
	@RequestMapping(method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, path = "/nodes/register", name = "/nodes/register")
	@ResponseBody
	public NodeDTO registerNode(@ApiParam(value = "nodesRequest", required = true) @RequestBody NodeDTO request) {

		blockChainService.registerNode(request);
		return request;
	}

	@ApiOperation(value = "resolve conflicts", tags = "BlockChain")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Conflicts resolved"),
			@ApiResponse(code = 400, message = "Request rejected", response = Error.class) })
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE }, path = "/nodes/resolve")
	@ResponseBody
	public BlockChainDTO resolveConflicts() {
		blockChainService.resolveConflicts();
		return blockChainService.getBlockchain();
	}
}
