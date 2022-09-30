package com.com.jnu.recycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
//import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ArrayList<Book> mainStringSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerViewMain = findViewById(R.id.recycle_view_books);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewMain.setLayoutManager(linearLayoutManager);

        mainStringSet = new ArrayList<>();
        for (int i = 1; i < 100; ++i) {
            Book a = new Book("软件项目管理案例教程（第4版）", R.drawable.book_2);
            mainStringSet.add(a);
            Book b = new Book("创新工程实践", R.drawable.book_no_name);
            mainStringSet.add(b);
            Book c = new Book("信息安全数学基础（第2版）", R.drawable.book_1);
            mainStringSet.add(c);
        }
        MainRecycleViewAdapter mainRecycleViewAdapter = new MainRecycleViewAdapter(mainStringSet);
        recyclerViewMain.setAdapter(mainRecycleViewAdapter);

    }

    public static class Book {
        private final String name;
        private final int imageId;

        public Book(String name, int imageId) {
            this.name = name;
            this.imageId = imageId;
        }

        public String getTitle() {
            return name;
        }

        public int getCoverResourceId() {
            return imageId;
        }
    }

    public ArrayList getListBooks(){
        return mainStringSet;
    }

    public static class MainRecycleViewAdapter extends RecyclerView.Adapter<MainRecycleViewAdapter.ViewHolder> {

        private final ArrayList<Book> localDataSet;

        public static final class ViewHolder extends RecyclerView.ViewHolder {
            private final TextView textView;
            private final ImageView imageview;

            public ViewHolder(View view) {
                super(view);
                textView = view.findViewById(R.id.text_view_book_title);
                imageview = view.findViewById(R.id.image_view_book_cover);
            }
            public TextView getTextView(){
                return textView;
            }
            public ImageView getImageView(){
                return imageview;
            }
        }

        public MainRecycleViewAdapter(ArrayList<Book> dataSet) {
            localDataSet = dataSet;
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.book_list_main, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            Book book = localDataSet.get(position);
            viewHolder.imageview.setImageResource(book.getCoverResourceId());
            viewHolder.textView.setText(book.getTitle());
        }

        @Override
        public int getItemCount() {
            return localDataSet.size();
        }
    }
}

