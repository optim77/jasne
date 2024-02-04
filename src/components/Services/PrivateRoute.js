import {useAuth} from "./AuthProvider";
import { Navigate, Route, Routes} from "react-router-dom";

const PrivateRoute = ({ component: Component, ...rest }) => {
    const { token } = useAuth();

    return (
        <Routes>
            <Route
                {...rest}
                render={(props) => {
                    return token ? (<Component {...props} />) : (<Navigate to="/sign-in" />)
                }}
            />
        </Routes>



    );
};
export default PrivateRoute