import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import loadingGif from "../styles/loading.gif";

function Main() {
    const [message, setMessage] = useState('');
    const [news, setNews] = useState([]);
    const [page, setPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const [isLoaded, setIsLoaded] = useState(false);
    const [loading, setLoading] = useState(false);
    let debounceTimeout;

    const fetchTop = async (page = 0) => {
        try {
            let res = await fetch(`${process.env.REACT_APP_API_ADDRESS}/top_week_all?page=${page}`, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                    "Accept": "application/json",
                }
            });
            if (res.ok) {
                let data = await res.json();
                setNews(prevNews => [...prevNews, ...data.content]);
                setIsLoaded(true);
                setTotalPages(data.totalPages);
                setLoading(false);
            } else {
                setMessage("Something went wrong");
                setLoading(false);
            }
        } catch (e) {
            setMessage("Something went wrong");
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchTop(page);
    }, [page]);

    useEffect(() => {
        const handleScroll = () => {
            if (debounceTimeout) {
                clearTimeout(debounceTimeout);
            }

            debounceTimeout = setTimeout(() => {
                if (window.innerHeight + document.documentElement.scrollTop >= document.documentElement.offsetHeight - 10 && !loading && page < totalPages - 1) {
                    setLoading(true);
                    setPage(prevPage => prevPage + 1);
                }
            }, 100);
        };

        window.addEventListener('scroll', handleScroll);

        return () => {
            window.removeEventListener('scroll', handleScroll);
            if (debounceTimeout) {
                clearTimeout(debounceTimeout);
            }
        };
    }, [loading, page, totalPages]);

    return (
        <>
            {
                isLoaded ? (
                    <div className="container">
                        <div className="row">
                            <div className="mt-5 text-white text-start">
                                <p className="text-white">{message}</p>
                                <p>
                                    Most liked posts of the week:
                                    {news.length > 0 ? news.map(content => (
                                        <div key={content.news_id} className="container mt-5">
                                            <div className="row">
                                                <div className="col-8 text-start">
                                                    <Link to={'/news/' + content.news_id}
                                                          className="text-decoration-none fw-bold text-white ">
                                                        <p className="col-8">{content.title.length > 50 ? `${content.title.substring(0, 50)}...` : content.title}</p>
                                                    </Link>
                                                </div>
                                                <div className="col-2">
                                                    <p>{
                                                        new Date(content.createdAt).toLocaleString('en-US', {
                                                            year: 'numeric',
                                                            month: '2-digit',
                                                            day: '2-digit'
                                                        }).split(',')[0]
                                                    }</p>
                                                </div>
                                                <div className="col-1">
                                                    <p>⬆️ {content.votes}</p>
                                                </div>
                                                <div className="col-1">
                                                    <p><Link className="text-decoration-none text-white" to={"/user/" + content.author_id}>👤</Link></p>
                                                </div>
                                            </div>
                                            <hr />
                                        </div>
                                    )) : <p className="text-white p-5 text-center">No posts yet</p>}
                                    {loading && <p className="text-white p-5 text-center">Loading more posts...</p>}
                                </p>
                            </div>
                        </div>
                    </div>
                ) : <div className="justify-content-center p-5">
                    <img width="10%" src={String(loadingGif)} alt=""/>
                </div>
            }
        </>
    );
}

export default Main;
