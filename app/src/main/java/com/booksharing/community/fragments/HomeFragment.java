package com.booksharing.community.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.booksharing.community.R;
import com.booksharing.community.activities.MainActivity;
import com.booksharing.community.adapters.BookAdapter;
import com.booksharing.community.models.Book;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Home Fragment - Displays list of available books
 * Uses RecyclerView with real-time Firebase updates
 * 
 * @author RANA MUHAMMAD AWAIS
 * @email rana.16241.ac@iqra.edu.pk
 */
public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private BookAdapter bookAdapter;
    private FloatingActionButton fab;
    private ProgressBar progressBar;
    private TextView emptyTextView;

    private DatabaseReference databaseReference;
    private List<Book> bookList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize views
        recyclerView = view.findViewById(R.id.recycler_view_books);
        fab = view.findViewById(R.id.fab_add_book);
        progressBar = view.findViewById(R.id.progress_bar);
        emptyTextView = view.findViewById(R.id.empty_text_view);

        // Setup RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        bookAdapter = new BookAdapter(getContext());
        recyclerView.setAdapter(bookAdapter);

        // Initialize Firebase
        // IMPORTANT: Replace with your Firebase Realtime Database URL
        // Example: https://your-project-id-default-rtdb.firebaseio.com/
        databaseReference = FirebaseDatabase.getInstance().getReference("Books");

        // Initialize book list
        bookList = new ArrayList<>();

        // FAB click listener - Navigate to Upload Fragment
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).navigateToFragment(new UploadFragment());
                }
            }
        });

        // Load books from Firebase
        loadBooks();

        return view;
    }

    /**
     * Load books from Firebase Realtime Database
     * Uses ValueEventListener for real-time updates
     */
    private void loadBooks() {
        progressBar.setVisibility(View.VISIBLE);
        emptyTextView.setVisibility(View.GONE);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bookList.clear();

                if (snapshot.exists()) {
                    // Iterate through all books
                    for (DataSnapshot bookSnapshot : snapshot.getChildren()) {
                        Book book = bookSnapshot.getValue(Book.class);
                        if (book != null) {
                            book.setId(bookSnapshot.getKey());
                            bookList.add(book);
                        }
                    }

                    // Update adapter
                    bookAdapter.setBooks(bookList);

                    // Show/hide empty state
                    if (bookList.isEmpty()) {
                        emptyTextView.setVisibility(View.VISIBLE);
                        emptyTextView.setText("No books available.\nTap + to add a book!");
                    } else {
                        emptyTextView.setVisibility(View.GONE);
                    }
                } else {
                    emptyTextView.setVisibility(View.VISIBLE);
                    emptyTextView.setText("No books available.\nTap + to add a book!");
                }

                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Failed to load books: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
