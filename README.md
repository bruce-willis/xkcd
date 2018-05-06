# xkcd
Best app to view xkcd comics

<p align="center">
    <img src="app\src\main\ic_launcher-web.png" alt="logo">
</p>

* [Installation](#installation)
* [TO-DO list](#to-do-list)
* [Used libraries](#used-libraries)
* [Used materials / Thanks](#used-materials-thanks)
* [Screenshots & gifs](#screenshots-and-gifs)

### Installation
* Download `apk` from [release](https://github.com/bruce-willis/xkcd/releases) section
* build manually:
```
git clone https://github.com/bruce-willis/xkcd.git
cd xkcd
./gradlew jar
```

**Note**: Make sure your *Android SDK* has the *Android Support Repository* installed, and that your `$ANDROID_HOME` environment
variable is pointing at the SDK or add a `local.properties` file in the root project with a `sdk.dir=...` line.

### TO-DO list
TO-DO list with status can be found in [`projects`](https://github.com/bruce-willis/xkcd/projects/1) section

### Used libraries  
* [Glide](https://github.com/bumptech/glide) for working with images. You can read more about reasons to pick it [here](https://github.com/bruce-willis/xkcd/issues/1)
* [PhotoView](https://github.com/chrisbanes/PhotoView) for zooming comics
* [Espresso](https://developer.android.com/training/testing/espresso/) for UI testing. Basic UI tests can be found [here](https://github.com/bruce-willis/xkcd/blob/develop/app/src/androidTest/java/combruce_willis/github/xkcd/RecyclerViewInstrumentedTest.java)

### Used materials / Thanks
* #### Glide
    * [Simple guide](https://ledron.github.io/RecyclerView/) to create `recyclerview` with Michael Jordan
    * [How to](https://stackoverflow.com/a/32887693) choose right context for glide
    * [Why we should use hardware bitmaps](https://bumptech.github.io/glide/doc/hardwarebitmaps.html#why-should-we-use-hardware-bitmaps)
    * [Continuous Shared Element Transitions: RecyclerView to ViewPager](https://android-developers.googleblog.com/2018/02/continuous-shared-element-transitions.html)
    * [Show pictures with highest res possible](https://github.com/bumptech/glide/issues/531#issuecomment-120012741)

* #### PhotoView
    * [Work around](https://github.com/chrisbanes/PhotoView/commit/92a2a281134ceddc6e402ba4a83cc91180db8115) for [annoying bug with viewgroup](https://github.com/chrisbanes/PhotoView#issues-with-viewgroups)

* #### Icons&Splashcreen
    * [Official documentation](https://developer.android.com/studio/write/image-asset-studio#create-adaptive) for creating adaptive and legacy launcher icons
    * [Android asset studio](https://romannurik.github.io/AndroidAssetStudio/icons-launcher.html) for possibility to add `score` effect. Difference with default image:
    ![difference](https://i.imgur.com/N9SvwTh.png)
    * [Create](https://android.jlelse.eu/launch-screen-in-android-the-right-way-aca7e8c31f52) splash screen in the right way (without unnecessary activity)

* #### Espresso
    * [Interact with recyclerview list items](https://developer.android.com/training/testing/espresso/lists#recycler-view-list-items)
    * [Testing ViewPager with Espresso](https://stackoverflow.com/a/29872541)

* #### Toolbar
    * [How to display android actionbar title without truncation occurring](https://stackoverflow.com/a/39713727)
    * [ActionBar Up button like the back button](https://alvinalexander.com/source-code/android/how-get-android-actionbar-backup-button-work-android-back-button)


### Screenshots and gifs
* main screen  
![main](https://i.imgur.com/rFuuPI5.png?1)
* fast opening & caching  
![open](https://thumbs.gfycat.com/ShamelessThatBufflehead-size_restricted.gif)  
* fast scrolling  
![scroll](https://thumbs.gfycat.com/FaintLongCivet-size_restricted.gif)
* Continuous Shared Element Transitions (specially slow down animation speed)  
![transition](https://thumbs.gfycat.com/AdoredScornfulInsect-size_restricted.gif)

* zooming  

![zooming](https://thumbs.gfycat.com/LoneMetallicBluetickcoonhound-size_restricted.gif)
* swiping  

![swiping](https://thumbs.gfycat.com/ScratchyInfatuatedDoe-size_restricted.gif)
* change recyclerview position (attention that recyclerview scrolled down)

![change](https://thumbs.gfycat.com/ShockedPalatableAnophelesmosquito-size_restricted.gif)
* sharing  

![sharing](https://thumbs.gfycat.com/PiercingDazzlingGermanspaniel-size_restricted.gif)