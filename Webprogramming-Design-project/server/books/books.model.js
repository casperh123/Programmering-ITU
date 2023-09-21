import * as fs from "fs/promises";
import { createRandomID } from "../utility.js";
const BOOK_FILE = "./books/books.json";

// return all books from file
export async function getAll() {
  try {
    let booksText = await fs.readFile(BOOK_FILE);
    let books = JSON.parse(booksText);
    return books;
  } catch (err) {
    if (err.code === "ENOENT") {
      // file does not exits
      await save([]); // create a new file with empty array
      console.log("No books found, returning empty array");
      return []; // return empty array
    } // // cannot handle this exception, so rethrow
    else throw err;
  }
}

// save array of books to file
async function save(books = []) {
  let bookText = JSON.stringify(books);
  await fs.writeFile(BOOK_FILE, bookText);
}

// helper function for bookId
function findBook(bookArray, bookId) {
  return bookArray.findIndex((currentBook) => currentBook.id === bookId);
}

// get book by ID
export async function getByID(bookId) {
  let bookArray = await getAll();
  let index = findBook(bookArray, bookId);
  if (index === -1) throw new Error(`Book with name '${bookId}' doesn't exist`);
  else return bookArray[index];
}

// create a new book
export async function add(newBook) {
  let book = newBook;
  book.id = createRandomID("book");
  let bookArray = await getAll();
  if (findBook(bookArray, newBook.id) !== -1)
    throw new Error(`Book with ID:${newBook.id} already exists`);
  bookArray.push(newBook);
  await save(bookArray);
}

// update existing book
export async function update(bookId, book) {
  let bookArray = await getAll();
  let index = findBook(bookArray, bookId); // findIndex
  if (index === -1) throw new Error(`Book with ID:${bookId} does not exist`);
  else {
    bookArray[index] = book;
    await save(bookArray);
  }
}

// delete existing book
export async function remove(bookId) {
  let bookArray = await getAll();
  let index = findBook(bookArray, bookId); // findIndex
  if (index === -1) throw new Error(`Book with ID:${bookId} does not exist`);
  else {
    bookArray.splice(index, 1); // remove book from array
    await save(bookArray);
  }
}
