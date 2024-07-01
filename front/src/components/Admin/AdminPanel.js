import {Link, useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";

function AdminPanel() {

    const navigate = useNavigate();
    const [isLoaded, setIsLoaded] = useState(false);
    const getPanel = async () => {
        try{
            let res = await fetch(`${process.env.REACT_APP_API_ADDRESS}/admin/panel`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Accept": "application/json"
                },
                body: JSON.stringify({
                    token: localStorage.getItem('jasne_adm')
                })
            })
            if (!res.ok) {
                navigate("/")
            }else{
                setIsLoaded(true)
            }
        }catch (e){
            navigate("/")
        }

    }
    useEffect(() => {
        if (!localStorage.getItem("jasne_adm")){navigate("/");}
        getPanel();
    }, []);

    return (
        <>
            {isLoaded ? (
                <div>
                    <Link to={"/admin/panel/users"} className="btn btn-primary m-5">Users</Link>
                    <Link to={"/admin/panel/news"} className="btn btn-primary m-5">Posts</Link>
                    <Link to={"/admin/panel/categories"} className="btn btn-primary m-5">Categories</Link>
                </div>
                ) : (
                    <>
                    <p>Loading</p>
                    </>
                )}


        </>
    )

}

export default AdminPanel;