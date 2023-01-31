package books.com.Bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import books.com.Bookstore.domain.Book;
import books.com.Bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	//add demonstration data
	@Bean
	public CommandLineRunner  demo(BookRepository repository) {
		return (args) -> {
			Book testB1 = new Book("The Chilling", "Matti meikäläinen", 1997, 105522, 25);
			Book testB2 = new Book("The Shiny", "Matti meikäläinen", 2002, 200522, 30);
			Book testB3 = new Book("The Project", "Matti meikäläinen", 2010, 206655, 35);
			
			repository.save(testB1);
			repository.save(testB2);
			repository.save(testB3);
	};
	
	}
	
}
