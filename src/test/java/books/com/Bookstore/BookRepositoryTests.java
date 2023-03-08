package books.com.Bookstore;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import books.com.Bookstore.domain.CategoryRepository;
import books.com.Bookstore.domain.Category;
import books.com.Bookstore.domain.Book;
import books.com.Bookstore.domain.BookRepository;

@DataJpaTest
public class BookRepositoryTests {

	@Autowired
	BookRepository repository;
	
	@Autowired
	CategoryRepository crepository;
	
	// listing not valid for book + ID problems, maybe caused by Book entity.
	@Test
	public void findByBookTitle() {
		List<Book> books = repository.findByTitle("The Chilling");
		assertThat(books.get(0).getTitle().equalsIgnoreCase("The Chilling"));
	}
	
	@Test
	public void saveBook() {
		Book book = new Book();
		repository.save(book);
	}
	
//	@Test
//	public void updateBook() {
//		Optional<Book> book = repository.findById((long) 1);
//		assertNotEquals(book.get().getId(), null);
//		List<Book> books = repository.findByTitle("testi");
//		assertThat(books).hasSize(1);
//	}
	
	@Test
    public void createNewBook() {
    	Book book = new Book("test", "test", 5353, 105522, 35, crepository.findByName("Horror").get(0));
    	repository.save(book);
    	assertThat(book.getId()).isNotNull();
    }
	
//	@Test
//    public void deleteNewBook() {
//		List<Book> books = repository.findByTitle("The Chilling");
//		Book books = books.get(0);
//		repository.delete(book);
//		List<Book> newBooks = repository.findByTitle("The Chilling");
//		assertThat(newBooks).hasSize(0);
//     }


	
}
