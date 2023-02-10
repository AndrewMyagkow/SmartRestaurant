package com.example.smartrestaurant.Admin.ui.writebook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartrestaurant.databinding.FragmentWbookBinding;

public class WBookFragment extends Fragment {

    private FragmentWbookBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        WBookViewModel wbookViewModel =
                new ViewModelProvider(this).get(WBookViewModel.class);

        binding = FragmentWbookBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textWbook;
       wbookViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}