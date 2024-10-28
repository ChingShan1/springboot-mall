## 專案介紹
模擬電商網站的後端程式功能，包括
1. 使用者登入和註冊。
2. 商品相關, ex:建立商品、查詢商品、更新商品資訊...等。
3. 訂單功能, ex:建立選擇購買商品的定單...等。
4. 管理使用者的權限。
## 專案技術
1. 使用java-springBoot的架構來時做後端WEB應用程式。
2. 使用java-security的架構來做認證和授權，認證是否有帳號密碼可以來做登入和授權哪些使用者可以存取資料的權限來作為資訊安全的保護。
3. 使用Oauth2的技術，讓第三方應用程式使用者登入, ex: 使用Google帳號登入。
## 基本架構圖
![基本架構圖](https://github.com/user-attachments/assets/7d26d65e-b3bd-4df0-903c-b86de9d61f20)

## 使用技術介紹_java-secuity
### 使用者註冊和登入
#### 1. 使用者註冊和登入_oAuth2
* 功能介紹: 讓使用者可以透過google帳號註冊，並賦予"normal-user"權限
* 執行步驟:
a. 點選Goole登入按鈕 ![oauth2-login](https://github.com/user-attachments/assets/12b3ebbd-f6e4-4aa8-b2c0-8b3b750cbc5c) <br/>
b. 選擇使用帳號 ![oauth2-login-1](https://github.com/user-attachments/assets/8b8a10a2-2e40-4043-9a8e-017c6803e01d)
c. 登入成功畫面 ![oauth2-login-role](https://github.com/user-attachments/assets/facb7bda-6222-4573-9faa-3bc766f79a08)


#### 2. 使用者註冊_普通使用者
* POST: http://localhost:8080/users/register
* 功能介紹: 讓使用者可以註冊帳號，並賦予"normal-user"權限
* Input: 帳號資料
* Ouput: 註冊成功畫面
![register](https://github.com/user-attachments/assets/ec87fb1c-9c6e-4cd7-baff-ca42bc321d9b)

#### 3. 使用者登入_普通使用者
* POST: http://localhost:8080/userLogin
* 功能介紹: 讓使用者可以登入帳號
* Input: 帳號資料
* Ouput: 登入成功畫面和獲得的權限
a. 登入帳號 ![login_normaluser_2](https://github.com/user-attachments/assets/dc6957a0-981d-4084-8aac-7be8bddf363d) <br/>
b. 登入成功畫面 ![login_normaluser_1](https://github.com/user-attachments/assets/190467c6-15be-48ab-8d34-89e96a3620ec)






## 使用技術介紹_java-springboot
### 商品相關功能
#### 1. 建立商品
* POST: http://localhost:8080/products
* 功能介紹: 讓管理者可以建立商品的相關資訊
* 權限: "ADMIN"
* Input: 商品資料
* Ouput: 建立好的商品資料  
![createproduct](https://github.com/user-attachments/assets/72db5392-7774-4b89-baa0-1d77c3560656)  
#### 2. 刪掉商品
* DELETE: http://localhost:8080/products/{productId}
* 功能介紹: 讓管理者可以刪掉商品的相關資訊
* 權限: "ADMIN"
* Input: 商品資料的編號
* Ouput: 回傳204刪除成功訊息
![deleteprduct](https://github.com/user-attachments/assets/c1677301-494e-4c26-9210-ff117f25c209)
#### 3. 更新商品
* PUT: http://localhost:8080/products/{productId}
* 功能介紹: 讓管理者可以更新商品的相關資訊
* 權限: "ADMIN"
* Input: 商品資料的編號
* Ouput: 更改好的商品資料
![updateprodcut](https://github.com/user-attachments/assets/68b0e3dd-0bb7-48a9-9844-937a0145b0bd)
#### 4. 獲取商品資料
##### a. 獲取商品資料_個人
* GET: http://localhost:8080/products/{productId}
* 功能介紹: 讓使用者可以查詢商品的相關資訊
* 權限: "ADMIN"、"normal-user"
* Input: 商品資料的編號
* Ouput: 查詢的商品資料
![getprpdcut](https://github.com/user-attachments/assets/44d2edee-0a0f-43b0-b900-ce1cfd825117)
##### b. 獲取商品資料_全部
* GET: http://localhost:8080/products
* 功能介紹: 讓使用者可以查詢所以商品的相關資訊
* 權限: "ADMIN"、"normal-user"
* Input: 無
* Ouput: 查詢的所以商品資料
![getallproduct](https://github.com/user-attachments/assets/60283cf1-3047-4165-9950-ab2a8f87a37f)
### 訂單相關功能
#### 1. 建立訂單
* POST: <br/>
a. 普通使用者: http://localhost:8080/users/{userId}/orders <br/>
b. oauth2使用者: http://localhost:8080/oauth2_users/{userId}/orders
* 功能介紹: 讓使用者可以建立購買商品的訂單相關資訊
* 權限: "ADMIN"、"normal-user"
* Input: 商品資料
* Ouput: 購買商品的訂單資料
![createorder_oauth2](https://github.com/user-attachments/assets/1e4e2907-b91c-4238-8514-c570b980e0a6)
#### 2. 查詢訂單
* Get: <br/>
a. 普通使用者: http://localhost:8080/users/{userId}/orders <br/>
b. oauth2使用者: http://localhost:8080/oauth2_users/{userId}/orders
* 功能介紹: 讓使用者可以查詢自己商品的訂單相關資訊
* 權限: "ADMIN"、"normal-user"
* Input: 無
* Ouput: 建立好的商品資料
![getorder](https://github.com/user-attachments/assets/3acf81c1-b266-4179-8761-41e421a4fb7b)







