const signInBtn = document.querySelector('.signIn-btn')
const users = [
    { email: "admin@gmail.com", password: "123456" },
    { email: "user@gmail.com", password: "abcdef" }
];
signInBtn.addEventListener('click', () => {
    //Lấy dữ liệu từ input email và password
    const email = document.getElementById('userAndEmail').value.trim();
    const password = document.getElementById('password').value.trim();

    //Check empty
    if (!email || !password) {
        alert("Vui lòng nhập đầy đủ email và mật khẩu!")
        return;
    }
    //Check valid email and Alert if wrong format
    const isValid = checkValidEmail(email);
    if(!isValid) return;
    //Change page
    //Giả lập đăng nhập đúng (ví dụ tạm thời)
    const found = users.find(u => u.email === email && u.password === password);
    if(isValid && found){
        alert("Đăng nhập thành công")
        window.location.href = "../pages/home.html";
    }
    else{
        alert("Sai email hoặc mật khẩu");
    }
});
function checkValidEmail(email){
    email = email.trim();
    if (!email.includes('@')) {
        alert("Email phải có @")
        return false;
    }
    const parts = email.split('@');
    //Email sẽ có 2 phần là trước @: username và sau @: domain. 
    //Nếu khi tách theo @ mà thấy có hơn 2 phần thì đó không phải là 1 email hợp lệ.
    if(parts.length !== 2){
        alert("Sai định dạng email (phải có 1 dấu @, ví dụ: nguyenvana123@gmail.com)");
        return false;
    }
    const [username, domain] = parts;
    //Nếu username rỗng hoặc có chứa khoảng trắng -> Không hợp lệ
    if(!username || username.includes(' ')){
        alert("Sai định dạng email (VD nguyenvana123@gmail.com)");
        return false;
    }
    //Nếu domain không có dấu . -> Không hợp lệ. Vd về domain hợp lệ: gmail.com
    if(!domain.includes('.')){
        alert("Phần domain của bạn không đúng (vd abc@gmail.com)");
        return false;
    }
    const domainPart = domain.split('.');
    const lastPart = domainPart[domainPart.length - 1]; //Lấy phần cuối sau dấu .
    if(lastPart.length < 2){
        alert("Bạn còn thiếu phần sau .")
        return false;
    }
    return true;
}


