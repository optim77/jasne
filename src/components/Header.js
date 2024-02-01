import React, {useContext} from "react";
import {BrowserRouter, Link, Route, Routes} from "react-router-dom";
import Login from "./Login";
import Register from "./Register";
import Main from "./Main";
import AuthProvider, {useAuth} from "./Services/AuthProvider";
import Elements from "./Elements";
import Categories from "./Categories";

function Header(){
    return(
        <BrowserRouter>
            <nav className="navbar navbar-expand-lg navbar-light bg-dark">
                <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse p-2" id="navbarNavAltMarkup">
                    <div className="navbar-nav">
                        <Link to="/" className="nav-item nav-link active text-white m-2 "><h4>Jasne!</h4></Link>
                    </div>

                    <div className="navbar-nav ms-auto">
                        <Link to="/categories" className="nav-item nav-link active text-white btn m-2 border">Categories</Link>
                        <Link to="/sign-in" className="nav-item nav-link active text-white btn m-2">Sign in</Link>
                        <Link to="/sign-up" className="nav-item nav-link active text-white btn m-2 border">Sign up</Link>



                    </div>
                </div>
            </nav>
            <Routes>
                <Route path="/categories" element={<Categories />} />
                <Route path="/" element={<Main />} />
                <Route path="/sign-in" element={<Login />} />
                <Route path="/sign-up" element={<Register />} />


            </Routes>
        </BrowserRouter>


    )
}
export default Header;