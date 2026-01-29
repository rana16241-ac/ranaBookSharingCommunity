# 📥 Installation Guide - Book Sharing Community

## 🎯 Quick Start Guide

Follow these steps to get the Book Sharing Community app running on your machine.

---

## 📋 Prerequisites

Before you begin, ensure you have:

- ✅ **Android Studio** (Latest version - Arctic Fox or newer)
- ✅ **JDK 8 or higher** (Java Development Kit)
- ✅ **Android SDK** (API Level 24 or higher)
- ✅ **Git** (for cloning the repository)
- ✅ **Google Account** (for Firebase setup)
- ✅ **Android Device or Emulator** (for testing)

---

## 🚀 Installation Steps

### Step 1: Clone the Repository

Open terminal/command prompt and run:

```bash
git clone https://github.com/rana16241-ac/ranaBookSharingCommunity.git
```

Or download as ZIP:
```
https://github.com/rana16241-ac/ranaBookSharingCommunity/archive/refs/heads/main.zip
```

### Step 2: Open Project in Android Studio

1. Launch **Android Studio**
2. Click **"Open an Existing Project"**
3. Navigate to the cloned `ranaBookSharingCommunity` folder
4. Click **"OK"**
5. Wait for Android Studio to load the project

### Step 3: Sync Gradle

1. Android Studio will automatically start syncing Gradle
2. If not, click **"Sync Now"** in the notification bar
3. Wait for the sync to complete (may take a few minutes)
4. Ensure no errors appear

### Step 4: Set Up Firebase

**Important**: The app requires Firebase Realtime Database to function.

Follow the detailed guide in **[FIREBASE_SETUP.md](FIREBASE_SETUP.md)**

Quick summary:
1. Create Firebase project at https://console.firebase.google.com/
2. Add Android app with package name: `com.booksharing.community`
3. Download `google-services.json`
4. Place it in the `app/` directory
5. Enable Realtime Database
6. Update Firebase URL in code

### Step 5: Add google-services.json

1. Download `google-services.json` from Firebase Console
2. In Android Studio, switch to **Project** view
3. Copy the file to: `app/google-services.json`
4. Verify the file is in the correct location:
   ```
   BookSharingCommunity/
   └── app/
       └── google-services.json  ← Here
   ```

### Step 6: Update Firebase URL

1. Open `app/src/main/java/com/booksharing/community/fragments/HomeFragment.java`
2. Find line 67:
   ```java
   databaseReference = FirebaseDatabase.getInstance().getReference("Books");
   ```
3. Replace with your Firebase URL:
   ```java
   databaseReference = FirebaseDatabase.getInstance("https://YOUR-PROJECT-ID-default-rtdb.firebaseio.com/").getReference("Books");
   ```

4. Open `app/src/main/java/com/booksharing/community/fragments/UploadFragment.java`
5. Find line 50 and update similarly

### Step 7: Build the Project

1. Click **Build → Clean Project**
2. Wait for cleaning to complete
3. Click **Build → Rebuild Project**
4. Ensure build completes successfully with no errors

### Step 8: Run the App

#### Option A: Using Android Emulator

1. Click **Tools → Device Manager**
2. Create a new virtual device (if none exists):
   - Choose a device (e.g., Pixel 5)
   - Select system image (API 24 or higher)
   - Click **Finish**
3. Start the emulator
4. Click the **Run** button (green play icon) or press **Shift + F10**
5. Select your emulator
6. Wait for app to install and launch

#### Option B: Using Physical Device

1. Enable **Developer Options** on your Android device:
   - Go to **Settings → About Phone**
   - Tap **Build Number** 7 times
2. Enable **USB Debugging**:
   - Go to **Settings → Developer Options**
   - Enable **USB Debugging**
3. Connect device via USB
4. Click the **Run** button
5. Select your device
6. Wait for app to install and launch

---

## ✅ Verification

After installation, verify everything works:

### 1. App Launches
- ✅ App opens without crashes
- ✅ Bottom navigation visible with 3 tabs

### 2. Home Screen
- ✅ "Available Books" title displayed
- ✅ FAB (+ button) visible at bottom-right
- ✅ Empty state message if no books

### 3. Upload Screen
- ✅ All input fields visible
- ✅ Upload button present
- ✅ Can type in all fields

### 4. About Screen
- ✅ App information displayed
- ✅ Developer details shown

### 5. Firebase Connection
- ✅ Try uploading a test book
- ✅ Should see success toast
- ✅ Book should appear in Home screen
- ✅ Check Firebase Console - data should be there

---

## 🧪 Test the App

### Upload a Test Book

1. Tap **Upload** tab
2. Fill in the form:
   - **Title**: Clean Code
   - **Author**: Robert C. Martin
   - **Description**: A handbook of agile software craftsmanship
   - **Image URL**: `https://images-na.ssl-images-amazon.com/images/I/41xShlnTZTL._SX376_BO1,204,203,200_.jpg`
3. Tap **Upload Book**
4. You should see: "Book uploaded successfully!"
5. Tap **Home** tab
6. Your book should appear in the list

---

## 🐛 Troubleshooting

### Issue: Gradle Sync Failed

