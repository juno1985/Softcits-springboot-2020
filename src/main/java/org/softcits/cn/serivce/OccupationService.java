package org.softcits.cn.serivce;

import org.softcits.cn.mapper.OccupationMapper;
import org.softcits.cn.model.Occupation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class OccupationService {
	
	@Autowired
	private OccupationMapper occupationMapper;
	
	public Occupation getFirstOccupation() {
		Occupation occupation = occupationMapper.getFirstOccupation();
		return occupation;
	}

}
