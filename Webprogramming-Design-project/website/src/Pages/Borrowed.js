import Layout from "../Layout";
import { useParams } from "react-router-dom"
import React, { useEffect, useState } from 'react';

function Borrowed() {

    const { bookId } = useParams();

    const [book, setBook] = useState(null);

    async function getBook() {
        const response = await fetch("http://localhost:3001/book/" + bookId);
        const data = await response.json();
        setBook(data);
    }

    useEffect(() => {
        getBook();

    }, []);

    return (
        <Layout>
            <div>
                {book ? (
                    <div class="column center round-border padding-m margin-m dropshadow">
                        <h1>Book Booked</h1>
                        <p>Congratulations! You have now borrowed: <strong>{book.title}</strong></p>
                    </div>
                ) : (
                    <p>loading....</p>
                )

                }

            </div>
        </Layout>
    );

}

export default Borrowed;