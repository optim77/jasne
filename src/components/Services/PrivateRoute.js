import {useAuth} from "./AuthProvider";
import {Navigate} from "react-router-dom";
import Main from "../Main";

const PrivateRoute = () => {
    const user = useAuth();
    if(!user.token){
        return <Navigate to="/sign-in" />
    }
    return <Main />
}
export default PrivateRoute;