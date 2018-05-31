/**
 * 
 */
package fr.fchantrel.blockchain.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import fr.fchantrel.blockchain.model.BlockChainDTO;
import fr.fchantrel.blockchain.service.BlockChainService;

/**
 * @author fchantrel
 * Dans ce test on précise qu'on ne charge pas tout le contexte mais seulement BlockChainRestControllerTest
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(BlockChainRestController.class)
public class BlockChainRestControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private BlockChainService service;
    
    @Test
    public void blockchainShouldReturnMessageFromService() throws Exception {
    	
    	BlockChainDTO retour = new BlockChainDTO(4);
    	
        when(service.getBlockchain()).thenReturn(retour);
        this.mockMvc.perform(get("/demoBlockChain/blockchain/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("blocks")));
    }
	
}
