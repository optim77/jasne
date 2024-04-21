import {useEffect, useState} from "react";
import {useNavigate, useParams} from "react-router-dom";
import login from "../Login";
import RedirectIfNotLogged from "../Services/RedirectIfNotLogged";

function View(){

    const [message, setMessage] = useState('');
    const [content, setContent] = useState([]);
    const [urls, setUrls] = useState([]);
    const [comment, setComment] = useState('');
    const [comments, setComments] = useState([]);
    const {id} = useParams();
    const navigate = useNavigate();

    useEffect(() => {
        try{
            let getNews = async (e) => {
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
                                //navigate("/");
                            });
                        } catch (e) {
                            console.error("Error handling response:", e);
                            //navigate("/");
                        }

                    })
                }catch (e){
                    //navigate("/")
                }
            };
            getNews();
        }catch (e){
            setMessage('Something gone wrong');
        }
        // TODO
        try{
            let getComments = async (e) => {
                let res = await fetch("http://localhost:8080/news_comments/" + id, {
                    method: "GET",
                    headers:{
                        "Content-Type": "application/json",
                        "Accept": "application/json"
                    }
                })
            }
        }catch (e){
            setMessage('Something gone wrong');
        }

    }, [id]);

    // TODO
    const handleSendComment = (e) => {
        e.preventDefault();
        let res = async () => {
            try{
                await fetch("http://localhost:8080/comment", {
                    method: "POST",
                    headers:{
                        "Content-Type": "application/json",
                        "Accept": "application/json"
                    },
                    body: {
                        comment: comment
                    }
                })
            }catch (e){

            }


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
                                    <p className='text-alert'>{message}</p>
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
                                    <form onSubmit={handleSendComment}>
                                        <textarea className="form-control bg-dark text-white"
                                                  name="comment" id="comment" cols="20" rows="5"
                                                  required
                                                  onChange={(e) => setComment(e.target.value)}></textarea>
                                        <button className="btn btn-success mt-2 justify-content-start">Send</button>
                                    </form>
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