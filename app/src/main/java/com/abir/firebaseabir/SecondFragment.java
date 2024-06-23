package com.abir.firebaseabir;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.abir.firebaseabir.databinding.FragmentSecondBinding;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SecondFragment extends Fragment {

    private static final String TAG = "FIRESTORE";
    private FragmentSecondBinding binding;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSecond.setOnClickListener(v ->
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment)
        );
        createDatabase();
    }

    public void createDatabase() {
        binding.buttonSaveUser.setOnClickListener(v -> {
            if (!binding.firestoreUserTittle.getText().toString().isEmpty() && !binding.firestoreUserFirstName.getText().toString().isEmpty() && !binding.firestoreUserLastName.getText().toString().isEmpty() && !binding.firestoreUserAge.getText().toString().isEmpty()) {
                Map<String, Object> user = new HashMap<>();
                user.put("first", binding.firestoreUserFirstName.getText().toString());
                user.put("last", binding.firestoreUserLastName.getText().toString());
                user.put("born", binding.firestoreUserAge.getText().toString());
                db.collection("users").document(binding.firestoreUserTittle.getText().toString())
                        .set(user)
                        .addOnSuccessListener(aVoid -> Log.d(TAG, "DocumentSnapshot successfully written!"))
                        .addOnFailureListener(e -> Log.w(TAG, "Error writing document", e));
            } else {
                binding.textviewSecond.setText("Please fill all fields");
            }
        });
        // Add a new document with a generated ID
        /*db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });*/

        /*db.collection("users")
                .document("Muhaiminur").set(user);*/


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}