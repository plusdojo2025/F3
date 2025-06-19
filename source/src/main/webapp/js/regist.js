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
	let password1 = document.getElementById("pw_input").value;
	let password2 = document.getElementById("pw_re_input").value;

	if (password1 === password2 && password1 !== "") {
		alert("パスワードが確認されました。フォームを送信できます！");
		return true;
	} else {
		alert("パスワードが一致しません。もう一度入力してください。");
		return false;
	}
}

function exceed30Name() {
	let name = document.getElementById("name_input").value;

	if ([...name].length() > 30) {
		alart("ユーザー名は30文字以下です。");
	}
}

function valiCheck() {
	let password = document.getElementById("pw_input").value;
	let rePassword = document.getElementById("pw_re_input").value;

	if (password === rePassword) {
		document.getElementById("pw_input").style.background = "white";
		document.getElementById("pw_re_input").style.background = "white";
	} else {
		document.getElementById("pw_input").style.background = "pink";
		document.getElementById("pw_re_input").style.background = "pink";
	}

	if (password.length < 8) {
		document.getElementById("pw_input").style.background = "pink";
	}
	
	if (password.length < 8) {
		document.getElementById("pw_input").style.background = "pink";
	}
}

function validateForm() {
	let email = document.forms["registForm"]["mail_input"].value;
	let password = document.forms["registForm"]["pw_input"].value;
	let rePassword = document.forms["registForm"]["pw_re_input"].value;
	let name = document.forms["registForm"]["name_input"].value;
	let regionId = document.forms["registForm"]["region_input"].value;
	document.registForm.pw_input.style.background = "pink";
	if (name === "") {
		alert("ユーザー名を入力してください。");
		return false;
	}
	if(user.)
	if (email === "") {
		alert("メールアドレスを入力してください。");
		return false;
	}
	if (password === "") {
		alert("パスワードを入力してください。");
		return false;
	}
	if (password.length < 8) {
		alert("パスワードは8文字以上必要です。");
		return false;
	}
	if (password !== rePassword) {
		alert("パスワード入力欄とパスワード再入力欄に同じ文字列が入力されていません。もしくはどちらか一方の入力欄が未入力となっております。");
		return false;
	}
	if (regionId === "1") {
		alert("居住地域を入力してください。");
		return false;
	}
	alert(regionId);
	return true;
}