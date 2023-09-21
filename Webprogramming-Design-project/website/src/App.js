import { BrowserRouter as Router } from 'react-router-dom';
import { Routes } from 'react-router-dom';
import { Route } from 'react-router-dom';
import About from './Pages/About';
import Home from './Pages/Home';
import AddBook from './Pages/AddBook';
import Book from './Pages/Book';
import Borrowed from './Pages/Borrowed';
import Login from './Pages/Login';
import SignUp from './Pages/SignUp';


function App() {
  return (
    <Router>
        <Routes>
          <Route exact path='/' element={<Home />} />
          <Route path='/about' element={<About/>} />
          <Route path="/profile" element={<Login/>} />
          <Route path="/add-book" element={<AddBook/>} />
          <Route path="/book/:bookId" element={<Book />} />
          <Route path="/borrowed/:bookId" element={<Borrowed />} />
          <Route path="/Login/signup" element={<SignUp />} />
          <Route path="/SignUp/create" element={<Home />} />
          <Route path="/Login/login" element={<Home />} />
        </Routes>
    </Router>
  );
}

export default App;
