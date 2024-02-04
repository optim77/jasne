import './styles/App.css';
import Header from "./components/Header";
import Main from "./components/Main";
import Footer from "./components/Elements/Footer";
import AuthProvider from "./components/Services/AuthProvider";

function App() {
  return (
    <div className="App">

        <AuthProvider>
            <Header />
        </AuthProvider>
        {/*<Footer />*/}
    </div>
  );
}

export default App;
