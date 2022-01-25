//检查表单是否填写正确，并提示错误信息。
function checkForm() {
    //通过document获取控件的属性值。
    let userName = document.getElementById("userName").value;
    let password = document.getElementById("password").value;
    let userTypes = document.getElementById("userTypes");
    let index = userTypes.selectedIndex; // 选中索引
    let userType = userTypes.options[index].value;
    if (userName == null || userName == "") {
        document.getElementById("error").innerHTML = "用户名不能为空";
        return false;
    }
    if (password == null || password == "") {
        document.getElementById("error").innerHTML = "密码不能为空";
        return false;
    }
    if (userType == null || userType == "") {
        document.getElementById("error").innerHTML = "请选择用户类型";
        return false;
    }
    return true;
}