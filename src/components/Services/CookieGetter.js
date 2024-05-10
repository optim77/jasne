import {useCookies} from "react-cookie";

function CookieGetter(){
    const credential = localStorage.getItem('jasne')
    return credential;
}
export default CookieGetter;