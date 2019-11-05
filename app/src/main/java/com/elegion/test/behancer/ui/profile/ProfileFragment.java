package com.elegion.test.behancer.ui.profile;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.elegion.test.behancer.R;
import com.elegion.test.behancer.common.RefreshOwner;
import com.elegion.test.behancer.common.Refreshable;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.model.user.User;
import com.elegion.test.behancer.databinding.ProfileBinding;
import com.elegion.test.behancer.databinding.ProjectsBinding;
import com.elegion.test.behancer.databinding.VProfileBinding;
import com.elegion.test.behancer.utils.ApiUtils;
import com.elegion.test.behancer.utils.DateUtils;
import com.squareup.picasso.Picasso;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Vladislav Falzan.
 */

public class ProfileFragment extends Fragment  {
    public static final String PROFILE_KEY = "PROFILE_KEY";
    private ProfileViewModel mProfileViewModel;
    private String mUsername;
    Storage mStorage;

    public static ProfileFragment newInstance(Bundle args) {
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Picasso.with(getContext());
        mStorage = context instanceof Storage.StorageOwner ? ((Storage.StorageOwner) context).obtainStorage() : null;

//        ProfileBinding binding =  ProfileBinding.inflate(inflater, container, false);
//        binding.setVm(mProfileViewModel);
//        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mUsername = getArguments().getString(PROFILE_KEY);
            mProfileViewModel = new ProfileViewModel(mStorage,mUsername);
            mProfileViewModel.loadProfile();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ProfileBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fr_profile, container, false);

        View view = binding.getRoot();
        //here data must be an instance of the class MarsDataProvider
        binding.setVm(mProfileViewModel);
        return view;

    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        if (getActivity() != null) {
            getActivity().setTitle(mUsername);
        }
        Log.i("TAG",mUsername+"");

    }




    @Override
    public void onDetach() {
        mProfileViewModel.dispatchDetach();
        super.onDetach();
        super.onDetach();
    }
}
