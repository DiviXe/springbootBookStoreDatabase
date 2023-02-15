package books.com.Bookstore;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import books.com.Bookstore.domain.Book;
import books.com.Bookstore.domain.BookRepository;
import books.com.Bookstore.domain.Category;
import books.com.Bookstore.domain.CategoryRepository;
import books.com.Bookstore.domain.User;
import books.com.Bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication implements CommandLineRunner{
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	@Autowired
	BookRepository repository;
	@Autowired
	CategoryRepository crepository;
	@Autowired
	UserRepository urepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	//add data (NEWER VERSION)
	@Override
	public void run (String... args) throws Exception {
	log.info("saving categories");
	crepository.save(new Category("Horror"));
	crepository.save(new Category("Thriller"));
	crepository.save(new Category("Action"));
	crepository.save(new Category("Romance"));
	crepository.save(new Category("History"));
	crepository.save(new Category("Art"));
	
	log.info("saving books");
	repository.save(new Book("The Chilling", "Matti meikäläinen", 1997, 105522, 25, crepository.findByName("Horror").get(0)));
	repository.save(new Book("The Shiny", "Matti meikäläinen", 2002, 200522, 30, crepository.findByName("Action").get(0)));
	repository.save(new Book("The Project", "Matti meikäläinen", 2010, 206655, 35,crepository.findByName("History").get(0)));
	
	
	log.info("fetch all books for console");
	for (Book book : repository.findAll() ) {
		log.info(book.toString());
		
//		log.info("Save users");
//		User user1 = new User("Leo", "$2a$10$5eYRBHG/P4XvqDhJ0S9EEOCbdaxQ2l9UzYvF2ZMs7OAd6La8.dLeS", "ADMIN");
//		User user2 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
//		urepository.saveAll(Arrays.asList(user1, user2));
}
		
	} 
	
			
			
			
}
	
