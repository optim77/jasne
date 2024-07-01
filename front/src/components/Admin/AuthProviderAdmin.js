import {createContext, useContext, useState} from "react";

const AuthContext = createContext();

function AuthProviderAdmin({children}) {
    const [user, setUser] = useState(null);
    const [token, setToken] = useState(localStorage.getItem("jasne_adm") || "");

    const processLoginAdmin = async (email, password) => {
        try {
            let res = await fetch(`${process.env.REACT_APP_API_ADDRESS}/admin/authenticate`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Accept": "application/json"
                },
                body: JSON.stringify({
                    email: email,
                    password: password
                }),
            })
            if (res.ok) {
                res.json().then(data => {
                    if (data.token) {
                        localStorage.setItem('jasne_adm', data.token)
                        setUser(data);
                        let expires = new Date()
                        expires.setTime(expires.getTime() + 9000000000)

                    } else {
                        return 500;
                    }
                });
                return 200;
            }else {
                return 500;
            }
        } catch (err) {
            return 500;
        }
    };


    const logOut = () => {
        setUser(null);
        setToken("");
        localStorage.removeItem('jasne')

        window.location.reload();
    };

    return <AuthContext.Provider value={{token, user, processLoginAdmin, logOut}}>{children}</AuthContext.Provider>
}

export default AuthProviderAdmin;

export const useAdminAuth = () => {
    return useContext(AuthContext);
}