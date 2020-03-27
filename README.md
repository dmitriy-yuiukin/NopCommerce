# demo.NopCommerce.com

Необходимо реализовать проект по автоматизации тестирования сайта https://demo.nopcommerce.com/ на языке Java, с использованием Selenium WebDriver в качестве фреймворка автоматизации и Maven/Gradle в качестве инструмента автоматической сборки.
 Во время работы над проектом необходимо следовать шаблону PageObject.
 
### Сценарий для автоматизации


1.	Открыть сайт https://demo.nopcommerce.com/
2.	Произвести нажатие на “Computers”
3.	Проверить,  что на открывшейся странице появились названия трех отделов товаров (“Desktops”, “Notebooks”, “Software”)
4.	Произвести нажатие на отдел товаров “Notebooks”
5.	На открывшейся странице выбрать Сортировку (Sort by) - “Price: High to Low”, а также выбрать кол-во отображенных товаров на станице (Display) - “6”
6.	Проверить,  что товары отображаются в долларовом эквиваленте
7.	Выбрать фильтрацию товаров (CPU Type) – “Intel Core i5” и (Memory) – “4 GB”
8.	Произвести нажатие на товар  “HP Spectre XT Pro UltraBook ”
9.	Проверить что товар доступен для покупки (Available) – “In stock”
10.	Установить кол-во товара – “2” и произвести нажатие на “AddToCart”
11.	Произвести нажатие на “Shopping cart” и перейти в корзину
12.	Проверить,  что в корзине находиться один товар
13.	Проверить,  что на странице отображается надпись “Shopping cart”
14.	Установить значение (Gift wrapping)  - “Yes[+10.00]”
15.	В поле “Discount Code” ввести неправильный код и произвести нажатие на “Apply Coupun”
16.	Проверить что под полем ввода “Discount Code” появилась ошибка “The coupon code you entered couldn't be applied to your order”
17.	В поле “Gift Cards” ввести неправильный код и произвести нажатие на “Add Gift Card”
18.	Проверить что под полем ввода “Gift Cards” появилась ошибка “The coupon code you entered couldn't be applied to your order”
19.	В отделе Estimate Shipping: 
в поле “Country” выбрать страну – “United States”, 
в поле “State / province” выбрать штат – “New York”, 
в поле “Zip / postal code” ввести цифровое значение не превышающее 5 цифр
20.	Произвести нажатие на “Estimate Shipping”
21.	Проверить что “Total” совпадает с сумой товара “Total Price” и “Gift wrapping: Yes[+10.00]”
22.	Произвести нажатие на (read) в строке “I agree with the terms of service and I adhere to them unconditionally”
23.	Проверить, что в появившемся окне присутствует текст “Put your conditions of use information here. You can edit this in the admin site.”
24.	Закрыть окно “Terms of Service” и поставить галочку напротив “I agree with the terms of service and I adhere to them unconditionally”
25.	Произвести нажатие на “Checkout”
26.	Проверить, что на открывшейся странице отобразились кнопки (“Checkout as Guest”, “Register”, “Login”)
27.	Произвести нажатие на “Checkout as Guest”
28.	Проверить,  что на открывшейся странице отображается надпись “Checkout”
29.	В подразделе “Build address” проверить,  что стоит галочка на “Ship to the same address”, заполнить все обязательные контактные данные:
в поле “FirstName” ввести  - Jonh, 
в поле “LastName” ввести – Dantenevich, 
в поле “Email” ввести – dantenevich@gmail.com, 
в поле “Country” ввести  – United Kingdom, 
в поле “City” ввести – London, 
в поле “Address” ввести  – 36 Chapel St, 
в поле “ZipCode”ввести  – состоящий из 5 цыфр, 
в поле “PhoneNumber” ввести  – 020 794 60240
Произвести нажатие на “Continue”
30.	Выбрать “Next Day Air ($0.00)” и произвести нажатие на “Continue”
31.	Выбрать “Credit Card” и произвести нажатие на “Continue”
32.	В отделе “Payment Information” 
в поле “Select Credit Card” выбрать “Master Card”,  
в поле “Card HolderName” ввести – Jonh Dantenevich,  
в поле “Card Number”ввести  - “5872 5838 3924 9041”,  
в поле “Expiration Date” ввести - “03 2023”,  
в поле “Card code” ввести  - 123, 
произвести нажатие на “Continue” 
33.	В отделе “Confirm Oreder” произвести нажатие на “Confirm”
34.	В открывшейся странице записать номер заказа (Order Number) в файл “order_number.txt”


