import CookieGetter from "./Services/CookieGetter";
import {useContext, useEffect, useState} from "react";
import RedirectIfNotLogged from "./Services/RedirectIfNotLogged";
import data from "bootstrap/js/src/dom/data";
import ReactPaginate from 'react-paginate';
import {Link} from "react-router-dom";

function Activity() {

    const cookie = CookieGetter();
    const [news, setNews] = useState([]);
    const [comments, setComments] = useState([]);
    const [message, setMessage] = useState();
    const [loading, setLoading] = useState(true);
    const [page, setPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const [pageComments, setPageComments] = useState(0);
    const [totalPagesComments, setTotalPagesComments] = useState(0);

    const fetchNewsActivity = async (pageNewsCount) => {
        try {
            let res = await fetch("http://localhost:8080/profile/news/activity?page=" + pageNewsCount, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Accept": "application/json",
                },
                body: JSON.stringify({
                    token: cookie
                }),
            })
            if (res.ok) {
                res.json().then(data => {
                    setNews(data.news)
                    setTotalPages(data.totalPages)
                    console.log(data)
                })
            }
        } catch (e) {
            setMessage('Something gone wrong with fetching news!');
        }
    }
    const fetchCommentsActivity = async (pageCommentsCount) => {
        try {
            let res = await fetch("http://localhost:8080/profile/comments/activity?page=" + pageCommentsCount, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Accept": "application/json",
                },
                body: JSON.stringify({
                    token: cookie
                }),
            })
            if (res.ok) {
                res.json().then(data => {
                    setComments(data.comments)
                    setPageComments(data.number)
                    console.log(data)
                })
            }
        } catch (e) {
            setMessage('Something gone wrong with fetching comments!');
        }
    }


    const handleDeleteNews = async (id) => {
        try {
            let res = await fetch("http://localhost:8080/profile/news/delete", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Accept": "application/json",
                },
                body: JSON.stringify({
                    token: cookie,
                    id: id
                }),
            })
            if (res.ok) {
                setMessage('News was deleted');
                fetchNewsActivity(0)
            } else {
                setMessage('Something went wrong');
            }
        } catch (e) {
            setMessage('Something went wrong');
        }
    }

    const handleDeleteComments = async (id) => {
        try {
            let res = await fetch("http://localhost:8080/profile/comments/delete", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Accept": "application/json",
                },
                body: JSON.stringify({
                    token: cookie,
                    id: id
                }),
            })
            if (res.ok) {
                setMessage('Comment was deleted');
            } else {
                setMessage('Something went wrong');
            }
        } catch (e) {
            setMessage('Something went wrong');
        }
    }

    useEffect(() => {
        fetchNewsActivity()
        fetchCommentsActivity()
        setLoading(false)

    }, [cookie]);
    RedirectIfNotLogged();

    const handlePageChange = (newPage) => {
        setPage(newPage);
        fetchNewsActivity(newPage);
    };
    const handleCommentsPageChange = (newPage) => {
        setPageComments(newPage);
        fetchCommentsActivity(newPage);
    }
    return (
        <>
            {!loading ? (

                <div className="container ">
                    <div className="row">
                        <p className="text-danger alert">{message}</p>
                        <div className="bg-dark mt-5 p-5 rounded-3 shadow">
                            <h4 className="text-white text-start">Your activity</h4>
                            <hr className="bg-white text-white"/>

                            <div className="text-white">
                                <h5 className="text-white text-start">Posts</h5>
                                <div className="container text-start">
                                    <div className="row">
                                        {news['content'] && news['content'].length > 0 ? news['content'].map(content => (
                                            <div className="container mt-3">
                                                <div className="row">
                                                    <Link to={'/news/' + content.id}
                                                          className="text-decoration-none fw-bold text-white"><p
                                                        className="col-8">{content.title.length > 50 ? `${content.title.substring(0, 50)}...` : content.title}</p>
                                                    </Link>
                                                    <Link to={"/edit_news/" + content.id}
                                                          className="btn btn-primary m-1 col-1">Edit</Link>


                                                    <button onClick={() => handleDeleteNews(content.id)}
                                                            className="btn btn-danger m-1 col-1">Delete
                                                    </button>


                                                </div>

                                            </div>
                                        )) : <p>You haven't created a post yet</p>}
                                        <div className="p-2 m-2 text-center">
                                            <button
                                                onClick={() => handlePageChange(page - 1)}
                                                disabled={page <= 0}
                                                className="btn btn-danger col-2"
                                            >
                                                Previous
                                            </button>
                                            <span className="col-8"> Page {page + 1} of {totalPages} </span>
                                            <button
                                                onClick={() => handlePageChange(page + 1)}
                                                disabled={page >= totalPages - 1}
                                                className="btn btn-primary col-2"
                                            >
                                                Next
                                            </button>
                                        </div>

                                    </div>

                                </div>

                            </div>


                            <div className="text-white">
                                <h5 className="text-white text-start">Conversations</h5>
                                <div className="container text-start">
                                    <div className="row">
                                        {comments['content'] && comments['content'].length > 0 ? comments['content'].map(content => (
                                            <div className="container mt-3">
                                                <div className="row">
                                                    <p className="col-8">{content.content.length > 50 ? `${content.content.substring(0, 50)}...` : content.content}</p>
                                                    <div className="container mt-3">
                                                        <div className="row">
                                                            <Link to={"/edit_comment/" + content.id}
                                                                  className="btn btn-primary m-1 col-1">Edit</Link>
                                                            <button onClick={() => handleDeleteComments(content.id)}
                                                                    className="btn btn-danger m-1 col-1">Delete
                                                            </button>
                                                        </div>
                                                    </div>

                                                </div>

                                            </div>
                                        )) : <p>You haven't created a post yet</p>}
                                        <div className="p-2 m-2 text-center">
                                            <button
                                                onClick={() => handleCommentsPageChange(page - 1)}
                                                disabled={page <= 0}
                                                className="btn btn-danger col-2"
                                            >
                                                Previous
                                            </button>
                                            <span
                                                className="col-8 text-center"> Page {page + 1} of {totalPagesComments} </span>
                                            <button
                                                onClick={() => handleCommentsPageChange(page + 1)}
                                                disabled={page >= totalPagesComments - 1}
                                                className="btn btn-primary col-2"
                                            >
                                                Next
                                            </button>
                                        </div>

                                    </div>
                                </div>


                            </div>

                        </div>
                    </div>
                </div>

            ) : (<p className="text-white">Loading...</p>)}

        </>
    );
}

export default Activity;