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
		alert("ユーザー名は30文字以下です。");
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
	//document.registForm.pw_input.style.background = "pink";
	
	const nameRegex = /^[\w!@#\$%\^&\*\(\)\-=\+_\[\]\{\},\.]{1,30}$/;
	const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
	
	function containsZenkaku(str) {
    	return /[^\u0020-\u007E]/.test(str);
 	 }
 	 
	const errors = [];
	if (name === "") {
		errors.push("ユーザー名を入力してください。");
	}
	if (email === "") {
		errors.push("メールアドレスを入力してください。");
	}
	if (password === "") {
		errors.push("パスワードを入力してください。");
	}
	if (password.length < 8) {
		errors.push("パスワードは8文字以上必要です。");
	}
	if (password !== rePassword) {
		errors.push("パスワード入力欄とパスワード再入力欄に同じ文字列が入力されていません。もしくはどちらか一方の入力欄が未入力となっております。");
	}
	if (regionId === "0") {
		errors.push("居住地域を入力してください。");
	}
	if (!nameRegex.test(name)) {
    	errors.push("・ユーザー名は全半角英数字と記号のみ、30文字以内で入力してください。");
  	}
  	if (!emailRegex.test(email)) {
    	errors.push("・正しいメールアドレスの形式で入力してください。");
  	} else {
    	const domain = email.split('@')[1];
    	if (domain && containsZenkaku(domain)) {
     	 	errors.push("・メールアドレスの @ 以降に全角文字は使用できません（半角英数字で入力してください）。");
    	}
  	}
  	//最終チェック
	if (errors.length > 0) {
    	alert("以下の内容を確認してください：\n" + errors.join("\n"));
    	e.preventDefault();
    	return false;
    }
    return true;
}