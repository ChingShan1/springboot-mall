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
![createproduct](https://hackmd.io/_uploads/BkwNoVQx1l.jpg)
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
* Input: 商品資料的編號
* Ouput: 查詢的商品資料
![getprpdcut](https://hackmd.io/_uploads/SyfeyHmxkg.jpg)
##### b. 獲取商品資料_全部
* GET: http://localhost:8080/products
* 功能介紹: 讓使用者可以查詢所以商品的相關資訊
* Input: 商品資料
* Ouput: 查詢的所以商品資料
![getallproduct](https://hackmd.io/_uploads/rJtCkSXeke.jpg)




