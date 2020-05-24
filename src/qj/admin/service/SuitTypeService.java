package qj.admin.service;

import org.springframework.stereotype.Service;

import qj.admin.pojo.SuitType;

@Service("SuitType")
public interface SuitTypeService {
	SuitType get(int id);
}
