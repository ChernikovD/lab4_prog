#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <string>
#include <vector>
#define MAX 100

class Author {
private:
    std::string firstName;
    std::string lastName;

public:
    Author(const std::string& firstName, const std::string& lastName)
        : firstName(firstName), lastName(lastName) {}

    void print() const {
        std::cout << "Author: " << firstName << " " << lastName << std::endl;
    }

    static int authorCount;
    static void incrementAuthorCount() {
        authorCount++;
    }
};

int Author::authorCount = 0;

class Book {
private:
    std::string title;
    Author author;
    int year;
    int pages;

public:
    Book(const std::string& title, const Author& author, int year, int pages)
        : author(author), year(year), pages(pages), title(title) {}

    void print() const {
        std::cout << "Book: " << title << ", Year: " << year << ", Pages: " << pages << std::endl;
        author.print();
    }

    int* getPagesPointer() {
        return &pages;
    }

    int& getPagesReference() {
        return pages;
    }

    Book operator+(const Book& other) {
        return Book(title + " and " + other.title, author, year, pages + other.pages);
    }

    Book& operator++() {
        pages++;
        return *this;
    }

    Book operator++(int) {
        Book temp = *this;
        pages++;
        return temp;
    }
};

class Reader {
private:
    std::string firstName;
    std::string lastName;
    int readerID;

public:
    Reader(const std::string& firstName, const std::string& lastName, int readerID)
        : firstName(firstName), lastName(lastName), readerID(readerID) {}

    void print() const {
        std::cout << "Reader: " << firstName << " " << lastName << ", ID: " << readerID << std::endl;
    }


    friend void displayReaderID(const Reader& reader);
};

void displayReaderID(const Reader& reader) {
    std::cout << "Reader ID: " << reader.readerID << std::endl;
}

class Order {
private:
    Book book;
    Reader reader;
    std::string orderDate;
    std::string returnDate;

public:
    Order(const Book& book, const Reader& reader, const std::string& orderDate, const std::string& returnDate)
        : book(book), reader(reader), orderDate(orderDate), returnDate(returnDate) {}

    void print() const {
        std::cout << "Order Date: " << orderDate << ", Return Date: " << returnDate << std::endl;
        book.print();
        reader.print();
    }
};

class Library {
private:
    std::string name;
    std::string address;
    std::vector<Book> books;
    std::vector<Reader> readers;
    std::vector<Order> orders;
    int bookCount;
    int readerCount;
    int orderCount;

public:
    Library(const std::string& name, const std::string& address)
        : name(name), address(address), bookCount(0), readerCount(0), orderCount(0) {}

    void addBook(const Book& book) {
        books.push_back(book);
        bookCount++;
    }

    void addReader(const Reader& reader) {
        readers.push_back(reader);
        readerCount++;
    }

    void addOrder(const Order& order) {
        orders.push_back(order);
        orderCount++;
    }

    void print() const {
        std::cout << "Library: " << name << ", Address: " << address << std::endl;
        std::cout << "Books:" << std::endl;
        for (const auto& book : books) {
            book.print();
        }
        std::cout << "Readers:" << std::endl;
        for (const auto& reader : readers) {
            reader.print();
        }
        std::cout << "Orders:" << std::endl;
        for (const auto& order : orders) {
            order.print();
        }
    }


    void setName(const std::string& newName) {
        this->name = newName;
    }
};

int main() {

    Author author1("J.K.", "Rowling");
    Author author2("George", "Orwell");
    Author::incrementAuthorCount();
    Author::incrementAuthorCount();


    Book book1("Harry Potter", author1, 1997, 300);
    Book book2("1984", author2, 1949, 328);


    Reader reader1("John", "Doe", 1);
    Reader reader2("Jane", "Smith", 2);

    Order order1(book1, reader1, "2023-10-01", "2023-10-15");
    Order order2(book2, reader2, "2023-10-02", "2023-10-16");


    Library library("City Library", "123 Main St");
    library.addBook(book1);
    library.addBook(book2);
    library.addReader(reader1);
    library.addReader(reader2);
    library.addOrder(order1);
    library.addOrder(order2);

    library.print();

    std::cout << "Total Authors: " << Author::authorCount << std::endl;

 
    int* pagesPointer = book1.getPagesPointer();
    std::cout << "Pages via pointer: " << *pagesPointer << std::endl;
    int& pagesReference = book1.getPagesReference();
    std::cout << "Pages via reference: " << pagesReference << std::endl;

 
    library.setName("Central Library");

 
    library.print();

 
    displayReaderID(reader1);


    Book combinedBook = book1 + book2;
    combinedBook.print();

    Book incrementedBook = ++book1;
    incrementedBook.print();

    Book postIncrementedBook = book1++;
    postIncrementedBook.print();

    return 0;
}
