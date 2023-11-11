package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

  private final AuthorRepository authorRepository;
  private final BookRepository bookRepository;
  private final PublisherRepository publisherRepository;

  public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
    this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
    this.publisherRepository = publisherRepository;
  }

  @Override
  public void run(String... args) throws Exception {

    Publisher publisher = new Publisher();
    publisher.setName("Oreilly");
    publisher.setZip("45100");

    publisherRepository.save(publisher);

    System.out.println("Publisher count "+publisherRepository.count());

    Author eric = new Author("ecric", "evans");
    Book ddd = new Book("Domain Driven Design", "123123");

    eric.getBooks().add(ddd);
    ddd.getAuthors().add(eric);
    ddd.setPublisher(publisher);
    publisher.getBooks().add(ddd);

    authorRepository.save(eric);
    bookRepository.save(ddd);
    publisherRepository.save(publisher);

    Author rod = new Author("Rod", "Johnson");
    Book noEjb = new Book("J2EE Development", "12314234");

    noEjb.getAuthors().add(rod);
    rod.getBooks().add(noEjb);
    noEjb.setPublisher(publisher);
    publisher.getBooks().add(noEjb);

    authorRepository.save(rod);
    bookRepository.save(noEjb);
    publisherRepository.save(publisher);

    System.out.println("Number of books "+bookRepository.count());
    System.out.println("Publisher Number of books "+publisher.getBooks().size());


  }
}
