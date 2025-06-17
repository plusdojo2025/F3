/**
 * 
 */
'use strict';
function confirmPassword() {
	const password = document.getElementById('pw_input').value;
	const confirmPassword = document.getElementById('pw_re_input').value;
	const errorMsg = document.getElementById('error_msg');

	if (password == confirmPassword) {
		errorMsg.innerText = "";
		return true;
	} else {
		errorMsg.innerText = "パスワードが一致しません";
		return false;
	}
}
function validatePassword() {
	var password1 = document.getElementById("pw_input").value;
	var password2 = document.getElementById("pw_re_input").value;

	if (password1 === password2 && password1 !== "") {
		alert("パスワードが確認されました。フォームを送信できます！");
		return true;
	} else {
		alert("パスワードが一致しません。もう一度入力してください。");
		return false;
	}
}

function exceed30Name() {
	var name = document.getElementById("name_input").value;
	
	if([...name].length() > 30) {
		alart("ユーザー名は30文字以下です。");
	}
}

function validateForm() {
	var email = document.forms["registForm"]["mail_input"].value;
	var password = document.forms["registForm"]["pw_input"].value;
	var name = document.forms["registForm"]["name_input"].value;
	var regionId = document.forms["registForm"]["region_input"].value;
	if (email === "") {
		alert("メールアドレスを入力してください。");
		return false;
	}
	if (password === "") {
		alert("パスワードを入力してください。");
		return false;
	}
	if (name === "") {
		alert("ユーザー名を入力してください。");
		return false;
	}
	if (regionId === 1) {
		alert("居住地域を入力してください。");
		return false;
	}
	alart("aaa");
	return true;
}