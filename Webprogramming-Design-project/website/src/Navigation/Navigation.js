import '../utility.css';
import '../index.css';
import MenuIcon from '../MenuIcon/MenuIcon';

function Navigation() {
    return(
        <nav class="navigation">
            <ul class="nav-list">
                <li><MenuIcon href="/" text="Home" src="/icons/home.svg"/></li>
                <li class="lifted-nav-element menu-icon-showcased"><MenuIcon href="/add-book" text="Add Book" src="/icons/AddToCart.svg"/></li>
                <li><MenuIcon href="/profile" text="Profile" src="/icons/profile.svg"/></li>
            </ul>
        </nav>
    );
}

export default Navigation;