package com.nt.service;

import java.util.List;

import com.nt.entity.IPLTeam;
import com.nt.vo.IPLTeamVO;

public interface IPLTeamMgmtService {
     public  String registerIPLTeam(IPLTeamVO teamVO);
     public   List<IPLTeamVO> getAllTeams();
     public    IPLTeamVO    getTeamById(int teamId)throws Exception;
}
