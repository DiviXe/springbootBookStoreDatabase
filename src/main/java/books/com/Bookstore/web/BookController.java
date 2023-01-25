package books.com.Bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import books.com.Bookstore.domain.Book;

@Controller
public class BookController {

	@GetMapping("/index")
	public String index() {
		return "index";
	}

}
