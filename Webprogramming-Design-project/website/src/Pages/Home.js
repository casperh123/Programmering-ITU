import React, { useEffect, useState } from 'react';
import '../utility.css'
import Layout from '../Layout'
import BookCard from '../BoookCard/BookCard'

function Home() {

    const [books, setBooks] = useState([]);

    async function getAllBooks() {
        const response = await fetch("http://localhost:3001/book");
        const data = await response.json();
        setBooks(data); 
    }

    useEffect(() => {
        getAllBooks();
    }, []);

    return (
        <Layout>
            <h1 className="center-text">BookBooks</h1>
            <div className="columns-2 center padding-s">
            {books.map(book => {
              return <BookCard>{book}</BookCard>
            })}
            </div>
        </Layout>
    );
}

export default Home;
