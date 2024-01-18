import {useState} from "react";

function RegisterForm(){

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [message, setMessage] = useState("");

    let handleRegister = async (e) => {
        e.preventDefault();
        try{
            let res = await fetch("http://localhost:8080/api/register", {
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
            if(resJson.ok === false && resJson.status === 302){
                setMessage("Email is already exist");
            }
        }catch (err){
            setMessage(err);
        }
    }

    return(
        <div>
            <form onSubmit={handleRegister} method="post">
                <p className="alert">{message}</p>
                <label htmlFor="emailRegister">Email</label>
                <input id="emailRegister" name="emailRegister" type="email" required onChange={(e) => setEmail(e.target.value)}/>
                <label htmlFor="passwordRegister">Password</label>
                <input id="passwordRegister" name="passwordRegister" type="password" required onChange={(e) => setPassword(e.target.value)}/>
                <label htmlFor="passwordConfirmRegister">Repeat password</label>
                <input id="passwordConfirmRegister" name="passwordConfirmRegister" type="password" required/>
                <input type="submit"/>
            </form>
        </div>

    )

}
export default RegisterForm;