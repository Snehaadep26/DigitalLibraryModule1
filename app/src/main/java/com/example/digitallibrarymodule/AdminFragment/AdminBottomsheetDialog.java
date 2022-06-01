package com.example.digitallibrarymodule.AdminFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.digitallibrarymodule.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AdminBottomsheetDialog extends BottomSheetDialogFragment {



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.admin_edit,container,false);
        return v;
    }
}
