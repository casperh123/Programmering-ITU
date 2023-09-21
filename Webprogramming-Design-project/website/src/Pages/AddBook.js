import Layout from '../Layout';
import { useState } from "react";
import { CreateID } from "../Utility/CreateID";
import InputField from "../InputField/InputField";

function AddBook() {

  const [status, setStatus] = useState("");
  const [isbn, setIsbn] = useState("");
  const [title, setTitle] = useState("");
  const [edition, setEdition] = useState("");
  const [authors, setAuthors] = useState("");
  const [publicationDate, setPublicationDate] = useState("");
  const [releaseDate, setReleaseDate] = useState("");
  const [originalPrice, setOriginalPrice] = useState("");
  const [imagePath, setImagePath] = useState("");
  const [language, setLanguage] = useState("");
  const [condition, setCondition] = useState("");

  async function handleSubmit(event) {
    event.preventDefault();

    const dateGetter = new Date();

    const year = dateGetter.getFullYear();
    const month = dateGetter.getMonth() + 1;
    const date = dateGetter.getDate();

    const authorsArray = authors.replace(" ", "").split(",");
    const currentDate = date + "-" + month + "-" + year;

    let book = {
      isbn: isbn,
      title: title,
      edition: edition,
      authors: authorsArray,
      publicationDate: currentDate,
      releaseDate: releaseDate,
      originalPrice: originalPrice,
      imagePath: imagePath,
      language: language,
      condition: condition,
    };
    const response = await fetch("http://localhost:3001/book", {
      method: "POST",
      mode: "cors",
      cache: "no-cache",
      credentials: "same-origin",
      headers: {
        "Content-Type": "application/json",
      },
      redirect: "follow",
      referrerPolicy: "no-referrer",
      body: JSON.stringify(book),
    });
    if (response) {
      if(response.status == "200") {
        setStatus(book.title + " was added");
      } else {
        setStatus("There was an error adding the book");
      }
    }
  }

  return (
    <Layout>
      <div class="column round-border center dropshadow margin-m padding-m">
        <h1>Add Book</h1>
        <form class="column center" className="form" onSubmit={handleSubmit}>
          <InputField name="ISBN" value={isbn} setValue={setIsbn} />
          <InputField name="Title" value={title} setValue={setTitle} />
          <InputField name="Edition" value={edition} setValue={setEdition} />
          <InputField name="Authors" value={authors} setValue={setAuthors} />
          <InputField
            name="Release Date"
            value={releaseDate}
            setValue={setReleaseDate}
          />
          <InputField
            name="Original Price"
            value={originalPrice}
            setValue={setOriginalPrice}
          />
          <InputField
            name="Image Path"
            value={imagePath}
            setValue={setImagePath}
          />
          <InputField name="Language" value={language} setValue={setLanguage} />
          <InputField
            name="Condition"
            value={condition}
            setValue={setCondition}
          />
          <button type="submit" class="button-primary margin-m fit-parent-width">Submit</button>
        </form>
        {status && <h4>{status}</h4>}
      </div>
    </Layout>
  )
}

export default AddBook;