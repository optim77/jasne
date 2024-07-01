import { useEffect, useRef, useState, useCallback } from "react";
import { useNavigate, useParams } from "react-router-dom";
import CookieGetter from "../Services/CookieGetter";
import loadingGif from "../../styles/loading.gif";

function View() {
    const [message, setMessage] = useState('');
    const [content, setContent] = useState([]);
    const [urls, setUrls] = useState([]);
    const [comment, setComment] = useState('');
    const [comments, setComments] = useState([]);
    const cookie = CookieGetter();
    const { id } = useParams();
    const [isLoadedNews, setIsLoadedNews] = useState(false);
    const [isLoadedComment, setIsLoadedComment] = useState(false);
    const [page, setPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const [isVoted, setIsVoted] = useState(false);
    const [newsVotes, setNewsVotes] = useState(0);
    const navigate = useNavigate();
    const buttonRefs = useRef({});
    const commentRefs = useRef({});
    const [loginMessage, setLoginMessage] = useState('');
    const [loginMessageComment, setLoginMessageComment] = useState('');
    const [loginMessageCommentUp, setLoginMessageCommentUp] = useState('');
    let debounceTimeout;

    useEffect(() => {
        getNews();
        setIsLoadedNews(true);
        fetchComments(id, 0);
        setIsLoadedComment(true);
    }, [id]);

    const handleSendComment = async (e) => {
        e.preventDefault();
        if (cookie) {
            try {
                let res = await fetch(`${process.env.REACT_APP_API_ADDRESS}/add_comment`, {
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
                });
                if (res.ok) {
                    window.location.reload();
                } else {
                    setMessage("Something went wrong");
                }
            } catch (e) {
                setMessage("Something went wrong");
            }
        } else {
            setLoginMessageComment("You must be logged in to comment");
            setTimeout(() => setLoginMessageComment(''), 3000);
        }
    };

    const fetchComments = async (id, page) => {
        try {
            let res = await fetch(`${process.env.REACT_APP_API_ADDRESS}/news_comments/` + id + '?page=' + page, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Accept": "application/json"
                },
                body: JSON.stringify({
                    token: cookie
                })
            });
            if (res.ok) {
                res.json().then(data => {
                    setComments(prevComments => [...prevComments, ...data.content]);
                    setTotalPages(data.totalPages);
                    setIsLoadedComment(true);
                });
            }
        } catch (e) {
            setMessage('Something gone wrong');
        }
    };

    const getNews = async () => {
        try {
            await fetch(`${process.env.REACT_APP_API_ADDRESS}/news/` + id, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Accept": "application/json"
                },
                body: JSON.stringify({
                    token: cookie
                })
            }).then((res) => {
                res.json().then(content => {
                    setContent(content);
                    setIsVoted(content.voted);
                    setNewsVotes(content.newsVotes);
                    if (content.newsUrl != null){
                        setUrls(content.newsUrl.split(';'));
                    }
                    setIsLoadedNews(true);
                }).catch(() => navigate("/"));
            });
        } catch (e) {
            navigate("/");
        }
    };

    const handleNewVote = async (id) => {
        if (cookie) {
            try {
                let res = await fetch(`${process.env.REACT_APP_API_ADDRESS}/make_vote`, {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                        "Accept": "application/json"
                    },
                    body: JSON.stringify({
                        token: cookie,
                        news_id: id
                    })
                });
                if (res.ok) {
                    setIsVoted(true);
                    res.json().then(data => {
                        setNewsVotes(prevVotes => prevVotes + (data === 0 ? -1 : 1));
                        setIsVoted(data === 0);
                    });
                } else {
                    setMessage('Something went wrong');
                }
            } catch (e) {
                setMessage('Something went wrong');
            }
        } else {
            setLoginMessage("You must be logged in to vote");
            setTimeout(() => setLoginMessage(''), 3000);
        }
    };

    const handleCommentVote = async (id) => {
        if (cookie) {
            try {
                let res = await fetch(`${process.env.REACT_APP_API_ADDRESS}/make_vote`, {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                        "Accept": "application/json"
                    },
                    body: JSON.stringify({
                        token: cookie,
                        comment_id: id
                    })
                });
                if (res.ok) {
                    res.json().then(data => {
                        const commentElement = commentRefs.current[id];
                        if (data === 0) {
                            if (buttonRefs.current[id]) {
                                buttonRefs.current[id].classList.remove('btn-primary');
                                buttonRefs.current[id].classList.add('btn-success');
                                commentElement.votes.textContent = parseInt(commentElement.votes.textContent) - 1;
                            }
                        } else if (data === 1) {
                            if (buttonRefs.current[id]) {
                                buttonRefs.current[id].classList.remove('btn-success');
                                buttonRefs.current[id].classList.add('btn-primary');
                                commentElement.votes.textContent = parseInt(commentElement.votes.textContent) + 1;
                            }
                        }
                    });
                } else {
                    setMessage('Something went wrong');
                }
            } catch (e) {
                setMessage('Something went wrong');
            }
        } else {
            setLoginMessageCommentUp("You must be logged in to vote");
            setTimeout(() => setLoginMessageCommentUp(''), 3000);
        }
    };

    const handleScroll = useCallback(() => {
        if (debounceTimeout) {
            clearTimeout(debounceTimeout);
        }

        debounceTimeout = setTimeout(() => {
            if (window.innerHeight + document.documentElement.scrollTop >= document.documentElement.offsetHeight - 10 && isLoadedComment && page < totalPages - 1) {
                setPage(prevPage => prevPage + 1);
                fetchComments(id, page + 1);
            }
        }, 100);
    }, [isLoadedComment, page, totalPages]);

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
        <>
            <div>
                {content ? (
                    <div>
                        <div className="container justify-content-center">
                            <div className="row bg-dark mt-5 p-5 ">
                                <div>
                                    {message ? (<p className='border text-danger'>{message}</p>) : <p></p>}
                                </div>
                                {isLoadedNews ? (
                                    <>
                                        <div className="text-end text-capitalize text-white">
                                            <div className="container">
                                                <div className="row">
                                                    <p className="col-2 text-center bg-pop shadow rounded-3 mt-1">
                                                        {new Date(content.newsCreatedAt).toLocaleString('en-US', {
                                                            year: 'numeric',
                                                            month: '2-digit',
                                                            day: '2-digit'
                                                        }).split(',')[0]}
                                                    </p>
                                                    <div className="col-8"></div>
                                                    <p className='col-2 text-center bg-pop shadow rounded-3 mt-1'>{content.categories}</p>
                                                </div>
                                            </div>
                                        </div>
                                        <div className="text-start text-capitalize text-white">
                                            <h4 className='text-white bg-dark'>{content.newsTitle}</h4>
                                        </div>
                                        <hr className="bg-white text-white" />
                                        <div className="text-start text-white">
                                            <p className='text-white bg-dark p-3'>{content.newsDescription}</p>
                                        </div>
                                        <div className="text-start text-white">
                                            <p>URLS:</p>
                                            <ul>
                                                {urls.map((url, index) => (
                                                    <li key={index}>{url}</li>
                                                ))}
                                            </ul>
                                        </div>
                                        <div className="text-start text-white">
                                            <div className="container">
                                                <div className="row">
                                                    <button onClick={() => handleNewVote(content.newsId)}
                                                            className={isVoted ? "col-2 btn btn-success" : "col-2 btn btn-primary"}>Up
                                                    </button>
                                                    <p className="col-1 mt-3">{newsVotes}</p>
                                                </div>
                                            </div>
                                            {loginMessage && (
                                                <div className="text-danger mt-2">{loginMessage}</div>
                                            )}
                                        </div>
                                    </>
                                ) : (
                                    <div className="justify-content-center p-5">
                                        <img width="10%" src={String(loadingGif)} alt="Loading..." />
                                    </div>
                                )}

                                <div className="mt-2 mb-2 text-white">
                                    <p>Comments</p>
                                    <hr />
                                </div>
                                <div className="">
                                    <form onSubmit={handleSendComment} method='post'>
                                        <textarea className="form-control bg-dark text-white"
                                                  name="comment" id="comment" cols="20" rows="5"
                                                  required
                                                  onChange={(e) => setComment(e.target.value)}></textarea>
                                        <input type="submit" className="btn btn-success mt-2 justify-content-start"
                                               value="Send"/>
                                    </form>
                                    {loginMessageComment && (
                                        <div className="text-danger mt-2">{loginMessageComment}</div>
                                    )}
                                </div>
                                {isLoadedComment ? (
                                    <div className='pt-5 text-start text-white'>
                                        {comments.map(comment => (
                                            <div key={comment.id}>
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
                                                        <button
                                                            ref={el => buttonRefs.current[comment.id] = el}
                                                            onClick={() => handleCommentVote(comment.id)}
                                                            className={comment.user_voted ? "col-2 btn btn-primary" : "col-2 btn btn-success"}>Up
                                                        </button>
                                                        <p
                                                            ref={el => commentRefs.current[comment.id] = {...commentRefs.current[comment.id], votes: el}}
                                                            className="col-10 text-end ">{comment.votes}</p>
                                                    </div>
                                                </div>
                                                {loginMessageCommentUp && (
                                                    <div className="text-danger mt-2">{loginMessageCommentUp}</div>
                                                )}
                                                <div className="mt-2 mb-2 text-white">
                                                    <hr />
                                                </div>
                                            </div>
                                        ))}
                                    </div>
                                ) : (
                                    <p className="text-white p-5">Loading...</p>
                                )}
                            </div>
                        </div>
                    </div>
                ) : (
                    <p>Loading...</p>
                )}
            </div>
        </>
    );
}

export default View;
