//IPLPlayerPOperationsController .java
package com.nt.ms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.client.IPLTeamServiceMSClient;
import com.nt.entity.IPLPlayer;
import com.nt.service.IPLPlayerMgmtService;
import com.nt.vo.IPLPlayerVO;
import com.nt.vo.IPLTeamVO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/player-api") //global path
@Slf4j
public class IPLPlayerPOperationsController {
	@Autowired
	private  IPLPlayerMgmtService playerService;
	@Autowired
	private   IPLTeamServiceMSClient  client;
	
	@PostMapping("/save")
	@CircuitBreaker(name = "IPLPLAYER-SERVICE",fallbackMethod = "doFallbackForIPLTeam")
	public  ResponseEntity<String> registerPlayer(@RequestBody IPLPlayerVO playerVO){
		log.debug("registerPlayer  begining");
		  //get Player's team id  
		 int tid=playerVO.getTeam().getTeamid();
			log.debug("  Team Id is gathered");
				 
		// get IPLTeam object from Target MS (IPLTeamMs)
		IPLTeamVO teamVO=client.getTeamById(tid);  // (MicroServices intra communication)
		log.debug("  MS Intra Communication is taking place");
		
		//set Team object to Player object
		playerVO.setTeam(teamVO);
		
		log.debug("Team obj is assigned to Player object");
		//use serivce  to save  player and his  team info
		 String msg=playerService.registerPlayer(playerVO);
		 log.debug("Service  method is invoked");
		 return  new ResponseEntity<String>(msg , HttpStatus.CREATED);
	}//method
	
	@GetMapping("/all")
	public   ResponseEntity<List<IPLPlayerVO>>   showAllPlayers(){
			//use service
			List<IPLPlayerVO> listVO=playerService.findAllPlayers();
			return  new ResponseEntity<List<IPLPlayerVO>>(listVO,HttpStatus.OK);
	}//method
	
	@GetMapping("/find/{id}")
	public    ResponseEntity<IPLPlayerVO>  showPlayerById(@PathVariable int id){
			//use service
			IPLPlayerVO playerVO=playerService.findPlayerById(id);
			return  new ResponseEntity<IPLPlayerVO>(playerVO,HttpStatus.OK);
	}//method
	
	
	// Circuit Breaker Fallback method
	public    ResponseEntity<String>   doFallbackForIPLTeam(Exception e){
		return new ResponseEntity<String>("Problm in  IPLTeam Communication"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
