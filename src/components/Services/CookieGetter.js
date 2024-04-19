import {useCookies} from "react-cookie";

function CookieGetter(){
    const cookie = useCookies('access_token');
    return cookie[0].access_token;
}
export default CookieGetter;