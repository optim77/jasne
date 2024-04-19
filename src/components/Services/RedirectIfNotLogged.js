import CookieGetter from "./CookieGetter";
import {useEffect} from "react";
import {useNavigate} from "react-router-dom";

function RedirectIfNotLogged(isLogged){
    const navigate = useNavigate();
    const cookie = CookieGetter();

    useEffect(() => {
        if (cookie){
            navigate("/profile");
        }
    }, [cookie]);

}
export default RedirectIfNotLogged;