/**
 * 
 */
package com.qfedu.scene.service;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.qfedu.common.entity.WoPage;
import com.qfedu.esys.dto.UserDto;
import com.qfedu.scene.dto.StaffDto;
import com.qfedu.scene.entity.Staff;

/**
 * @author cailei
 *
 */
public interface IStaffService {
	
	

	WoPage<StaffDto> getList(String name, Long start, Long rows);

	void delete(String[] ids);

	void create(StaffDto s);

	void update(StaffDto s);

	/**
	 * @return
	 */
	List<Staff> findAll();

}
