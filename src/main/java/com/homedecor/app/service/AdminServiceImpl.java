package com.homedecor.app.service;



import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homedecor.app.dao.AdminRepository;
import com.homedecor.app.dto.Admin;
import com.homedecor.app.exception.AdminException;


@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepositary;

	@Override
	public Boolean addAdmin(Admin admin) throws AdminException {
		if (admin == null) {
			throw new AdminException("Admin not added please fill the mandatory feilds");
		}
		Optional<Admin> addAdmin = this.adminRepositary.findById(admin.getAdminID());
		if (addAdmin.isPresent()) {
			throw new AdminException("This Admin Id is already present! Try with new");
		} else {
			this.adminRepositary.save(admin);
		}
		return true;
	}

	@Override
	public Optional<Admin> getAdminById(Integer adminId) throws AdminException {
		Optional<Admin> foundAdminById = this.adminRepositary.findById(adminId);
		if (foundAdminById.isEmpty()) {
			throw new AdminException(adminId+" Admin Id is not present in the record");
		}
		return foundAdminById;
	}

	@Override
	public Boolean login(Integer adminId, String password) throws AdminException {
		Boolean isLoginBoolean = false;
		Optional<Admin> foundAdmin = this.adminRepositary.findByAdminIDAndAdminPassword(adminId, password);
		if (foundAdmin.isEmpty()) {
			throw new AdminException("Invalid Id or password");
		} else {
			isLoginBoolean = true;
		}
		return isLoginBoolean;
	}

	@Override
	public Boolean updatePassword(Integer adminId, String oldPassword, String newPassword) throws AdminException {
		Optional<Admin> getAdmin=this.adminRepositary.findById(adminId);
		if(getAdmin.isEmpty())throw new AdminException(adminId+" Admin ID is not present in the record");
		Admin foundAdmin  = getAdmin.get();
		String savedPassword = foundAdmin.getAdminPassword();
		Boolean isLogin = false;
		if (savedPassword.compareTo(oldPassword) == 0) {
			foundAdmin.getAdminPassword().replaceAll(oldPassword, newPassword);
			foundAdmin.setAdminPassword(newPassword);
			adminRepositary.save(foundAdmin);
			isLogin=true;
		} else {
			throw new AdminException("Old password dosen't match");
		}
		return isLogin;
	}

	@Override
	public Boolean deleteAdminById(Integer adminId) throws AdminException {
		Optional<Admin> foundAdmin = this.adminRepositary.findById(adminId);
		if (foundAdmin.isEmpty())throw new AdminException("Admin not exist for this Id "+adminId);
		this.adminRepositary.deleteById(adminId);
		return true;
	}

}
