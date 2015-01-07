package service

import dao.BookDao
import entity.Book
import spock.lang.Specification

class BookServiceSpec extends Specification {

    BookServiceImpl service
    BookDao mockDao

    def setup() {
        service = new BookServiceImpl()
        mockDao = Mock()
        BookServiceImpl.metaClass.setAttribute(service, "bookDao", mockDao)
    }

    def "save a book when book is not null"() {
        setup:
        mockDao.save(!null) >> 1L
        def book = new Book(title: "unknown", author: "anonymous")

        expect:
        service.save(book) == 1L
    }

    def "save a book when book is null"() {
        when:
        service.save(null)

        then:
        thrown(NullPointerException)
    }

    def "find all books"() {
        setup:
        def book = new Book(title: "unknown", author: "anonymous")
        mockDao.findByCriteria(null) >> [book]

        when:
        def books = service.findAll()

        then:
        books.size() == 1
        books.get(0) == book
    }

    def "find books by title when title is not null"() {
        setup:
        def book = new Book(title: "unknown", author: "anonymous")
        mockDao.findByTitle("unknown") >> [book]

        when:
        def booksOfUnknown = service.findByTitle("unknown")

        then:
        booksOfUnknown.size() == 1
        booksOfUnknown.get(0) == book

        when:
        def booksOfTest = service.findByTitle("test")

        then:
        booksOfTest == null
    }

    def "find books by title when title is null"() {
        when:
        service.findByTitle(null)

        then:
        thrown(NullPointerException)
    }

    def "find books by author when author is not null"() {
        setup:
        def book = new Book(title: "unknown", author: "anonymous")
        mockDao.findByAuthor("anonymous") >> [book]

        when:
        def booksOfAnonymous = service.findByAuthor("anonymous")

        then:
        booksOfAnonymous.size() == 1
        booksOfAnonymous.get(0) == book

        when:
        def booksOfTest = service.findByAuthor("test")

        then:
        booksOfTest == null
    }

    def "find books by author when author is null"() {
        when:
        service.findByAuthor(null)

        then:
        thrown(NullPointerException)
    }

}
