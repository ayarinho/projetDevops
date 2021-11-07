package tn.esprit.spring.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.NonNull;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;


@Service
public class UserServiceImpl {

	@Autowired
	UserRepository userRepository;

	Logger logger = LoggerFactory.getLogger(EmployeServiceImpl.class); 

	
	public User authenticate(String login, String password) {
		
		
		logger.info("Signing In");
		User user = userRepository.getUserByEmailAndPassword(login, password);
		if (user != null) {
			logger.info("user Logged In seccufully  :"+ user);
		return user ; 
		}else {
			if (login == "" || password == "") {
				logger.error("Credentials must not be Empty ! ");
			}else {
				logger.error("User doesn't Exist or Wrong Credentials !");
			}
			return null ; 
		}

	}

	
	public int addUser(User user) {
		
		logger.info("Adding or Updating user");
		userRepository.save(user);
		logger.info("User Added  Succefully !");
		if (user.getPassword() == "") {
			logger.warn("User Created without Password !");
		}
		return user.getId();
		
	}


	public Boolean updateUserWithEmail( String email, int userId) {
		logger.info("Updating User Email");
		User user = userRepository.findById(userId).orElse(null);
		 
		if (email != "") {
		user.setEmail(email);
		userRepository.save(user);
		logger.info("Email Updated Succefully !" +user);
		
		return true;
		}else {
			logger.error("The new Email Must not Be Empty !");
			
			return false;
		}

	}
	
	
	public String getUserPrenomById(int userId) {
		
	       User userEntity =null;

			if (userId == 0) {
				logger.error("No User exist !");
			
		
			}else {
			
				userEntity = userRepository.findById(userId).get();
			logger.info(" User exist  : " +userEntity.getPrenom());

			}
			return userEntity.getPrenom();

		}
		 
		public Boolean deleteUserById(int userId)
		{
			
			if (userId == 0) {
				
				logger.error("No User exist for deleting !");
			 return false;
		
			}else {
			
			User user = userRepository.findById(userId).get();

			userRepository.delete(user);
			logger.info("User deleted succefully");
			
           return true;
			}
		}

		public List<User> getAllUsers() {
			
			List<User> users=	(List<User>) userRepository.findAll();
			
			if(users != null) {
				
			logger.info("Users are exists ");
			return (List<User>) userRepository.findAll();

			}else {
				
				logger.info("Users does not  exists !");

				
				return null;
			}
			
		}
	}
