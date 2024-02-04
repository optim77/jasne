import {useState} from "react";

function Recovery() {

    const [email, setEmail] = useState();
    const [message, setMessage] = useState('');
    const [notification, setNotification] = useState('alert text-danger');
    const handleRecover = async (e) => {
        e.preventDefault();
        try{
            await fetch("http://localhost:8080/recover", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Accept": "application/json"
                },
                body: {
                    "email": email
                }
            }).then((res) => {
                try{
                    res.json().then((data) => {
                        setMessage("Request for password change was send");
                        setNotification("alert alert-success");
                    })
                }catch (err){
                    setMessage("Something gone wrong");
                }
            })
        }catch (err){
            setMessage("Something gone wrong");
        }
    }

    return (
        <div className="container d-flex justify-content-center">
            <div className="row">
                <div className="bg-dark mt-5 p-5 rounded-3 shadow">
                    <form onSubmit={handleRecover} action="post">
                        <h2 className="text-white">Jasne!</h2>
                        <p className={notification}>{message}</p>
                        <div className="form-group">
                            <label className="text-white" htmlFor="email">Email</label>
                            <input className="form-control bg-dark text-white" id="emailRegister"
                                   name="emailRegister" type="email"
                                   required
                                   onChange={(e) => setEmail(e.target.value)}/>
                            <input className="btn btn-success mt-4 w-100" type="submit" value="Submit"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    )

}

export default Recovery;