# xkcd
Best app to view xkcd comics

### TO-DO list
<!-- Task              | Status
------------------|------------------- -->
TO-DO list with status can be found in [`projects`](https://github.com/bruce-willis/xkcd/projects/1) section

### Used libraries  
* [Glide](https://github.com/bumptech/glide) for working with images. You can read more about reasons to pick it [here](https://github.com/bruce-willis/xkcd/issues/1)
* [PhotoView](https://github.com/chrisbanes/PhotoView) for zooming comics
* [Espresso](https://developer.android.com/training/testing/espresso/) for UI testing

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
