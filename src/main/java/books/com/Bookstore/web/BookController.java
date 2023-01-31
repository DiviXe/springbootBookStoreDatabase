package books.com.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import books.com.Bookstore.domain.Book;
import books.com.Bookstore.domain.BookRepository;

@Controller
public class BookController {
	
	//BookRepository includes all the CRUD methods
	@Autowired
	private BookRepository repository;

	@RequestMapping("/booklist")
	public String BookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
		//ALWAYS RETURN NAME OF TEMPLATE
	}
	//adding book
	@RequestMapping (value="/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		return "addbook";
	}
	//save the book
	@PostMapping("/save")
	public String save(Book book) {
		repository.save(book);
		return "redirect:booklist";
	}
	//delete function 
	@GetMapping("/delete/{id}")
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		repository.deleteById(bookId);
		return "redirect:../booklist";
	}

}
