import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Author {
    private String firstName;
    private String lastName;

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void print() {
        System.out.println("Author: " + firstName + " " + lastName);
    }

    // Static method to create an author from full name
    public static Author createAuthorFromFullName(String fullName) {
        String[] nameParts = fullName.split(" ");
        if (nameParts.length != 2) {
            throw new IllegalArgumentException("Full name must contain exactly two parts");
        }
        return new Author(nameParts[0], nameParts[1]);
    }
}

class Book {
    private String title;
    private Author author;
    private int year;
    private int pages;

    public Book(String title, Author author, int year, int pages) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.pages = pages;
    }

    public void print() {
        System.out.println("Book: " + title + ", Year: " + year + ", Pages: " + pages);
        author.print();
    }

    // Static field to keep track of the number of books created
    private static int bookCount = 0;

    // Static method to get the number of books created
    public static int getBookCount() {
        return bookCount;
    }

    // Increment book count in the constructor
    {
        bookCount++;
    }
}

class Reader {
    private String firstName;
    private String lastName;
    private int readerID;

    public Reader(String firstName, String lastName, int readerID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.readerID = readerID;
    }

    public void print() {
        System.out.println("Reader: " + firstName + " " + lastName + ", ID: " + readerID);
    }
}

class Order {
    private Book book;
    private Reader reader;
    private String orderDate;
    private String returnDate;

    public Order(Book book, Reader reader, String orderDate, String returnDate) {
        this.book = book;
        this.reader = reader;
        this.orderDate = orderDate;
        this.returnDate = returnDate;
    }

    public void print() {
        System.out.println("Order Date: " + orderDate + ", Return Date: " + returnDate);
        book.print();
        reader.print();
    }
}

class Library {
    private String name;
    private String address;
    private List<Book> books;
    private List<Reader> readers;
    private List<Order> orders;
    private int bookCount;
    private int readerCount;
    private int orderCount;

    public Library(String name, String address) {
        this.name = name;
        this.address = address;
        this.books = new ArrayList<>();
        this.readers = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.bookCount = 0;
        this.readerCount = 0;
        this.orderCount = 0;
    }

    public void addBook(Book book) {
        books.add(book);
        bookCount++;
    }

    public void addReader(Reader reader) {
        readers.add(reader);
        readerCount++;
    }

    public void addOrder(Order order) {
        orders.add(order);
        orderCount++;
    }

    public void print() {
        System.out.println("Library: " + name + ", Address: " + address);
        System.out.println("Books:");
        for (Book book : books) {
            book.print();
        }
        System.out.println("Readers:");
        for (Reader reader : readers) {
            reader.print();
        }
        System.out.println("Orders:");
        for (Order order : orders) {
            order.print();
        }
    }

    // Method to return the number of books in the library
    public int getNumberOfBooks() {
        return bookCount;
    }

    // Method to demonstrate string handling
    public String getLibraryInfo() {
        return "Library: " + name + ", Address: " + address + ", Books: " + bookCount + ", Readers: " + readerCount + ", Orders: " + orderCount;
    }
}

class Helper {
    public static int getIntegerValue() {
        return 42;
    }
}

public class Main {
    public static void main(String[] args) {
        // Инициализация библиотеки
        Library library = new Library("Central Library", "123 Main Street");

        // Инициализация автора
        Author author = Author.createAuthorFromFullName("John Doe");

        // Инициализация книги
        Book book = new Book("The Great Book", author, 2000, 300);

        // Инициализация читателя
        Reader reader = new Reader("Jane", "Smith", 12345);

        // Инициализация заказа
        Order order = new Order(book, reader, "2023-10-01", "2023-10-15");

        // Добавление в библиотеку
        library.addBook(book);
        library.addReader(reader);
        library.addOrder(order);

        // Вывод информации
        library.print();

        // Инициализация второй библиотеки
        Library library2 = new Library("City Library", "456 Elm St");

        // Инициализация второго автора
        Author author2 = Author.createAuthorFromFullName("Alice Johnson");

        // Инициализация второй книги
        Book book2 = new Book("The Amazing Book", author2, 2010, 400);

        // Инициализация второго читателя
        Reader reader2 = new Reader("Bob", "Brown", 67890);

        // Инициализация второго заказа
        Order order2 = new Order(book2, reader2, "2023-10-02", "2023-10-16");

        // Добавление во вторую библиотеку
        library2.addBook(book2);
        library2.addReader(reader2);
        library2.addOrder(order2);

        // Вывод информации для второй библиотеки
        library2.print();

        // Demonstrate static field and method
        System.out.println("Total number of books created: " + Book.getBookCount());

        // Demonstrate working with an array of objects
        Book[] booksArray = {book, book2};
        for (Book b : booksArray) {
            b.print();
        }

        // Demonstrate returning an integer value using a helper class
        int value = Helper.getIntegerValue();
        System.out.println("Integer value from helper class: " + value);

        // Demonstrate string handling
        String libraryInfo = library.getLibraryInfo();
        System.out.println(libraryInfo);
    }
}