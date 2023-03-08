package books.com.Bookstore.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import books.com.Bookstore.domain.ApplicationUserRepository;
import books.com.Bookstore.domain.Book;
import books.com.Bookstore.domain.BookRepository;
import books.com.Bookstore.domain.CategoryRepository;
import jakarta.validation.Valid;

@Controller
public class BookController {
	private static final Logger log = LoggerFactory.getLogger(BookController.class);
	//BookRepository includes all the CRUD methods
	
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository crepository;
	
	
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	} 
	
	//INDEX page get's the first name of the user (not working properly) 
	@GetMapping(value= {"/", "index"})
	public String showMainPage(@AuthenticationPrincipal ApplicationUserRepository userRepository, Model model) {
	log.info("open main page");
	return "index";
	}
	
	//ALWAYS RETURN NAME OF THYMELEAF TEMPLATE
	//show all books
	@RequestMapping("/booklist")
	public String BookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
	
	//adding book
	@RequestMapping (value="/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", crepository.findAll());
		return "addbook";
	}
	
	//save the book included with validation error 
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute ("book") Book book, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			System.out.println("Validation error has happened, please recheck.");
			model.addAttribute("categories", crepository.findAll());
			return "addbook";
		}
		repository.save(book);
		return "redirect:booklist";
	}
	
	//delete function 
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping("/delete/{id}")
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		repository.deleteById(bookId);
		return "redirect:../booklist";
	}
	
	// Edit function
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable("id") Long bookId, Model model) {
    	model.addAttribute("book", repository.findById(bookId));
    	model.addAttribute("categories", crepository.findAll());
    	return "editbook";
    }   

}
