package com.nt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.advice.TeamNotFoundException;
import com.nt.entity.IPLTeam;
import com.nt.repository.IPLTeamRepository;
import com.nt.vo.IPLTeamVO;

import lombok.extern.slf4j.Slf4j;

@Service("IPLTeamService")
@Slf4j
public class IPLTeamMgmtServiceImpl implements IPLTeamMgmtService {
	@Autowired
	private IPLTeamRepository teamRepo;

	@Override
	public String registerIPLTeam(IPLTeamVO teamVO) {
	  log.debug("converting  VO to Entity ");
	   IPLTeam teamEntity=new IPLTeam();
	   teamEntity.setCreatedBy(System.getProperty("user.name"));
	   BeanUtils.copyProperties(teamVO, teamEntity);
		log.info("registerIPLTeam method (service)");
		int idVal=teamRepo.save(teamEntity).getTeamid();
		return "IPLTeam is saved with id value:"+idVal;
	}

	@Override
	public List<IPLTeamVO> getAllTeams() {
		log.info("getAllTeams method (service)");
		Iterable<IPLTeam> listEntities= teamRepo.findAll();
		log.debug("Converting  List of Entities to List of VOs");
		List<IPLTeamVO>  listVO=new ArrayList<IPLTeamVO>();
		listEntities.forEach(entity->{
		  IPLTeamVO teamVO=new IPLTeamVO();
		  BeanUtils.copyProperties(entity, teamVO);
		  listVO.add(teamVO);
		});
		
		return listVO;
		
	}

	@Override
	public IPLTeamVO getTeamById(int teamId) throws Exception{
		log.info("getTeamById(-) method (service)");
		IPLTeam entity=teamRepo.findById(teamId).orElseThrow(()->new TeamNotFoundException("Invalid Team Id"));
		log.debug("converting Entity object to VO obj");
		IPLTeamVO   teamVO=new IPLTeamVO();
		BeanUtils.copyProperties(entity, teamVO);
		return teamVO;
	}

}
