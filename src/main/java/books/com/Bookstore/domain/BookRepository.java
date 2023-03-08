package books.com.Bookstore.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
	
	List<Book> findByTitle(String Title);
	List<Book> findByAuthor(String Author);
	

}
