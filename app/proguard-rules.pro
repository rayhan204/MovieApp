# Keep semua model dari API response agar tidak dihapus saat obfuscation
-keep class com.data.source.remote.response.** { *; }

-keep class com.example.model.** { *; }
-keep class com.example.network.** { *; }

# Keep semua field di dalam model agar tidak diubah namanya
-keepclassmembers class com.data.source.remote.response.** { *; }

# Pastikan Retrofit & Gson tidak error saat deserialisasi
-keepattributes *Annotation*
-keep class kotlin.Metadata { *; }
-keep class com.google.gson.** { *; }
-keep class retrofit2.** { *; }
-keep class okhttp3.** { *; }
-keep class okio.** { *; }

# Hindari obfuscation pada kode Kotlin
-keep class kotlin.** { *; }
-keep class androidx.** { *; }
