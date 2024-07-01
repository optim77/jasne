import {createContext, useContext, useState} from "react";

const AuthContext = createContext();

function AuthProvider({children}){
    const [user, setUser] = useState(null);
    const [token, setToken] = useState(localStorage.getItem("jasne") || "");

    const processLogin = async (email, password) => {
        try {
            await fetch(`${process.env.REACT_APP_API_ADDRESS}/authenticate`, {
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
                        if(data.status === 200){
                            localStorage.setItem('jasne', data.token)
                            setUser(data);
                            let expires = new Date()
                            expires.setTime(expires.getTime() + 9000000000)
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
        localStorage.removeItem('jasne')
        window.location.reload();
    };

    return <AuthContext.Provider value={{token, user, processLogin, logOut}}>{children}</AuthContext.Provider>
}
export default AuthProvider;

export const useAuth = () => {
    return useContext(AuthContext);
}