import {useState} from "react";
import {useAuth} from "./Services/AuthProvider";

function Categories(){

    const [categories, setCategories] = useState([]);
    const {token, logOut} = useAuth();

    const getCategories = async () => {
        try{
            let res = await fetch("http://localhost:8080/categories", {
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                }
            }).then((res) => {
                res.json().then(data => {
                    setCategories(data);
                })
                console.log(res);
            })
        }catch (err){
            console.log(err)
        }
    }
    getCategories()
    return(
        <div>{categories}</div>
    )

}
export default Categories;