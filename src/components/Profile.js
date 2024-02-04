import {useAuth} from "./Services/AuthProvider";
import {Navigate} from "react-router-dom";
import {useEffect, useState} from "react";

function Profile(){
    const {user} = useAuth()
    const [profile, setProfile] = useState();
    const [message, setMessage] = useState('');

    useEffect(() => {
        const getProfileData = async () => {
            try{
                await fetch("http://localhost:8080/profile", {
                    method: 'GET',
                    headers: {
                        "Content-Type": "application/json",
                        "Accept": "application/json",
                        "Authorization": user.token
                    }
                }).then((res) => {
                    try{
                        res.json().then((data) =>  {
                            setProfile(data);
                        })
                    }catch (err){
                        setMessage("Something gone wrong");
                    }
                })
            }catch (err){
                setMessage("Something gone wrong");
            }
        };
        if (user) {
            getProfileData();
        }
    }, [user]);


    if(!user){
        return <Navigate to="/sign-up" />
    }else{
        return (
            <>
                <p>{message}</p>
            </>
        )
    }
}
export default Profile;