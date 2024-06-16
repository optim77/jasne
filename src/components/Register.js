import {useState} from "react";
import {Link, useNavigate} from "react-router-dom";
import RedirectIfLogged from "./Services/RedirectIfLogged";

function Register() {

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [message, setMessage] = useState("");
    const [notification, setNotification] = useState('alert text-danger');
    const [csrfToken, setToken] = useState(null);
    let t = null;
    const navigate = useNavigate();

    RedirectIfLogged();
    let handleRegister = async (e) => {
        e.preventDefault();
        if (t !== csrfToken) {
            setMessage("Something gone wrong try later. Err no.12");
            setNotification("alert text-danger");
        }
        try {
            let res = await fetch("http://localhost:8080/register", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Accept": "application/json"
                },
                body: JSON.stringify({
                    email: email,
                    password: password
                }),
            });
            if (res.status === 208) {
                setMessage("Email is in use");
                setNotification("alert text-danger");
            } else if (res.status === 200) {
                setMessage('Registered Successfully');
                setNotification("alert text-success");
                navigate("/sign-in");
            } else {
                setMessage("Something went wrong");
                setNotification("alert text-danger");
            }
        } catch (err) {
            setMessage("Something went wrong");
            setNotification("alert text-danger");
        }
    }

    return (
        <>
            <div className="container d-flex justify-content-center">
                <div className="row">
                    <div className="bg-dark mt-5 p-5 rounded-3 shadow">
                        <form onSubmit={handleRegister} method="post">
                            <h2 className="text-white">Jasne!</h2>
                            <p className={notification}>{message}</p>
                            <div className="form-group">
                                <input type="hidden" value={csrfToken}/>
                                <label className="text-white" htmlFor="emailRegister">Email</label>
                                <input className="form-control bg-dark text-white" id="emailRegister"
                                       name="emailRegister" type="email"
                                       required
                                       onChange={(e) => setEmail(e.target.value)}/>
                            </div>
                            <div className="form-group m-auto mt-3">

                                <label className="text-white" htmlFor="passwordRegister">Password</label>
                                <input className="form-control bg-dark text-white" id="passwordRegister"
                                       name="passwordRegister"
                                       type="password"
                                       required onChange={(e) => setPassword(e.target.value)}/>
                            </div>
                            <div className="form-group m-auto mt-3">

                                <label className="text-white" htmlFor="passwordConfirmRegister">Repeat password</label>
                                <input className="form-control bg-dark text-white" id="passwordConfirmRegister"
                                       name="passwordConfirmRegister"
                                       type="password" required/>
                            </div>
                            <div className="form-group m-auto mt-3">
                                <input name="rules" type="checkbox" required/>
                                <label className="text-white m-lg-2" htmlFor=""> Accept rules</label>
                            </div>
                            <input className="btn btn-success mt-4 w-100" type="submit" value="Sign-up"/>
                        </form>
                        <p className="text-white mt-3">Already have an account?</p>
                        <Link to="/sign-in" className="text-white">Sign In</Link>
                    </div>
                </div>
            </div>
            <div className="container d-flex justify-content-center">
                <div className="row">
                    <div className="col- p-3 text-center">
                        <a className="text-dark" href="">About us</a>
                    </div>
                </div>
            </div>
        </>

    )

}

export default Register;