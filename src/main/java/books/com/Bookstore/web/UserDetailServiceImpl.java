package books.com.Bookstore.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;

import books.com.Bookstore.domain.User;
import books.com.Bookstore.domain.UserRepository;


@Service
public class UserDetailServiceImpl  implements UserDetailsService{

	private static final Logger log = LoggerFactory.getLogger(UserDetailServiceImpl.class);
	private final UserRepository repository;
	
	@Autowired
	public UserDetailServiceImpl(UserRepository userRepository) {
	this.repository = userRepository;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	log.info("loadUserByUsername: " + username);
	
	//loadUserbyUsername must be defined in ApplicationUserRepository interface
	User curruser = repository.findByUsername(username);
	
	UserBuilder builder = null;
	if (curruser == null) {
    	throw new UsernameNotFoundException("User not found.");
	}
	else {
    	builder = org.springframework.security.core.userdetails.User.withUsername(username);
    	builder.password(curruser.getPasswordHash());
    	builder.roles(curruser.getRole()); 
	}
	
    return builder.build();
}
	
}
