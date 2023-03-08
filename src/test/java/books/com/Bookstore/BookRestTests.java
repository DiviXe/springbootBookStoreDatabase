package books.com.Bookstore;

//import static org.hamcrest.Matchers.containsString;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import books.com.Bookstore.domain.BookRepository;

@SpringBootTest
public class BookRestTests {
	
	@Autowired
	BookRepository brepository;

	@Autowired
	private WebApplicationContext webAppContext;
	
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
	}
	
	@Test
	public void statusOk() throws Exception {
		mockMvc.perform(get("/books")).andExpect(status().isOk());
	}
	
	//same kun status ok, mutta katsoo JSON muodon.
	@Test
	public void responseTypeApplicationJson() throws Exception {
		mockMvc.perform(get("/books"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	
	@Test
	public void apiStatusOk() throws Exception {
		mockMvc.perform(get("/api/books")).andExpect(status().isOk());
	}
	
	
}

