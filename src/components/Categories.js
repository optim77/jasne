import {useEffect, useState} from "react";
import {useAuth} from "./Services/AuthProvider";
import {render} from "react-dom";

function Categories() {

    const [categories, setCategories] = useState([]);
    const [message, setMessage] = useState('');
    const {token, logOut} = useAuth();

    useEffect(() => {
        getCategories();
    }, []);

    const getCategories = async () => {
        try {
            let res = await fetch("http://localhost:8080/categories", {
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
            return 503;
        }
    }

    return (
        <div className='container text-white'>
            <div className='row'>
                {categories.map(category => (
                    <div className='col-4'>
                        <button className='btn mt-5 btn-primary border'>
                            {category.name}
                        </button>
                    </div>
                ))}
            </div>
            <p>{message}</p>
        </div>

    )

}

export default Categories;