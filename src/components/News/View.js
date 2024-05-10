import {useEffect, useState} from "react";
import {useNavigate, useParams} from "react-router-dom";
import CookieGetter from "../Services/CookieGetter";

function View(){

    const [message, setMessage] = useState('');
    const [content, setContent] = useState([]);
    const [urls, setUrls] = useState([]);
    const [comment, setComment] = useState('');
    const [comments, setComments] = useState([]);
    const cookie = CookieGetter()
    const {id} = useParams();
    const [loading, setLoading] = useState(false);
    const [page, setPage] = useState(0);

    const navigate = useNavigate();



    useEffect(() => {
        getNews();
        fetchComments();

    }, [id]);


    const handleSendComment = async (e) => {
        e.preventDefault();
        if (!cookie){
            setMessage("You must be logged in to comment")
        }else {


            try {
                let res = await fetch("http://localhost:8080/add_comment", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                        "Accept": "application/json"
                    },
                    body: JSON.stringify({
                        content: comment,
                        token: cookie,
                        news_id: id
                    })
                })
                if (res.ok) {
                    window.location.reload();
                } else {
                    setMessage("Something went wrong")
                }
            } catch (e) {
                setMessage("Something went wrong")
            }
        }
    }
    const fetchComments = async () => {
        try{
                let res = await fetch("http://localhost:8080/news_comments/" + id + '?page=0', {
                    method: "GET",
                    headers: {
                        "Content-Type": "application/json",
                        "Accept": "application/json"
                    }
                })
            if(res.ok){
                res.json().then(data =>{
                    setComments(data.content)
                    setPage(prevPage => prevPage + 1)
                    console.log(data);
                })
            }

        }catch (e){
            setMessage('Something gone wrong');
        }
    }
    const getNews = async (e) => {
        try{
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
                            try{
                                let urls = [];
                                if(content.newsUrl.include(';')){
                                    urls = content.newsUrl.split(';')
                                }else{
                                    urls = content.newsUrl;
                                }
                                setUrls(urls);
                            }catch (e){

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
        }catch (e){
            navigate("/")
        }
    }


    return(
        <>

            <div>
                {content ? (
                    <div>
                        <div className="container justify-content-center">
                            <div className="row bg-dark mt-5 p-5 ">
                                <div>
                                    {
                                        message ? (<p className='border   text-danger'>{message}</p>) : <p></p>
                                    }

                                </div>
                                <div className="text-end text-capitalize text-white">
                                    <div className="container">
                                        <div className="row">
                                            <p className="col-2 text-center bg-pop shadow rounded-3 mt-1">
                                                {
                                                    new Date(content.newsCreatedAt).toLocaleString('en-US', {
                                                        year: 'numeric',
                                                        month: '2-digit',
                                                        day: '2-digit'
                                                    }).split(',')[0]
                                                }
                                            </p>
                                            <div className="col-8"></div>
                                            <p className='col-2 text-center bg-pop shadow rounded-3 mt-1'>{content.categories}</p>
                                        </div>
                                    </div>

                                </div>
                                <div className="text-start text-capitalize text-white">
                                    <h4 className='text-white bg-dark'>{content.newsTitle}</h4>
                                </div>
                                <hr className="bg-white text-white"/>
                                <div className="text-start text-white">
                                    <p className='text-white bg-dark p-3'>{content.newsDescription}</p>
                                </div>
                                <div className="text-start text-white">
                                    <p>URLS:</p>
                                    <ul>
                                        {
                                            urls.map(url => (
                                            <li>{url}</li>
                                            ))
                                        }
                                    </ul>

                                </div>
                                <div className="text-start text-white">
                                    <div className="container">
                                        <div className="row">
                                            <button className="col-1 btn btn-primary">Up</button>
                                            <p className="col-1 mt-3">{content.newsVotes}</p>
                                        </div>
                                    </div>

                                </div>
                                <div className="mt-2 mb-2 text-white">
                                    <p>Comments</p>
                                    <hr/>
                                </div>
                                <div className="">
                                    <form onSubmit={handleSendComment} method='post'>
                                        <textarea className="form-control bg-dark text-white"
                                                  name="comment" id="comment" cols="20" rows="5"
                                                  required
                                                  onChange={(e) => setComment(e.target.value)}></textarea>
                                        <input type="submit" className="btn btn-success mt-2 justify-content-start" value="Send" />
                                    </form>
                                </div>

                                <div className='pt-5 text-start text-white'>
                                    {comments.map(comment => (
                                        <div>
                                            <div className='container'>
                                                <div className='row'>
                                                    <p className='col-6'>{comment.author_comment_name}</p>
                                                    <p className='col-6 text-end'>{
                                                        new Date(comment.comment_create_at).toLocaleString('en-US', {
                                                            year: 'numeric',
                                                            month: '2-digit',
                                                            day: '2-digit'
                                                        }).split(',')[0]
                                                    }</p>
                                                </div>

                                            </div>
                                            <div className="container">
                                                <div className="row">
                                                    <p className="col-10 text-start">{comment.comment_content}</p>

                                                </div>
                                                <div className="row">
                                                    <button className="col-2 btn btn-success">Up</button>
                                                    <p className="col-10 text-end "> ↑ {comment.votes}</p>
                                                </div>
                                            </div>

                                            <div className="mt-2 mb-2 text-white">
                                                <hr/>
                                            </div>
                                        </div>

                                    ))}
                                </div>

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

export default View;