import Footer from "./Footer/Footer";
import Header from "./Header/Header";

function Layout({children}) {
    return (
        <div id="main-wrapper">
            <Header />
                <section id="main-content">
                   {children}
                </section>
            <Footer />
            <div class="footer-spacer"></div>
        </div>
    );
}

export default Layout;