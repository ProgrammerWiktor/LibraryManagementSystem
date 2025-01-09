package pl.ProgrammerWiktor.LibraryManagementSystem;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book book) {
        Book existringBook = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono książki o id " + id));

        if (book.getTitle() != null) {
            existringBook.setTitle(book.getTitle());
        }

        if (book.getAuthor() != null) {
            existringBook.setAuthor(book.getAuthor());
        }

        if (book.getPublishedDate() != null) {
            existringBook.setPublishedDate(book.getPublishedDate());
        }

        if (book.getGenre() != null) {
            existringBook.setGenre(book.getGenre());
        }

        if (book.isAvailable() != existringBook.isAvailable()) {
            existringBook.setAvailable(book.isAvailable());
        }

        return bookRepository.save(existringBook);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
