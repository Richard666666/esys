package com.qfedu.esys.service;

import com.qfedu.common.entity.WoPage;
import com.qfedu.esys.dto.RoleDto;

public interface IRoleService {

	WoPage<RoleDto> getGridData(String name, Long start, Long rows);

	void create(RoleDto r);

	void delete(String[] ids);

	void update(RoleDto r);
}
