import {useEffect, useState} from "react";
import {useAuth} from "./Services/AuthProvider";
import {render} from "react-dom";
import {Link} from "react-router-dom";

function Categories() {

    const [categories, setCategories] = useState([]);
    const [currentCategoryContent, setCurrentCategoryContent] = useState([]);
    const [selectedCategory, setSelectedCategory] = useState('Nature')
    const [message, setMessage] = useState('');
    const [isLoaded, setIsLoaded] = useState()
    const [page, setPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const [pageSize, setPageSize] = useState(20);

    useEffect(() => {
        getCategories();
        toggleCategory("Nature");
    }, []);

    const getCategories = async () => {
        try {
            let res = await fetch("http://localhost:8080/category/all", {
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                }
            }).then((res) => {
                res.json().then(data => {
                    setCategories(data);
                })

            })
            if (!res.ok) {
                setMessage('Something get wrong')
            }
        } catch (err) {
            setMessage('Something get wrong')
        }
    }

    const toggleCategory = async (category, page) => {
        setIsLoaded(false)
        setSelectedCategory(category)
        try {
            let res = await fetch(`http://localhost:8080/news/category/${category}?page=${page}`, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                }
            })
            if (res.ok) {
                res.json().then(data => {
                    setCurrentCategoryContent(data.content)
                    setTotalPages(data.totalPages)
                    setIsLoaded(true)
                    setMessage('')
                })

            } else {
                setMessage("Cannot get " + category + " category.")
            }
        } catch (e) {
            setMessage("Cannot get " + category + " category.")
        }
    }
    const handlePageChange = (newPage) => {
        setPage(newPage);
        toggleCategory(selectedCategory, newPage)
    };

    return (
        <div className='container text-white'>

            <div className='row'>
                {categories.map(category => (
                    <div className='col-3'>
                        <button onClick={() => toggleCategory(category.name, page)} className='btn mt-5 btn-primary border'>
                            {category.name}
                        </button>
                    </div>

                ))}

            </div>
            <div className="container">
                <div className="row mt-5">
                    <p>{message}</p>
                    <div className="text-white">
                        {currentCategoryContent.map(content => (
                                <div className="container">
                                    <div className="row">
                                        <div className="col-8 text-start">
                                            <Link to={'/news/' + content.news_id}
                                                  className="text-decoration-none fw-bold text-white "><p
                                                className="col-8">{content.title.length > 50 ? `${content.title.substring(0, 50)}...` : content.title}</p>
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

                                    <hr/>
                                </div>

                            )
                        )
                        }
                        <button
                            onClick={() => handlePageChange(page - 1, selectedCategory)}
                            disabled={page <= 0}
                            className="btn btn-danger"
                        >
                            Previous
                        </button>
                        <span> Page {page + 1} of {totalPages} </span>
                        <button
                            onClick={() => handlePageChange(page + 1, selectedCategory)}
                            disabled={page >= totalPages - 1}
                            className="btn btn-primary"
                        >
                            Next
                        </button>
                        <div className="p-5"></div>
                    </div>
                </div>
            </div>

        </div>

    )

}

export default Categories;