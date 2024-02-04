import {useAuth} from "./AuthProvider";

function AuthChecker(){
    const {token} = useAuth();
    if(token){
           return true;
    }
    return false;
}
export default AuthChecker;