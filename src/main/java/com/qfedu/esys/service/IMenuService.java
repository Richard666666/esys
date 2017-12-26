package com.qfedu.esys.service;

import java.util.List;

import com.qfedu.esys.dto.MenuDto;
import com.qfedu.esys.dto.UserDto;
import com.qfedu.esys.entity.Menu;

public interface IMenuService {

	/**
	 * @param parentId
	 * @return
	 */
	List<Menu> getChildren(String parentId, UserDto u);

	void create(MenuDto m);

	List<Menu> getChildren(String id);

	void delete(String woSelectedIds);
}
