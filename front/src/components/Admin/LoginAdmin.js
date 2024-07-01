import {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";
import {useAdminAuth} from "./AuthProviderAdmin";



function LoginAdmin() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [message, setMessage] = useState("");
    const auth = useAdminAuth();
    const navigate = useNavigate();
    useEffect(() => {
        if (localStorage.getItem("jasne_adm")){
            navigate("/admin/panel");
        }
    }, []);

    let handleAdminLogin = async (e) => {
        e.preventDefault();
        try {
            auth.processLoginAdmin(email, password).then((res) => {
                if(res === 200){
                    window.location.reload();
                    navigate("/admin/panel");
                }
                if (res === 500) {
                    setMessage("Wrong Credentials");
                } else if (res === 503) {
                    setMessage("Something went wrong");
                }
            })
        } catch (err) {
            setMessage("Something went wrong");
        }
        return;
    }

    return (
        <>
            <div className="container d-flex justify-content-center">
                <div className="row">
                    <div className="bg-dark mt-5 p-5 rounded-3 shadow">
                        <form onSubmit={handleAdminLogin} method='post'>
                            <h2 className="text-white">Admin Panel</h2>
                            <p className="alert text-danger">{message}</p>
                            <div className="form-group m-auto">
                                <label className="text-white" htmlFor="email">Email</label>
                                <input className="form-control bg-dark text-white" name='email' id='email' type='email'
                                       required
                                       onChange={(e) => setEmail(e.target.value)}/>
                            </div>
                            <div className="form-group m-auto mt-3">
                                <label className="text-white" htmlFor="password">Password</label>
                                <input className="form-control bg-dark text-white" min="6" name='password' id='password'
                                       type='password' required
                                       onChange={(e) => setPassword(e.target.value)}/>
                            </div>
                            <input className="btn btn-success mt-4 w-100" type="submit" value="Login"/>
                        </form>
                    </div>
                </div>

            </div>
        </>


    )
}

export default LoginAdmin;