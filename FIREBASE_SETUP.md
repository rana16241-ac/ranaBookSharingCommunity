# 🔥 Firebase Setup Guide for Book Sharing Community

This guide will help you set up Firebase Realtime Database for the Book Sharing Community Android app.

## 📋 Prerequisites
- Google Account
- Android Studio installed
- Project cloned from GitHub

## 🚀 Step-by-Step Setup

### Step 1: Create Firebase Project

1. Go to [Firebase Console](https://console.firebase.google.com/)
2. Click **"Add project"** or **"Create a project"**
3. Enter project name: `BookSharingCommunity` (or any name you prefer)
4. Click **Continue**
5. (Optional) Enable Google Analytics
6. Click **Create project**
7. Wait for project creation to complete
8. Click **Continue**

### Step 2: Add Android App to Firebase

1. In Firebase Console, click the **Android icon** to add an Android app
2. Enter the following details:
   - **Android package name**: `com.booksharing.community`
   - **App nickname** (optional): Book Sharing Community
   - **Debug signing certificate SHA-1** (optional for now)
3. Click **Register app**

### Step 3: Download google-services.json

1. Click **Download google-services.json**
2. Save the file to your computer
3. In Android Studio, switch to **Project** view (from the dropdown at top-left)
4. Copy `google-services.json` to the `app/` directory
   ```
   BookSharingCommunity/
   └── app/
       └── google-services.json  ← Place file here
   ```
5. Click **Next** in Firebase Console
6. Click **Next** again (dependencies already added)
7. Click **Continue to console**

### Step 4: Enable Realtime Database

1. In Firebase Console, click **Realtime Database** from the left menu
2. Click **Create Database**
3. Select a location (choose closest to your users):
   - United States
   - Europe
   - Asia
4. Click **Next**

### Step 5: Set Database Rules (Test Mode)

1. Select **Start in test mode** (for development)
2. Click **Enable**

**Important**: Test mode rules allow anyone to read/write. For production, use secure rules:

```json
{
  "rules": {
    "Books": {
      ".read": true,
      ".write": true
    }
  }
}
```

### Step 6: Get Database URL

1. In Realtime Database page, you'll see your database URL at the top
2. It looks like: `https://your-project-id-default-rtdb.firebaseio.com/`
3. **Copy this URL** - you'll need it in the next step

### Step 7: Update Android Code

1. Open `HomeFragment.java` in Android Studio
2. Find this line (around line 67):
   ```java
   databaseReference = FirebaseDatabase.getInstance().getReference("Books");
   ```
3. Replace with:
   ```java
   databaseReference = FirebaseDatabase.getInstance("YOUR_DATABASE_URL").getReference("Books");
   ```
4. Replace `YOUR_DATABASE_URL` with your actual Firebase URL

5. Open `UploadFragment.java`
6. Find this line (around line 50):
   ```java
   databaseReference = FirebaseDatabase.getInstance().getReference("Books");
   ```
7. Replace with:
   ```java
   databaseReference = FirebaseDatabase.getInstance("YOUR_DATABASE_URL").getReference("Books");
   ```

**Example:**
```java
databaseReference = FirebaseDatabase.getInstance("https://booksharingcommunity-12345-default-rtdb.firebaseio.com/").getReference("Books");
```

### Step 8: Sync and Build

1. In Android Studio, click **File → Sync Project with Gradle Files**
2. Wait for sync to complete
3. Click **Build → Rebuild Project**
4. Ensure no errors

### Step 9: Run the App

1. Connect an Android device or start an emulator
2. Click the **Run** button (green play icon) or press **Shift + F10**
3. Select your device
4. Wait for app to install and launch

### Step 10: Test the App

1. **Upload a Book**:
   - Tap the **Upload** tab
   - Fill in book details:
     - Title: "Clean Code"
     - Author: "Robert C. Martin"
     - Description: "A handbook of agile software craftsmanship"
     - Image URL: `https://images-na.ssl-images-amazon.com/images/I/41xShlnTZTL._SX376_BO1,204,203,200_.jpg`
   - Tap **Upload Book**
   - You should see "Book uploaded successfully!"

2. **View Books**:
   - Tap the **Home** tab
   - You should see the book you just uploaded
   - The list updates in real-time

3. **Check Firebase Console**:
   - Go back to Firebase Console
   - Click **Realtime Database**
   - You should see your data under `Books/`

## 🔒 Security Rules (Production)

For production apps, use these secure rules:

```json
{
  "rules": {
    "Books": {
      ".read": true,
      ".write": "auth != null",
      "$bookId": {
        ".validate": "newData.hasChildren(['title', 'author', 'description', 'imageUrl'])"
      }
    }
  }
}
```

This requires authentication for writing but allows public reading.

## 📊 Database Structure

Your Firebase Realtime Database will have this structure:

```
Books/
  └── -NXxXxXxXxXxXxXxX/
       ├── id: "-NXxXxXxXxXxXxXxX"
       ├── title: "Clean Code"
       ├── author: "Robert C. Martin"
       ├── description: "A handbook of agile software craftsmanship"
       └── imageUrl: "https://example.com/image.jpg"
  └── -NXyYyYyYyYyYyYyY/
       ├── id: "-NXyYyYyYyYyYyYyY"
       ├── title: "Effective Java"
       ├── author: "Joshua Bloch"
       ├── description: "Best practices for Java programming"
       └── imageUrl: "https://example.com/image2.jpg"
```

## 🐛 Troubleshooting

### Issue: "Default FirebaseApp is not initialized"
**Solution**: Ensure `google-services.json` is in the `app/` directory and sync Gradle.

### Issue: "Permission denied"
**Solution**: Check Firebase Database Rules. For testing, use:
```json
{
  "rules": {
    ".read": true,
    ".write": true
  }
}
```

### Issue: Books not appearing
**Solution**: 
1. Check internet connection
2. Verify Firebase URL is correct
3. Check Logcat for errors in Android Studio

### Issue: Build errors
**Solution**:
1. Clean project: `Build → Clean Project`
2. Rebuild: `Build → Rebuild Project`
3. Invalidate caches: `File → Invalidate Caches / Restart`

## 📱 Sample Book Data for Testing

Use these sample books to test your app:

**Book 1:**
- Title: Clean Code
- Author: Robert C. Martin
- Description: A handbook of agile software craftsmanship
- Image URL: `https://images-na.ssl-images-amazon.com/images/I/41xShlnTZTL._SX376_BO1,204,203,200_.jpg`

**Book 2:**
- Title: Effective Java
- Author: Joshua Bloch
- Description: Best practices for the Java programming language
- Image URL: `https://images-na.ssl-images-amazon.com/images/I/41-+g1a2Y1L._SX376_BO1,204,203,200_.jpg`

**Book 3:**
- Title: Design Patterns
- Author: Gang of Four
- Description: Elements of reusable object-oriented software
- Image URL: `https://images-na.ssl-images-amazon.com/images/I/51szD9HC9pL._SX395_BO1,204,203,200_.jpg`

## ✅ Verification Checklist

- [ ] Firebase project created
- [ ] Android app added to Firebase
- [ ] `google-services.json` downloaded and placed in `app/` directory
- [ ] Realtime Database enabled
- [ ] Database rules set (test mode for development)
- [ ] Database URL copied
- [ ] Code updated with Firebase URL in `HomeFragment.java`
- [ ] Code updated with Firebase URL in `UploadFragment.java`
- [ ] Gradle synced successfully
- [ ] Project builds without errors
- [ ] App runs on device/emulator
- [ ] Can upload books successfully
- [ ] Books appear in Home screen
- [ ] Data visible in Firebase Console

## 🎓 For Exam/Submission

Make sure to:
1. ✅ Include `google-services.json` in your submission (or provide instructions)
2. ✅ Document your Firebase URL in comments
3. ✅ Test all features before submission
4. ✅ Take screenshots of working app
5. ✅ Verify real-time updates work

## 📞 Support

If you encounter issues:
1. Check [Firebase Documentation](https://firebase.google.com/docs/database/android/start)
2. Review Android Studio Logcat for errors
3. Verify all steps were followed correctly

---

**Developer**: RANA MUHAMMAD AWAIS  
**Email**: rana.16241.ac@iqra.edu.pk  
**University**: Iqra University
