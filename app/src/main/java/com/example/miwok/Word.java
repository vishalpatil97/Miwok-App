package com.example.miwok;

public class Word {
    private String mMiwokTransition;

    private String mEnglishTransition;

    private int mImageResourceID = NO_IMAGE_CONSTANT;

    private int mAudioResourceID;

    private static final int NO_IMAGE_CONSTANT = -1;

    public Word(String mEnglishTransition, String mMiwokTransition, int audioId) {
        this.mMiwokTransition = mMiwokTransition;
        this.mEnglishTransition = mEnglishTransition;
        mAudioResourceID = audioId;
    }

    public Word(String mEnglishTransition, String mMiwokTransition, int mImageID, int audioId) {
        this.mEnglishTransition = mEnglishTransition;
        this.mMiwokTransition = mMiwokTransition;
        mImageResourceID = mImageID;
        mAudioResourceID = audioId;
    }

    // Return if image is there or not
    public boolean hasImage(){
        return mImageResourceID != NO_IMAGE_CONSTANT;
    }

    public int getImageResourceID() {
        return mImageResourceID;
    }

    public String getMiwokTransition() {
        return mMiwokTransition;
    }

    public String getEnglishTransition() {
        return mEnglishTransition;
    }

    public int getAudioResourceID() {
        return mAudioResourceID;
    }

    @Override
    public String toString() {
        return "Word{" +
                "mMiwokTransition='" + mMiwokTransition + '\'' +
                ", mEnglishTransition='" + mEnglishTransition + '\'' +
                ", mImageResourceID=" + mImageResourceID +
                ", mAudioResourceID=" + mAudioResourceID +
                '}';
    }
}
