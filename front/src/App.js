import './styles/App.css';
import Header from "./components/Header";
import AuthProvider from "./components/Services/AuthProvider";
import AuthProviderAdmin from "./components/Admin/AuthProviderAdmin";

function App() {
  return (
    <div className="App">

        <AuthProvider>
            <AuthProviderAdmin>
                <Header />
            </AuthProviderAdmin>
        </AuthProvider>
        {/*<Footer />*/}
    </div>
  );
}

export default App;
