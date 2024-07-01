import {useState} from "react";
import {useAuth} from "./Services/AuthProvider";

function Elements() {
    const [elements, setElements] = useState([]);
    const [message, setMessage] = useState("");
    const {token, logOut} = useAuth();
    const getElements = async () => {
        try {
            await fetch(`${process.env.REACT_APP_API_ADDRESS}/element/random`, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                    "Accept": "application/json",
                    "Authorization": "Bearer " + token
                }
            }).then((res) => {
                res.json().then(data => {
                    setElements(data)
                })
            })
        } catch (err) {
            setMessage("Something go wrong");
        }
    }
    getElements();

    return(
        <div>{elements}</div>
    )


}

export default Elements