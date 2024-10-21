## 專案介紹
模擬電商網站的後端程式功能，包括
1. 使用者登入和註冊。
2. 商品相關, ex:建立商品、查詢商品、更新商品資訊...等。
3. 訂單功能, ex:建立選擇購買商品的定單...等。
4. 管理使用者的權限。
## 專案技術
1. 使用java-springBoot的架構來時做後端WEB應用程式
2. 使用java-secuity的架構，來作資訊安全的保護
3. 使用Oauth2的技術，讓第三方應用程式使用者登入, ex: 使用Google帳號登入

## 使用技術介紹_java-springboot
### 商品相關功能
#### 1. 建立商品
* POST: http://localhost:8080/products
* 功能介紹: 讓管理者可以建立商品的相關資訊
* Input: 商品資料
* Ouput: 建立好的商品資料
![createproduct](https://hackmd.io/uploads/BkwNoVQx1l.jpg)
#### 2. 刪掉商品
* DELETE: http://localhost:8080/products/{productId}
* 功能介紹: 讓管理者可以刪掉商品的相關資訊
* Input: 商品資料的編號
* Ouput: 回傳204刪除成功訊息
![deleteprduct](https://hackmd.io/_uploads/Sk4HnEQl1g.jpg)
#### 3. 更新商品
* PUT: http://localhost:8080/products/{productId}
* 功能介紹: 讓管理者可以更新商品的相關資訊
* Input: 商品資料的編號
* Ouput: 更改好的商品資料
![updateprodcut](https://hackmd.io/_uploads/H1ek0EQlyx.jpg)
#### 4. 獲取商品資料
##### a. 獲取商品資料_個人
* GET: http://localhost:8080/products/{productId}
* 功能介紹: 讓使用者可以查詢商品的相關資訊
* Input: 無
* Ouput: 查詢的商品資料
![getprpdcut](https://hackmd.io/_uploads/SyfeyHmxkg.jpg)
##### b. 獲取商品資料_全部
* GET: http://localhost:8080/products
* 功能介紹: 讓使用者可以查詢所以商品的相關資訊
* Input: 無
* Ouput: 查詢的所以商品資料
![getallproduct](https://hackmd.io/_uploads/rJtCkSXeke.jpg)
### 訂單相關功能
#### 1. 建立訂單
* POST: 
a. 普通使用者: http://localhost:8080/users/{userId}/orders
b. oauth2使用者: http://localhost:8080/oauth2_users/{userId}/orders
* 功能介紹: 讓使用者可以建立購買商品的訂單相關資訊
* Input: 商品資料
* Ouput: 購買商品的訂單資料
![createorder_oauth2](https://hackmd.io/_uploads/HJiemHQeyx.jpg)
#### 2. 建立訂單
* Get: 
a. 普通使用者: http://localhost:8080/users/{userId}/orders
b. oauth2使用者: http://localhost:8080/oauth2_users/{userId}/orders
* 功能介紹: 讓使用者可以查詢自己商品的訂單相關資訊
* Input: 無
* Ouput: 建立好的商品資料
![getorder](https://hackmd.io/_uploads/B1xoQrQgJl.jpg)
## 使用技術介紹_java-secuity
### 使用者註冊和登入
#### 1. 使用者註冊和登入_oAuth2
* 功能介紹: 讓使用者可以透過google帳號註冊
* 執行步驟:
a. 點選Goole登入按鈕 ![oauth2-login](https://hackmd.io/_uploads/SJthLHXl1g.jpg)
b. 選擇使用帳號![oauth2-login-1](https://hackmd.io/_uploads/SyGJtr7xyx.jpg)
c. 登入成功畫面![oauth2-login-2](https://hackmd.io/_uploads/SkXltSQekx.jpg)
#### 2. 使用者註冊_普通使用者
* POST: http://localhost:8080/users/register
* 功能介紹: 讓使用者可以註冊帳號
* Input: 帳號資料
* Ouput: 註冊成功畫面
![register](https://hackmd.io/_uploads/SkFE5BXgJl.jpg)
#### 3. 使用者登入_普通使用者
* POST: http://localhost:8080/userLogin
* 功能介紹: 讓使用者可以登入帳號
* Input: 帳號資料
* Ouput: 登入成功畫面和獲得的權限
![login](https://hackmd.io/_uploads/rygt9HQx1l.jpg)







