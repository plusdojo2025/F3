/**
 * 
 */

'use strict';
function validateForm() {
	var email = document.forms["loginForm"]["email_input"].value;
	var password = document.forms["loginForm"]["pw_input"].value;

	if (email === "") {
		alert("メールアドレスを入力してください。");
		return false;
	}
	if (password === "") {
		alert("パスワードを入力してください。");
		return false;
	}
	return true;
}