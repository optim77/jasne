import {createContext, useContext, useState} from "react";
import {useNavigate} from "react-router-dom";
import {useCookies} from "react-cookie";

const AuthContext = createContext();

function AuthProvider({children}){
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [message, setMessage] = useState("");
    const [user, setUser] = useState(null);
    const [token, setToken] = useState(localStorage.getItem("jasne") || "");
    const [userCookie, setUserCookie] = useCookies('access_token')

    const processLogin = async (email, password) => {
        try {
            let res = await fetch("http://localhost:8080/authenticate", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Accept": "application/json"
                },
                body: JSON.stringify({
                    email: email,
                    password: password
                }),
            }).then((res) => {
                try{
                    res.json().then(data => {
                        if(data){
                            setToken(data);
                            setUser(data);
                            let expires = new Date()
                            expires.setTime(expires.getTime() + 9000000000)
                            setUserCookie('access_token', data.token, { path: '/',  expires})
                        }else{
                            return 404;
                        }
                    });
                }catch(err){
                    return 503;
                }
            });
            return 200;
        } catch (err) {
            return 503;
        }
    };


    const logOut = () => {
        setUser(null);
        setToken("");
        setUserCookie('access_token', null);

        window.location.reload();
    };

    return <AuthContext.Provider value={{token, user, processLogin, logOut}}>{children}</AuthContext.Provider>
}
export default AuthProvider;

export const useAuth = () => {
    return useContext(AuthContext);
}