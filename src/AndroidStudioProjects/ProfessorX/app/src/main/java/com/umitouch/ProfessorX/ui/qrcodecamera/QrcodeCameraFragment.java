package com.umitouch.ProfessorX.ui.qrcodecamera;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umitouch.ProfessorX.R;

public class QrcodeCameraFragment extends Fragment {

    private QrcodeCameraViewModel mViewModel;

    public static QrcodeCameraFragment newInstance() {
        return new QrcodeCameraFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.qrcode_camera_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(QrcodeCameraViewModel.class);
        // TODO: Use the ViewModel
    }

}
