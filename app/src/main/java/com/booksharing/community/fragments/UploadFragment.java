package com.booksharing.community.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.booksharing.community.R;
import com.booksharing.community.activities.MainActivity;
import com.booksharing.community.models.Book;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Upload Fragment - Allows users to add new books
 * Validates input and uploads to Firebase
 * 
 * @author RANA MUHAMMAD AWAIS
 * @email rana.16241.ac@iqra.edu.pk
 */
public class UploadFragment extends Fragment {

    private EditText titleEditText;
    private EditText authorEditText;
    private EditText descriptionEditText;
    private EditText imageUrlEditText;
    private Button uploadButton;
    private ProgressBar progressBar;

    private DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upload, container, false);

        // Initialize views
        titleEditText = view.findViewById(R.id.edit_text_title);
        authorEditText = view.findViewById(R.id.edit_text_author);
        descriptionEditText = view.findViewById(R.id.edit_text_description);
        imageUrlEditText = view.findViewById(R.id.edit_text_image_url);
        uploadButton = view.findViewById(R.id.button_upload);
        progressBar = view.findViewById(R.id.progress_bar_upload);

        // Initialize Firebase
        // IMPORTANT: Replace with your Firebase Realtime Database URL
        databaseReference = FirebaseDatabase.getInstance().getReference("Books");

        // Upload button click listener
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadBook();
            }
        });

        return view;
    }

    /**
     * Validate input fields and upload book to Firebase
     */
    private void uploadBook() {
        // Get input values
        String title = titleEditText.getText().toString().trim();
        String author = authorEditText.getText().toString().trim();
        String description = descriptionEditText.getText().toString().trim();
        String imageUrl = imageUrlEditText.getText().toString().trim();

        // Validation - Check if any field is empty
        if (title.isEmpty()) {
            titleEditText.setError("Title is required");
            titleEditText.requestFocus();
            return;
        }

        if (author.isEmpty()) {
            authorEditText.setError("Author is required");
            authorEditText.requestFocus();
            return;
        }

        if (description.isEmpty()) {
            descriptionEditText.setError("Description is required");
            descriptionEditText.requestFocus();
            return;
        }

        if (imageUrl.isEmpty()) {
            imageUrlEditText.setError("Image URL is required");
            imageUrlEditText.requestFocus();
            return;
        }

        // Show progress bar
        progressBar.setVisibility(View.VISIBLE);
        uploadButton.setEnabled(false);

        // Generate unique ID for the book
        String bookId = databaseReference.push().getKey();

        // Create Book object
        Book book = new Book(bookId, title, author, description, imageUrl);

        // Upload to Firebase
        if (bookId != null) {
            databaseReference.child(bookId).setValue(book)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            // Hide progress bar
                            progressBar.setVisibility(View.GONE);
                            uploadButton.setEnabled(true);

                            // Show success message
                            Toast.makeText(getContext(), "Book uploaded successfully!", Toast.LENGTH_SHORT).show();

                            // Clear input fields
                            clearFields();

                            // Navigate back to Home Fragment
                            if (getActivity() instanceof MainActivity) {
                                ((MainActivity) getActivity()).navigateToFragment(new HomeFragment());
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Hide progress bar
                            progressBar.setVisibility(View.GONE);
                            uploadButton.setEnabled(true);

                            // Show error message
                            Toast.makeText(getContext(), "Upload failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            progressBar.setVisibility(View.GONE);
            uploadButton.setEnabled(true);
            Toast.makeText(getContext(), "Failed to generate book ID", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Clear all input fields after successful upload
     */
    private void clearFields() {
        titleEditText.setText("");
        authorEditText.setText("");
        descriptionEditText.setText("");
        imageUrlEditText.setText("");
    }
}
