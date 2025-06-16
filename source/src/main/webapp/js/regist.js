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