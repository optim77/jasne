import { Link, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import Popup from "reactjs-popup";
import 'reactjs-popup/dist/index.css';

function AdminCategoryPanel() {
    const navigate = useNavigate();
    const [isLoaded, setIsLoaded] = useState(false);
    const [categories, setCategories] = useState([]);
    const [message, setMessage] = useState('');
    const [popupOpen, setPopupOpen] = useState(false);
    const [addPopupOpen, setAddPopupOpen] = useState(false);
    const [selectedCategory, setSelectedCategory] = useState(null);
    const [newCategoryName, setNewCategoryName] = useState('');
    const [newCategory, setNewCategory] = useState('');

    const getCategories = async () => {
        let res = await fetch("http://localhost:8080/category/all", {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
            },
        });
        if (res.ok) {
            setIsLoaded(true);
            res.json().then(data => {
                setCategories(data);
                setIsLoaded(true);
            });
        } else {
            navigate("/");
        }
    }

    useEffect(() => {
        getCategories();
        console.log(categories);
    }, []);

    const openPopup = (category) => {
        setSelectedCategory(category);
        setNewCategoryName(category.name);
        setPopupOpen(true);
    };

    const closePopup = () => {
        setPopupOpen(false);
        setSelectedCategory(null);
    };

    const openAddPopup = () => {
        setAddPopupOpen(true);
    };

    const closeAddPopup = () => {
        setAddPopupOpen(false);
        setNewCategory('');
    };

    const handleCategoryNameChange = (e) => {
        setNewCategoryName(e.target.value);
    };

    const handleNewCategoryChange = (e) => {
        setNewCategory(e.target.value);
    };

    const handleUpdateCategory = async () => {
        if (selectedCategory) {
            let res = await fetch(`http://localhost:8080/admin/category/update`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    id: selectedCategory.id,
                    name: newCategoryName,
                    token: localStorage.getItem("jasne_adm")
                })
            });
            if (res.ok) {
                setMessage("Category updated successfully");
                getCategories();
                closePopup();
            } else {
                setMessage("Failed to update category");
            }
        }
    };

    const handleCreateCategory = async () => {
        let res = await fetch(`http://localhost:8080/admin/category/create`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                name: newCategory,
                token: localStorage.getItem("jasne_adm")
            })
        });
        if (res.ok) {
            setMessage("Category created successfully");
            getCategories();
            closeAddPopup();
        } else {
            setMessage("Failed to create category");
        }
    };

    const handleDelete = async(id) => {
        // eslint-disable-next-line no-restricted-globals
        if(confirm("By deleting a category you delete all posts belonging to that category!")){
            let res = await fetch("http://localhost:8080/admin/category/delete", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    category_id: id,
                    token: localStorage.getItem("jasne_adm")
                })
            })
            if (res.ok){
                setMessage("Category deleted successfully");
            }else{
                setMessage("Something went wrong with deleting category");
            }
        }
    }

    return (
        <>
            {isLoaded ? (
                <>
                    <div className="container">
                        <div className="row">
                            <div className="bg-dark mt-5 p-5 rounded-3 shadow">
                                <h4 className="text-white text-start">Categories</h4>
                                <hr className="bg-white text-white" />
                                <p className="text-white">{message}</p>
                                <div className="text-white text-start">
                                    <div className="row">
                                        <button className="btn btn-success" onClick={openAddPopup}>Add</button>
                                        {categories && categories.length > 0 ? categories.map(content => (
                                            <div className="container mt-3" key={content.id}>
                                                <div className="row">
                                                    <p>{content.name}</p>
                                                    <button onClick={() => openPopup(content)} className="btn btn-primary m-1 col-1">Edit</button>
                                                    <button onClick={() => handleDelete(content.id)}  className="btn btn-danger m-1 col-1">Delete</button>
                                                </div>
                                            </div>
                                        )) : <p>No categories yet</p>}
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <Popup open={popupOpen} className="bg-dark" closeOnDocumentClick onClose={closePopup} modal>
                        <div className="modal-dialog bg-dark text-white p-2">
                            <div className="modal-content">
                                <div className="modal-header">
                                    <h5 className="modal-title">Edit Category</h5>
                                    <button type="button" className="btn-close bg-white p-2 mb-2" aria-label="Close" onClick={closePopup}></button>
                                </div>
                                <div className="modal-body">
                                    <input
                                        type="text"
                                        className="form-control w-75 m-2"
                                        value={newCategoryName}
                                        onChange={handleCategoryNameChange}
                                    />
                                </div>
                                <div className="modal-footer justify-content-start">
                                    <button className="btn btn-primary m-2" onClick={handleUpdateCategory}>Update</button>
                                    <button className="btn btn-secondary m-2" onClick={closePopup}>Cancel</button>
                                </div>
                            </div>
                        </div>
                    </Popup>

                    <Popup open={addPopupOpen} className="bg-dark" closeOnDocumentClick onClose={closeAddPopup} modal>
                        <div className="modal-dialog bg-dark text-white p-2">
                            <div className="modal-content">
                                <div className="modal-header">
                                    <h5 className="modal-title">Add Category</h5>
                                    <button type="button" className="btn-close bg-white p-2 mb-2" aria-label="Close" onClick={closeAddPopup}></button>
                                </div>
                                <div className="modal-body">
                                    <input
                                        type="text"
                                        className="form-control w-75 m-2"
                                        value={newCategory}
                                        onChange={handleNewCategoryChange}
                                    />
                                </div>
                                <div className="modal-footer justify-content-start">
                                    <button className="btn btn-primary m-2" onClick={handleCreateCategory}>Create</button>
                                    <button className="btn btn-secondary m-2" onClick={closeAddPopup}>Cancel</button>
                                </div>
                            </div>
                        </div>
                    </Popup>
                </>
            ) : (
                <><p>Loading</p></>
            )}
        </>
    );
}

export default AdminCategoryPanel;
