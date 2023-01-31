package books.com.Bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import books.com.Bookstore.domain.Book;
import books.com.Bookstore.domain.BookRepository;
import books.com.Bookstore.domain.Category;
import books.com.Bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	//add demonstration data
	@Bean
	public CommandLineRunner  demoData(BookRepository repository, CategoryRepository crepository) {
		return (args) -> {
			//adding categories
			crepository.save(new Category("Horror"));
			crepository.save(new Category("Thriller"));
			crepository.save(new Category("Action"));
			crepository.save(new Category("Romance"));
			crepository.save(new Category("History"));
			crepository.save(new Category("Art"));
			
			repository.save(new Book("The Chilling", "Matti meikäläinen", 1997, 105522, 25, crepository.findByName("Horror").get(0)));
			repository.save(new Book("The Shiny", "Matti meikäläinen", 2002, 200522, 30, crepository.findByName("Action").get(0)));
			repository.save(new Book("The Project", "Matti meikäläinen", 2010, 206655, 35,crepository.findByName("History").get(0)) );
			
			
	};
	
	}
	
}
