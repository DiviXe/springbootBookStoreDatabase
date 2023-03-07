package books.com.Bookstore.web;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import books.com.Bookstore.domain.Book;
import books.com.Bookstore.domain.BookRepository;





@RestController
public class RestBookController {

	private static final Logger log = LoggerFactory.getLogger(RestBookController.class);
	
	@Autowired
	BookRepository brepository;
	
	//list of books is returned
	@GetMapping("/books")
	public Iterable<Book> getBooks() { 
		log.info("//fetch and returning all books");
		return brepository.findAll();
	}
	
	// add new book
		@PostMapping("books")
		Book newBook(@RequestBody Book newBook) {
			log.info("save new car " + newBook);
			return brepository.save(newBook);
		}

		// edit existing book information
		@PutMapping("/books/{id}")
		Book editBook(@RequestBody Book editedBook, @PathVariable Long id) {
			log.info("edit book " + editedBook);
			editedBook.setId(id);
			return brepository.save(editedBook);
		}


		@DeleteMapping("/books/{id}")
		public Iterable<Book> deleteBook(@PathVariable Long id) {
			log.info("delete book, id = " + id);
			brepository.deleteById(id);
			return brepository.findAll();
		}

		// find one book and return it
		@GetMapping("/books/{id}")
		Optional<Book> getBook(@PathVariable Long id) {
			log.info("find book, id = " + id);
			return brepository.findById(id);
		}
}
