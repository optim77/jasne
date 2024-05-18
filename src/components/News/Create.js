import {useEffect, useState} from "react";
import {useAuth} from "../Services/AuthProvider";
import {Link, useNavigate} from "react-router-dom";
import {useCookies} from "react-cookie";
import {redirect} from "react-router-dom";
import RedirectIfNotLogged from "../Services/RedirectIfNotLogged";
import CookieGetter from "../Services/CookieGetter";

function Create_News(){

    const [url, setUrl] = useState();
    const [description, setDescription] = useState();
    const [title, setTitle] = useState();
    const [selectedCategory, setSelectedCategory] = useState('');
    const [fetchedCategories, setFetchedCategories] = useState([]);
    const [message, setMessage] = useState('');
    const cookie = CookieGetter();
    const navigate = useNavigate();


    let handle_create_news = async (e) => {
        e.preventDefault();
        if(!selectedCategory){
            setMessage('Please select a category');
            return;
        }
        try{
            let res = await fetch("http://localhost:8080/news", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Accept": "application/json"
                },
                body: JSON.stringify({
                    URL: url,
                    description: description,
                    author: cookie,
                    categories: selectedCategory,
                    title: title
                })
            })
            if (res.ok){
                res.json().then((data) => {
                    return navigate('/news/' + data);
                })

            }
        }catch (e){
            setMessage('Something gone wrong')
        }
    }
    useEffect(() => {
        if (!cookie){
            navigate("/sign-in");
        }
        getCategories();
    }, [cookie]);
    let getCategories = async () => {
        try{
            let res = await fetch("http://localhost:8080/category/all", {
                method: "GET",
                "Content-Type": "application/json",
            }).then((res) => {
                res.json().then(data => {
                    setFetchedCategories(data);
                })
            })
            if (res.ok){

            }
        }catch (e){
            return 503;
        }
    }

    const handleCategoryChange = (e) => {
        setSelectedCategory(e.target.value);
    };

    return(
        <>
            <div className="container ">
                <div className="row">
                    <div className="bg-dark mt-5 p-5 rounded-3 shadow">
                        <form onSubmit={handle_create_news} method='post'>
                            <h3 className="text-white">Add news to check</h3>
                            <p className="alert text-danger">{message}</p>
                            <div className="form-group justify-content-end">
                                <label className="text-white " htmlFor="title">Title</label>
                                <input className="form-control bg-dark text-white" name='title' id='title' type='title'
                                       required
                                       onChange={(e) => setTitle(e.target.value)}/>
                            </div>
                            <div className="form-group m-auto mt-3">
                                <label className="text-white" htmlFor="description">Description</label>
                                <textarea className="form-control bg-dark text-white" rows={5} name='description' id='description' required
                                       onChange={(e) => setDescription(e.target.value)}/>
                            </div>

                            <div className="form-group m-auto mt-3">
                                <label className="text-white" htmlFor="urls">URLS</label>
                                <textarea className="form-control bg-dark text-white" name='urls' id='urls'
                                          onChange={(e) => setUrl(e.target.value)}/>
                            </div>
                            <div>
                                <select onChange={handleCategoryChange} className="form-group m-auto mt-3 form-select">
                                    <option value="0">Select category...</option>
                                    {fetchedCategories.map(category => (
                                        <option key={category.id} value={category.id}>{category.name}</option>
                                    ))}
                                </select>
                            </div>
                            <input className="btn btn-success mt-4 w-100" type="submit" value="Send"/>
                        </form>
                    </div>


                </div>
            </div>
        </>
    );
}
export default Create_News;