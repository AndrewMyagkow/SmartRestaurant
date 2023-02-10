package com.example.smartrestaurant.Admin.ui.reserved;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartrestaurant.databinding.FragmentReservedBinding;

public class ReservedFragment extends Fragment {

    private FragmentReservedBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ReservedViewModel reservedViewModel =
                new ViewModelProvider(this).get(ReservedViewModel.class);

        binding = FragmentReservedBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textReserved;
        reservedViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}