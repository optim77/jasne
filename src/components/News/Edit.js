import {useEffect, useState} from "react";
import {useNavigate, useParams} from "react-router-dom";
import View from "./View";
import cookieGetter from "../Services/CookieGetter";

function Edit() {

    const [content, setContent] = useState([]);
    const [message, setMessage] = useState('');
    const [urls, setUrls] = useState([]);
    const [updateUrls, setUpdateUrls] = useState('')
    const [updateDescription, setUpdateDescription] = useState('')
    const [updateTitle, setUpdateTitle] = useState('');
    const [fetchedCategories, setFetchedCategories] = useState([]);
    const [selectedCategory, setSelectedCategory] = useState('');
    const {id} = useParams();
    const navigate = useNavigate();
    const [isLoading, setIsLoading] = useState(false)

    useEffect(() => {
        getNews();
        getCategories();

    }, [id]);

    const getNews = async (e) => {
        try {
            let res = await fetch("http://localhost:8080/news/" + id, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                    "Accept": "application/json"
                },
            }).then((res) => {
                try {
                    res.json().then(content => {
                        console.log(content);

                        try {
                            setContent(content);
                            try {
                                let urls = [];
                                if (content.newsUrl.include(';')) {
                                    urls = content.newsUrl.split(';')
                                } else {
                                    urls = content.newsUrl;
                                }
                                setUrls(urls);
                            } catch (e) {

                            }

                        } catch (e) {
                            navigate("/");
                        }

                    }).catch(error => {
                        console.error("Error handling response:", e);
                        navigate("/");
                    });
                } catch (e) {
                    console.error("Error handling response:", e);
                    navigate("/");
                }

            })
        } catch (e) {
            navigate("/")
        }
    }

    let getCategories = async () => {
        try {
            let res = await fetch("http://localhost:8080/category/all", {
                method: "GET",
                "Content-Type": "application/json",
            }).then((res) => {
                res.json().then(data => {
                    setFetchedCategories(data);
                })
            })
            if (res.ok) {

            }
        } catch (e) {
            return 503;
        }
    }
    const handleCategoryChange = (e) => {
        setSelectedCategory(e.target.value);
    };

    const handle_update_news = async (e) => {
        e.preventDefault();
        if(!selectedCategory){
            setSelectedCategory(content.categories);
        }
        console.log(content)
        try {
            let res = await fetch('http://localhost:8080/profile/news/update', {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                    "Accept": "application/json",
                },
                body: JSON.stringify({
                    id: content.newsId,
                    author: cookieGetter(),
                    url: updateUrls ? updateUrls : content.newsUrl,
                    description: updateDescription ? updateDescription : content.newsDescription,
                    title: updateTitle ? updateTitle : content.newsTitle,
                    categories: selectedCategory ? selectedCategory : content.categories
                })
            })
            if(res.ok){
                return navigate('/news/' + content.newsId);
            }else {
                setMessage("Something went wrong!")
            }
        }catch (e){
            setMessage("Something went wrong!")
        }
    }

    return (
        <>

            <div>
                {content ? (
                    <div>
                        <div className="container justify-content-center">

                            <div className="row bg-dark mt-5 p-5 ">
                                <form onSubmit={handle_update_news} method='post'>
                                    <div>
                                        {
                                            message ? (<p className='border   text-danger'>{message}</p>) : <p></p>
                                        }

                                    </div>
                                    <div className="text-end text-capitalize text-white">

                                        <div className="container">
                                            <div className="row">
                                                <p className="col-2 text-center rounded-3 mt-1">
                                                    {
                                                        new Date(content.newsCreatedAt).toLocaleString('en-US', {
                                                            year: 'numeric',
                                                            month: '2-digit',
                                                            day: '2-digit'
                                                        }).split(',')[0]
                                                    }
                                                </p>
                                                <div className="col-8"></div>
                                                <p className='col-2 '>
                                                    <select onChange={handleCategoryChange}
                                                            className="form-group m-auto mt-3 form-select col-2">
                                                        <option selected>{content.categories}</option>
                                                        {fetchedCategories.map(category => (
                                                            <option key={category.id}
                                                                    value={category.id}>{category.name}</option>
                                                        ))}
                                                    </select>
                                                </p>
                                            </div>
                                        </div>


                                    </div>
                                    <div className="text-start text-capitalize text-white">
                                        <input className="form-control bg-dark text-white" name='title' id='title'
                                               type='title'
                                               required
                                               defaultValue={content.newsTitle}
                                               onChange={(e) => setUpdateTitle(e.target.value)}/>
                                    </div>
                                    <hr className="bg-white text-white mt-3"/>
                                    <div className="text-start text-white">

                                        <div className="form-group m-auto mt-3">
                                            <label className="text-white" htmlFor="description">Description</label>
                                            <textarea className="form-control bg-dark text-white" rows={5}
                                                      name='description' id='description' required
                                                      defaultValue={content.newsDescription}
                                                      onChange={(e) => setUpdateDescription(e.target.value)}/>
                                        </div>
                                    </div>
                                    <div className="text-start text-white">
                                        <div className="form-group m-auto mt-3">
                                            <label className="text-white" htmlFor="urls">URLS</label>
                                            <textarea className="form-control bg-dark text-white" name='urls' id='urls'
                                                      defaultValue={content.urls}
                                                      onChange={(e) => setUpdateUrls(e.target.value)}/>
                                        </div>



                                    </div>
                                    <input className="btn btn-primary mt-3" type="submit" value="Update"/>
                                </form>
                            </div>


                        </div>


                    </div>
                ) : (
                    <p>Loading...</p>
                )}
            </div>
        </>
    )

}

export default Edit;