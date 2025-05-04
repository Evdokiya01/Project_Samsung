package com.example.project_samsung;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ImageViewHolder> {

    private List<String> imageUrls; // Список URL картинок

    public MyAdapter(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        String imageUrl = imageUrls.get(position);
        Picasso.get().load(imageUrl).into(holder.imageViewItem);
    }

    @Override
    public int getItemCount() {
        return imageUrls.size();
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewItem;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewItem = itemView.findViewById(R.id.imageViewItem);
        }
    }

    public void updateData(List<String> newImageUrls) {
        this.imageUrls = newImageUrls;
        notifyDataSetChanged();
    }
}