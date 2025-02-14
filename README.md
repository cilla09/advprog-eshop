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