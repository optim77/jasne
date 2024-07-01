import {useState} from "react";
import {Link, useNavigate} from "react-router-dom";
import {useAuth} from "./Services/AuthProvider";
import RedirectIfLogged from "./Services/RedirectIfLogged";


function Login() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [message, setMessage] = useState("");
    const auth = useAuth();
    const navigate = useNavigate();

    RedirectIfLogged();

    let handleLogin = async (e) => {
        e.preventDefault();
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
                        if(data.token){

                            localStorage.setItem('jasne', data.token)
                            let expires = new Date()
                            expires.setTime(expires.getTime() + 9000000000)
                            navigate("/")
                            window.location.reload();

                        }else{
                            setMessage('Wrong credentials')
                        }
                    });
                }catch(err){
                    setMessage("Something went wrong");
                }
            });
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
                        <form onSubmit={handleLogin} method='post'>
                            <h2 className="text-white">Jasne!</h2>
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
                        <p className="text-white mt-3">New on Jasne!</p>
                        <Link to="/sign-up" className="text-white">Sign Up</Link>
                    </div>
                </div>

            </div>
            <div className="container d-flex justify-content-center">
                <div className="row">
                    <div className="col- p-3 text-center">
                        <Link to="/recover" >Forgot password?</Link>
                    </div>
                </div>
            </div>
        </>


    )
}

export default Login;