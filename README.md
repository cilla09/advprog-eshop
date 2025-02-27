Pemrograman Lanjut (Advanced Programming) 2024/2025 Genap
* Nama    : Priscilla Natanael Surjanto
* NPM     : 2306152153
* Kelas   : Pemrograman Lanjut - A

Link Deployment: [ADVShop](https://advprog-eshop-cilla09.koyeb.app)

<details>
<summary>Module 1</summary>

## Reflection 1

### Clean Code Principles yang Sudah Saya Terapkan
#### 1. Meaningful names
Setiap variabel yang saya buat cukup straightforward dengan kegunaan dari variabel tersebut.
#### 2. Layout and formatting
Saya sudah menerapkan layout yang rapi, misalnya menaruh blank line di sela antara fungsi, membuat fungsi-fungsi dan keseluruhan kode lebih mudah dibaca.
#### 3. Function
Saya sudah menerapkan nama fungsi yang singkat namun tetap deskriptif.
#### 4. DRY (Don't Repeat Yourself)
Saya menggunakan ulang fungsi-fungsi `findById` untuk mengurangi redundancy penulisan kode.

### Secure Coding Practices yang Sudah Saya Terapkan
#### 1. Produk menggunakan UUID sebagai Identifier
Menggunakan UUID lebih aman dibanding auto-increment integer, karena tidak bisa ditembus oleh brute force attack.
#### 2. Validation produk tidak boleh null
Menerapkan validasi produk yang mau diedit tidak boleh null.
```python
if (productToEdit != null) {
    productToEdit.setProductName(product.getProductName());
    productToEdit.setProductQuantity(product.getProductQuantity());
}
```

### Kesalahan yang Dapat Diperbaiki
- Menerapkan validasi bahwa input quantity tidak boleh negatif
- Daripada menggunakan `ArrayList` untuk menyimpan data product, lebih baik menggunakan struktur data yang lebih optimal seperti `HashMap` atau `TreeMap`.
- Method GET dan POST dapat diubah menggunakan PUT dan DELETE agar lebih sesuai.

## Reflection 2

### Nomor 1
Setelah membuat unit test, saya merasa aplikasi saya lebih 'matang' dan sesuai ekspektasi saya. Untuk jumlah unit test yang perlu dibuat dalam satu class, menurut saya tidak bisa ditentukan secara pasti berapa.
Namun, hal yang perlu diperhatikan adalah setiap metode harus memiliki minimal satu unit test untuk memastikan metode tersebut berjalan sesuai harapan. Jika ada lebih dari satu, maka lebih baik lagi untuk memperbesar code coverage. Secara umum, 80% code coverage sudah memadai. Code coverage 100% juga belum menjamin aplikasi bebas dari bug.

### Nomor 2
Dalam kasus ini, saya diminta untuk menambah functional test baru, yaitu untuk menguji verifikasi jumlah item dalam list product. Saya membuat class baru yang mempunyai dengan functional test yang sudah saya buat sebelumnya. Menurut saya, hal laf akan berpengaruh terhadap kebersihan kode karena ada duplikasi class yang hampir sama. Dalam hal tersebut, prinsip DRY (Don't Repeat Yourself) tidak terlaksanakan secara ideal. Agar kode menjadi lebih baik, kita dapat menggunakan base test class supaya class functional test yang sudah dibuat sebelumnya dapat digunakan ulang.

</details>
<details>
<summary>Module 2</summary>

## Reflection
### List of Issues
1. Add a nested comment explaining why this method is empty, throw an UnsupportedOperationException or complete the implementation.
```python
// ProductRepositoryTest.java

@BeforeEach
void setUp() {
}
```
Untuk memperbaikinya, saya menghapus metode tersebut karena ternyata tidak diperlukan. Pengujian pada file ini bersifat independen, masing-masing membuat instance `Product` sendiri dan beroperasi secara mandiri, sehingga tidak memerlukan shared setup dari metode ini.

### About my CI/CD Workflow Implementation
Ya, implementasi saya sudah sesuai dengan definisi Continuous Integration & Continuous Deployment. Untuk CI (implementasi & testing), saya sudah mengimplementasi `ci.yml`, `scorecard.yml`, `sonarcloud.yml` untuk secara otomatis menguji kode saya ketika ada push atau pull request. Sementara untuk CD (deployment & maintenance), saya menggunakan Koyeb yang secara otomatis mendeploy aplikasi saya setiap ada push atau pull request. Keduanya berjalan lancar, sehingga sudah memenuhi definisi CI/CD.

### Code Coverage
![image](https://github.com/user-attachments/assets/9d9dec07-0c81-4c94-a8e8-d169c32ecd98)

</details>
<details>
<summary>Module 3</summary>

## Reflection
### Principles I Applied to My Project

1. **Single Responsibility Principle (SRP)**

SRP merupakan prinsip yang menyatakan bahwa sebuah class Java harus memiliki hanya satu tanggung jawab. Ini diaplikasikan agar kode kita lebih terorganisasi, yaitu class yang dibagi-bagi menjadi lebih kecil membuat kita mudah menavigasi kode dibanding class besar yang tercampur-campur tanggung jawabnya, Selain itu, SRP juga membuat kode membutuhkan testing yang lebih sedikit karena class yang terfokus pada satu tujuan.

Implementasi: memisahkan `CarController` dari `ProductController`, sehingga terdapat 2 class dengan file terpisah, yaitu  `CarController` yang bertanggungjawab mengatur mapping untuk halaman Car dan `ProductController` untuk halaman Product.

2. **Open-Closed Principle (OCP)**

OCP merupakan prinsip yang menyatakan bahwa suatu kode harus terbuka untuk ekstensi, namun tertutup untuk modifikasi. Artinya, kode dasar yang sudah dibuat tidak boleh dimodifikasi karena beresiko kehilangan esensinya/menimbulkan bug baru. Namun, kode dapat ditambahkan fungsi/method baru untuk meng-extend kode tersebut.

Implementasi: penggunaan interface, yaitu `CarService` dan `ProductService`. Dengan adanya interface, bisa dibuat beberapa implementasi berbeda dari kedua interface tersebut tanpa mengubah kode interface itu sendiri.

3. **Liskov Substitution Principle (LSP)**

LSP merupakan prinsip yang menyatakan bahwa subclass harus bisa menggantikan superclass tanpa merubah fungsionalitas program, dan sebaliknya. Artinya, subclass harus memiliki perilaku yang sama dengan superclassnya.

Implementasi: menghapus inheritance dari `ProductController` ke `CarController`, kedua class ini tidak bisa menggantikan satu sama lain karena memiliki perilaku yang berbeda. Yang satu untuk Car, yang satu untuk Product.

4. **Interface Segregation Principle (ISP)**
   
ISP merupakan prinsip yang menyatakan bahwa suatu interface, jika berukuran besar, perlu dipecah menjadi lebih kecil dan terfokus. ISP mirip dengan SRP, sekaligus juga membantu SRP terimplementasi, yaitu dengan interface yang lebih kecil dan terfokus, maka method yang perlu diimplementasikan pada class juga bisa lebih spesifik untuk satu tanggungjawab.

Implementasi: adanya interface `CarService` dan `ProductService`, yang sudah dipecah berdasarkan fokus tanggungjawabnya. `CarService` untuk operasi yang berhubungan dengan Car, dan `ProductService` untuk operasi yang berhubungan dengan Product.

5. **Dependency Inversion Principle (DIP)**

DIP merupakan prinsip yang menyatakan bahwa suatu class harus bergantung pada interface atau abstract class, bukan concrete class/functions. High-level modules (logika bisnis) dan low-level modules (database, APIs) dua-duanya bergantung pada interface. Sehingga, ketika salah satunya ingin diubah, maka tidak akan menimbulkan masalah.

Implementasi: `CarServiceImpl` yang bergantung pada interface `CarService` dan `ProductServiceImpl` yang bergantung pada interface `ProductService`

### Advantages of Applying SOLID in My Project

- Dengan menerapkan SRP, saya lebih mudah menavigasi kode saya pada `CarController` dan `ProductController`. Kedua ini memiliki tanggung jawab yang berbeda, sehingga setelah dipisahkan saya dapat membuat test case untuk masing-masing dengan lebih mudah.
- Dengan menerapkan OCP, kode dasar yang sudah saya buat tidak beresiko timbul bug baru, sehingga kode lebih mudah di-maintain.
- Dengan menerapkan LSP, saya menjaga agar kode saya tidak mudah dirusak inheritance. Saya juga mencegah error yang tidak diinginkan dan membuat kode lebih mudah di-maintain.
- Dengan menerapkan ISP, kode saya menjadi lebih ringkas dan terfokus pada satu tanggung jawab sembari meminimalisir implementasi kode yang tidak diperlukan.
- Dengan menerapkan DIP, saya memiliki fleksibilitas dalam mengganti database atau logika bisnis, karena keduanya tidak bergantung satu sama lain melainkan pada satu sumber yang sama, yaitu interface.

### Disadvantages of Not Applying SOLID in My Project
- Sebelumnya, saya menaruh `CarController` di dalam file `ProductController.java`, membuat file ini menjadi rumit dan tanggung jawabnya dua. Hal ini menyebabkan kode sulit dinavigasi dan testing pun menjadi lebih susah karena dualitas tanggung jawab.
- Jika tidak menerapkan OCP, saya dapat berhadapan dengan banyak bug sebab kode tanpa bug yang sudah saya buat bisa dimodifikasi sehingga berubah menjadi ada bug.
- Sebelumnya, saya menginherit class `ProductController` ke `CarController`. Hal ini menyebabkan `CarController` menginherit atribut dan method yang tidak diperlukan, karena perilaku `CarController` sama sekali berbeda dengan `ProductController`. Dalam jangka waktu panjang, ini bisa menyebabkan bug dan membuat kode lebih sulit di-maintain.
- Jika tidak menerapkan LSP, bisa terdapat banyak implementasi fungsi yang tidak diperlukan dalam suatu class, sehingga membuat kode lebih rumit dan sia-sia.
- Jika tidak menerapkan DIP, akan sulit mengganti database atau logika bisnis karena mereka bergantung satu sama lain, sehingga kurang fleksibilitas saya dalam memodifikasi kode.
</details>
