import '../utility.css';
import '../index.css';

import Navigation from '../Navigation/Navigation'
import { Link } from 'react-router-dom';

function Header() {
    return (
        <header class="header">
            <Link to="/">
                <img class="header-logo" src="/icons/logo.svg" alt="" />
            </Link>
            <Navigation />
        </header>
    );
}

export default Header;