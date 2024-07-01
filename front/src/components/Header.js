import React from "react";
import {BrowserRouter, Link, Route, Routes, useNavigate} from "react-router-dom";
import Login from "./Login";
import Register from "./Register";
import Main from "./Main";
import {useAuth} from "./Services/AuthProvider";
import Categories from "./Categories";
import Profile from "./Profile";
import Recovery from "./Recovery";
import Create from "./News/Create";
import View from "./News/View";
import {Dropdown} from "react-bootstrap";
import Activity from "./Activity";
import Edit from "./News/Edit";
import Edit_comment from "./News/Edit_comment";
import LoginAdmin from "./Admin/LoginAdmin";
import AdminPanel from "./Admin/AdminPanel";
import UsersAdminPanel from "./Admin/UsersAdminPanel";
import NewsAdminPanel from "./Admin/NewsAdminPanel";
import AdminCategoryPanel from "./Admin/AdminCategoryPanel";

function Header() {

    const {logOut} = useAuth();
    const handleLogout = () => {
        logOut();
    }
    const handleAdminLogout = () => {
        localStorage.removeItem('jasne_adm');
        window.location.reload();
    }
    const adm_cookie = localStorage.getItem('jasne_adm');
    const cookie = localStorage.getItem('jasne');
    return (
        <BrowserRouter>
            <nav className="navbar navbar-expand-lg navbar-light bg-dark">
                <button className="navbar-toggler" type="button" data-toggle="collapse"
                        data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false"
                        aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse p-2" id="navbarNavAltMarkup">
                    <div className="navbar-nav">
                        <Link to="/" className="nav-item nav-link active text-white m-2 "><h4>Jasne!</h4></Link>
                    </div>

                    <div className="navbar-nav ms-auto">
                        <Link to="/categories"
                              className="nav-item nav-link active text-white btn m-2 border">Categories</Link>
                        {cookie ? (

                            <>
                                <Link to="create" className="nav-item nav-link active text-white m-2 btn btn-primary">Ask
                                    question</Link>
                                <Dropdown>
                                    <Dropdown.Toggle className="nav-item nav-link active text-white btn m-2"
                                                     variant="success" id="dropdown-basic">
                                        Profile
                                    </Dropdown.Toggle>
                                    <Dropdown.Menu>
                                        <Dropdown.Item href="/activity"><Link to="activity"
                                                                              className="text-decoration-none text-dark">Activity</Link></Dropdown.Item>
                                        <Dropdown.Item><Link to="/profile"
                                                             className="text-decoration-none text-dark">Edit</Link></Dropdown.Item>
                                        <Dropdown.Item onClick={handleLogout}>Sign out</Dropdown.Item>
                                    </Dropdown.Menu>
                                </Dropdown>

                            </>

                        ) : (
                            <>
                                <Link to="/sign-in" className="nav-item nav-link active text-white btn m-2">Sign
                                    in</Link>
                                <Link to="/sign-up" className="nav-item nav-link active text-white btn m-2">Sign
                                    up</Link>
                            </>
                        )}
                        {adm_cookie ? (


                                <Dropdown>
                                    <Dropdown.Toggle className="nav-item nav-link active text-white btn m-2"
                                                     variant="success" id="dropdown-basic">
                                        Admin
                                    </Dropdown.Toggle>
                                    <Dropdown.Menu>
                                        <Dropdown.Item><Link to="/admin/panel"
                                                             className="text-decoration-none text-dark">Admin</Link></Dropdown.Item>
                                        <Dropdown.Item onClick={handleAdminLogout}>Sign out</Dropdown.Item>
                                    </Dropdown.Menu>
                                </Dropdown>
                            ) :
                            (<></>)}


                    </div>
                </div>
            </nav>
            <Routes>
                <Route path="/categories" element={<Categories/>}/>
                <Route path="/" element={<Main/>}/>
                <Route path="/sign-in" element={<Login/>}/>
                <Route path="/sign-up" element={<Register/>}/>
                <Route path="/activity" element={<Activity/>}/>
                <Route path="/profile" element={<Profile/>}/>
                <Route path="/recover" element={<Recovery/>}/>
                <Route path="/create" element={<Create/>}/>
                <Route path="/news/:id" element={<View/>}/>
                <Route path="/edit_news/:id" element={<Edit/>}/>
                <Route path="/edit_comment/:id" element={<Edit_comment/>}/>
                <Route path="/admin" element={<LoginAdmin/>}/>
                <Route path="/admin/panel" element={<AdminPanel/>}/>
                <Route path="/admin/panel/users" element={<UsersAdminPanel/>}/>
                <Route path="/admin/panel/news" element={<NewsAdminPanel/>}/>
                <Route path="/admin/panel/categories" element={<AdminCategoryPanel/>}/>
            </Routes>
        </BrowserRouter>


    )
}

export default Header;