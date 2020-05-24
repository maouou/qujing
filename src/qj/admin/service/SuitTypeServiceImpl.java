package qj.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qj.admin.dao.SuittypeDAO;
import qj.admin.pojo.SuitType;


public class SuitTypeServiceImpl implements SuitTypeService {

	@Autowired
	SuittypeDAO suitTypeDAO;
	@Override
	public SuitType get(int id) {
		// TODO Auto-generated method stub
		return suitTypeDAO.get(id);
	}

}
