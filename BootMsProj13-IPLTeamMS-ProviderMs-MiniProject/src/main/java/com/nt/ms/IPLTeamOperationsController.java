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

import com.nt.entity.IPLTeam;
import com.nt.service.IPLTeamMgmtService;
import com.nt.vo.IPLTeamVO;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/team-api")  //global path
@Slf4j  //gives  Logger obj having   name log
public class IPLTeamOperationsController {
	@Autowired
	private IPLTeamMgmtService teamService;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerTeam(@RequestBody IPLTeamVO teamVO)throws Exception{
		//use service
			String resultMsg=teamService.registerIPLTeam(teamVO);
			log.info("registerTeam method -- Restcontroller ");
			return new ResponseEntity<String>(resultMsg,HttpStatus.CREATED);
	}//method
	
	@GetMapping("/all")
	public  ResponseEntity<List<IPLTeamVO>>   showAllTeams()throws Exception{
			//use service
			 List<IPLTeamVO> list=teamService.getAllTeams();
				log.info("showAllTeams() method --Rest Controller ");
			 return  new ResponseEntity<List<IPLTeamVO>>(list,HttpStatus.OK);
	}//method
	
	@GetMapping("/find/{id}")
	public  ResponseEntity<IPLTeamVO>  showTeamById(@PathVariable int id)throws Exception{
			//use service
			IPLTeamVO teamVO=teamService.getTeamById(id);
			log.info("showByTeamId(-) method -- controller ");
			return new ResponseEntity<IPLTeamVO>(teamVO,HttpStatus.OK);
	}//method

}
