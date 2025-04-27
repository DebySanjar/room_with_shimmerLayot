Loyihaning qisqacha tavsifi

ShimmerLayout – yuklanayotgan ma'lumotlar uchun animatsion effekt (loading indicator) ishlatiladi.

TabLayout – erkak va ayol kategoriyalarini ko‘rsatadi.

Ilova asosiy funksiyalari
Dialog orqali foydalanuvchi Ism va Jins ma'lumotini kiritadi.

Ma'lumotlar Room Database orqali saqlanadi.

Shimmer animatsiyasi ma'lumot yuklangunga qadar ko‘rsatiladi.

Ma'lumotlar jinsiga qarab fragmentlarga ajratilib, ViewPager2 yordamida ko‘rsatiladi.

CoordinatorLayout va CollapsingToolbarLayout bilan interfeys silliq va zamonaviy ko‘rinishga ega.

Texnologiyalar va kutubxonalar
Android Kotlin

Room Database

ViewPager2

TabLayout

Material Components

ShimmerLayout (Facebook Shimmer)

CoordinatorLayout

CollapsingToolbarLayout

DialogFragment

``` gradle
//Kerakli kutubxonalar
implementation "androidx.room:room-runtime:2.5.2"
kapt "androidx.room:room-compiler:2.5.2"
implementation "androidx.viewpager2:viewpager2:1.1.0-beta02"
implementation "com.facebook.shimmer:shimmer:0.5.0"

```
Sync Project qiling.

Loyihani qurib (build) ishga tushiring.

Arxitektura
MVVM (Model-View-ViewModel) printsiplariga yaqin struktura.

Room: Mahalliy ma'lumotlar saqlash uchun.

ViewBinding: XML view elementlarga xavfsiz va oson ulanish uchun.


Skreenshotlar
![image](https://github.com/user-attachments/assets/7e6237bd-4c9b-4f5b-94cd-c8425c790a67)

