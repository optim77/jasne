function AuthChecker(){
    const jwtToken = localStorage.getItem('janse')
    if(jwtToken && jwtToken !== 'null'){
           return true;
    }
    return false;
}
export default AuthChecker;