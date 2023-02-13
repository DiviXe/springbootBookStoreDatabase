package books.com.Bookstore.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserController {

	@GetMapping("/login")
	public String Login () {
		return "login";
		}
}
