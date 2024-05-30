import {Link, redirect, useNavigate, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import RedirectIfLogged from "../Services/RedirectIfLogged";
import RedirectIfNotLogged from "../Services/RedirectIfNotLogged";
import cookieGetter from "../Services/CookieGetter";

function Edit_comment() {
    const [content, setContent] = useState([]);
    const [message, setMessage] = useState('');
    const [comment, setComment] = useState('')
    const navigate = useNavigate();
    const {id} = useParams();

    const getComment = async () => {
        try {
            let res = await fetch("http://localhost:8080/comment/" + id, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                    "Accept": "application/json"
                }
            })
            if (res.ok) {
                res.json().then(data => {
                    setContent(data);
                })
            } else {
                setMessage("Something went wrong!");
            }
        } catch (e) {
            setMessage("Something went wrong!");
        }
    }
    const handle_update_comment = async (e) => {
        e.preventDefault();
        try {
            let res = await fetch('http://localhost:8080/comment', {
                method: 'PATCH',
                headers: {
                    "Content-Type": "application/json",
                    "Accept": "application/json",
                },
                body: JSON.stringify({
                    token: cookieGetter(),
                    content: comment ? comment : content.content,
                    comment_id: id
                })
            })
            if (res.ok){
                navigate("/activity");
            }else {
                setMessage("Something went wrong");
            }
        }catch (e){
            setMessage('Update was failed!')
        }
    }

    RedirectIfNotLogged();

    useEffect(() => {
        getComment();
    }, [id]);

    return (
        <>
            {content ? (
                    <div>
                        <div>
                            <div className="container justify-content-center">

                                <div className="row bg-dark mt-5 p-5 ">

                                        <div>
                                            {
                                                message ? (<p className='border   text-danger'>{message}</p>) : <p></p>
                                            }

                                        </div>
                                        <div className="text-start text-capitalize text-white">

                                            <div className="container">
                                                <div className="row">
                                                    <div className="container">
                                                        <div className="row">
                                                            <p>
                                                                Comment to: <Link to={"/news/" + content.news_id} className="text-white text-decoration-none fw-bold col-6">{content.newsTitle}</Link>
                                                                <Link className="text-decoration-none text-white col-4" to={"/user/" + content.authorNews}>👤</Link>
                                                            </p>
                                                        </div>
                                                    </div>
                                                    <form onSubmit={handle_update_comment} action="post">
                                                        <textarea className="form-control bg-dark text-white" rows={5}
                                                                  name='description' id='description' required
                                                                  defaultValue={content.content}
                                                                  onChange={(e) => setComment(e.target.value)}/>
                                                        <p className="mt-2">⬆️ {content.commentVotes}</p>
                                                        <input className="btn btn-success mt-3" type="submit" value="Update"/>
                                                    </form>

                                                </div>
                                            </div>
                                        </div>
                                </div>
                            </div>
                        </div>



                    </div>
                ) :
                <p>Loading...</p>
            }


        </>
    )

}

export default Edit_comment;