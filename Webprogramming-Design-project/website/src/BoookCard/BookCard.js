import '../utility.css';
import '../index.css';

import { Link } from 'react-router-dom';

function BookCard({ children }) {

    const bookLink = '/book/' + children.id;

    return (
        <Link to={bookLink} class="book-card dropshadow" style={{ "text-decoration": "unset" }}>
            <div>
                <img src={children.imagePath} alt="" class="book-card-image" />
                <h3 class="book-card-title">{children.title}</h3>
                <div class="margin-vertical-m">
                    {children.authors.map(author => <p class="book-card-author">{author}</p>)}
                </div>
            </div>
            <div class="row-spaced-out column">
            <p><strong>Original Price: { children.originalPrice }</strong></p>
                <Link class="fit-parent-width" to={bookLink}>
                    <button class="button-secondary fit-parent-width">Borrow</button>
                </Link>
            </div>
        </Link>
    )
}

export default BookCard;