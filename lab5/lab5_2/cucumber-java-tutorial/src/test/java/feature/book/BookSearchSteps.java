package feature.book;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hascode.tutorial.cucumber.book.Book;
import com.hascode.tutorial.cucumber.book.Library;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;  

public class BookSearchSteps {
	Library library = new Library();
	List<Book> result = new ArrayList<>();
	SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");  

    @ParameterType("([0-9]{2})/([0-9]{2})/([0-9]{4})")
	public LocalDateTime iso8601Date(String day, String month, String year){
		return LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day),0, 0);
	}

	@Given("a/another book with the title {string}, written by {string}, published in {isoDate}")
	public void addNewBook(final String title, final String author, final LocalDateTime published) {
		Book book = new Book(title, author, Date.from(published.toInstant(ZoneOffset.UTC)));
		library.addBook(book);
	}

	@When("the customer searches for books published between {iso8601Date} and {iso8601Date}")
	public void setSearchParameters(final LocalDateTime from, final LocalDateTime to) {
        Date inicio = Date.from(from.toInstant(ZoneOffset.UTC));
        Date fim = Date.from(to.toInstant(ZoneOffset.UTC));
        result = library.findBooks(inicio, fim);
	}

	@Then("{int} books should have been found$")
	public void verifyAmountOfBooksFound(final int booksFound) {
		assertThat(result.size(), equalTo(booksFound));
	}

	@Then("Book {int} should have the title {string}")
	public void verifyBookAtPosition(final int position, final String title) {
		assertThat(result.get(position - 1).getTitle(), equalTo(title));
	}

	@When("the customer searches for books by author {string}")
	public void setSearchbyAuthor(final String author) {
        result = library.findBooksByAuthor(author);
	}

    @When("the customer searches for books by title {string}")
	public void setSearchbyTitle(final String title) {
        result = library.findBooksByTitle(title);
	}
}
