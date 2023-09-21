import express, { response } from "express";
import cors from "cors";
import fileUpload from "express-fileupload";
import { createRandomID } from "./utility.js";
import * as books from "./books/books.model.js";
import * as users from "./users/users.model.js"

const app = express();
const PORT = 3001; // fetch url: localhost:3001

app.use(express.json());
app.use(cors());
app.use(fileUpload());
app.use(express.static("public"));
app.use("/images", express.static("images"));

app.post("/upload", (request, response) => {
  // Get the file that was set to our field named "image"
  console.log(request.files);
  const { file } = request.files;

  // If no image submitted, exit
  if (!file) return response.sendStatus(400);

  // Move the uploaded image to our upload folder
  const fileName = createRandomID("book") + file.name;
  file.mv("./images/" + fileName);
  response.send(
    JSON.stringify({
      status: 200,
      body: { imagePath: `http://localhost:${PORT}/images/${fileName}` },
    })
  );
});

// vJERES KODE HERv

app.get('/book', async (request, response) => {
  const booksData = await books.getAll();
  response.send(JSON.stringify(booksData));
  console.log(booksData);
})

app.get('/book/:id', async (request, response) => {

  const id = request.params.id;

  const bookData = await books.getByID(id);
  response.send(JSON.stringify(bookData));
  console.log(bookData);
})

app.post('/book', async (request, response) => {
  books.add(request.body);
  response.send("Book Added");
})

app.delete('/book/:id', async (request, response) => {
  
  const bookId = request.params.id;

  books.remove(bookId);

  response.send("Book Deleted");

})

// ^JERES KODE HER^

app.listen(PORT, function (err) {
  if (err) console.log("Error in server setup");
  console.log("Server listening on Port", PORT);
});
