import {useAuth} from "./Services/AuthProvider";
import {Navigate} from "react-router-dom";
import {useEffect, useState} from "react";
import CookieGetter from "./Services/CookieGetter";

function Profile() {

    //const [profile, setProfile] = useState();
    const [fetchedUserName, setFetchedUserName] = useState('');
    const [fetchedUserSurname, setFetchedUserSurname] = useState('');
    const [fetchedUserBio, setFetchedUserBio] = useState('')
    const [fetchedUserSpecialization, setFetchedUserSpecialization] = useState('')
    const [message, setMessage] = useState('');
    const [messageColors, setMesageColors] = useState('alert text-danger')
    const cookie = CookieGetter();
    const [userName, setUserName] = useState();
    const [userSurname, setUserSurname] = useState();
    const [userSpecialization, setUserSpecialization] = useState();
    const [userBio, setUserBio] = useState();
    const [loadFlag, setLoadFlag] = useState(false);

    const getProfileData = async () => {
        try {
            let res = await fetch("http://localhost:8080/profile", {
                method: 'POST',
                headers: {
                    "Content-Type": "application/json",
                    "Accept": "application/json",
                },
                body: JSON.stringify({
                    token: cookie
                })
            })
            if (res.ok) {
                try {
                    res.json().then((data) => {
                        setFetchedUserBio(data.bio);
                        setFetchedUserName(data.name);
                        setFetchedUserSurname(data.surname);
                        setFetchedUserSpecialization(data.specialization)
                    })
                } catch (err) {
                    setMessage("Something gone wrong");
                    setMesageColors('alert text-danger');
                }
            }
        } catch (err) {
            setMessage("Something gone wrong");
            setMesageColors('alert text-danger');
        }
    };

    useEffect(() => {
        getProfileData();
        setLoadFlag(true);
    }, [cookie]);

    if (!CookieGetter()) {
        return <Navigate to="/sign-up"/>
    }

    const handle_update_profile = async (e) => {
        e.preventDefault();
        // if(!userName){
        //     setUserName(fetchedUserName);
        // }
        // if(!userSurname){
        //     setUserSurname(fetchedUserSurname);
        // }
        // if(!userSpecialization){
        //     setUserSpecialization(fetchedUserSpecialization);
        // }
        // if(!userBio){
        //     setUserBio(fetchedUserBio);
        // }

        try{
            let res = await fetch("http://localhost:8080/profile", {
                method: "PUT",
                headers:{
                    "Content-Type": "application/json",
                    "Accept": "application/json",
                },
                body: JSON.stringify({
                    token: cookie,
                    bio: userBio ? userBio : fetchedUserBio,
                    name: userName ? userName : fetchedUserName,
                    surname: userSurname ? userSurname : fetchedUserSurname,
                    specialization: userSpecialization ? userSpecialization : fetchedUserSpecialization

                })
            })
            if(res.ok){
                setMessage("Updated profile");
                setMesageColors('success text-success');
            }
        }catch (e){
            setMessage("Error on update!");
            setMesageColors('alert text-danger');
        }
    }
    return (
        <>
            {loadFlag ? (
                <div className="container ">
                    <div className="row">
                        <div className="bg-dark mt-5 p-5 rounded-3 shadow">
                            <p className={messageColors}>{message}</p>
                            <h4 className="text-white text-start">Profile</h4>
                            <form action="post" onSubmit={handle_update_profile} className="text-white text-start">
                                <div>
                                    <label htmlFor="name" className="mt-5 mb-2">Name:</label>
                                    <input type="text" name="name" className="form-control bg-dark text-white" required
                                           defaultValue={fetchedUserName} onChange={(e) => setUserName(e.target.value)}/>
                                </div>
                                <div>
                                    <label htmlFor="surname" className="mt-2 mb-2">Surname:</label>
                                    <input type="text" name="surname" className="form-control bg-dark text-white" required
                                           defaultValue={fetchedUserSurname} onChange={(e) => setUserSurname(e.target.value)}/>
                                </div>
                                <div>
                                    <label htmlFor="specialization" className="mt-2 mb-2">Specialization:</label>
                                    <input type="text" name="specialization" className="form-control bg-dark text-white" required
                                          defaultValue={fetchedUserSpecialization} onChange={(e) => setUserSpecialization(e.target.value)}/>
                                </div>
                                <div>
                                    <label htmlFor="bio" className="mt-2 mb-2">Bio:</label>
                                    <textarea name="bio" cols="30" rows="10" className="form-control bg-dark text-white" required
                                             defaultValue={fetchedUserBio} onChange={(e) => setUserBio(e.target.value)}></textarea>
                                </div>
                                <div>
                                    <input type="submit" className="btn btn-primary mt-2" value="Update"/>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            ) : (<p>Loading...</p>) }

        </>
    )

}

export default Profile;