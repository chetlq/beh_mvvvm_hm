package com.elegion.test.behancer.ui.profile;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;
import com.elegion.test.behancer.data.model.user.User;
import com.elegion.test.behancer.utils.DateUtils;

public class Binding extends BaseObservable {

    private String mImageUrl;
    private String mProfileName;
    private String mProfileCreatedOn;
    private String mProfileLocation;


    public void bind(User user){
        mImageUrl = user.getImage().getPhotoUrl();
        mProfileName = user.getDisplayName();
        mProfileCreatedOn = DateUtils.format(user.getCreatedOn());
        mProfileLocation = user.getLocation();
        notifyPropertyChanged(BR.imageUrl);
        notifyPropertyChanged(BR.profileName);
        notifyPropertyChanged(BR.profileCreatedOn);
        notifyPropertyChanged(BR.profileLocation);
    }

    @Bindable
    public String getImageUrl() {
        return mImageUrl;
    }

    @Bindable
    public String getProfileName() {
        return mProfileName;
    }

    @Bindable
    public String getProfileCreatedOn() {
        return mProfileCreatedOn;
    }

    @Bindable
    public String getProfileLocation() {
        return mProfileLocation;
    }
}
