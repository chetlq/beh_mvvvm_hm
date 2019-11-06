package com.elegion.test.behancer.ui.profile;

import com.elegion.test.behancer.data.model.user.User;
import com.elegion.test.behancer.utils.DateUtils;

public class ProfileBindViewModel {
    private String mImageUrl;
    private String mProfileName;
    private String mProfileCreatedOn;
    private String mProfileLocation;

    public ProfileBindViewModel(User user) {
        mImageUrl = user.getImage().getPhotoUrl();
        mProfileName = user.getDisplayName();
        mProfileCreatedOn = DateUtils.format(user.getCreatedOn());
        mProfileLocation = user.getLocation();
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getProfileName() {
        return mProfileName;
    }

    public String getProfileCreatedOn() {
        return mProfileCreatedOn;
    }

    public String getProfileLocation() {
        return mProfileLocation;
    }
}
