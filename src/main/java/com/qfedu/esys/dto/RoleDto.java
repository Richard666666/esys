package com.qfedu.esys.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.qfedu.common.entity.WoPage;
import com.qfedu.common.util.WoUtil;
import com.qfedu.esys.entity.Menu;
import com.qfedu.esys.entity.Role;

public class RoleDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1175712721650430872L;

	private String id;

	private String name;

	private String description;

	private String type;

	private String menuIds = "";

	private String menuNames = "";

	public RoleDto() {
	}

	private List<MenuDto> menus = new ArrayList<MenuDto>();

	public RoleDto(Role r) {
		this.id = r.getId();
		this.name = r.getName();
		this.description = r.getDescription();
		for (Menu m : r.getMenus()) {
			if (!"".equals(menuIds)) {
				menuIds += ",";
				menuNames += ",";
			}
			menuIds += m.getId();
			menuNames += m.getName();
			menus.add(new MenuDto(m));
		}
		this.type = r.getType();
	}

	public Role createEntity() {
		Role r = new Role();
		r.setId(id);
		r.setDescription(description);
		r.setName(name);
		r.setType(type);
		// 设置关系数据
		List<Menu> menus = new ArrayList<Menu>();
		for (String mId : getMenuIdArray()) {
			Menu m = new Menu();
			m.setId(mId);
			menus.add(m);
		}
		r.setMenus(menus);
		return r;
	}

	/**
	 * @return
	 */
	public String[] getMenuIdArray() {
		return WoUtil.splitIds(menuIds);
	}

	public String getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}

	public String getMenuNames() {
		return menuNames;
	}

	public void setMenuNames(String menuNames) {
		this.menuNames = menuNames;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<MenuDto> getMenus() {
		return menus;
	}

	public void setMenus(String menuIds) {
		this.menuIds = menuIds;
	}

	public static WoPage<RoleDto> getGridData(List<Role> pu, Long total) {
		List<RoleDto> rs = new ArrayList<RoleDto>();
		for (Role r : pu) {
			RoleDto dto = new RoleDto(r);
			rs.add(dto);
		}
		WoPage<RoleDto> puDto = new WoPage<RoleDto>(rs, total);
		return puDto;
	}
}
