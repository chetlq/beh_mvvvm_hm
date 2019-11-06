package com.elegion.test.behancer.ui.profile;

import android.databinding.BaseObservable;
import android.databinding.BindingMethods;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableChar;
import android.databinding.ObservableField;
import android.provider.ContactsContract;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.elegion.test.behancer.common.RefreshOwner;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.model.user.User;
import com.elegion.test.behancer.utils.ApiUtils;
import com.elegion.test.behancer.utils.DateUtils;
import com.squareup.picasso.Picasso;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.Url;

public class ProfileViewModel extends BaseObservable {
    public static final String PROFILE_KEY = "PROFILE_KEY";

    private String mUsername;
    private Storage mStorage;
    private Disposable mDisposable;
    private Binding binding = new Binding();


   private ObservableBoolean mIsErrorVisible = new ObservableBoolean(false);
//    private ObservableField<User> mUser= new ObservableField<>();


    public ProfileViewModel(Storage mStorage, String mUsername) {
        this.mStorage = mStorage;
        this.mUsername = mUsername;
    }


    public void loadProfile() {
        mDisposable = ApiUtils.getApiService().getUserInfo(mUsername)
                .subscribeOn(Schedulers.io())
                .doOnSuccess(response -> mStorage.insertUser(response))
                .onErrorReturn(throwable ->
                        ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ?
                                mStorage.getUser(mUsername) :
                                null)
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(
                        response -> {
                            mIsErrorVisible.set(false);
                            binding.bind(response.getUser());


                        },e->{
                            System.out.println("Error:***"+e.getMessage());
                            System.out.println("Error:***"+e.getStackTrace());
                            mIsErrorVisible.set(true);
                        });

//                        throwable -> mIsErrorVisible.set(true));
    }


    public ObservableBoolean getIsErrorVisible() {
        return mIsErrorVisible;
    }

    public Binding getBinding() {
        return binding;
    }

    //    public ObservableField<User> getUser() {
//        return mUser;
//    }

//    public SwipeRefreshLayout.OnRefreshListener getOnRefreshListener() {
//        return OnRefreshListener;
//    }

//    public String getImageUrl() {
//        return mImageUrl;
//    }
//
//    public String getProfileName() {
//        return mProfileName;
//
//
//    }
//    public String getProfileCreatedOn() {
//        return mProfileCreatedOn;
//    }
//
//    public String getProfileLocation() {
//        return mProfileLocation;
//    }

    public void dispatchDetach() {
        mStorage = null;
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }
//    private void bind(User user) {
//
//        picasso.load(user.getImage().getPhotoUrl())
//                .fit()
//                .into(mProfileImage);
//        mProfileName.setText(user.getDisplayName());
//        mProfileCreatedOn.setText(DateUtils.format(user.getCreatedOn()));
//        mProfileLocation.setText(user.getLocation());
//    }

}
