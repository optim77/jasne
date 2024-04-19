import {useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import login from "../Login";

function View(){

    const [message, setMessage] = useState('');
    const [content, setContent] = useState([]);
    const {id} = useParams();
    useEffect(() => {
        try{
            let getNews = async (e) => {
                let res = await fetch("http://localhost:8080/news/" + id, {
                    method: "GET",
                    headers: {
                        "Content-Type": "application/json",
                        "Accept": "application/json"
                    },
                }).then((res) => {
                    console.log(res)
                    res.json().then(content => {
                        console.log(content)
                        setContent(content);
                    })
                })
            };
        }catch (e){
            setMessage('Something gone wrong');
        }
    }, [id]);

    return(
        <>

            <div>
                <h1>Record Details</h1>
                {content ? (
                    <div>
                        <h2>Record ID: {id}</h2>
                        <div className="container d-flex justify-content-center">
                            <div className="row">
                                <div className="bg-dark mt-5 p-5 rounded-3 shadow">
                                    <p>{message}</p>
                                    <p>{content.id}</p>
                                    <p>{content.title}</p>
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