# Jangan tampilkan warning jika ada class yang tidak dikenali
-dontwarn com.example.core.**

# Pertahankan class Resource dan subclass-nya
-keep class com.example.core.data.Resource { *; }
-keep class com.example.core.data.Resource$* { *; }

# Pastikan model Movie tetap ada
-keep class com.example.core.domain.model.Movie { *; }

-keep class com.example.core.domain.usecase.** { *; }
-keep class com.example.core.domain.repository.** { *; }

# Pastikan Gson tetap berjalan untuk serialisasi/deserialisasi
-keep class com.google.gson.** { *; }
-dontwarn com.google.gson.**
-keepattributes *Annotation*

-keep class com.example.core.di.** { *; }
-keepclassmembers class * {
    @org.koin.core.annotation.* <fields>;
}

-keep class com.example.core.ui.MovieAdapter { *; }


# Pastikan Retrofit tetap berjalan tanpa obfuscation pada method penting
-keep class retrofit2.** { *; }
-dontwarn retrofit2.**
-keep class okhttp3.** { *; }
-dontwarn okhttp3.**

# Pastikan semua model yang digunakan Gson tidak ter-obfuscate
-keep class com.example.core.model.** { *; }

# Jika menggunakan Retrofit dengan Gson Converter, jaga konstruktor tanpa argumen
-keepclassmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

# Pastikan semua interface API Retrofit tetap bisa digunakan
-keep interface com.example.core.network.** { *; }

# Pastikan semua metode dalam API tetap dipertahankan
-keepclassmembers,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}
