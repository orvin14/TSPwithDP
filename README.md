# TSPwithDP
Penyelesain Travelling Salesperson Problem dengan Dynamic Programming dalam Bahasa Pemrograman Scala
## Cara Kerja
Program ini menyelesaikan masalah Traveling Salesman Problem (TSP) menggunakan pendekatan Dynamic Programming (DP) dengan bitmasking.
Input
File teks berisi matriks ketetanggaan (adjacency matrix) berupa jarak antar kota.
Matriks berbentuk persegi N x N, di mana N adalah jumlah kota.
Elemen matriks pada baris i kolom j menyatakan jarak dari kota i ke kota j.
Output
Total jarak (cost) minimum yang harus ditempuh untuk mengunjungi semua kota tepat satu kali dan kembali ke kota asal.
Jalur (path) terpendek yang dilalui (urutan kota).
Algoritma
1. Representasi status dengan bitmask  
Setiap subset kota yang sudah dikunjungi direpresentasikan sebagai sebuah integer (bitmask) dengan bit ke-i menyatakan apakah kota i sudah dikunjungi (1) atau belum (0).
Contoh: mask = 01101 (biner) berarti kota ke-0, 2, dan 3 sudah dikunjungi.
2. Memoisasi (DP Table)  
Gunakan tabel memo berukuran [2^N][N] untuk menyimpan hasil minimum cost.
memo(mask)(u) menyimpan biaya minimum untuk rute yang sudah mengunjungi kota sesuai mask dan berada di kota u terakhir.
3. Transisi state  
Dari status memo(mask)(u), coba pindah ke kota v yang belum dikunjungi (v dimana bit ke-v di mask = 0).
Update memo(nextMask)(v) dengan biaya yang lebih kecil jika ditemukan.
nextMask = mask | (1 << v) berarti tambahkan kota v ke dalam subset yang dikunjungi.
4. Inisialisasi  
Mulai dari hanya kota 0 yang sudah dikunjungi: memo(1)(0) = 0
5. Rekonstruksi jalur  
Setelah semua kota dikunjungi (mask = (1 << N) - 1), cari kota terakhir i yang menghasilkan biaya minimum untuk kembali ke kota asal.
Gunakan array parent untuk melacak jalur yang diambil dan membangun path akhir.
## Cara Compile dan Run Menggunakan VSCode
1. Install Scala dari https://www.scala-lang.org/download/
2. Install Java
3. Install extensions Scala (Metals) dan Java Extension Pack di VSCode
4. Pindah ke direktori src
5. Masukkan input ke .txt
6. Compile dengan command
```sh
scala-cli run TSPDP.scala -- <nama-file.txt>
```
Jika kosong defaultnya adalah matrix.txt
## Input dan Output
1. ![image](https://github.com/user-attachments/assets/875fd621-df9d-42bc-a6a9-e9cb8601d71e)
   ![image](https://github.com/user-attachments/assets/d230b92a-e088-4da4-bb7e-b35e002b5982)
2. ![image](https://github.com/user-attachments/assets/e72222bc-5bcb-4fce-a72e-ab99257d3946)
   ![image](https://github.com/user-attachments/assets/099ca748-ec5b-4173-a163-9de5b9b1f1d0)
3. ![image](https://github.com/user-attachments/assets/df7ea9e1-e05e-4e48-a7ca-084cb030c07e)
   ![image](https://github.com/user-attachments/assets/39e542d7-7570-4461-b97c-7218e7edf854)
4. ![image](https://github.com/user-attachments/assets/a9f315db-293d-4c32-8b9a-d71859182ae3)
   ![image](https://github.com/user-attachments/assets/cdc11167-a39f-4947-bf14-9276a2a1520a)
5. ![image](https://github.com/user-attachments/assets/18bd9f66-2ecf-4ea5-ae7b-fb37ad417e2c)
   ![image](https://github.com/user-attachments/assets/69975186-7d0d-44a2-abef-0f89816d821a)
6. ![image](https://github.com/user-attachments/assets/2f658f25-878b-4cc5-9c57-702f740676c0)
   ![image](https://github.com/user-attachments/assets/41e80996-7672-4302-9733-d9689823b6d2)

 



