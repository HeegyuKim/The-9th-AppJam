package kr.applepi.drinkingcare.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import kr.applepi.drinkingcare.Activitys.AddActivity;
import kr.applepi.drinkingcare.R;
import kr.applepi.drinkingcare.models.Product;

/**
 * Created by 1002461 on 15. 7. 27..
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<Product> list;

    public RecyclerAdapter(List<Product> pList) {
        this.list = pList;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        final Product data = list.get(i);

        if(data.getIcon()!=null) {
            holder.imageView.setImageDrawable(data.getIcon());
        }

        holder.textView.setText(String.valueOf(data.getText()));

    }

    @Override
    public int getItemCount() {
        return (null == list) ? 0 : list.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_add, viewGroup, false);

        return new ViewHolder(v);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private TextView textView;
        private LinearLayout linearLayout;
        public ViewHolder(View v) {
            super(v);
            imageView = (ImageView)v.findViewById(R.id.image);
            textView = (TextView) v.findViewById(R.id.tv);
            linearLayout = (LinearLayout)v.findViewById(R.id.item_1);
            linearLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v==linearLayout){
                AddActivity.drinkType = getAdapterPosition();
                //Toast.makeText(v.getContext(), AddActivity.drinkType+"", Toast.LENGTH_SHORT).show();
                AddActivity.MainActivitypager.setCurrentItem(1);
            }
        }
    }
}
