import { Link, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";

function UsersAdminPanel() {
    const navigate = useNavigate();
    const [isLoaded, setIsLoaded] = useState(false);
    const [users, setUsers] = useState([]);
    const [message, setMessage] = useState('');
    const [page, setPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);

    const getPanel = async (page) => {
        try {
            let res = await fetch(`${process.env.REACT_APP_API_ADDRESS}/admin/users?page=` + page, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Accept": "application/json"
                },
                body: JSON.stringify({
                    token: localStorage.getItem('jasne_adm')
                })
            });
            if (res.ok) {
                setIsLoaded(true);
                res.json().then(data => {
                    setUsers(data.content);
                    setTotalPages(data.totalPages);
                });
            } else {
                setMessage('Something went wrong');
            }
        } catch (e) {
            navigate("/");
        }
    };

    useEffect(() => {
        if (!localStorage.getItem("jasne_adm")){navigate("/");}
        getPanel(page);
    }, [page]);

    const handleBlockUser = async (id) => {
        try {
            let res = await fetch(`${process.env.REACT_APP_API_ADDRESS}/admin/block`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Accept": "application/json"
                },
                body: JSON.stringify({
                    token: localStorage.getItem("jasne_adm"),
                    user_id: id
                })
            });
            if (res.ok) {
                res.json().then(data => {
                    if (data === 1) {
                        setUsers(prevUsers => prevUsers.map(user =>
                            user.id === id ? { ...user, enabled: false } : user
                        ));
                        setMessage('User was blocked');
                    } else {
                        setUsers(prevUsers => prevUsers.map(user =>
                            user.id === id ? { ...user, enabled: true } : user
                        ));
                        setMessage('User was unblocked');
                    }
                });
            }
        } catch (e) {
            setMessage('Something went wrong with blocking/unblocking user');
        }
    };

    const handlePageChange = (newPage) => {
        if (newPage >= 0 && newPage < totalPages) {
            setPage(newPage);
        }
    };

    return (
        <>
            {isLoaded ? (
                <div>
                    <div className="container ">
                        <div className="row">
                            <div className="bg-dark mt-5 p-5 rounded-3 shadow">
                                <h4 className="text-white text-start">Users posts</h4>
                                <hr className="bg-white text-white" />
                                <p className="text-white p-3">{message}</p>
                                <div className="text-white text-start">
                                    <div className="row">
                                        {users && users.length > 0 ? users.map(user => (
                                            <div key={user.id} className="container mt-3">
                                                <div className="row">
                                                    <p>Username: <Link className="text-decoration-none fw-bold text-white" to={"/user/" + user.id}>{user.username}</Link></p>
                                                    <p>Name: {user.name}</p>
                                                    <p>Surname: {user.surname}</p>
                                                    <p>Specialization: {user.specialization}</p>
                                                    <p>Role: {user.role}</p>
                                                    <p>
                                                        Created at:&nbsp;
                                                        {
                                                            new Date(user.created_at).toLocaleString('en-US', {
                                                                year: 'numeric',
                                                                month: '2-digit',
                                                                day: '2-digit'
                                                            }).split(',')[0]
                                                        }
                                                    </p>

                                                    <p>Enabled: {user.enabled ? 'NO' : 'YES'}</p>
                                                    <button
                                                        onClick={() => handleBlockUser(user.id)}
                                                        className={user.enabled ? 'btn btn-primary mb-2' : 'btn btn-danger mb-2'}
                                                    >
                                                        {user.enabled ? 'Unblock' : 'Block'}
                                                    </button>
                                                </div>
                                            </div>
                                        )) : <p>No users yet</p>}
                                        <div className="container">
                                            <div className="row justify-content-center">
                                                <button
                                                    onClick={() => handlePageChange(page - 1)}
                                                    disabled={page <= 0}
                                                    className="col-2 btn btn-danger"
                                                >
                                                    Previous
                                                </button>
                                                <span className="col-8 text-center"> Page {page + 1} of {totalPages} </span>
                                                <button
                                                    onClick={() => handlePageChange(page + 1)}
                                                    disabled={page >= totalPages - 1}
                                                    className="col-2 btn btn-primary"
                                                >
                                                    Next
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            ) : (
                <p>Loading</p>
            )}
        </>
    );
}

export default UsersAdminPanel;
