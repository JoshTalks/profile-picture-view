<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/b/b7/Josh_Talk_Logo.png/480px-Josh_Talk_Logo.png" width="158">

# Profile Picture View for Android

Use this Library to show Profile Picture using url or show initial letter of user's name. This Library is Written in Kotlin.



## Glimpse

<img src="https://github.com/JoshTalks/profile-picture-view/blob/master/ppv_demo_one.gif" width="208">

# Profile Picture View for Android

## Dependency

[![](https://jitpack.io/v/JoshTalks/Profile-Picture-View.svg)](https://jitpack.io/#JoshTalks/Profile-Picture-View)

Add Jitpack in Profile level build.gradle file if you don't have.

```bash
  allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
    
Add Profile Picture View Dependency.

```bash
  implementation 'com.github.JoshTalks:profile-picture-view:1.0.0'
```
## Attributes

| Action             | Code                                                                |
| ----------------- | ------------------------------------------------------------------ |
| Pass Image Url using data binding | app:dynamicImageUrl="@{handler.imageUrl}" |
| Pass User Name using data binding | app:dynamicUsername="@{handler.username}" |
| Change Shape (Rounded is Default) | app:shape |
| Change Corner Radius if using Rounded Shape | app:cornerRadius |
| Change Name Letters) | app:nameLetters |
| Change text color | app:textColor |
| Change text size | app:textSize |
| Change background color | android:background |
| Change scale type | android:scaleType |
| Pass User Name without data binding | app:userName="User Name" |
| Pass User Name without data binding | app:imageUrl="https://upload.wikimedia.org/wikipedia/commons/b/b7/Josh_Talk_Logo.png"|


Note: All these Attributes can be set or changed programmatically as well.
## License

[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)


## 🔗 Follow Josh Talks
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/company/joshtalks/mycompany/)

[![github](https://img.shields.io/github/followers/JoshTalks?style=social)](https://github.com/JoshTalks)

[![youtube](https://img.shields.io/github/followers/JoshTalks?style=social)](https://github.com/JoshTalks)