**Solution**:
1. Check internet connection
2. Click **File → Invalidate Caches / Restart**
3. Try syncing again

### Issue: Build Errors

**Solution**:
1. Ensure JDK is properly configured
2. Check Android SDK is installed
3. Clean and rebuild project
4. Check `build.gradle` files for errors

### Issue: "Default FirebaseApp is not initialized"

**Solution**:
1. Verify `google-services.json` is in `app/` directory
2. Sync Gradle again
3. Clean and rebuild project

### Issue: "Permission denied" from Firebase

**Solution**:
1. Check Firebase Database Rules
2. For testing, use:
   ```json
   {
     "rules": {
       ".read": true,
       ".write": true
     }
   }
   ```

### Issue: Books Not Appearing

**Solution**:
1. Check internet connection
2. Verify Firebase URL is correct in code
3. Check Logcat for errors
4. Verify Firebase Database has data

### Issue: App Crashes on Launch

**Solution**:
1. Check Logcat for error messages
2. Verify all dependencies are synced
3. Ensure minimum SDK version is met (API 24)
4. Clean and rebuild project

---

## 📱 System Requirements

### Minimum Requirements
- **Android Studio**: 4.0 or higher
- **JDK**: 8 or higher
- **Android SDK**: API 24 (Android 7.0) or higher
- **RAM**: 4 GB minimum (8 GB recommended)
- **Disk Space**: 4 GB free space
- **Internet**: Required for Firebase

### Recommended Requirements
- **Android Studio**: Latest version
- **JDK**: 11 or higher
- **Android SDK**: API 33 (Android 13) or higher
- **RAM**: 8 GB or more
- **Disk Space**: 10 GB free space
- **Internet**: Stable broadband connection

---

## 📂 Project Structure

After installation, your project structure should look like:

```
ranaBookSharingCommunity/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/booksharing/community/
│   │       │   ├── activities/
│   │       │   │   └── MainActivity.java
│   │       │   ├── fragments/
│   │       │   │   ├── HomeFragment.java
│   │       │   │   ├── UploadFragment.java
│   │       │   │   └── AboutFragment.java
│   │       │   ├── adapters/
│   │       │   │   └── BookAdapter.java
│   │       │   └── models/
│   │       │       └── Book.java
│   │       ├── res/
│   │       │   ├── layout/
│   │       │   ├── values/
│   │       │   ├── drawable/
│   │       │   └── menu/
│   │       └── AndroidManifest.xml
│   ├── build.gradle
│   └── google-services.json  ← Add this file
├── gradle/
├── build.gradle
├── settings.gradle
└── README.md
```

---

## 🔧 Configuration Files

### build.gradle (app level)

Key dependencies:
```gradle
// Firebase
implementation 'com.google.firebase:firebase-database:21.3.0'

// Material Design
implementation 'com.google.android.material:material:1.11.0'

// RecyclerView & CardView
implementation 'androidx.recyclerview:recyclerview:1.3.2'
implementation 'androidx.cardview:cardview:1.0.0'

// Glide for images
implementation 'com.github.bumptech.glide:glide:4.16.0'
```

### AndroidManifest.xml

Required permissions:
```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

---

## 📚 Additional Resources

- **Firebase Setup**: See [FIREBASE_SETUP.md](FIREBASE_SETUP.md)
- **Exam Checklist**: See [EXAM_CHECKLIST.md](EXAM_CHECKLIST.md)
- **Project Documentation**: See [README.md](README.md)

---

## 🎓 For Students/Examiners

### Quick Setup for Demonstration

1. Clone repository (2 minutes)
2. Open in Android Studio (1 minute)
3. Add `google-services.json` (1 minute)
4. Update Firebase URL (1 minute)
5. Run on emulator/device (2 minutes)

**Total Time**: ~7 minutes

### Pre-configured Firebase (Optional)

If you have a pre-configured Firebase project:
1. Share `google-services.json` with examiner
2. Provide Firebase URL
3. Examiner can run immediately

---

## ✅ Installation Checklist

- [ ] Android Studio installed
- [ ] JDK configured
- [ ] Repository cloned
- [ ] Project opened in Android Studio
- [ ] Gradle synced successfully
- [ ] Firebase project created
- [ ] `google-services.json` added
- [ ] Firebase URL updated in code
- [ ] Project builds without errors
- [ ] App runs on device/emulator
- [ ] Can upload books
- [ ] Books appear in Home screen
- [ ] Firebase Console shows data

---

## 📞 Support

If you encounter issues during installation:

1. Check the troubleshooting section above
2. Review [FIREBASE_SETUP.md](FIREBASE_SETUP.md) for Firebase-specific issues
3. Check Android Studio Logcat for error messages
4. Verify all prerequisites are met

---

## 🎉 Success!

If you can see the app running and can upload/view books, congratulations! 🎊

The installation is complete and the app is ready to use.

---

**Developer**: RANA MUHAMMAD AWAIS  
**Email**: rana.16241.ac@iqra.edu.pk  
**University**: Iqra University  
**Repository**: https://github.com/rana16241-ac/ranaBookSharingCommunity

---

**Last Updated**: January 2026
