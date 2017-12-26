package com.qfedu.esys.service;

import java.util.List;

import com.qfedu.common.entity.WoPage;
import com.qfedu.esys.dto.DictionaryDto;
import com.qfedu.esys.entity.Dictionary;
public interface IDictionaryService {

	WoPage<Dictionary> getGridData(String dicType, Long start, Long limit);

	void create(DictionaryDto dic);
	
	void update(DictionaryDto dic);

	void updatePO (Dictionary dic);
	
	void delete (String[] ids);
	
	List<Dictionary> getByType (String type);
}
