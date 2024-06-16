import {Link, useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";
import Activity from "../Activity";

function NewsAdminPanel() {

    const navigate = useNavigate();
    const [isLoaded, setIsLoaded] = useState(false);
    const [news, setNews] = useState([]);
    const [page, setPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const [message, setMessage] = useState('');

    const getNews = async (p) => {
        let res = await fetch("http://localhost:8080/admin/panel/news?page=" + p, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
            },
            body: JSON.stringify({
                token: localStorage.getItem('jasne_adm')
            })
        })
        if (res.ok) {
            setIsLoaded(true)
            res.json().then(data => {
                setTotalPages(data.totalPages)
                setNews(data.content);
                setIsLoaded(true);
            })
        } else {
            navigate("/")
        }
    }
    useEffect(() => {
        getNews(0);

    }, []);

    const handlePageChange = (newPage) => {
        setPage(newPage);
        getNews(newPage);
    };

    const handleDeleteNews = async (id) => {
        try {
            let res = await fetch("http://localhost:8080/profile/news/delete", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Accept": "application/json",
                },
                body: JSON.stringify({
                    token: localStorage.getItem('jasne_adm'),
                    id: id
                }),
            })
            if (res.ok) {
                setMessage('News was deleted');
                getNews(0)
            } else {
                setMessage('Something went wrong');
            }
        } catch (e) {
            setMessage('Something went wrong');
        }
    }


    return (
        <>
            {isLoaded ? (
                <>
                    <div className="container ">
                        <div className="row">

                            <div className="bg-dark mt-5 p-5 rounded-3 shadow">
                                <h4 className="text-white text-start">Users posts</h4>
                                <hr className="bg-white text-white"/>
                                <p className="text-white p-5">{message}</p>
                                <div className="text-white text-start">
                                    <div className="row">
                                        {news && news.length > 0 ? news.map(content => (
                                            <div className="container mt-3">
                                                <div className="row">
                                                    <Link to={'/news/' + content.newsId}
                                                          className="text-decoration-none fw-bold text-white"><p
                                                        className="col-8">{content.newsTitle.length > 50 ? `${content.newsTitle.substring(0, 50)}...` : content.newsTitle}</p>
                                                    </Link>
                                                    <Link to={"/edit_news/" + content.newsId}
                                                          className="btn btn-primary m-1 col-1">Edit</Link>
                                                    <button onClick={() => handleDeleteNews(content.newsId)}
                                                            className="btn btn-danger m-1 col-1">Delete
                                                    </button>
                                                </div>
                                            </div>
                                        )) : <p>No posts yet</p>}
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
                        </div>
                    </div>
                </>
            ) : (
                <>
                    <p>Loading</p>
                </>
            )}
        </>
    )

}

export default NewsAdminPanel;