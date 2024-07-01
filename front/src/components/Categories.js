import { useEffect, useState, useCallback } from "react";
import { Link } from "react-router-dom";
import loadingGif from "../styles/loading.gif";

function Categories() {

    const [categories, setCategories] = useState([]);
    const [currentCategoryContent, setCurrentCategoryContent] = useState([]);
    const [selectedCategory, setSelectedCategory] = useState('Nature')
    const [message, setMessage] = useState('');
    const [isLoaded, setIsLoaded] = useState(false);
    const [page, setPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    let debounceTimeout;

    useEffect(() => {
        getCategories();
        toggleCategory("Science", 0);
    }, []);

    const getCategories = async () => {
        try {
            const res = await fetch(`${process.env.REACT_APP_API_ADDRESS}/category/all`, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                }
            });
            const data = await res.json();
            setCategories(data);
            setIsLoaded(true);
        } catch (err) {
            setMessage('Something went wrong');
        }
    };

    const toggleCategory = async (category, page) => {
        setIsLoaded(false);
        setSelectedCategory(category);
        try {
            const res = await fetch(`${process.env.REACT_APP_API_ADDRESS}/news/category/${category}?page=${page}`, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                }
            });
            if (res.ok) {
                const data = await res.json();
                if (page === 0) {
                    setCurrentCategoryContent(data.content);
                } else {
                    setCurrentCategoryContent(prevContent => [...prevContent, ...data.content]);
                }
                setTotalPages(data.totalPages);
                setIsLoaded(true);
                setMessage('');
            } else {
                setMessage("Cannot get categories.");
            }
        } catch (e) {
            setMessage("Cannot get categories.");
        }
    };

    const handleScroll = useCallback(() => {
        if (debounceTimeout) {
            clearTimeout(debounceTimeout);
        }

        debounceTimeout = setTimeout(() => {
            if (window.innerHeight + document.documentElement.scrollTop >= document.documentElement.offsetHeight - 10 && isLoaded && page < totalPages - 1) {
                setPage(prevPage => prevPage + 1);
                toggleCategory(selectedCategory, page + 1);
            }
        }, 100);
    }, [isLoaded, page, selectedCategory, totalPages]);

    useEffect(() => {
        window.addEventListener('scroll', handleScroll);
        return () => {
            window.removeEventListener('scroll', handleScroll);
            if (debounceTimeout) {
                clearTimeout(debounceTimeout);
            }
        };
    }, [handleScroll]);

    return (
        <div className='container text-white'>
            <div className='row'>
                {isLoaded ? categories.map(category => (
                        <div className='col-3' key={category.name}>
                            <button onClick={() => { setPage(0); toggleCategory(category.name, 0); }}
                                    className='btn mt-5 btn-primary border'>
                                {category.name}
                            </button>
                        </div>
                    )) :
                    <div className="justify-content-center p-5">
                        <img width="10%" src={String(loadingGif)} alt="Loading..." />
                    </div>}
            </div>
            <div className="container">
                <div className="row mt-5">
                    <p>{message}</p>
                    <div className="text-white">
                        {currentCategoryContent.map(content => (
                            <div className="container" key={content.news_id}>
                                <div className="row">
                                    <div className="col-8 text-start">
                                        <Link to={'/news/' + content.news_id}
                                              className="text-decoration-none fw-bold text-white">
                                            <p className="col-8">{content.title.length > 50 ? `${content.title.substring(0, 50)}...` : content.title}</p>
                                        </Link>
                                    </div>
                                    <div className="col-2">
                                        <p>{new Date(content.createdAt).toLocaleString('en-US', {
                                            year: 'numeric',
                                            month: '2-digit',
                                            day: '2-digit'
                                        }).split(',')[0]}</p>
                                    </div>
                                    <div className="col-1">
                                        <p>⬆️ {content.votes}</p>
                                    </div>
                                    <div className="col-1">
                                        <p><Link className="text-decoration-none text-white"
                                                 to={"/user/" + content.author_id}>👤</Link></p>
                                    </div>
                                </div>
                                <hr/>
                            </div>
                        ))}
                        {isLoaded && page < totalPages - 1 && <div className="text-center p-5">
                            <img width="5%" src={String(loadingGif)} alt="Loading..." />
                        </div>}
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Categories;
