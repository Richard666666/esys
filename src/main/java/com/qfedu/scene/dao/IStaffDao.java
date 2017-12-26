/**
 * 
 */
package com.qfedu.scene.dao;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.qfedu.common.entity.WoPage;
import com.qfedu.esys.entity.User;
import com.qfedu.scene.entity.Staff;

/**
 * @author cailei
 *
 */
public interface IStaffDao {

	List<Staff> findAll();

	void create(Staff s);

	void delete(String id);
	
	void update(Staff s);

	WoPage<Staff> findAllByName(String name, Long start, Long rows);
	
	
}
