package com.quranapp.islamic.utils.chapterInfo;

import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.quranapp.islamic.activities.ActivityChapInfo;
import com.quranapp.islamic.components.quran.QuranMeta;
import com.quranapp.islamic.utils.Log;
import com.quranapp.islamic.utils.reader.TranslUtils;
import com.quranapp.islamic.utils.univ.MessageUtils;

import kotlin.Pair;

public class ChapterInfoJSInterface {
    private final ActivityChapInfo mActivity;

    public ChapterInfoJSInterface(ActivityChapInfo activityChapInfo) {
        mActivity = activityChapInfo;
    }

    @JavascriptInterface
    public void openReference(int chapterNo, int fromVerse, int toVerse) {
        QuranMeta quranMeta = mActivity.mQuranMeta;

        if (!QuranMeta.isChapterValid(chapterNo) || !quranMeta.isVerseRangeValid4Chapter(chapterNo, fromVerse,
                toVerse)) {
            Log.d(chapterNo, fromVerse, toVerse);
            MessageUtils.INSTANCE.showRemovableToast(mActivity, "Could not open references", Toast.LENGTH_LONG);
            return;
        }

        mActivity.showReferenceSingleVerseOrRange(
                TranslUtils.defaultTranslationSlugs(),
                chapterNo, new Pair<>(fromVerse, toVerse)
        );
    }
}
