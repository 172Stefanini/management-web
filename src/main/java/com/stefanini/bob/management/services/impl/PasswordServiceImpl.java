package com.stefanini.bob.management.services.impl;
import com.stefanini.bob.management.domain.Password;
import com.stefanini.bob.management.services.PasswordService;
import java.util.List;

public class PasswordServiceImpl implements PasswordService {

	public Password updatePassword(Password password) {
		
		Password old = passwordDAO.findOneByPerson(password.getPerson());
		
		if(old.getKeyPass() != password.getOldPassword())
			throw new IllegalArgumentException("Palavra chave antiga não bate com a fornecida pelo usuário.");
			
        return passwordDAO.save(password);
    }

	public List<Password> findAllPasswords() {
        return passwordDAO.findAll();
    }

	public Password findPassword(Long id) {
        return passwordDAO.findOne(id);
    }
}
