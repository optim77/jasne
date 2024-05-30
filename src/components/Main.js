import {useEffect, useState} from "react";

function Main(){

    const [message, setMessage] = useState('');
    const [news, setNews] = useState([]);

    const fetchTop = async () => {
        try {
            let res = await fetch("http://localhost:8080/top_week_all", {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                    "Accept": "application/json",
                }
            })
            if (res.ok){
                res.json().then(data => {
                    setNews(data);
                })
            }else {
                setMessage("Something went wrong");
            }
        }catch (e){
            setMessage("Something went wrong");
        }
    }

    useEffect(() => {
        fetchTop();
        console.log(news)
    }, []);

    return(
        <>
            {
                news ? (
                    <div className="container">
                        <div className="row">
                            <div className="mt-5 text-white text-start">
                                <p>{message}</p>
                                <p>
                                    Most voted posts in this week:
                                </p>

                            </div>
                        </div>
                    </div>
                ) : <p className="text-white">Loading...</p>
            }
        </>


)

}
export default Main;