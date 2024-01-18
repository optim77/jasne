import {useState} from "react";




function LoginForm(){
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [message, setMessage] = useState("");
    let handleLogin  = async (e) => {
        e.preventDefault()
        try{
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
            console.log(resJson)
            if(resJson.status === 401){
                setMessage("Wrong Credentials");

            }
        }catch (err){
            setMessage("Something went wrong")
        }
    }

    return (
        <div>
            <form onSubmit={handleLogin} method='post' >
                <p className="alert">{message}</p>
                <label htmlFor="email">Email</label>
                <input name='email' id='email' type='email' required onChange={(e) => setEmail(e.target.value)} />
                <label htmlFor="password">Password</label>
                <input name='password' id='password' type='password' required onChange={(e) => setPassword(e.target.value)} />
                <input type="submit"/>
            </form>
        </div>

    )
}

export default LoginForm;