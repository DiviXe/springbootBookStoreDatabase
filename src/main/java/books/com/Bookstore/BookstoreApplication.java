package books.com.Bookstore;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import books.com.Bookstore.domain.ApplicationUserRepository;
import books.com.Bookstore.domain.ApplicationUser;
import books.com.Bookstore.domain.Book;
import books.com.Bookstore.domain.BookRepository;
import books.com.Bookstore.domain.Category;
import books.com.Bookstore.domain.CategoryRepository;


@SpringBootApplication
public class BookstoreApplication{
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	@Autowired
	BookRepository brepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookData (BookRepository brepository, CategoryRepository crepository, ApplicationUserRepository applicationUserRepository) {
		return (args) -> {
			
		
		log.info("Save users");
		ApplicationUser user1 = new ApplicationUser("Leo", "Ahopalo", "ADMIN", "admin",
		"$2a$10$2Gm74iOxuJZsVVKIF47aiut3PBzMVI8REsQ.JngNDu/rrJoS8dum.");
			
		ApplicationUser user2 = new ApplicationUser("Leo", "testi", "USER", "user", 
		"$2a$10$2Gm74iOxuJZsVVKIF47aiut3PBzMVI8REsQ.JngNDu/rrJoS8dum.");
		applicationUserRepository.saveAll(Arrays.asList(user1, user2));
			
		log.info("saving categories");
		crepository.save(new Category("Horror"));
		crepository.save(new Category("Thriller"));
		crepository.save(new Category("Action"));
		crepository.save(new Category("Romance"));
		crepository.save(new Category("History"));
		crepository.save(new Category("Art"));
	
		log.info("saving books");
		brepository.save(new Book("The Chilling", "Matti meikäläinen", 1997, 105522, 25, crepository.findByName("Horror").get(0)));
		brepository.save(new Book("The Shiny", "Matti meikäläinen", 2002, 200522, 30, crepository.findByName("Action").get(0)));
		brepository.save(new Book("The Project", "Matti meikäläinen", 2010, 206655, 35,crepository.findByName("History").get(0)));
		
		
	
		log.info("fetch all books for console");
		for (Book book : brepository.findAll() ) {
			log.info(book.toString());
		}
		
	
	}; 
	
	}
	}
	
