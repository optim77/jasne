import {useAuth} from "./AuthProvider";
import {useCookies} from "react-cookie";

function AuthChecker(){
    const {token} = useAuth();
    const cookie = useCookies('access_token');
    if(cookie[0].access_token && cookie[0].access_token != 'null'){
           return true;
    }
    return false;
}
export default AuthChecker;