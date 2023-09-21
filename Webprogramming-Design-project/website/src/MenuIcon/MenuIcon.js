import '../utility.css'
import '../index.css'
import { Link } from 'react-router-dom';

function MenuIcon({href, text, src}) {

    return (
        <div class="padding-xs">
            <Link to={href} class="menu-link column center">
                <img class="menu-icon" src={src}/>
                <h2 class="menu-text center-text">{text}</h2>
            </Link>
        </div>
    );
}

export default MenuIcon;