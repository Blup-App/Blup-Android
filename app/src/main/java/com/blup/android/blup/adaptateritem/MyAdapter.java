package com.blup.android.blup.adaptateritem;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.blup.android.blup.R;
import com.blup.android.blup.activity.HomeActivity;
import com.blup.android.blup.activity.ItemFocusActivity;


import org.json.JSONException;
import org.json.JSONObject;


import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private String userId;
    public List getItemsList() {
        return itemsList;
    }

    public MyAdapter setItemsList(List itemsList) {
        this.itemsList = itemsList;

        return this;
    }

    private List itemsList;

    public String getUserId() {
        return userId;
    }

    public MyAdapter setUserId(String userId) {
        this.userId = userId;

        return this;
    }




   @Override
   public int getItemCount() {
       return itemsList.size();
   }

   @Override
   public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       LayoutInflater inflater = LayoutInflater.from(parent.getContext());
       View view = inflater.inflate(R.layout.list_home, parent, false);
       return new MyViewHolder(view);
   }

   @Override
   public void onBindViewHolder(MyViewHolder holder, int position) {
       Object pair = itemsList.get(position);
       try {
           JSONObject item = new JSONObject(pair.toString());
           holder.setCurrentItemId(item.getString("id"));
           holder.display(pair.toString());

       } catch (JSONException e) {
           e.printStackTrace();
       }
   }


    public class MyViewHolder extends RecyclerView.ViewHolder {

       private final TextView title;
       private final TextView date;
       private final ImageView preview;

        public void setCurrentItemId(String currentItemId) {
            this.currentItemId = currentItemId;
        }

        private String currentItemId;

        public void setItemId(String itemId) {
            this.itemId = itemId;
        }

        private String itemId;

       private JSONObject currentPair;

       public MyViewHolder(final View itemView) {
           super(itemView);

           title = ((TextView) itemView.findViewById(R.id.title));
           date = ((TextView) itemView.findViewById(R.id.date));
           preview = ((ImageView) itemView.findViewById(R.id.preview));

           final Intent oldIntent = ((HomeActivity) itemView.getContext()).getIntent();

           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent = new Intent(view.getContext(), ItemFocusActivity.class);
                   intent.putExtra("item_id", currentItemId);
                   view.getContext().startActivity(intent);
               }
           });
       }

       public void display(String pair) {
           try {
               JSONObject currentPair = new JSONObject(pair);

               title.setText(currentPair.getString("title"));
               date.setText(currentPair.getString("returnDate").substring(0,10));
               preview.setImageURI(Uri.parse(currentPair.getString("img")));



           } catch (JSONException e) {
               e.printStackTrace();
           }
       }
   }

}