import Layout from "../Layout";
import { useParams } from "react-router-dom"
import React, { useEffect, useState } from 'react';
import { Link } from "react-router-dom";

function Book() {

    const { bookId } = useParams();

    const bookLink = "/borrowed/" + bookId;

    const [book, setBook] = useState(null);
    const [status, setStatus] = useState("");

    async function getBook() {
        const response = await fetch("http://localhost:3001/book/" + bookId);
        const data = await response.json();
        setBook(data);
    }

    async function deleteBook() {
        
        const response = await fetch("http://localhost:3001/book/" + bookId, {
            method: "DELETE",
            mode: "cors",
            cache: "no-cache",
            credentials: "same-origin",
            headers: {
                "Content-Type": "application/json",
            },
            redirect: "follow",
            referrerPolicy: "no-referrer",
            body: JSON.stringify(book),
        })
        console.log(response);
        if(response.status == "200") {
            setStatus(book.title + " Deleted");
        } else {
            setStatus("Could not delete: " + book.title);
        }
    }

    useEffect(() => {
        getBook();

    }, []);

    return (
        <Layout>
            {book ? (
                <div class="main-book-wrapper dropshadow columns-2 margin-m padding-m">
                    <div class="fit-parent-width">
                        <h1>{book.title}</h1>
                        <h2>Author(s)</h2>
                        {book.authors.map(author => { return <h3 class="book-card-author" key={author}>{author}</h3> })}
                        <h3 class="underline">ISBN: { book.isbn }</h3>
                        <p>Edition: { book.edition }</p>
                        <p>Published: {book.publicationDate}</p>
                        <p>First Released: {book.releaseDate}</p>
                    </div>
                    <div>
                        <img src={book.imagePath} alt="" class="fit-parent-width" />
                    </div>
                    <Link to={bookLink} class="fit-parent-width fill-columns-2">
                        <button class="button-large button-primary fit-parent-width">Borrow</button>
                    </Link>
                    <p class="delete-book" onClick={deleteBook}>Delete Book</p>
                    <p>{status && <h4>{status}</h4>}</p>
                </div>
            ) :
                (
                    <div>Loading...</div>
                )}
        </Layout>
    )
}

export default Book;
