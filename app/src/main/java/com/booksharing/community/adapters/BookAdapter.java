package com.booksharing.community.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.booksharing.community.R;
import com.booksharing.community.models.Book;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView Adapter for displaying books
 * Handles book list display with CardView items
 * 
 * @author RANA MUHAMMAD AWAIS
 * @email rana.16241.ac@iqra.edu.pk
 */
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private Context context;
    private List<Book> bookList;

    /**
     * Constructor
     * 
     * @param context Application context
     */
    public BookAdapter(Context context) {
        this.context = context;
        this.bookList = new ArrayList<>();
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = bookList.get(position);

        // Set book details
        holder.titleTextView.setText(book.getTitle());
        holder.authorTextView.setText("By " + book.getAuthor());
        holder.descriptionTextView.setText(book.getDescription());

        // Load image using Glide
        if (book.getImageUrl() != null && !book.getImageUrl().isEmpty()) {
            Glide.with(context)
                    .load(book.getImageUrl())
                    .placeholder(R.drawable.ic_book_placeholder)
                    .error(R.drawable.ic_book_placeholder)
                    .into(holder.bookImageView);
        } else {
            holder.bookImageView.setImageResource(R.drawable.ic_book_placeholder);
        }
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    /**
     * Update the book list and refresh RecyclerView
     * 
     * @param newBooks New list of books from Firebase
     */
    public void setBooks(List<Book> newBooks) {
        this.bookList = newBooks;
        notifyDataSetChanged();
    }

    /**
     * ViewHolder class for book items
     */
    public static class BookViewHolder extends RecyclerView.ViewHolder {
        ImageView bookImageView;
        TextView titleTextView;
        TextView authorTextView;
        TextView descriptionTextView;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            bookImageView = itemView.findViewById(R.id.book_image);
            titleTextView = itemView.findViewById(R.id.book_title);
            authorTextView = itemView.findViewById(R.id.book_author);
            descriptionTextView = itemView.findViewById(R.id.book_description);
        }
    }
}
