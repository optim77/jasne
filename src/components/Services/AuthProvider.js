import {createContext, useContext, useState} from "react";
import {useNavigate} from "react-router-dom";

const AuthContext = createContext();

function AuthProvider({children}){
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [message, setMessage] = useState("");
    const [user, setUser] = useState(null);
    const [token, setToken] = useState(localStorage.getItem("jasne") || "");


    const processLogin = async (email, password) => {
        try {
            let res = await fetch("http://localhost:8080/authentication", {
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
                        if(data.length > 20){
                            setToken(data);
                        }else{
                            return 404;
                        }
                    });

                }catch(err){
                    return 503;
                }
            });
        } catch (err) {
            return 503;
        }
    };


    const logOut = () => {
        setUser(null);
        setToken("");
        localStorage.removeItem("jasne");
        // navigate("/login");
    };

    return <AuthContext.Provider value={{token, user, processLogin, logOut}}>{children}</AuthContext.Provider>
}
export default AuthProvider;

export const useAuth = () => {
    return useContext(AuthContext);
}