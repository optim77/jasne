import {useState} from "react";
import {Link} from "react-router-dom";


function Login() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [message, setMessage] = useState("");
    let handleLogin = async (e) => {
        e.preventDefault()
        try {
            let res = await fetch("http://localhost:8080/api/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    email: email,
                    password: password
                }),
            });
            let resJson = await res;
            if (resJson.status === 401) {
                setMessage("Wrong Credentials");

            }
        } catch (err) {
            setMessage("Something went wrong")
        }
    }

    return (
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


    )
}

export default Login;