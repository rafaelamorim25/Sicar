package services;

import domain.Usuario;
import repositories.LoginRepository;

public class LoginService {
	
	LoginRepository loginRepository = new LoginRepository();
	
	public boolean login(Usuario usuario) {
		return loginRepository.login(usuario);
	}

}
