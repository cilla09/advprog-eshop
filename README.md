# Reflection 1

## Clean Code Principles yang Sudah Saya Terapkan
### 1. Meaningful names
Setiap variabel yang saya buat cukup straightforward dengan kegunaan dari variabel tersebut.
### 2. Layout and formatting
Saya sudah menerapkan layout yang rapi, misalnya menaruh blank line di sela antara fungsi, membuat fungsi-fungsi dan keseluruhan kode lebih mudah dibaca.
### 3. Function
Saya sudah menerapkan nama fungsi yang singkat namun tetap deskriptif.
### 4. DRY (Don't Repeat Yourself)
Saya menggunakan ulang fungsi-fungsi `findById` untuk mengurangi redundancy penulisan kode.

## Secure Coding Practices yang Sudah Saya Terapkan
### 1. Produk menggunakan UUID sebagai Identifier
Menggunakan UUID lebih aman dibanding auto-increment integer, karena tidak bisa ditembus oleh brute force attack.
### 2. Validation produk tidak boleh null
Menerapkan validasi produk yang mau diedit tidak boleh null.
```python
if (productToEdit != null) {
productToEdit.setProductName(product.getProductName());
productToEdit.setProductQuantity(product.getProductQuantity());
}
```

## Kesalahan yang Dapat Diperbaiki
- Menerapkan validasi bahwa input quantity tidak boleh negatif
- Daripada menggunakan `ArrayList` untuk menyimpan data product, lebih baik menggunakan struktur data yang lebih optimal seperti `HashMap` atau `TreeMap`.
- Method GET dan POST dapat diubah menggunakan PUT dan DELETE agar lebih sesuai.

# Reflection 2

## Nomor 1
Setelah membuat unit test, saya merasa aplikasi saya lebih 'matang' dan sesuai ekspektasi saya. Untuk jumlah unit test yang perlu dibuat dalam satu class, menurut saya tidak bisa ditentukan secara pasti berapa.
Namun, hal yang perlu diperhatikan adalah setiap metode harus memiliki minimal satu unit test untuk memastikan metode tersebut berjalan sesuai harapan. Jika ada lebih dari satu, maka lebih baik lagi untuk memperbesar code coverage. Secara umum, 80% code coverage sudah memadai. Code coverage 100% juga belum menjamin aplikasi bebas dari bug.

## Nomor 2
Dalam kasus ini, saya diminta untuk menambah functional test baru, yaitu untuk menguji verifikasi jumlah item dalam list product. Saya membuat class baru yang mempunyai dengan functional test yang sudah saya buat sebelumnya. Menurut saya, hal laf akan berpengaruh terhadap kebersihan kode karena ada duplikasi class yang hampir sama. Dalam hal tersebut, prinsip DRY (Don't Repeat Yourself) tidak terlaksanakan secara ideal. Agar kode menjadi lebih baik, kita dapat menggunakan base test class supaya class functional test yang sudah dibuat sebelumnya dapat digunakan ulang.