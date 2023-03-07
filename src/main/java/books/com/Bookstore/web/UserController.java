package books.com.Bookstore.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import books.com.Bookstore.domain.UserRegistration;
import books.com.Bookstore.domain.ApplicationUser;
import books.com.Bookstore.domain.ApplicationUserRepository;
import jakarta.validation.Valid;




@Controller
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	ApplicationUserRepository userRepository;

	@GetMapping("/admin/register")
	public String addNewApplicationUser(Model model) {
		log.info("new user template " + new UserRegistration());
		model.addAttribute("newuser", new UserRegistration());
		return "registration";

	}

	@PostMapping("/admin/saveuser")
	public String saveUser(@Valid @ModelAttribute("newuser") UserRegistration newUser, BindingResult bindingResult) {

		log.info("saveuser: newUser is " + newUser.toString());
		if (!bindingResult.hasErrors()) { // validation errors
			if (newUser.getPassword().equals(newUser.getPasswordCheck())) { // check password match
				String pwd = newUser.getPassword();
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				String hashPwd = bc.encode(pwd);

				ApplicationUser newAppUser = new ApplicationUser();
				newAppUser.setPasswordHash(hashPwd);
				newAppUser.setUsername(newUser.getUsername());
				newAppUser.setRole("USER");
				if (userRepository.findByUsername(newUser.getUsername()) == null) { // Check if user exists
					userRepository.save(newAppUser);
				} else {
					log.info("username already exists");
					bindingResult.rejectValue("username", "err.username", "Username already exists");
					return "registration";
				}
			} else {
				log.info("Password doesn't match");
				bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");
				return "registration";
			}
		} else {
			return "registration";
		}
		return "redirect:/login";
	}

}
