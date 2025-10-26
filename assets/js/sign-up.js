const signUpBtn = document.querySelector('.signUp-btn');
const nameRegex = /^[A-Za-zÀ-ỹ\s]+$/;
const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
const phoneRegex = /^0\d{9}$/;
const passwordRegex = /^[A-Za-z0-9!@#$%^&*]{6,}$/;
signUpBtn.addEventListener('click', (e) => {
    e.preventDefault();
    //Lấy giá trị từ các input
    const lName = document.getElementById('lname').value.trim();
    const fName = document.getElementById('fname').value.trim();
    const email = document.getElementById('userAndEmail').value.trim();
    const phoneNumber = document.getElementById('phoneNumber').value.trim();
    const password = document.getElementById('password').value.trim();
    const confirmPassword = document.getElementById('confirmPassword').value.trim();

    //Clear old error
    ['lname', 'fname', 'userAndEmail', 'phoneNumber', 'password', 'confirmPassword'].forEach(clearError);

    //Check empty condition
    if(!lName || !fName || !email || !phoneNumber || !password || !confirmPassword){
        if (!lName) showError('lname', "Không được để trống");
        if (!fName) showError('fname', "Không được để trống");
        if (!email) showError('userAndEmail', "Không được để trống");
        if (!phoneNumber) showError('phoneNumber', "Không được để trống");
        if (!password) showError('password', "Không được để trống");
        if (!confirmPassword) showError('confirmPassword', "Không được để trống");
        return;
    }
    const isValidLnameAndFname = checkValidName(lName, fName);
    const isValidEmail = checkValidEmail(email);
    const isValidPhoneNumber = checkValidPhoneNumber(phoneNumber);
    const isValidPassword = checkValidPassword(password);
    
    //Check valid lname and fname
    if (!isValidLnameAndFname) {
         showError('lname', "Họ không được chứa kí tự đặc biệt");
         showError('fname', "Tên không được chứa kí tự đặc biệt");
    }
    //Check valid email
    if(!isValidEmail){
        showError('userAndEmail', "Sai định dạng email");
    }
    //Check valid phone number
    if(!isValidPhoneNumber){
        showError('phoneNumber', "Số điện thoại của bạn chưa đúng") ;
    }
    //Check valid password
    if(!isValidPassword){
        showError('password', "Mật khẩu của bạn chưa bảo đảm an toàn");
    }
    if (password !== confirmPassword) {
        showError('confirmPassword', "Mật khẩu xác nhận không khớp") ;
    }

    //Check remain errors
    const errors = document.querySelectorAll('.error-msg');
    let hasError = false;
    for(const span of errors){
        if(span.textContent !== ""){
            hasError = true;
            break;
        }
    }
    if (hasError) return;

    alert("Đăng ký thành công")
    window.location.href = "../pages/login.html";
});

function checkValidName(lname, fname){
    if(!nameRegex.test(lname) || !nameRegex.test(fname)){
        return false;
    }
    else{
        return true;
    }
}
function checkValidEmail(email){
    if (!emailRegex.test(email)) {
        return false;
    }
    else{
        return true;
    }
}
function checkValidPhoneNumber(phoneNumber){
    if(!phoneRegex.test(phoneNumber)){
        return false;
    }
    else{
        return true;
    }
}
function checkValidPassword(password){
    if(!passwordRegex.test(password)){
        return false;
    }
    else{
        return true;
    }
}
function showError(inputId, message) {
    const inputDiv = document.getElementById(inputId).parentElement;
    const errorSpan = inputDiv.querySelector('.error-msg');
    errorSpan.textContent = message;
}
function clearError(inputId){
    const inputDiv = document.getElementById(inputId).parentElement;
    const errorSpan = inputDiv.querySelector('.error-msg');
    errorSpan.textContent = "";
}
//Xử lí lỗi khi người dùng nhập từng ô input.
document.querySelectorAll('input').forEach(input => {
    input.addEventListener('input', () => {
        const id = input.id;
        const value = input.value.trim();

        clearError(id);

        if(id === 'lname' || id === 'fname'){
            if(value && !nameRegex.test(value)) showError(id, "Không được chứa ký tự đặc biệt hoặc số");
        }
        if(id === 'userAndEmail'){
            if(value && !emailRegex.test(value)) showError(id, "Email không hợp lệ");
        }
        if(id === 'phoneNumber'){
            if(value && !phoneRegex.test(value)) showError(id, "Số điện thoại không hợp lệ");
        }
        if(id === 'password'){
            if(value && !passwordRegex.test(value)) showError(id, "Mật khẩu yếu hoặc thiếu ký tự đặc biệt.");
        }
        if(id === 'confirmPassword'){
            const password = document.getElementById('password').value.trim();
            if(value && value !== password) showError(id, "Không khớp với mật khẩu trên");
        }
    });
});