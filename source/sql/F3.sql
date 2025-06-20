CREATE TABLE IdPw (
id VARCHAR(20) PRIMARY KEY, 	/* ID */
pw VARCHAR(20)					/* パスワード */
);

INSERT INTO IdPw VALUES('id', 'password');

SELECT * FROM IdPw;

CREATE TABLE Bc (
number INT PRIMARY KEY AUTO_INCREMENT,	/* 番号 */
name VARCHAR(20),						/* 氏名 */
address VARCHAR(40)						/* 住所 */
);

INSERT INTO Bc VALUES(
0,
'日本太郎',
'東京都千代田区永田町2-3-1'
);

INSERT INTO Bc VALUES(
0,
'東京花子',
'東京都新宿区西新宿2-8-1'
);

INSERT INTO Bc VALUES(
0,
'大阪次郎',
'大阪府大阪市中央区大手前2-1-22'
);

SELECT * FROM Bc;