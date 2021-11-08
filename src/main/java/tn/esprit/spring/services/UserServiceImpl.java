package tn.esprit.spring.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
			logger.info("user Logged In seccufully  :");
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
		
		Optional<User> user = userRepository.findById(userId);

            if(user.isPresent()) {
            	
            User user1=	user.get();
            
            if (email != "") {
        		user1.setEmail(email);
        		userRepository.save(user1);
        		logger.info("Email Updated Succefully !");
        		
        		return true;
        		}else {
        			logger.error("The new Email Must not Be Empty !");
        			
        			return false;
        		}
            }
          
		return true;
		

	}
	
	
	public String getUserPrenomById(int userId) {
	
		
        
			Optional<User> user = userRepository.findById(userId);


			if (!user.isPresent()) {
				logger.error("No User exist !");
			
		       return "No User exist";
			}else {
			
				User user1 = user.get();
			logger.info(" User exist  : ");
			
			return user1.getPrenom();

			}
			
		}
		 
		public Boolean deleteUserById(int userId)
		{
			
			Optional<User> user = userRepository.findById(userId);

			
			if (!user.isPresent()) {
				
				logger.error("No User exist for deleting !");
			 return false;
		
			}else {
			
			User user1=user.get();

			userRepository.delete(user1);
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

				
				return users;
			}
			
		}
	}
